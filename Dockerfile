FROM maven:3.9.0-eclipse-temurin AS build

WORKDIR /app

COPY pom.xml .
COPY src/ src/

RUN mvn clean package -DskipTests

FROM eclipse-temurin:21-jdk

WORKDIR /app

COPY --from=build /app/target/caju-*.jar app.jar

EXPOSE 8080

CMD ["java", "-jar", "app.jar"]
