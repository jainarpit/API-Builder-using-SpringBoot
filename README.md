# Project Title
This project provides service to validate and change user password

## Getting Started
The project is hosted on github.
https://github.com/jainarpit/API-Builder-using-SpringBoot.git

### Prerequisites
You will need following to run this application: 
<br>1.java
<br>2.gradle
<br>3.passy
<br>4.Similarity Checker

### How to Run:
```gradle assemble``` <br>
```./gradlew bootrun```

<br> The application by default runs on <br>
http://localhost:8080

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
``docker pull jarpit/assignment:v3``


### Future Work
1. Adding validation on number of characters to be not more than 50% of total password.
2. Throw Proper Error Message in case of 500 Internal Server Error

### Acknowledgment:

1. rrice/java-string-similarity for similarity checker
