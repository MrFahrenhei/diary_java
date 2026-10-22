FROM gradle:9.0.0-jdk21-alpine AS builder
WORKDIR /build

COPY build.gradle settings.gradle /build/
RUN gradle dependencies --no-daemon

COPY src /build/src
RUN gradle clean build -x test --no-daemon

FROM eclipse-temurin:21-jre-alpine
WORKDIR /app

RUN addgroup -S system_group && adduser -S system_user -G system_group
USER system_user:system_group

COPY --from=builder /build/build/libs/*.jar /app/app.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app/app.jar"]