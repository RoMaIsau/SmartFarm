CREATE DATABASE SmartFarm;
CREATE USER 'SmartFarmApp'@'localhost' IDENTIFIED BY 'SmartFarm2020';
GRANT ALL ON SmartFarm.* TO 'SmartFarmApp'@'localhost';