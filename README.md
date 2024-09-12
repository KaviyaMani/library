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

------------------------------------------------------
Run with custom network
1. Define custom network with networks top level element and add them to services
2. Try "docker network ls" and "library_secure-network bridge  local" network will be available after docker compose up
3. If you "docker inspect container_name" you can see container connected to custom network

-------------------------------------------------------
Create and manage docker volumes
Since we are running postgres data store under /var/lib/postgresql/data in container
1. Define custom volumes with volumes top level element and add them to services
2. Run "docker compose up"
3. Run "docker volume ls" - to see newly created volume library_postgres-volume
4. Run "docker volume inspect volume_name" - get the mount path
5. cd into the mount path you can see the files being copied there. try with sudo -i id permission denied

-------------------------------------------------------
Create backup of docker volume
docker run --rm --volumes-from library-mypostgres-1 -v $(pwd):/backup ubuntu tar cvf /backup/librarybackup.tar /var/lib/postgresql/data
1. We are going to run the container temporarily to do the backup(docker run)
2. --rm will remove the container once the backup is done(--rm)
3. Mention the database container from which we are going to take the backup(library-mypostgres-1)
4. Creating bind mount with present working directory(-v)
5. Mention the container which is going to do the backup process(ubuntu)
6. tar file details(librarybackup.tar)
7. Mention the directory inside container which volume is using for backup(/var/lib/postgresql/data)

Restore data from backup
docker run --rm --volumes-from library-mypostgres-1 -v $(pwd):/backup bash -c "cd /var/lib/postgresql/data && tar xvf /backup/librarybackup.tar"

---------------------------------------------------------
Run container with fewer privileges
1. Add cap_add with necessary permission in docker compose of specific service

----------------------------------------------------------
Docker Bench for security vulnerabilities
1. Go to https://github.com/docker/docker-bench-security
2. Follow the steps provided use docker build or compose
3. Start your containers which has to be checked
4. Run docker-bench-security container with necessary details

-----------------------------------------------------------
Optimize image
1. Use multi-stage build to have just the runtime and necessary files at end image
2. Copy only necessary files to the final image
3. Use jdk just to build the image and jre to run the image this will reduce image size drastically
4. Use distroless image if possible which will contain just the runtime which increases security


