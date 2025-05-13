CREATE DATABASE postgresnar
TEMPLATE template0;

CREATE SCHEMA storage;

DROP TABLE storage.keys;
DROP TABLE storage.user;



CREATE TABLE IF NOT EXISTS storage.user (
    id SERIAL PRIMARY KEY,
    names BIGINT UNIQUE,
    code BIGINT NOT NULL,
    create_date DATE NOT NULL
);


CREATE TABLE IF NOT EXISTS storage.keys (
    id SERIAL PRIMARY KEY,
    user_id BIGINT REFERENCES storage.user (names),
    key_name VARCHAR(128) NOT NULL,
    user_key VARCHAR(128) NOT NULL,
    create_date DATE NOT NULL,
    difficult INT NOT NULL
);


