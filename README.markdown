# Spring Boot on Local connecting to MySQL Docker container

1. Use MySQL Image published by Docker Hub (https://hub.docker.com/_/mysql/)
   Command to run the mysql container

docker run --detach --env MYSQL_ROOT_PASSWORD=root --env MYSQL_USER=admin --env MYSQL_PASSWORD=admin --env MYSQL_DATABASE=stockRepository --name mysql --publish 3306:3306 mysql:5.7

1. In the Spring Boot Application,  application.properties setup for connecting with docker

Since I was using docker toolbox(Windows 10 Home) , i was not able to bind  sql started in docker  with local host,  I was only able to bind only with docker toolbox ipAddress

  Please change 'spring.datasource.url' based on below case

  Case 1 : If we are using docker desktop
  
`spring.datasource.url = jdbc:mysql://localhost:3306/stockRepository?createDatabaseIfNotExist=true`

  Case 2 : If we are using docker toolbox
  
`spring.datasource.url = jdbc:mysql://"ip Adress of docker machine":3306/stockRepositorycreateDatabaseIfNotExist=true`

   command to find docker Ip Adress:
      "docker-machine ip"

