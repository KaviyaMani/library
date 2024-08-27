FROM openjdk:17
COPY target/library.jar library.jar
ENTRYPOINT ["java", "-jar", "/library.jar"]