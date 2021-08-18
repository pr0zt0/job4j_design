-- 1
create table if not exists departments
(
    id serial primary key
    ,dep_name varchar (200) not null
);

create table if not exists employees
(
    id serial primary key
    ,full_name text not null
    ,dep_id int references departments(id)
);


insert into departments
values
(1, '1 dep'),
(2, '2 dep'),
(3, '3 dep');


insert into employees
values
(1, 'John Snow', 1),
(2, 'Bob Job', 1),
(3, 'Jhon Cena', 2),
(4, 'harry truman', 2);

-- 2
select *
from departments d
left join employees e
on d.id = e.dep_id;


select *
from departments d
right  join employees e
on d.id = e.dep_id;

select *
from departments d
full join employees e
on d.id = e.dep_id;

select *
from departments d
cross join employees e;

-- 3
select *
from departments d
left  join employees e
on d.id = e.dep_id
where e.id is null;

-- 4
select *
from employees e
left join departments d
on d.id = e.dep_id;

select *
from departments d
right  join employees e
on d.id = e.dep_id;

-- 5
create table teens
(
    name varchar(10),
    gender varchar(10)
);


insert into teens
values
('1', 'masculine'),
('2', 'female'),
('3', 'neuter'),
('4', 'common');

select o1.gender || ' = ' || o2.gender as pare
from teens o1 cross join teens o2
where o1.gender <> o2.gender;