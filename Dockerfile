#FROM openjdk:17
#COPY target/library.jar library.jar
#ENTRYPOINT ["java", "-jar", "/library.jar"]

FROM maven:3.8.6-openjdk-18-slim AS build
COPY src /app/src
COPY pom.xml /app
RUN mvn -f /app/pom.xml clean package -DskipTests

FROM gcr.io/distroless/java
COPY --from=build /app/target/library.jar /app/library.jar
ENTRYPOINT ["java","-jar","/app/library.jar"]