#FROM gradle:5.3.1-jdk8-alpine as build
#WORKDIR /work
#USER root
#COPY . .
#RUN gradle clean build
#
#FROM openjdk:8-jdk-alpine
#VOLUME /tmp
#USER root
#COPY --from=build work/build/libs/Report-0.0.1-SNAPSHOT.jar app.jar
#EXPOSE 8080
#CMD ["java", "-jar", "app.jar"]


FROM openjdk:8-jdk-alpine
USER root
COPY . .
RUN ./gradlew clean build
EXPOSE 8080
CMD ["java", "-jar", "/build/libs/Report-0.0.1-SNAPSHOT.jar"]