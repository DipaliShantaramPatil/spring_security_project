FROM openjdk:17-jdk
WORKDIR /app
COPY target/userservice.jar /app/userservice.jar
EXPOSE 8080
CMD ["java", "-jar", "userservice.jar"]
