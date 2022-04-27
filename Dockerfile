FROM maven:3.8.5-openjdk-17-slim AS build
COPY src /home/app/src
COPY pom.xml /home/app
RUN mvn -f /home/app/pom.xml clean package -DskipTests

FROM openjdk:17-jdk-slim
COPY --from=build /home/app/target/*.jar /usr/local/lib/booking-room.jar
EXPOSE 3000
ENTRYPOINT ["java", "-jar", "/usr/local/lib/booking-room.jar"]