INSERT INTO category (id, name) VALUES (1, 'Eurogames');
INSERT INTO category (id, name) VALUES (2, 'Ameritrash');
INSERT INTO category (id, name) VALUES (3, 'Familiar');
INSERT INTO category (id, name) VALUES (4, 'Party Games');
INSERT INTO category (id, name) VALUES (5, 'Abstract');


INSERT INTO author (id, name, nationality) VALUES (1, 'Alan R. Moon', 'US');
INSERT INTO author (id, name, nationality) VALUES (2, 'Vital Lacerda', 'PT');
INSERT INTO author (id, name, nationality) VALUES (3, 'Simone Luciani', 'IT');
INSERT INTO author (id, name, nationality) VALUES (4, 'Perepau Llistosella', 'ES');
INSERT INTO author (id, name, nationality) VALUES (5, 'Michael Kiesling', 'DE');
INSERT INTO author (id, name, nationality) VALUES (6, 'Phil Walker-Harding', 'US');


INSERT INTO game (id, title, age, category_id, author_id) VALUES (1, 'On Mars', '14', 1, 2);
INSERT INTO game (id, title, age, category_id, author_id) VALUES (2, 'Aventureros al tren', '8', 3, 1);
INSERT INTO game (id, title, age, category_id, author_id) VALUES (3, '1920: Wall Street', '12', 1, 4);
INSERT INTO game (id, title, age, category_id, author_id) VALUES (4, 'Barrage', '14', 1, 3);
INSERT INTO game (id, title, age, category_id, author_id) VALUES (5, 'Los viajes de Marco Polo', '12', 1, 3);
INSERT INTO game (id, title, age, category_id, author_id) VALUES (6, 'Azul', '8', 3, 5);


INSERT INTO client (id, name) VALUES (1, 'Sam Miller');
INSERT INTO client (id, name) VALUES (2, 'Lies Van');
INSERT INTO client (id, name) VALUES (3, 'Ann Marie');
INSERT INTO client (id, name) VALUES (4, 'Robyn Klein');
INSERT INTO client (id, name) VALUES (5, 'Sarah Ellerboon');


INSERT INTO prestamo (fecha_prestamo, fecha_devolucion, game_id, cliente_id, category_id, author_id)
VALUES ('2025-05-21', '2026-06-21', 1, 1, 1, 1);
INSERT INTO prestamo (fecha_prestamo, fecha_devolucion, game_id, cliente_id, category_id, author_id)
VALUES ('2025-06-21', '2025-08-10', 2, 2, 2, 2);
INSERT INTO prestamo (fecha_prestamo, fecha_devolucion, game_id, cliente_id, category_id, author_id)
VALUES ('2025-07-22', '2025-09-29', 3, 3, 3, 3);
INSERT INTO prestamo (fecha_prestamo, fecha_devolucion, game_id, cliente_id, category_id, author_id)
VALUES ('2025-02-21', '2025-06-23', 4, 4, 4, 4);
INSERT INTO prestamo (fecha_prestamo, fecha_devolucion, game_id, cliente_id, category_id, author_id)
VALUES ('2025-11-26', '2025-12-21', 5, 5, 5, 5);



