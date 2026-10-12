# Activité Pratique N°1 — Micro-service de gestion des comptes bancaires

Micro-service Spring Boot qui permet de gérer des comptes bancaires.

## Technologies

Java 21, Spring Boot 3.5, Spring Web, Spring Data JPA, H2, Lombok.

## Étapes réalisées

1. Projet Spring Boot avec les dépendances Web, Spring Data JPA, H2, Lombok
2. Entité JPA `BankAccount` (compte) : `id`, `createdAt`, `balance`, `currency`, `type`
3. Interface `BankAccountRepository` basée sur Spring Data
4. Test de la couche DAO : insertion de 10 comptes au démarrage (`CommandLineRunner`)
   et tests unitaires `BankAccountRepositoryTest` (`@DataJpaTest`)

## Démarrage

```bash
mvn spring-boot:run
```

- Application : http://localhost:8081
- Console H2 : http://localhost:8081/h2-console (JDBC URL `jdbc:h2:mem:account-db`, utilisateur `sa`)

## Tests

```bash
mvn test
```
