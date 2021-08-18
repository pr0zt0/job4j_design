create table chassis
(
    id serial primary key
    ,model varchar (20)
);

insert into chassis
values
(3, 'купе');

create table transmission
(
    id serial primary key
    , number int
);

insert into  transmission
values
(1, 123)
,(2, 321)
,(3, 22)

create table engine
(
    id serial primary key
    ,typeEng smallint
);

insert into engine
values
(1, 0)
,(2, 1)
,(3, 3)

create table car
(
    id serial primary key
    ,model text
    ,id_chassis int references chassis(id)
    ,id_tran int references transmission(id)
    ,id_eng int references engine(id)
);

insert into car
values
(1, 'машина_1', 1, 1, 1)
,(2, 'машина_2', 1, 1, 2)
,(3, 'машина_3', 2, 2, 2);

-- 1
select c.model, ch.model, tr.number, e.typeEng
from car c
join chassis ch
on c.id_chassis = ch.id
join transmission tr
on tr.id = c.id_tran
join engine e
on e.id = c.id_eng;

-- 2
select ch.model
from chassis ch
left join car c
on c.id_chassis = ch.id
where c.id_chassis is null;

select tr.number
from car c
right  join transmission tr
on tr.id = c.id_tran
where c.id_tran is null;

select e.typeEng
from car c
right join engine e
on e.id = c.id_eng
where c.id_eng is null;