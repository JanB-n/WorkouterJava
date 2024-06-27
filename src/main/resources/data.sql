DROP TABLE IF EXISTS user;
CREATE TABLE user ( id serial primary key, fname varchar, lname varchar, city varchar, email varchar(50), tel varchar(50) );
INSERT INTO user (fname, lname, city, email, tel) VALUES ('Adam', 'Abacki', 'Szczecin','abacki@o2.pl','11222222');
INSERT INTO user (fname, lname, city, email, tel) VALUES ('Marek', 'Zazacki', 'Krakow','zazacki@o2.pl','1122244');
INSERT INTO user (fname, lname, city, email, tel) VALUES ('Bogdan', 'Babacki', 'Gdynia','babacki@o2.pl','112451112');
INSERT INTO user (fname, lname, city, email, tel) VALUES ('Witold', 'Dadacki', 'Warszawa','dadacki@o2.pl','11789122');