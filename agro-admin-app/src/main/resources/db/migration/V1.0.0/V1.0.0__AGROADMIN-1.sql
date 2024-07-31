CREATE TABLE farming_land
(
    id                                   int primary key,
    created_by                           varchar(100) not null,
    title                                varchar(100) not null,
    area                                 float        not null,
    area_unit_type                       varchar(100),
    roughly_distance_from_farm           float        not null,
    roughly_distance_from_farm_unit_type varchar(100),
    created_at                           timestamp    not null,
    updated_at                           timestamp    not null,
    version                              int          not null
);

ALTER TABLE farming_land
    ADD CONSTRAINT created_by_and_title_unique_constraint UNIQUE (created_by, title);

CREATE SEQUENCE farming_land_seq
    start with 1
    increment by 1
    minvalue 0
    maxvalue 1000
    no cycle;

CREATE TABLE farming_land_operation_history
(
    id                              int primary key,
    farming_land_id                 int          not null,
    created_by                      varchar(100) not null,
    operation                       varchar(100) not null,
    estimated_cost                  float        not null,
    estimated_harvest               float,
    estimated_revenue               float,
    created_at                      timestamp    not null,
    updated_at                      timestamp    not null,
    applied_at                      timestamp    not null,
    estimated_harvest_measure_type  varchar(100),
    estimated_cost_currency_type    varchar(100) not null,
    estimated_revenue_currency_type varchar(100),
    plant_type                      varchar(100),
    version                         int          not null,
    constraint fk_farming_land foreign key (farming_land_id) references farming_land (id)
);

CREATE SEQUENCE farming_land_operation_history_seq
    start with 1
    increment by 1
    minvalue 0
    maxvalue 1000
    no cycle;