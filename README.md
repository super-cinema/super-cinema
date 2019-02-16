# SuperCinema
REST application for managing movies screenings in cinema. <br/>
Project developed with Scrum methodology and Gitflow Workflow.

#### Stack
* Backend: Java 8, Spring Boot, JPA, REST, MySQL, H2 DB, Mockito, JUnit4, Lombok, Maven 
* Frontend: Angular 7, TypeScript, HTML, Scss
* Tools: Docker, MySql Workbench 

#### Examples of some features

![add crew](img/superCinema-addCrew.png)
![all crew](img/superCinema-allCrew.png)
![sort crew](img/superCinema-crew-sort.png)
![delete crew](img/superCinema-allCrew-delete.png)
![add movie](img/superCinema-addMovie1.png)
![add directorToMovie](img/superCinema-addDirectorToMovie.png)
![add movie validation1](img/superCinema-addMovie-validation.png)
![add movie success](img/superCinema-addMovie-success.png)
![delete movie success](img/superCinema-movie-deleting-succes.png)
![all movies delete](img/superCinema-allMovied-delete.png)
![edit movie](img/superCinema-editMovie.png)

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
- [x] Validation of incorrect addition and editing actors/directors or movie
- [x] Notification for correct or incorrect addition/removal actors/directors or movie
- [x] Searching movie by title, searching actors/directors by surname
- [x] H2 database for testing only
- [x] Dockerizing backend

#### To do

- [ ] User authentication
- [ ] Parsing CSV file with screening rooms configuration (rows and seats number in rooms)
- [ ] Managing display of movies in screening rooms
- [ ] Dockerizing frontend

