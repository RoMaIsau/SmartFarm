CREATE DATABASE SmartFarm;
CREATE USER 'SmartFarmApp'@'localhost' IDENTIFIED BY 'SmartFarm2020';
GRANT ALL ON SmartFarm.* TO 'SmartFarmApp'@'localhost';
USE SmartFarm;

INSERT INTO usuario VALUES (1, "Prueba", "admin@gmail.com", "2020/05/12", "Usuario", "123", "Admin");