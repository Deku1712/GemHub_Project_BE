FROM openjdk:17.0.1-jdk-slim
VOLUME /tmp
COPY target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/app.jar"]

