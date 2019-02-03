#SuperCinema
Application for managing movies screenings in cinema.
#### Stack
* Backend: Java, Spring Boot, JPA, REST, Lombok, Maven, MySQL, H2 DB
* Frontend: Angular 7

#### Prerequisites
Application requires running MySQL database on localhost:3306 with an user specified in *\backend\src\main\resources\application-remote.properties*
 
#### Build and run Java application (localhost:8080)

```sh
$ cd backend && mvn clean install
```
```sh
$ java -jar -Dspring.profiles.active=remote target/backend-0.0.1-SNAPSHOT.jar
```

#### Develop Java (localhost:8080) and Angular (localhost:4200):

```sh
$ cd backend && mvn spring-boot:run -Dspring-boot.run.profiles=remote
```
```sh
$ cd ..\frontend && ng serve --open
```

#### Run Java tests:
```sh
$ cd backend && mvn test
```
#### Features

- [x] Adding, editing and deleting actors/directors 
- [x] Adding, editing and deleting movies with actors/directors

#### To do

- [x] User authentication
- [x] Parsing CSV file with screening rooms configuration (rows and seats number in rooms)
- [x] Managing display of movies in screening rooms

