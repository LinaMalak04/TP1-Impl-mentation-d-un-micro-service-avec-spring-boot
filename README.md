# Activité Pratique N°1 — Micro-service de gestion des comptes bancaires

Micro-service Spring Boot qui permet de gérer des comptes bancaires.

## Technologies

Java 21, Spring Boot 3.5, Spring Web, Spring Data JPA, Spring Data REST, H2, Lombok,
springdoc-openapi (Swagger UI).

## Étapes réalisées

1. Projet Spring Boot avec les dépendances Web, Spring Data JPA, H2, Lombok
2. Entité JPA `BankAccount` (compte) : `id`, `createdAt`, `balance`, `currency`, `type`
3. Interface `BankAccountRepository` basée sur Spring Data
4. Test de la couche DAO : insertion de 10 comptes au démarrage (`CommandLineRunner`)
   et tests unitaires `BankAccountRepositoryTest` (`@DataJpaTest`)
5. Web service RESTful `AccountRestController` (`/api/bankAccounts`)
6. Test du micro-service avec un client REST (fichier [`requests.http`](requests.http), utilisable aussi dans Postman)
7. Documentation Swagger des API REST
8. API RESTful générée par Spring Data REST (`/bankAccounts`) avec la projection `p1`

## Démarrage

```bash
mvn spring-boot:run
```

| Outil            | URL                                            |
|------------------|------------------------------------------------|
| Swagger UI       | http://localhost:8081/swagger-ui.html          |
| OpenAPI (JSON)   | http://localhost:8081/v3/api-docs              |
| Console H2       | http://localhost:8081/h2-console (JDBC URL `jdbc:h2:mem:account-db`, utilisateur `sa`) |

## API REST (`AccountRestController`)

| Méthode | URL                       | Description          |
|---------|---------------------------|----------------------|
| GET     | `/api/bankAccounts`       | Liste des comptes    |
| GET     | `/api/bankAccounts/{id}`  | Un compte            |
| POST    | `/api/bankAccounts`       | Créer un compte      |
| PUT     | `/api/bankAccounts/{id}`  | Modifier un compte   |
| DELETE  | `/api/bankAccounts/{id}`  | Supprimer un compte  |

## API Spring Data REST

| URL                                                | Description                              |
|----------------------------------------------------|------------------------------------------|
| `/bankAccounts`                                    | Liste paginée des comptes (HAL)          |
| `/bankAccounts?page=0&size=3`                      | Pagination                               |
| `/bankAccounts?projection=p1`                      | Projection `p1` : `id`, `type`, `balance` |
| `/bankAccounts/search/byType?t=CURRENT_ACCOUNT`    | Recherche par type de compte             |

## Tests

```bash
mvn test
```
