FROM openjdk:17-jdk
WORKDIR /app
COPY target/serviceregistry.jar /app/serviceregistry.jar
EXPOSE 8761
CMD ["java", "-jar", "serviceregistry.jar"]
