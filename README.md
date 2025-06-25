# 🚴 Sistema di Prenotazione Biciclette – Progetto Universitario

Questo progetto è un'applicazione sviluppata in **Java con Spring Boot** che consente la gestione delle **prenotazioni di biciclette**, dei **pagamenti** e delle **tariffe**, simulando un sistema reale di bike-sharing.

## 🏗️ Stato del progetto

⚠️ **Il progetto è in fase di sviluppo** e fa parte di un'attività universitaria. Alcune funzionalità potrebbero essere incomplete o soggette a modifiche.

---

## 📌 Funzionalità principali

- 👤 Gestione utenti (clienti e amministratori)
- 🚲 Prenotazione delle biciclette
- 📍 Gestione parcheggi e posizione biciclette
- 💳 Gestione dei pagamenti con **Strategy Pattern**
- 💰 Calcolo dinamico del costo tramite una classe `Tariffa` (€/km e €/ora)
- 🔐 Sicurezza con ruoli (Spring Security)
- 🌐 REST API per interazione frontend/backend

---

## 🛠️ Tecnologie utilizzate

- Java 17+
- Spring Boot
- Spring Data JPA (Hibernate)
- Spring Security
- **MySQL** (come database principale)
- Maven
- Lombok
- Postman (per test API)

---

## ⚙️ Configurazione database (MySQL)

Assicurati di avere MySQL installato e in esecuzione.  
Modifica il file `application.properties` (o `application.yml`) come segue:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/nome_database
spring.datasource.username=tuo_username
spring.datasource.password=tuo_password
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL8Dialect
