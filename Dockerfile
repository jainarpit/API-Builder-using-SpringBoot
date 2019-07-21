FROM openjdk:alpine

WORKDIR /app

COPY ./build/libs/assignment-0.0.1-SNAPSHOT.jar ./app/

EXPOSE 8080

ENTRYPOINT ["java", "-jar","./app/assignment-0.0.1-SNAPSHOT.jar"]
