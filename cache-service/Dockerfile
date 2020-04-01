FROM openjdk:8-jdk-alpine
VOLUME /tmp
COPY target/*.jar cache-micro.jar
ENTRYPOINT ["java","-jar","/cache-micro.jar"]