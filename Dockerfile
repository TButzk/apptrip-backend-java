FROM maven:3.9.11-eclipse-temurin-25 AS build

WORKDIR /workspace

COPY pom.xml .
RUN mvn -B dependency:go-offline

COPY src ./src
RUN mvn -B package -DskipTests

FROM eclipse-temurin:25-jre

WORKDIR /app

COPY --from=build /workspace/target/*.jar /app/apptrip.jar

ENV SERVER_PORT=5010
ENV APPTRIP_UPLOADS_PATH=/app/uploads

EXPOSE 5010

ENTRYPOINT ["java", "-jar", "/app/apptrip.jar"]

