# Java/SpringBoot/Angular challenge - speed integration with Bundesbank.

From scratch to fully functional currency exchange application in 5 days.

Application has two parts: dynamic and very live **front end** and robust easy to maintain/deploy/container **back end**. Technical details you will find below:

### Front:
* Angular10 - Glues the front-end.
* AngularMaterial - UI Components.
* D3V5 - Js visualisation of currency rate graph.
* HTML5 - Animations, effects, validations.

### Back:
* Gradle - Automates build.
* Spring Boot - Backend core. 
* Spring REST - Rest API.
* Spring Data - ORM Hibernate/JPA/Projections.
* Liquibase - Database schema control.
* Swagger - Rest API design and integration.
* H2 DB - in memory Database .
* xjcGenerate - Generates JAXB BundesBank Schema BBKcompact. See schema folder for xsd.
  * https://api.statistiken.bundesbank.de/rest/download/BBEX3/A.AED.EUR.CA.AA.A04?format=sdmx&lang=en
    * https://api.statistiken.bundesbank.de/rest/download/BBEX3/A.ATS.DEM.AA.AC.A04?format=sdmx&lang=en
* DataFeed - https://www.bundesbank.de/dynamic/action/de/statistiken/zeitreihen-datenbanken/zeitreihen-datenbank/759778/759778?listId=www_s331_b01012_3 as SDMX-ML XML

![](exchnage_design.png)

### Build/Run steps

You will need JAVA non less than 1.8. NODE, NPM, Angular CLI.

You can import it into your IDE as gradle project so you can review source code build/test it using IDE.

Or build/run it manually:

### BACK:
```sh
You can clean build this server by executing:
./gradlew clean build
This will generate build folder with /boiler/build/libs/boiler-1.0-SNAPSHOT.jar. 
To run:
java -jar boiler-1.0-SNAPSHOT.jar
```
This will start the server on port 8212.
REST API Swagger available at http://localhost:8212/swagger-ui/index.html#/

### FRONT:
```sh
In client folder run:
ng serve --proxy-config proxy.conf.json
```
this will start client on http://localhost:4200/

### TODO
1. Delta load from Bundesbank
2. Testing 
