ARG APPTRIP_BUILD_ID=local

FROM maven:3.9.11-eclipse-temurin-25 AS build

ARG APPTRIP_BUILD_ID

WORKDIR /workspace

COPY pom.xml .
RUN mvn -B dependency:go-offline

COPY src ./src
RUN mvn -B package -DskipTests \
	&& printf '%s\n' "$APPTRIP_BUILD_ID" > /workspace/.apptrip-build-id

FROM eclipse-temurin:25-jre

ARG APPTRIP_BUILD_ID
LABEL org.opencontainers.image.version="$APPTRIP_BUILD_ID" \
	  org.apptrip.build-id="$APPTRIP_BUILD_ID"

WORKDIR /app

COPY --from=build /workspace/target/*.jar /app/apptrip.jar

ENV SERVER_PORT=5010
ENV APPTRIP_UPLOADS_PATH=/app/uploads

EXPOSE 5010

ENTRYPOINT ["java", "-jar", "/app/apptrip.jar"]

