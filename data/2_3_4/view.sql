create table users (
	id serial primary key
	, fullname text not null
	, age int2
);

create table goods (
	id serial primary key
	, description text not null
	, price numeric(28, 4)
	, available int4
);

create table orders (
	id serial primary key
	, trun_date date
	, amount numeric(28, 4)
	, fk_users int4 references "snd".users(id)
	, fk_good int4 references "snd".goods(id)
);

insert into users (fullname, age)
select 'Jhon Smith',  12
union all
select 'Bob', 45;

insert into goods(description, price, available)
select 'watermelon', 235.4, 356
union all
select 'bread', 0.5, 13
union all
select 'lemon', 21, 2000;

insert into orders (trun_date, amount, fk_users, fk_good)
as
select '2022-03-01'::date, 23, 1, 1
union all
select '2022-04-05'::date, 24, 2, 3
union all
select '2021-04-03'::date, 23, 2, 1;

create or replace view sal_by_2022_cur_date
as
with us as (
	select *
	from users
	where age >= 18
)
select
	u.fullname
	, coalesce(date_trunc('month', ord.trun_date::date), date_trunc('month', current_date)) as salary_date
	, sum(amount) as total_amount
	, sum(price * amount) as total_price
from us u
join orders ord
	on 1 = 1
	and u.id = ord.fk_users
join goods gd
	on 1 = 1
	and ord.fk_good = gd.id
where trun_date between '2022-01-01' and current_date
group by date_trunc('month', ord.trun_date::date), u.fullname;