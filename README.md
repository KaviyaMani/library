Simple Library application based on Spring Boot and containerize using docker.
Included Dockerfile and docker-compose.yaml for image creation and running it as container.

Steps to clone repo:
1. git clone https://github.com/KaviyaMani/library.git or download it as zip and extract
2. Open the project in Intellij
3. Once maven resolved dependencies. Run mvn install
4. This will create jar under target folder
5. Build using docker-compose build
6. Run using docker-compose up
7. Visit localhost:8080/swagger-ui/index.html which will give available apis
8. Stop the containers using docker-compose down
