CREATE DATABASE SmartFarm;
CREATE USER 'SmartFarmApp'@'localhost' IDENTIFIED BY 'SmartFarm2020';
GRANT ALL ON SmartFarm.* TO 'SmartFarmApp'@'localhost';

USE SmartFarm;

-- TIPOS DE USUARIOS
INSERT INTO tipodeusuario (nombre) VALUES ("Admin"), ("Veterinario"), ("Empleado");

-- USUARIOS
INSERT INTO usuario VALUES 
(1, "Prueba", "admin@gmail.com", "2020/05/12", "Usuario", "123", "Admin"),
(2, "Prueba", "empleado@gmail.com", "2020/05/12", "Usuario", "123", "Empleado"),
(3, "Prueba", "veterinario@gmail.com", "2020/05/12", "Usuario", "123", "Veterinario");

-- TIPOS DE GASTOS
INSERT INTO TipoDeGasto (nombre) VALUES 
("Alimenticio"), 
("Empresarial"), 
("Médico"), 
("Tecnológico");

-- TIPO ALIMENTO
INSERT INTO TipoAlimento VALUES 
(1, "Granos"),
(2, "Forrajes"),
(3, "Cereales");

-- ALIMENTO
INSERT INTO Alimento (id, nombre, cantidad, stockMinimo, tipo_id) VALUES 
(1, "Maiz", 200, 10, 1),
(2, "Trigo", 200, 10, 1),
(3, "Centeno", 200, 10, 1),
(4, "Heno", 200, 10, 2),
(5, "Pasto", 200, 10, 2),
(6, "Avena", 200, 10, 3),
(7, "Cebada", 200, 10, 3);

-- TIPO ANIMAL
INSERT INTO TipoAnimal VALUES(1, "CAPRINO");
INSERT INTO TipoAnimal VALUES(2, "EQUINO");
INSERT INTO TipoAnimal VALUES(3, "OVINO");
INSERT INTO TipoAnimal VALUES(4, "PORCINO");
INSERT INTO TipoAnimal VALUES(5, "VACUNO");

-- CAPRINOS
INSERT INTO Raza VALUES(1, "ANGLO NUBIAN", 1);
INSERT INTO Raza VALUES(2, "BOER", 1);
INSERT INTO Raza VALUES(3, "CRIOLLA", 1);
INSERT INTO Raza VALUES(4, "PARDO ALPINA", 1);
-- EQUINOS
INSERT INTO Raza VALUES(5, "ANGLO ÁRABE", 2);
INSERT INTO Raza VALUES(6, "ÁRABE", 2);
INSERT INTO Raza VALUES(7, "CRIOLLO", 2);
INSERT INTO Raza VALUES(8, "CUARTO DE MILLA", 2);
-- OVINOS
INSERT INTO Raza VALUES(9, "ARGENTINO", 3);
INSERT INTO Raza VALUES(10, "CORRIEDALE", 3);
INSERT INTO Raza VALUES(11, "LINCOLN", 3);
INSERT INTO Raza VALUES(12, "MERINO", 3);
-- PORCINOS
INSERT INTO Raza VALUES(13, "DUROC", 4);
INSERT INTO Raza VALUES(14, "HAMPSHIRE", 4);
INSERT INTO Raza VALUES(15, "LANDRACE", 4);
INSERT INTO Raza VALUES(16, "YORKSHIRE", 4);
-- VACUNOS
INSERT INTO Raza VALUES(17, "CHIANINA", 5);
INSERT INTO Raza VALUES(18, "HOLANDO ARGENTINO", 5);
INSERT INTO Raza VALUES(19, "LIMOUSIN", 5);
INSERT INTO Raza VALUES(20, "VACA CANARIA", 5);

-- GENEROS
INSERT INTO Genero VALUES(1, "HEMBRA");
INSERT INTO Genero VALUES(2, "MACHO");

-- COORDENADAS POR DEFECTO
INSERT INTO ubicacionesCentrales (latitudCaprinoCentral, longitudCaprinoCentral, latitudEquinoCentral, longitudEquinoCentral, latitudOvinoCentral,
longitudOvinoCentral, latitudPorcinoCentral, longitudPorcinoCentral, latitudVacunoCentral, longitudVacunoCentral)
VALUES
(35.280943, 59.242249, 35.275880, 59.232271, 35.273971, 59.256475, 35.269674, 59.244542, 35.275999, 59.244134);

