-- many-to-one
begin;

drop table if exists "public".t_account;

create table "public".t_account
(
    id serial,
    account_name text not null,
    register_date date default current_date,
    primary key(id)
);

drop table if exists "public".t_human;

create table "public".t_human
(
    id uuid default uuid_generate_v4(),
    full_name varchar(255),
    account_id int references "public".t_account(id),
    primary key(id)
);

commit;



-- one-to-one
begin;

drop table if exists "public".t_cup;

create table "public".t_cup
(
    id serial,
    model_name  varchar(255) not null,
    primary key(id)
);

drop table if exists "public".t_human;

create table "public".t_human
(
    id uuid default uuid_generate_v4(),
    full_name varchar(255) not null ,
    cup_id int references "public".t_cup(id) unique,
    primary key(id)
);

commit;


-- many-to-many
begin;

drop table if exists "public".t_human;

create table "public".t_human
(
    id uuid not null default uuid_generate_v4(),
    full_name not null varchar(255) set latin,
    primary key(id)
);

drop table if exists "public".t_dic_works_to_human;

create table "public".t_dic_works_to_human
(
    id serial,
    human_id uuid references "public".t_human(id),
    workstation_id uuid references "public".t_workstation(id),
    primary key(id)
);

drop table if exists "public".t_workstation;

create table "public".t_workstation
(
    id uuid not null default uuid_generate_v4(),
    number bigint not null,
    qr_code blob,
    position not null varchar(5),
    primary key(id)
);

commit;