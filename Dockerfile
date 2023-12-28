FROM gradle:8.5.0-jdk17 AS BUILD

WORKDIR .

COPY ./build.gradle ./build.gradle
COPY ./gradlew ./gradlew
COPY ./settings.gradle ./settings.gradle
COPY ./gradle ./gradle
RUN gradle build --no-daemon || return 0

COPY ./src ./src
RUN gradle build --no-daemon


# Path: Dockerfile
FROM openjdk:17-jdk-alpine

WORKDIR .

ARG PROFILE

ARG PORT=8080

ENV PROFILE_ENV=${PROFILE}

RUN echo "--spring.profiles.active=${PROFILE}"

COPY --from=BUILD /home/gradle/build/libs/*.jar app.jar

EXPOSE ${PORT}

ENTRYPOINT ["java", "-jar", "app.jar", "--spring.profiles.active=${PROFILE_ENV}"]