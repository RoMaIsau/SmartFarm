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

--ENFERMEDADES
INSERT INTO Enfermedad (id,fecha,finTratamiento,inicioTratamiento,nombre,tratamientoA,tratamientoB,historia_id) VALUES
(2,"2020.06.10",null,null,'Leptospirosis',null,null,2),
(3,"2020.06.19",null,null,'Fiebre Aftosa',null,null,2),
(4,"2020.06.17",null,null,'Miocardiopatiacongenita',null,null,1),
(5,"2020.06.10",null,null,'Rinotraqueitis infecciosa',null,null,2),
(6,"2020.08.10",null,null,'Leptospirosis',null,null,2),
(7,"2020.08.19",null,null,'Fiebre Aftosa',null,null,2),
(8,"2020.08.17",null,null,'Miocardiopatiacongenita',null,null,1),
(9,"2020.08.10",null,null,'Rinotraqueitis infecciosa',null,null,2);


select * from signosvitales;
delete from animaldegranja;
delete from usuario;
select* from animaldegranja;
select * from usuario;

SELECT * FROM ubicacion 

