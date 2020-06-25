CREATE DATABASE SmartFarm;
CREATE USER 'SmartFarmApp'@'localhost' IDENTIFIED BY 'SmartFarm2020';
GRANT ALL ON SmartFarm.* TO 'SmartFarmApp'@'localhost';

USE SmartFarm;

-- VACUNAS
INSERT INTO VACUNAS VALUES 
(1, "Aftosa", 2),
(2, "Brucelosis", 5),
(3, "Carbunco",3),(4,"Botulismo",12),(5,"Leptospirosis",4),(6,"Policlostridiales1",4),(7,"Policlostridiales2",12);



-- TIPOS DE USUARIOS
INSERT INTO tipodeusuario (nombre) VALUES ("admin"), ("veterinario"), ("empleado");

-- USUARIOS
INSERT INTO usuario VALUES 
(1, "Prueba", "admin@gmail.com", "2020/05/12", "Usuario", "123", "Admin"),
(2, "Prueba", "empleado@gmail.com", "2020/05/12", "Usuario", "123", "Empleado"),
(3, "Prueba", "veterinario@gmail.com", "2020/05/12", "Usuario", "123", "Veterinario");

-- TIPOS DE GASTOS
INSERT INTO TipoDeGasto (nombre) VALUES ("Gastos alimenticios"), ("Gastos empresariales"), ("Gastos médicos"), ("Gastos tecnologicos");

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

-- ANIMALES PARA PRUEBA DE MAPA Y VACUNAS
INSERT INTO animalDeGranja (peso, genero_id, raza_id, tipo_id, fechaNacimiento) VALUES
(50, 1,	1, 1, "2020.01.10"),
(60, 1,	7, 2, "2020.02.10"),
(70, 1,	2, 1, "2019.02.10"),
(75, 1,	20, 5, "2020.01.10"),
(1,	1, 1, 1, "2020.02.10"),
(1,	1, 1, 1, "2019.02.10"),
(1,	1, 1, 1, "2020.01.10"),
(1,	1, 1, 1, "2020.02.10"),
(1,	1, 1, 1, "2019.02.10"),
(2,	1, 5, 2, "2020.01.10"),
(2,	1, 5, 2, "2020.02.10"),
(2,	1, 5, 2, "2019.02.10"),
(2,	1, 5, 2, "2020.01.10"),
(2,	1, 5, 2, "2020.02.10"),
(3,	1, 9, 3, "2019.02.10"),
(3,	1, 9, 3, "2020.01.10"),
(3,	1, 9, 3, "2020.02.10"),
(3,	1, 9, 3, "2019.02.10"),
(3,	1, 9, 3, "2020.01.10"),
(4,	1, 13, 4, "2020.02.10"),
(4,	1, 13, 4, "2019.02.10"),
(4,	1, 13, 4, "2020.01.10"),
(4,	1, 13, 4, "2020.02.10"),
(4,	1, 13, 4, "2019.02.10"),
(5,	1, 17, 5, "2020.01.10"),
(5,	1, 17, 5, "2020.02.10"),
(5,	1, 17, 5, "2019.02.10"),
(5,	1, 17, 5, "2020.01.10"),
(5,	1, 17, 5, "2020.02.10"),
(2,	1, 1, 1, "2019.02.10");

select * from signosvitales;
delete from animaldegranja;
delete from usuario;
select* from animaldegranja;
select * from usuario;

SELECT * FROM ubicacion 