-- ANIMALES PARA PRUEBAS DEL MAPA
INSERT INTO animalDeGranja (tipo_id, raza_id, genero_id, peso, fechaNacimiento) VALUES
/* Se agregan CAPRINOS */
(1, 1, 1, 80, "2020.01.10"),
(1, 2, 1, 80, "2019.02.10"),
(1, 3, 1, 75, "2020.02.10"),
(1, 4, 1, 75, "2019.02.10"),
(1, 1, 2, 80, "2019.02.10"),
(1, 2, 2, 80, "2020.01.10"),
(1, 3, 2, 75, "2020.02.10"),
(1, 4, 2, 75, "2019.02.10"),
(1, 1, 1, 50, "2020.01.10"),
(1, 2, 1, 70, "2019.02.10"),
(1,	1, 1, 1, "2020.02.10"),
(1, 1, 1, 2, "2019.02.10"),
(1,	1, 1, 1, "2019.02.10"),
(1,	1, 1, 1, "2020.01.10"),
(1,	1, 1, 1, "2020.02.10"),
(1,	1, 1, 1, "2019.02.10"),
/* Se agregan EQUINOS */
(2, 5, 1, 90, "2020.02.10"),
(2, 6, 1, 90, "2020.01.10"),
(2, 7, 1, 85, "2020.02.10"),
(2, 8, 1, 85, "2019.02.10"),
(2, 5, 2, 90, "2020.01.10"),
(2, 6, 2, 90, "2020.02.10"),
(2, 7, 2, 85, "2020.02.10"),
(2, 8, 2, 85, "2020.01.10"),
(2, 7, 1, 60, "2020.02.10"),
(2, 5, 1, 2, "2020.01.10"),
(2, 5, 1, 2, "2020.02.10"),
(2, 5, 1, 2, "2019.02.10"),
(2, 5, 1, 2, "2020.01.10"),
(2, 5, 1, 2, "2020.02.10"),
/* Se agregan OVINOS */
(3, 9, 1, 60, "2019.02.10"),
(3, 10, 1, 60, "2020.01.10"),
(3, 11, 1, 65, "2020.02.10"),
(3, 12, 1, 65, "2019.02.10"),
(3, 9, 2, 60, "2020.01.10"),
(3, 10, 2, 60, "2019.02.10"),
(3, 11, 2, 65, "2020.01.10"),
(3, 12, 2, 65, "2020.02.10"),
(3, 9, 1, 3, "2019.02.10"),
(3, 9, 1, 3, "2020.01.10"),
(3, 9, 1, 3, "2020.02.10"),
(3, 9, 1, 3, "2019.02.10"),
(3, 9, 1, 3, "2020.01.10"),
/* Se agregan PORCINOS */
(4, 13, 1, 100, "2020.02.10"),
(4, 14, 1, 100, "2019.02.10"),
(4, 15, 1, 95, "2020.01.10"),
(4, 16, 1, 95, "2020.02.10"),
(4, 13, 2, 100, "2019.02.10"),
(4, 14, 2, 100, "2020.02.10"),
(4, 15, 2, 95, "2019.02.10"),
(4, 16, 2, 95, "2020.01.10"),
(4, 13, 1, 4, "2020.02.10"),
(4, 13, 1, 4, "2019.02.10"),
(4, 13, 1, 4, "2020.01.10"),
(4, 13, 1, 4, "2020.02.10"),
(4, 13, 1, 4, "2019.02.10"),
/* Se agregan VACUNOS */
(5, 17, 1, 110, "2020.01.10"),
(5, 18, 1, 110, "2020.01.10"),
(5, 19, 1, 100, "2020.02.10"),
(5, 20, 1, 100, "2019.02.10"),
(5, 17, 2, 110, "2020.01.10"),
(5, 18, 2, 110, "2020.01.10"),
(5, 19, 2, 100, "2020.01.10"),
(5, 20, 2, 100, "2020.02.10"),
(5, 20, 1, 75, "2020.01.10"),
(5, 17, 1, 5, "2020.01.10"),
(5, 17, 1, 5, "2020.02.10"),
(5, 17, 1, 5, "2019.02.10"),
(5, 17, 1, 5, "2020.01.10"),
(5, 17, 1, 5, "2020.02.10");

/* Al crear un animal también se le tiene que crear una historia clínica, signos vitales y vacunas */

-- HISTORIA CLINICA
INSERT INTO historiaclinica VALUES
(1,1),(2,2),(3,3),(4,4),(5,5),(6,6),(7,7),(8,8),(9,9),(10,10),
(11,11),(12,12),(13,13),(14,14),(15,15),(16,16),(17,17),(18,18),(19,19),(20,20),
(21,21),(22,22),(23,23),(24,24),(25,25),(26,26),(27,27),(28,28),(29,29),(30,30),
(31,31),(32,32),(33,33),(34,34),(35,35),(36,36),(37,37),(38,38),(39,39),(40,40),
(41,41),(42,42),(43,43),(44,44),(45,45),(46,46),(47,47),(48,48),(49,49),(50,50),
(51,51),(52,52),(53,53),(54,54),(55,55),(56,56),(57,57),(58,58),(59,59),(60,60),
(61,61),(62,62),(63,63),(64,64),(65,65),(66,66),(67,67),(68,68),(69,69),(70,70);

