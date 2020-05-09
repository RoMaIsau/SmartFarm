# SmartFarm
_SmartFarm es una aplicación web de monitoreo de animales realizada para Taller Web I en la carrera Tecnicatura de Desarrollo Web._

## Construido con 🛠️
* [Java](https://www.oracle.com/java/technologies) - Lenguaje utilizado
* [Spring](https://spring.io/) - Framework Java
* [Maven](https://maven.apache.org/) - Manejador de dependencias
* [Hibernate](https://hibernate.org/) - Herramienta ORM
* [Bootstrap](https://getbootstrap.com/) - Usado para los estilos CSS

## Autores ✒️

* **Baudach Agustina** - []()
* **Isaurralde Rocio** - [RoMaIsau](https://github.com/RoMaIsau)
* **Murineddu Cristian** - [Mury10](https://github.com/Mury10)
* **Vasari Ernesto** - [EzeVasari](https://github.com/EzeVasari)

## Entorno Local

# Base de datos

### Conexión

* **Base de datos:** SmartFarm
* **Usuario de base de datos:** SmartFarmApp
* **Password:** SmartFarm2020

### Creación

_Las querys están en el archivo scripts/CrearBaseDeDatos.sql_

```sql
CREATE DATABASE SmartFarm;
```

```sql
CREATE USER 'SmartFarmApp'@'localhost' IDENTIFIED BY 'SmartFarm2020';
```

```sql
GRANT ALL ON SmartFarm.* TO 'SmartFarmApp'@'localhost';
```

# Applicación web

### URL

* [SmartFarm](http://localhost:8080/SmartFarm) - http://localhost:8080/SmartFarm

### Empaquetado

`mvn clean package`

### Ejecución de test

`mvn test`

