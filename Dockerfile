FROM openjdk:17.0.2
WORKDIR /app/test-tecnico

COPY . .

RUN ./mvnw clean package -DskipTests
EXPOSE 8080
ENTRYPOINT ["java", "-jar","./target/my-app-0.0.1-SNAPSHOT.jar"]