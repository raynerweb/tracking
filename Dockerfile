FROM openjdk:17-alpine

COPY target/*.jar /app/app.jar
WORKDIR /app

CMD ["java", "-jar", "app.jar"]
