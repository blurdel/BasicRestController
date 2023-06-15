FROM openjdk:17

COPY target/BasicRestController-0.0.1-SNAPSHOT.jar service.jar

EXPOSE 8080

CMD ["java", "-jar", "service.jar"]

# build image
# docker build -t basic-rest-controller:1.0.0 .

# build container
# docker run -d --name basic-rest-controller -p 8080:8080 basic-rest-controller:1.0.0
