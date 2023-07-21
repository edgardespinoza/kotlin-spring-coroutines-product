create schema if not exists application;

create table if not exists application.company(
    id serial primary key,
    name varchar(255) not null,
    address varchar(255) not null
);

create table if not exists application.app_user(
    id serial primary key,
    email varchar(255) not null unique,
    name varchar(255) not null,
    age int not null,
    company_id bigint not null references application.company(id) on delete cascade
);
