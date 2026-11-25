CREATE TABLE provinces (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    code VARCHAR(10) NOT NULL UNIQUE,
    nom VARCHAR(100) NOT NULL UNIQUE,
    chef_lieu VARCHAR(100) NOT NULL
);

CREATE TABLE departements (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    code VARCHAR(10) NOT NULL UNIQUE,
    nom VARCHAR(100) NOT NULL UNIQUE,
    chef_lieu VARCHAR(100) NOT NULL,
    province_id BIGINT NOT NULL,
    CONSTRAINT fk_departement_province
        FOREIGN KEY (province_id) REFERENCES provinces(id)
);

CREATE TABLE communes (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    code VARCHAR(10) NOT NULL UNIQUE,
    nom VARCHAR(100) NOT NULL,
    departement_id BIGINT NOT NULL,
    CONSTRAINT fk_commune_departement
        FOREIGN KEY (departement_id) REFERENCES departements(id)
);