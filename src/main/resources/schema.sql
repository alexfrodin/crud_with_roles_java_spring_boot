CREATE TABLE students (
    student_id BIGINT AUTO_INCREMENT,
    student_name VARCHAR(64) NOT NULL,
    student_age INTEGER,
    PRIMARY KEY(student_id)
);

CREATE TABLE roles (
    role_id INTEGER AUTO_INCREMENT,
    role_name VARCHAR(50) NOT NULL,
    PRIMARY KEY(role_id)
);

CREATE TABLE users (
    user_id BIGINT AUTO_INCREMENT,
    user_name VARCHAR(64) NOT NULL,
    user_password VARCHAR(128) NOT NULL,
    is_enabled BOOL default true
);

CREATE TABLE users_roles (
    user_id BIGINT NOT NULL,
    role_id INTEGER NOT NULL
)
