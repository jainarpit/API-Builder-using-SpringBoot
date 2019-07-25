# Project Title
This project provides service to validate and change user password. It validates if the new password follows the pattern.

## Getting Started
The project is hosted on github.
https://github.com/jainarpit/API-Builder-using-SpringBoot.git

### Pre-requisites
You will need following to run this application: 
<br>1.java
<br>2.gradle
<br>3.passy
<br>4.Similarity Checker
<br>5. H2 Database

### How to Run:
```gradle assemble``` <br>
```./gradlew bootrun```

<br> The application by default runs on <br>
http://localhost:8080

Note: Please make sure port 8080 is free

### Endpoint
1. /user
2. /user/{id}

### HTTP methods Supported
1. GET
2. PUT
3. POST
4. DELETE

### Docker Support:
You can get the docker image by running <br>
``docker pull jarpit/assignment:v3`` <br> 
`` docker run -p 8080:8080 jarpit/assignment:v3 ``

### Current Features:
1. By Default when application starts, few data is loaded in h2 database, (as of now 2 entries).<br>
2. If user wants to sign up , make a "POST" request with json body having username and currentPassword attributes. In the headers, a "id" is generated which can be used as path parameter while making a /GET or /PUT request. <br>
Also, there is no password pattern/criteria for creating a new user.
3. If user wants to see his details, make a "GET" request by hitting "/user/{id}" <br> 
4. If user wants to change his password, make a "PUT" request by hitting "/user/{id}" and request json body having username,currentPassword and newPassword attributes. <br> 
5. Also, for Password similarity Checking, currently we are using JaroDistance algorith which is provided by the dependency mentioned in the acknowledgment section <br>

### Future Work
1. Adding validation on number of characters to be not more than 50% of total password.
2. Throw Proper Error Message in case of 500 Internal Server Error
3. Adding proper controller level test
4. To implement our own Similarity Checker

### Acknowledgment:
1. rrice/java-string-similarity for similarity checker
