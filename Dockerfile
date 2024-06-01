FROM openjdk:17.0.1-jdk-slim
COPY --from=build /target/gemhub-0.0.1-SNAPSHOT.jar /app/gemhub.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app/gemhub.jar"]