-- SIGNOS VITALES
-- falta agregarle registros
INSERT INTO SIGNOSVITALES VALUES
(1,"2020.01.10",70.4,25.2,48.1,42.0,1),
(2,"2020.04.10",70.4,25.2,48.1,42.0,1),
(3,"2020.05.10",50.0,25.2,80.1,37.6,1),
(4,"2020.02.10",40.0,25.2,80.1,37.6,1),
(5,"2020.01.13",40.0,25.2,80.1,37.6,1),
(6,"2020.01.11",35.0,25.2,80.1,37.6,1),
(7,"2020.03.13",90.0,25.2,80.1,40.0,2),
(8,"2020.03.10",80.0,25.2,80.1,39.6,2),
(9,"2020.03.18",85.0,25.2,80.1,39.7,2),
(10,"2020.11.10",90.0,25.2,80.1,39.6,2),
(11,"2020.12.10",100.0,25.2,80.1,40.6,2),
(12,"2020.01.23",57.4,29.2,43.4,36.4,3),
(13,"2020.01.10",70.4,25.2,48.1,42.8,1),
(14,"2020.06.10",70.4,25.2,48.9,41.0,1),
(15,"2020.01.23",70.4,25.2,48.1,40.8,1),
(16,"2020.01.10",70.4,25.2,48.1,40.0,2),
(17,"2020.11.10",71.4,25.2,48.1,41.0,2),
(18,"2020.01.10",78.4,25.2,48.1,39.9,2),
(19,"2020.01.10",70.4,25.2,48.1,41.6,2),
(20,"2020.04.10",50.4,25.2,48.1,37.0,3),
(21,"2020.01.19",45.4,25.2,48.1,38.0,3),
(22,"2020.12.10",50.0,25.2,48.1,36.9,3),
(23,"2020.05.04",44.4,25.2,48.1,40.0,3),
(24,"2020.01.10",70.4,25.2,48.1,42.8,4),
(25,"2020.06.10",70.4,25.2,48.9,41.0,4),
(26,"2020.01.23",70.4,25.2,48.1,40.8,4),
(27,"2020.01.10",70.4,25.2,48.1,40.0,5),
(28,"2020.11.10",71.4,25.2,48.1,41.0,5),
(29,"2020.01.10",78.4,25.2,48.1,39.9,6),
(30,"2020.01.10",70.4,25.2,48.1,41.6,7),
(31,"2020.04.10",50.4,25.2,48.1,37.0,9),
(32,"2020.01.19",45.4,25.2,48.1,38.0,9),
(33,"2020.12.10",50.0,25.2,48.1,36.9,9),
(34,"2020.05.04",44.4,25.2,48.1,40.0,9),
(35,"2020.01.10",67,25.2,100.1,37.8,10),
(36,"2020.06.10",60.4,25.2,98.9,38.0,4),
(37,"2020.01.23",68.4,25.2,108.1,38.8,10),
(38,"2020.01.10",0.4,25.2,98.1,38.0,10);

-- TRATAMIENTO
INSERT INTO tratamiento VALUES
(1,"Leptospirosis", "Inyecciones","Intravenosas"),
(2,"Fiebre", "Aspirina","Comprimidos"),
(3,"IBR", "Nebulizaciones","Durante diez dias");

-- VACUNAS
INSERT INTO VACUNA (id, nombre, edadAplicacionMeses) VALUES
(1, "Aftosa", 2),
(2, "Brucelosis", 5),
(3, "Carbunco",3),
(4, "Botulismo",12),
(5, "Leptospirosis",4),
(6, "Policlostridiales1",4),
(7, "Policlostridiales2",12);

-- ENFERMEDADES
INSERT INTO Enfermedad (fecha, finTratamiento, inicioTratamiento, nombre, tratamientoA, tratamientoB, historia_id) VALUES
("2020.06.10",null,null,'Leptospirosis',null,null,2),
("2020.06.19",null,null,'Fiebre Aftosa',null,null,2),
("2020.06.17",null,null,'Miocardiopatia congenita',null,null,1),
("2020.06.10",null,null,'Rinotraqueitis infecciosa',null,null,2),
("2020.08.10",null,null,'Leptospirosis',null,null,2),
("2020.08.19",null,null,'Fiebre Aftosa',null,null,2),
("2020.08.17",null,null,'Miocardiopatia congenita',null,null,1),
("2020.08.10",null,null,'Rinotraqueitis infecciosa',null,null,2),
("2020.08.18",null,null,'Rinotraqueitis infecciosa',null,null,3),
("2020.05.10",null,null,'Leptospirosis',null,null,9); 

-- ANIMALDEGRANJA_VACUNA
-- Se deja para que salte la aleta de que a tal animal no se le aplicó tal vacuna
INSERT INTO animalDeGranja_vacuna VALUES 
(1,1),(1,2),(1,3),(1,5),(2,1),(2,2),(2,5),(2,6),(3,1),(3,2),(3,3),(3,4),(3,5),(3,6),(3,7);
