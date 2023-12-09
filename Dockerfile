FROM openjdk:17-oracle
ARG JAR_FILE=target/phone-book-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} app.jar
EXPOSE ${SERVER_PORT}
ENTRYPOINT ["java", "-Dspring.profiles.active=prod", "-jar","/app.jar"]
