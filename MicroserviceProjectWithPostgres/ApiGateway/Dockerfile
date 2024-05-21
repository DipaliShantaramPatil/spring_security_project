FROM openjdk:17-jdk
WORKDIR /app
COPY target/apigateway.jar /app/apigateway.jar
EXPOSE 8080
CMD ["java", "-jar", "apigateway.jar"]
