FROM maven:3.8.5-openjdk-17-slim as BUILDER
ARG VERSION=0.0.1-SNAPSHOT
WORKDIR /build/
COPY pom.xml /build/
COPY src /build/src/

RUN mvn clean package
COPY target/myfirstapp-${VERSION}.jar target/application.jar

FROM eclipse-temurin:17-jre
WORKDIR /app/

COPY --from=BUILDER /build/target/application.jar /app/
CMD ["java", "-jar", "/app/application.jar"]