FROM amazoncorretto:17.0.5-alpine as builder
WORKDIR app
COPY target/*.jar locations.jar
RUN jar xvf locations.jar

FROM amazoncorretto:17.0.5-alpine
WORKDIR app
COPY --from=builder app/BOOT-INF/lib lib
COPY --from=builder app/META-INF META-INF
COPY --from=builder app/BOOT-INF/classes classes

CMD ["java", "-cp", "classes:lib/*", "com.learn.locations.LocationsApplication"]