CREATE SEQUENCE IF NOT EXISTS sequence_generator START WITH 1 INCREMENT BY 50;

CREATE TABLE app_authority
(
    name VARCHAR(50) NOT NULL,
    CONSTRAINT pk_app_authority PRIMARY KEY (name)
);

CREATE TABLE app_user
(
    id                 BIGINT      NOT NULL,
    created_by         VARCHAR(50) NOT NULL,
    created_date       TIMESTAMP WITHOUT TIME ZONE,
    last_modified_by   VARCHAR(50),
    last_modified_date TIMESTAMP WITHOUT TIME ZONE,
    login              VARCHAR(50) NOT NULL,
    password_hash      VARCHAR(60) NOT NULL,
    first_name         VARCHAR(50),
    last_name          VARCHAR(50),
    email              VARCHAR(254),
    activated          BOOLEAN     NOT NULL,
    lang_key           VARCHAR(10),
    image_url          VARCHAR(256),
    activation_key     VARCHAR(20),
    reset_key          VARCHAR(20),
    reset_date         TIMESTAMP WITHOUT TIME ZONE,
    CONSTRAINT pk_app_user PRIMARY KEY (id)
);

CREATE TABLE app_user_authority
(
    authority_name VARCHAR(50) NOT NULL,
    user_id        BIGINT      NOT NULL,
    CONSTRAINT pk_app_user_authority PRIMARY KEY (authority_name, user_id)
);

ALTER TABLE app_user
    ADD CONSTRAINT uc_app_user_email UNIQUE (email);

ALTER TABLE app_user
    ADD CONSTRAINT uc_app_user_login UNIQUE (login);

ALTER TABLE app_user_authority
    ADD CONSTRAINT fk_appuseaut_on_authority FOREIGN KEY (authority_name) REFERENCES app_authority (name);

ALTER TABLE app_user_authority
    ADD CONSTRAINT fk_appuseaut_on_user FOREIGN KEY (user_id) REFERENCES app_user (id);
