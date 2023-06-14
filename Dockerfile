FROM openjdk:17-oracle
ARG JAR_FILE=target/data-api-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} data-api-0.0.1-SNAPSHOT.jar

ENTRYPOINT ["sh", "-c", "java ${JAVA_OPTS} -jar data-api-0.0.1-SNAPSHOT.jar"]
