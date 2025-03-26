CREATE TABLE user_entity
(
    id         int primary key,
    email      varchar(100) not null,
    password   varchar(100) not null,
    full_name  varchar(100) not null,
    created_at timestamp    not null,
    updated_at timestamp    not null,
    version    int          not null
);

CREATE SEQUENCE user_seq
    start with 1
    increment by 1
    minvalue 0
    maxvalue 1000
    no cycle;