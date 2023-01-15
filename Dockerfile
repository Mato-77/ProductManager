
FROM maven:3.8.1-openjdk-17-slim
COPY src /backend/src
COPY pom.xml /backend
RUN mvn -f /backend/pom.xml clean package



FROM openjdk:17-slim
COPY --from=build /backend/target/product-store-0.0.1-SNAPSHOT.jar product-store.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/product-store.jar"]






