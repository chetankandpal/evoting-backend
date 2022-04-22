FROM openjdk:11
EXPOSE 8083
ARG JAR_FILE=target/eVoting_backend-1.0-SNAPSHOT.jar
ADD ${JAR_FILE} evoting.jar
ENTRYPOINT ["java","-jar","/evoting.jar"]
