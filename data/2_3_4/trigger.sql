/*
    Триггер должен срабатывать после вставки данных
    , для любого товара и просто насчитывать налог на товар
    (нужно прибавить налог к цене товара).
    Действовать он должен не на каждый ряд, а на запрос (statement уровень)
*/

create table products (
    id serial primary key,
    name varchar(50),
    producer varchar(50),
    count integer default 0,
    price integer
);

comment on table products is 'Товары';

comment on column products.id is 'id';
comment on column products.name is 'наименование товара';
comment on column products.producer is 'производитель';
comment on column products.count is 'кол-во';
comment on column products.price is 'цена';

create or replace function tax_after()
returns trigger as
$$
declare
	tax int2 := 20;
begin

	update products
	set price = price + tax
	where id = (select id from inserted);

	return new;
end;
$$
language 'plpgsql'

create trigger tax_pay_after
after insert
on products
referencing new table as inserted
for each statement
execute procedure tax_after();

insert into products (name, producer, count, price) VALUES ('product_6', 'producer_6', 3, 50);

insert into products (name, producer, count, price) VALUES ('product_5', 'producer_5', 3, 50);

truncate products;
vacuum products;
analyze products;

alter table products disable trigger tax_pay_after;

/*
    Триггер должен срабатывать до вставки данных и
    насчитывать налог на товар (нужно прибавить налог к цене товара). Здесь используем row уровень.
*/
create or replace function tax_before()
returns trigger as
$$
declare
	tax int2 := 20;
begin

	new.price = new.price + 20;

	return new;
end;
$$
language 'plpgsql'


create trigger tax_pay_before
before insert
on products
for each row
execute procedure tax_before();

insert into products (name, producer, count, price) VALUES ('product_12', 'producer_12', 3, 50);


truncate products;
analyze products;
vacuum products;


alter table products disable trigger tax_pay_before;

/*
    Создайте таблицу history_of_price
    Нужно написать триггер на row уровне, который при вставке продукта в таблицу products,
    будет заносить имя, цену и текущую дату в таблицу history_of_price
*/
create table history_of_price (
    id serial primary key,
    name varchar(50),
    price integer,
    date timestamp
);


create or replace function save_history_price()
returns trigger
as
$$
begin
	insert into history_of_price(name, price, date)
	select new.name, new.price, current_timestamp(6);

	return new;
end;
$$
language 'plpgsql'


create trigger save_price
after insert
on products
for each row
execute procedure save_history_price();


insert into products (name, producer, count, price) VALUES ('product_12', 'producer_12', 3, 50);