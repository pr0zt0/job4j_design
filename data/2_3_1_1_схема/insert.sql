insert into "simple_app".state
(
    status
)
select
    floor(random()* 2)::int::bit as status
from generate_series(1, 10) s(i);

insert into "simple_app".d_category_name
(
    id
    ,category_code
    ,name_category
)
select
    s as id
    ,trunc(random() * 200 + 1) as category_code
    ,md5(random()::text) as name_category
from generate_series(1, 10) s(i);

insert into "simple_app".category
(
    category_code
    ,d_cat_id
)
select
    trunc(random() * s + 1) as category_code
    ,trunc(random() * s + 1) as d_cat_id
from generate_series(1, 10) s(i);


insert into "simple_app".role
(
    role_name
)
select
    left(md5(random()::varchar), 20) as role_name
from generate_series(1, 30) s(i);

insert into "simple_app".rules
(
    rule_name
)
select
    left(md5(random()::varchar), 50) as rule_name
from generate_series(1, 10) s(i);

insert into "simple_app".d_role_rules
(
     rules_id
    ,role_id
)
select ru.id, ro.id from "simple_app".role ro
join "simple_app".rules ru
on ro.id = ru.id + (random() * 10 + 1)::int

insert into  "simple_app".users
(
    role_id
    ,full_name
)
select
    ro.id
    ,left(md5(random()::varchar), 255) as full_name
from generate_series(1, 10) s(i)
join "simple_app".role ro
on (random() * 10 + 1)::int + s = ro.id;

insert into "simple_app".item
(
    users_id
    ,category_id
    ,state_id
    ,model
)
select
    users.id as users_id
    ,s as category_id
    ,s as state_id
    ,left(md5(i::varchar), 10) as model
from "simple_app".users
join generate_series(1, 10) s(i)
on users.id = s;

insert into "simple_app".attachs
(
    items_id
)
select
   trunc(random() * s + 1) as items_id
from generate_series(1, 10) s(i);

insert into "simple_app".comments
(
    items_id
    ,comment
)
select
    trunc(random() * s + 1) as items_id
    ,md5(random()::text) as comment
from generate_series(1, 10) s(i);

select * from "simple_app".state;
select * from "simple_app".d_category_name;
select * from "simple_app".category;
select * from "simple_app".role;
select * from "simple_app".rules;
select * from "simple_app".d_role_rules;
select * from "simple_app".users;
select * from "simple_app".item;
select * from "simple_app".attachs;
select * from "simple_app".comments