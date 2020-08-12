# Java/Angular challenge - speed integration with Bundesbank.

### Front:
* Angular9 - Glues the front-end.
* Material - UI Components
* D3V5 - Js visualisation of currency rate


### Back:
* Gradle - Automates build.
* Spring Boot - Back end core. 
* Spring REST - Rest API
* Spring Data - ORM Hibernate/JPA/Projections.
* Swagger - Rest API design and integration available http://localhost:8212/swagger-ui/index.html#/
* H2 DB - in memory Database to save Data.
* xjcGenerate - Generates JAXB BundesBank Schema bbk http://www.bundesbank.de/statistik/zeitreihen/BBKcompact see schema folder for xsd.
* Data - Comes from https://www.bundesbank.de/dynamic/action/de/statistiken/zeitreihen-datenbanken/zeitreihen-datenbank/759778/759778?listId=www_s331_b01012_3 as SDMX-ML XML

![](exchnage_design.png)

Build/Run steps

You will need JAVA non less than 1.8. NODE, NPM, Angular CLI.

You can import it into your IDE as gradle project so you can review source code build/test it using IDE.

Or

BACK:
You can clean build this server by executing:
./gradlew clean build
This will generate build folder with /boiler/build/libs/boiler-1.0-SNAPSHOT.jar. To run:
java -jar boiler-1.0-SNAPSHOT.jar

This will start the server on port 8212.
REST API Swagger http://localhost:8212/swagger-ui/index.html#/

FRONT:
