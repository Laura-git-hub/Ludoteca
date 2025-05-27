
-- Insert categories
INSERT INTO category (name) VALUES ('Eurogames');
INSERT INTO category (name) VALUES ('Ameritrash');
INSERT INTO category (name) VALUES ('Familiar');
INSERT INTO category (name) VALUES ('Party Games');
INSERT INTO category (name) VALUES ('Abstract');

-- Insert authors
INSERT INTO author (name, nationality) VALUES ('Alan R. Moon', 'US');
INSERT INTO author (name, nationality) VALUES ('Vital Lacerda', 'PT');
INSERT INTO author (name, nationality) VALUES ('Simone Luciani', 'IT');
INSERT INTO author (name, nationality) VALUES ('Perepau Llistosella', 'ES');
INSERT INTO author (name, nationality) VALUES ('Michael Kiesling', 'DE');
INSERT INTO author (name, nationality) VALUES ('Phil Walker-Harding', 'US');

-- Insert games
INSERT INTO game (title, age, category_id, author_id) VALUES ('On Mars', '14', 1, 2);
INSERT INTO game (title, age, category_id, author_id) VALUES ('Aventureros al tren', '8', 3, 1);
INSERT INTO game (title, age, category_id, author_id) VALUES ('1920: Wall Street', '12', 1, 4);
INSERT INTO game (title, age, category_id, author_id) VALUES ('Barrage', '14', 1, 3);
INSERT INTO game (title, age, category_id, author_id) VALUES ('Los viajes de Marco Polo', '12', 1, 3);
INSERT INTO game (title, age, category_id, author_id) VALUES ('Azul', '8', 3, 5);

-- Insert clients
INSERT INTO client (name) VALUES ('Sam Miller');
INSERT INTO client (name) VALUES ('Lies Van');
INSERT INTO client (name) VALUES ('Ann Marie');
INSERT INTO client (name) VALUES ('Robyn Klein');
INSERT INTO client (name) VALUES ('Sarah Ellerboon');

-- Insert prestamos
INSERT INTO prestamo (fecha_prestamo, fecha_devolucion, game_id, client_id, category_id, author_id)
VALUES ('2025-05-21', '2026-06-21', 1, 1, 1, 1);
INSERT INTO prestamo (fecha_prestamo, fecha_devolucion, game_id, client_id, category_id, author_id)
VALUES ('2025-06-21', '2025-08-10', 2, 3, 2, 2);
INSERT INTO prestamo (fecha_prestamo, fecha_devolucion, game_id, client_id, category_id, author_id)
VALUES ('2025-07-22', '2025-09-29', 3, 2, 3, 3);
INSERT INTO prestamo (fecha_prestamo, fecha_devolucion, game_id, client_id, category_id, author_id)
VALUES ('2025-02-21', '2025-06-23', 4, 4, 4, 4);
INSERT INTO prestamo (fecha_prestamo, fecha_devolucion, game_id, client_id, category_id, author_id)
VALUES ('2025-11-26', '2025-12-21', 5, 5, 5, 5);
INSERT INTO prestamo (fecha_prestamo, fecha_devolucion, game_id, client_id, category_id, author_id)
VALUES ('2025-12-01', '2026-01-01', 6, 1, 1, 1);
