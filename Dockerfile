FROM gcr.io/distroless/java17-debian12

COPY build/libs/url-shortner-backend*.jar /app/url-shortner-backend.jar

ENTRYPOINT ["java",  "-XX:InitialRAMPercentage=25", "-XX:MaxRAMPercentage=75", "-jar", "/app/url-shortner-backend.jar"]

EXPOSE 8080
