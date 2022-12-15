FROM amazoncorretto:17.0.5-alpine as builder
WORKDIR application
COPY target/employees-0.0.1-SNAPSHOT.jar employees.jar

RUN java -Djarmode=layertools -jar employees.jar extract

FROM amazoncorretto:17.0.5-alpine
WORKDIR application
COPY --from=builder application/dependencies/ ./
COPY --from=builder application/spring-boot-loader/ ./
COPY --from=builder application/snapshot-dependencies/ ./
COPY --from=builder application/application/ ./
CMD ["java", \
"org.springframework.boot.loader.JarLauncher"]