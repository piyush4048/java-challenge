### How to use this spring-boot project

- Install packages with `mvn package`
- Run `mvn spring-boot:run` for starting the application (or use your IDE)

Application (with the embedded H2 database) is ready to be used ! You can access the url below for testing it :

- Swagger UI : http://localhost:8080/swagger-ui.html
- H2 UI : http://localhost:8080/h2-console

> Don't forget to set the `JDBC URL` value as `jdbc:h2:mem:testdb` for H2 UI.



### Instructions

- download the zip file of this project
- create a repository in your own github named 'java-challenge'
- clone your repository in a folder on your machine
- extract the zip file in this folder
- commit and push


#### Restrictions
- use java 8

#### Lombok Setup
If Lombok is not set up in Eclipse, getters/setters will not be generated, and a compile error will occur even if the project is imported Lombok. Therefore, set up Lombok by referring to the following.

Setup eclipse Lombok plugin : https://projectlombok.org/setup/eclipse

Go to help -> About Eclipse IDE -> check for lombok plugin

#### Authentication 

All api/v1/** urls are Authenticated. 

Please use the following credentials to login.

username : test

password : P@ssw0rd

#### Link to HELP document

Javadoc has been created for the following project, please check Javadoc for the understanding of classes, methods, variables, and tests.

#### Junit Test

Junit test is added for repository, controller, and caching layers.

#### Code Enhancement Spot/ Things can be improve further

Currently Application is using default spring security login/logout pages, Custom login/logout pages can be created.

Currently an user is hard coded in server memory (in memory), Custom Sigup page can be added.

- Curretly application performing service memory Authentication , we can store the user data inside database and can perform the JDBC  Authentication Instead. 

- currently appilication is not restricted on user role basis, but some apis can be restricted on role/Authority basis.

Currenly caching logic implemented without any conditions. Conditions can be further applied to the caching logic.

#### Postman API request 

Since the URL of the endpoints are secure, add an Authorization header while sending the request to avoid the below error.
("status": 401,
"error": "Unauthorized")

Go to Authorization -> Select Type ( Basic Auth) -> Provide the credentials. (username : test , password : P@ssw0rd)

#### CURL Request

Add an user header with the curl request for authentication, like below:

Curl -X POST -H "Content-Type: application/json" -d "{data}" -u "test:P@ssw0rd"

#### Caching

Caching logic improves application efficiency and eliminates database calls


#### Experience in Java

- I have almost 3 years of experience in Java and 1 year of hands on Experience on Spring Boot
