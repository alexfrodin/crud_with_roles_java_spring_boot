INSERT INTO students (student_name, student_age) VALUES ('Alexander', 30);
INSERT INTO students (student_name, student_age) VALUES ('Oskar', 30);
INSERT INTO students (student_name, student_age) VALUES ('Oscar', 30);
INSERT INTO students (student_name, student_age) VALUES ('Franck', 30);

INSERT INTO roles (role_id, role_name) VALUES (1, 'USER');
INSERT INTO roles (role_id, role_name) VALUES (2, 'CREATOR');
INSERT INTO roles (role_id, role_name) VALUES (3, 'EDITOR');
INSERT INTO roles (role_id, role_name) VALUES (4, 'ADMIN');

INSERT INTO users (user_name, user_password) VALUES ('Alexander', '$2a$10$OlpVGGz1EXm.LQ/OcvmBQOFdAe3FQNYhOOXrKD6y9fhxOr2aBKwHu');

INSERT INTO users_roles (user_id, role_id) VALUES (1, 4);
INSERT INTO users_roles (user_id, role_id) VALUES (1, 1);