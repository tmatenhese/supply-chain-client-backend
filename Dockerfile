FROM openjdk:8-jdk-alpine
RUN addgroup -S spring && adduser -S spring -G spring
USER spring:spring
WORKDIR /opt/supply-chain/
COPY target/original-supply-chain-0.0.1-SNAPSHOT.jar /opt/supply-chain/supply-chain-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/opt/supply-chain/supply-chain-0.0.1-SNAPSHOT.jar"]
