INSERT INTO students (student_name, student_age) VALUES ('Alexander', 30);
INSERT INTO students (student_name, student_age) VALUES ('Oskar', 30);
INSERT INTO students (student_name, student_age) VALUES ('Oscar', 30);
INSERT INTO students (student_name, student_age) VALUES ('Franck', 30);
INSERT INTO students (student_name, student_age) VALUES ('Krillinator', 30);

INSERT INTO roles (role_id, role_name) VALUES (1, 'USER');
INSERT INTO roles (role_id, role_name) VALUES (2, 'CREATOR');
INSERT INTO roles (role_id, role_name) VALUES (3, 'EDITOR');
INSERT INTO roles (role_id, role_name) VALUES (4, 'ADMIN');

INSERT INTO users (user_name, user_password) VALUES ('User', '$2a$10$NlcuuHfAF2trDvWpkmdoEO6.Ookdtixqg25kQLmMCC4od5zf9fpmS');
INSERT INTO users (user_name, user_password) VALUES ('Creator', '$2a$10$NlcuuHfAF2trDvWpkmdoEO6.Ookdtixqg25kQLmMCC4od5zf9fpmS');
INSERT INTO users (user_name, user_password) VALUES ('Editor', '$2a$10$NlcuuHfAF2trDvWpkmdoEO6.Ookdtixqg25kQLmMCC4od5zf9fpmS');
INSERT INTO users (user_name, user_password) VALUES ('Admin', '$2a$10$NlcuuHfAF2trDvWpkmdoEO6.Ookdtixqg25kQLmMCC4od5zf9fpmS');
INSERT INTO users (user_name, user_password) VALUES ('Multi', '$2a$10$NlcuuHfAF2trDvWpkmdoEO6.Ookdtixqg25kQLmMCC4od5zf9fpmS');

INSERT INTO users_roles (user_id, role_id) VALUES (1, 1);
INSERT INTO users_roles (user_id, role_id) VALUES (2, 2);
INSERT INTO users_roles (user_id, role_id) VALUES (3, 3);
INSERT INTO users_roles (user_id, role_id) VALUES (4, 4);
INSERT INTO users_roles (user_id, role_id) VALUES (5, 1);
INSERT INTO users_roles (user_id, role_id) VALUES (5, 3);