-- liquibase formatted sql

-- changeset ismirnov:1
CREATE TABLE users (
    id INT NOT NULL PRIMARY KEY,
    email VARCHAR(255) NOT NULL UNIQUE,
    first_name VARCHAR(255) NOT NULL,
    last_name VARCHAR(255) NOT NULL,
    phone VARCHAR(255) NOT NULL UNIQUE,
    role VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL
);
CREATE TABLE ads (
    pk INT NOT NULL PRIMARY KEY,
    price INT NOT NULL,
    title VARCHAR(255) NOT NULL,
    description TEXT NOT NULL,
    users_id INT REFERENCES users (id)
);
CREATE TABLE comments (
    pk INT NOT NULL PRIMARY KEY,
    text TEXT NOT NULL,
    createdAt BIGINT NOT NULL,
    users_id INT REFERENCES users (id),
    ads_id INT REFERENCES ads (pk)
);
CREATE TABLE avatars (
    id INT NOT NULL PRIMARY KEY,
    filePath VARCHAR(255) NOT NULL,
    pathForEndpoint VARCHAR(255) NOT NULL,
    mediaType VARCHAR(255) NOT NULL,
    users_id INT REFERENCES users (id)
);
CREATE TABLE images (
    id INT NOT NULL PRIMARY KEY,
    filePath VARCHAR(255) NOT NULL,
    pathForEndpoint VARCHAR(255) NOT NULL,
    mediaType VARCHAR(255) NOT NULL,
    ads_id INT REFERENCES ads (pk)
);