FROM openjdk:8
ADD target/urlShortnerBackend-0.0.1-SNAPSHOT.jar urlShortnerBackend-0.0.1-SNAPSHOT.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/urlShortnerBackend-0.0.1-SNAPSHOT.jar"]
