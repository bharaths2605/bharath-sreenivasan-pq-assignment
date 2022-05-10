FROM adoptopenjdk/openjdk11:latest
ADD target/Paycoinz-Challenge.jar Paycoinz-Challenge.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "Paycoinz-Challenge.jar"]