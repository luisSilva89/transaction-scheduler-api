FROM eclipse-temurin:17
COPY  target/transaction-scheduler-app-0.0.1.jar app.jar
ENTRYPOINT ["sh", "-c", "java -jar /app.jar"]