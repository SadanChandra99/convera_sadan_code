# Getting Started

### Overview
The simple template project for the Convera java data api services based on SpringBoot + Maven + Postgres DB

### Local run
- Clone the project
- Install the [parent pom](https://gitlab.com/convera_platform/convera-spring-boot-super-pom) to your local maven repository
```mvn clean install```
- Enable annotation processing for lombok in your IDE
- Run ```docker-compose up``` in the terminal to bring up the db image. See [docker-compose.yml](docker-compose.yml) for details.
- Run Application, it will be running at port ```8080```
- Use [postgres-data-api-template.postman_collection.json](postgres-data-api-template.postman_collection.json) postman collection to run the requests
- Access Swagger UI by [localhost:8080/swagger-ui.html](localhost:8080/swagger-ui.html) from your browser
- Access ```swagger.json``` API definition by [localhost:8080/v3/api-docs](localhost:8080/v3/api-docs)
