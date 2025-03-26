CREATE TABLE role_entity
(
    role       varchar(100) primary key,
    created_at timestamp not null,
    updated_at timestamp not null,
    version    int      not null
);

CREATE TABLE user_role_entity
(
    user_id    int,
    role       varchar(100) not null,
    created_at timestamp     not null,
    updated_at timestamp     not null,
    version    int          not null,
    PRIMARY KEY (user_id, role),
    CONSTRAINT fk_user FOREIGN KEY (user_id) REFERENCES user_entity (id),
    CONSTRAINT fk_role FOREIGN KEY (role) REFERENCES role_entity (role)
);

INSERT INTO role_entity (role, created_at, updated_at, version)
VALUES (N'ADMIN', CURRENT_DATE, CURRENT_DATE, 0);
INSERT INTO role_entity (role, created_at, updated_at, version)
VALUES (N'USER', CURRENT_DATE, CURRENT_DATE, 0);