FROM openjdk:17-jdk-slim AS builder

# Set application jar-filename
ARG APP_VERSION=0.0.0

ARG JAR_FILE=target/basic-rest-controller-${APP_VERSION}.jar

WORKDIR /application

COPY ${JAR_FILE} application.jar
RUN java -Djarmode=layertools -jar application.jar extract


FROM openjdk:17-jdk-slim

EXPOSE 8080

WORKDIR /application

COPY --from=builder application/dependencies/ ./
COPY --from=builder application/spring-boot-loader/ ./
COPY --from=builder application/snapshot-dependencies/ ./
COPY --from=builder application/application/ ./

ENTRYPOINT ["java", "org.springframework.boot.loader.launch.JarLauncher"]
