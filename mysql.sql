drop database IF EXISTS libro;
CREATE DATABASE IF NOT EXISTS libro;

USE libro;

CREATE TABLE IF NOT EXISTS libro (
    id INT AUTO_INCREMENT PRIMARY KEY,
    titulo VARCHAR(255) NOT NULL,
    autor VARCHAR(255) NOT NULL,
    anio_publicacion INT NOT NULL
);