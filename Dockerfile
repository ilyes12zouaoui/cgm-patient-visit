# First Stage Build Project and Run Tests
FROM adoptopenjdk/maven-openjdk11 AS build

WORKDIR /app

COPY pom.xml .
COPY src ./src
RUN mvn clean package

# Second stage take the built artifacts and run the project
FROM registry.access.redhat.com/ubi8/openjdk-17:1.14

ENV LANGUAGE='en_US:en'
COPY --from=build --chown=185 /app/target/quarkus-app/lib/ /app/deployments/lib/
COPY --from=build --chown=185 /app/target/quarkus-app/*.jar /app/deployments/
COPY --from=build --chown=185 /app/target/quarkus-app/app/ /app/deployments/app/
COPY --from=build --chown=185 /app/target/quarkus-app/quarkus/ /app/deployments/quarkus/

EXPOSE 8080
USER 185
ENV JAVA_OPTS="-Dquarkus.http.host=0.0.0.0 -Djava.util.logging.manager=org.jboss.logmanager.LogManager"
ENV JAVA_APP_JAR="/app/deployments/quarkus-run.jar"