FROM openjdk:17

ARG JAR_FILE=build/libs/microservice-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} microservice-0.0.1-SNAPSHOT.jar

EXPOSE 8070

ENTRYPOINT ["java","-jar","/microservice-0.0.1-SNAPSHOT.jar"]
