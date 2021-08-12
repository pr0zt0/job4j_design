begin;

create table if not exists "public".t_test
(
    c_first bigserial,
    c_second varchar(20) not null,
    c_third date,
    primary key(c_first)
);

insert into "public".t_test
(
    c_first
    ,c_second
    ,c_third
)
select
    123 as c_first
    ,'some_text' as c_second
    ,date'2021-01-01';

update "public".t_test
set c_second = 'new_message'
explain select * from "public".t_test
where c_first = 123;

delete from "public".t_test
where c_first = 123;

commit;