FROM amazoncorretto:17.0.5-alpine as builder
RUN mkdir /opt/app
ADD maven/${project.artifactId}-${project.version}.jar /opt/app/locations.jar
CMD ["java", "-jar", "/opt/app/locations.jar"]