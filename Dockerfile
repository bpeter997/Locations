FROM amazoncorretto:17.0.5-alpine
WORKDIR /opt/app
COPY target/*.jar locations.jar
CMD ["java", "-jar", "locations.jar"]