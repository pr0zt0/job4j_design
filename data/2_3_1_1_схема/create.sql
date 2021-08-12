create table if not exists "simple_app".users
(
    id serial
    ,role_id int references "simple_app".role(id)
    ,full_name varchar(255) not null
    ,primary key (id)
);

create table if not exists "simple_app".role
(
    id serial
    ,role_name char (20)
    ,primary key (id)
);

create table if not exists "simple_app".d_role_rules
(
    rules_id int references "simple_app".rules(id)
    ,role_id int references "simple_app".role(id)
    ,primary key (role_id, rules_id)
);

create table if not exists "simple_app".rules
(
    id serial
    ,rule_name varchar (50)
    ,primary key (id)
);

create table if not exists "simple_app".item
(
    id serial
    ,users_id int references "simple_app".users(id)
    ,category_id int references "simple_app".category(id)
    ,state_id int references "simple_app".state(id)
    ,model varchar (10) not null
    ,primary key (id)
);

create table if not exists "simple_app".comments
(
    id serial
    ,items_id int references "simple_app".item(id)
    ,comment text
    ,primary key (id)
);

create table if not exists "simple_app".attachs
(
    id serial
    ,items_id int references "simple_app".item(id)
    ,attach oid
    ,primary key (id)
);

create table if not exists "simple_app".category
(
    id serial
    ,category_code smallint
    ,d_cat_id int references  "simple_app".d_category_name(id)
    ,primary key (id)
);

create table if not exists "simple_app".d_category_name
(
    id int
    ,category_code smallint
    ,name_category text
    ,primary key (id)
);

create table if not exists "simple_app".state
(
    id serial
    ,status bit
    ,primary key (id)
);