-- 1
select *
from product p
join type t
on p.type_id = t.id
where t.name = 'СЫР';

-- 2
select *
from product p
where name like '%мороженое%';

-- 3
select *
from product
where expired_date < current_date;

-- 4
select name, price
from product
where price in (select max(price) from product);

-- 5
select t.name, count(p.name)
from product p
join type t
on p.type_id = t.id
group by t.name;

-- 6
select *
from product p
join type t
on p.type_id = t.id
where t.name in ('СЫР', 'МОЛОКО');

-- 7
select t.name
from type t
group by t.name
having count(*) < 10;

-- 8
select *
from product p
join type t
on p.type_id = t.id;