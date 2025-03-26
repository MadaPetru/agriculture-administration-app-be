CREATE TABLE farming_land_image
(
    id              int primary key,
    farming_land_id int          not null,
    created_by      int          not null,
    file_name       varchar(100) not null,
    created_at      timestamp    not null,
    at              timestamp    not null,
    updated_at      timestamp    not null,
    version         int          not null
);

ALTER TABLE farming_land_image
    ADD CONSTRAINT created_by_and_id_unique_constraint UNIQUE (created_by, id);

CREATE SEQUENCE farming_land_image_seq
    start with 1
    increment by 1
    minvalue 0
    maxvalue 1000
    no cycle;