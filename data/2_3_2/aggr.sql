create table devices(
    id serial primary key,
    name varchar(255),
    price float
);

create table people(
    id serial primary key,
    name varchar(255)
);

create table devices_people(
    id serial primary key,
    device_id int references devices(id),
    people_id int references people(id)
);

insert into devices
values
(1, 'Apple', 100500),
(2, 'Samsung', 500.23),
(3, 'Nokia', 0.0);

insert into people
values
(1, 'Bob'),
(2, 'Sam');

insert into devices_people
values
(1, 1, 1),
(2, 2, 2),
(3, 3, 2);


select avg(price) as avg_price
from devices;

select avg(price) as avg_price, p.name
from devices_people dp
join devices d
on dp.device_id = d.id
join people p
on dp.people_id = p.id
group by p.id, p.name;

select avg(price) as avg_price, p.name
from devices_people dp
join devices d
on dp.device_id = d.id
join people p
on dp.people_id = p.id
group by p.id, p.name
having avg(price) > 5000;