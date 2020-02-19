FROM openjdk:8-jdk-alpine
VOLUME /tmp
ARG docker-demo
COPY docker-demo.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]