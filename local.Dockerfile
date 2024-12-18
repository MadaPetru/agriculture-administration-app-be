FROM gradle:8.8-jdk17 AS build
WORKDIR /app
COPY . .
RUN gradle clean build -x test 

FROM amazoncorretto:17
COPY --from=build /app/agro-admin-app/build/libs/agro-admin-app-1.0.0.jar app.jar
EXPOSE 8080
CMD ["java", "-jar", "app.jar", "--spring.profiles.active=docker"]