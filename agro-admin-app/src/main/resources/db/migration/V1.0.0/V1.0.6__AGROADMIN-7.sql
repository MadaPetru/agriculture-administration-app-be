CREATE TABLE role_entity
(
    role       varchar(100) primary key,
    created_at datetime not null,
    updated_at datetime not null,
    version    int      not null
);

CREATE TABLE user_role_entity
(
    user_id    int,
    role       varchar(100) not null,
    created_at datetime     not null,
    updated_at datetime     not null,
    version    int          not null,
    PRIMARY KEY (user_id, role),
    CONSTRAINT fk_user FOREIGN KEY (user_id) REFERENCES user_entity (id),
    CONSTRAINT fk_role FOREIGN KEY (role) REFERENCES role_entity (role)
);

ALTER TABLE farming_land
DROP
CONSTRAINT created_by_and_title_unique_constraint;

ALTER TABLE farming_land_image
DROP
CONSTRAINT created_by_and_id_unique_constraint;

-- Change the created_by column to integer and add foreign key constraint for farming_land table
ALTER TABLE farming_land
ALTER
COLUMN created_by INT NOT NULL;

ALTER TABLE farming_land
    ADD CONSTRAINT fk_farming_land_user_entity FOREIGN KEY (created_by) REFERENCES user_entity (id);

ALTER TABLE farming_land
    ADD CONSTRAINT created_by_and_title_unique_constraint UNIQUE (created_by, title);

-- Change the created_by column to integer and add foreign key constraint for farming_land_operation_history table
ALTER TABLE farming_land_operation_history
ALTER
COLUMN created_by INT NOT NULL;

ALTER TABLE farming_land_operation_history
    ADD CONSTRAINT fk_farming_land_operation_history_user_entity FOREIGN KEY (created_by) REFERENCES user_entity (id);

-- Change the created_by column to integer and add foreign key constraint for farming_land_statistics_per_year table
ALTER TABLE farming_land_statistics_per_year
ALTER
COLUMN created_by INT NOT NULL;

ALTER TABLE farming_land_statistics_per_year
    ADD CONSTRAINT fk_farming_land_statistics_per_year_user_entity FOREIGN KEY (created_by) REFERENCES user_entity (id);

-- Change the created_by column to integer and add foreign key constraint for farming_land_statistics_per_operation_and_year table
ALTER TABLE farming_land_statistics_per_operation_and_year
ALTER
COLUMN created_by INT NOT NULL;

ALTER TABLE farming_land_statistics_per_operation_and_year
    ADD CONSTRAINT fk_farming_land_statistics_per_operation_and_year_user_entity FOREIGN KEY (created_by) REFERENCES user_entity (id);

-- Change the created_by column to integer and add foreign key constraint for farming_land_image table
ALTER TABLE farming_land_image
ALTER
COLUMN created_by INT NOT NULL;

ALTER TABLE farming_land_image
    ADD CONSTRAINT fk_farming_land_image_user_entity FOREIGN KEY (created_by) REFERENCES user_entity (id);

-- Build back the pk keys
ALTER TABLE farming_land_statistics_per_operation_and_year
    ADD CONSTRAINT PK_farming_land_statistics_per_operation_and_year PRIMARY KEY (year, operation, created_by);

ALTER TABLE farming_land_statistics_per_year
    ADD CONSTRAINT PK_farming_land_statistics_per_year PRIMARY KEY (year, created_by);


INSERT INTO role_entity (role, created_at, updated_at, version)
VALUES (N'ADMIN', N'2025-02-02 00:35:42.000', N'2025-02-02 00:35:43.000', 0);
INSERT INTO role_entity (role, created_at, updated_at, version)
VALUES (N'USER', N'2025-02-02 00:35:42.000', N'2025-02-02 00:35:43.000', 0);