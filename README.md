# maBibliotheque

Cette application est à destination de la ville pour la gestion de sa bibliothèque municipale

### Informations générales

Elle est articulé autour de 3 microservices:

- Bibliotheque
- Utilisateur
- ClientUI

Et des EDGE Service suivant:

- Spring Cloud Config
- Eureka
- ZUUL

### Pré-requis

Vous avez besoin d'installer:
- JDK 8  
- BDD Mysql pour 2 BDD
- Maven  

### Installation

1 - Initialiser les bases de données avec comme nom : maBibliMsBibliotheque et une autre maBibliMsUtilisateur (port: 3306 - username: root - password: vide)  
2 - Cloner le projet depuis le repository  
3 - déployer les BDD avec les fichiers: "mabiblimsbibliotheque - structure et données.sql" et "mabiblimsutilisateur - strucutres et données.sql"  
4 - Exécuter le maven goal suivant: 
```
mvn clean install
```
5 - Vous pouvez lancer les microservices

Plusieurs utilisateurs ont été créés :

Un administrateur:   
- Identifiant : admin Mot de passe : admin

Deux utilisateurs:  
- Identifiant : ludo Mot de passe : ludo
- Identifiant : aurelie Mot de passe : aurelie

### Ordre de lancement:

Lancer en premier les edges services et ensuite le microservice bibliotheque puis le microservice utilisateur en enfin clientUi

## application.properties:

Les fichiers de configuration se trouve dans ce repository: https://github.com/ludo77100/maBibilotheque-config-repo

## Port pour accéder aux MicroServices:

- Bibliotheque : 8080
- Utilisateur : 8081
- ClientUI : 8090
- Spring Cloud Config: 9101
- Eureka: 8761
- ZUUL: 9004

## Construit avec:
* [Maven](https://maven.apache.org/)
* [SpringBoot](https://spring.io/projects/spring-boot)
* [Swagger](https://swagger.io/)

## Auteur
Ludovic SOUPLET - Pour reconversion professionnelle via OpenclassRooms
