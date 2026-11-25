# geo_tchad — API REST du découpage administratif du Tchad

API REST développée avec **Java 21** et **Spring Boot 4** permettant de consulter et gérer le découpage administratif du Tchad (23 provinces, 95 départements et 365 communes).

## Stack technique

* **Langage & Framework** : Java 21, Spring Boot 4, Spring Web, Spring Data JPA (Hibernate)
* **Base de données & Migrations** : MySQL, HikariCP, Flyway
* **Validation & Documentation** : Jakarta Validation, Springdoc OpenAPI (Swagger UI)
* **Tests** : JUnit 5, Mockito, MockMvc (`@WebMvcTest`)

## Architecture & choix techniques

* **Architecture en couches** : séparation stricte `Controller` / `Service` / `Repository` / `Entity` avec injection de dépendances par constructeur (`private final`).
* **DTOs immuables** : utilisation de Java `record` distincts pour la lecture (`ProvinceDto`, `ProvinceDetailDto`, `DepartementDto`).
* **Optimisation SQL** : relations configurées en `FetchType.LAZY` par défaut et chargement ciblé en un seul `LEFT JOIN` via `@EntityGraph` pour éviter le problème N+1.
* **Migrations versionnées** : schéma et données initiales des 23 provinces gérés par **Flyway** (`V1__init_schema.sql`, `V2__seed_provinces.sql`) avec Hibernate en mode `ddl-auto: validate`.

##  Endpoints principaux

| Ressource | Méthode | Endpoint | Description |
| :--- | :--- | :--- | :--- |
| **Provinces** | `GET` | `/api/provinces` | Liste paginée (`page`, `size`, `sort`) |
| **Provinces** | `GET` | `/api/provinces/{id}` | Détail d'une province avec ses départements |
| **Provinces** | `GET` | `/api/provinces/{provinceId}/departements` | Départements d'une province |
| **Provinces** | `POST` | `/api/provinces` | Créer une province (`201 Created`) |
| **Provinces** | `PUT` | `/api/provinces/{id}` | Mettre à jour une province |
| **Provinces** | `DELETE` | `/api/provinces/{id}` | Supprimer une province (`204 No Content`) |
| **Départements** | `GET` | `/api/departements` | Liste paginée des départements |
| **Départements** | `GET` | `/api/departements/{id}` | Détail d'un département |
| **Départements** | `POST` / `PUT` / `DELETE` | `/api/departements/{id}` | CRUD complet des départements |

##  Installation et lancement

### 1. Prérequis
* Java 21+
* Maven 3.9+ (ou le wrapper `./mvnw`)
* MySQL 8+

### 2. Base de données
Créez la base MySQL (Flyway se charge des tables et du chargement initial des 23 provinces au démarrage) :
```sql
CREATE DATABASE geo_tchad;