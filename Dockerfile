FROM adoptopenjdk/openjdk11:jre-11.0.6_10-alpine
COPY target/starwars-server-0.0.1.jar starwars-app-0.0.1.jar
ENTRYPOINT ["java","-jar","/starwars-app-0.0.1.jar"]