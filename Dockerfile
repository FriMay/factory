FROM openjdk:8
ADD build/libs/factory-1.0.jar factory-1.0.jar
EXPOSE 8085
ENTRYPOINT ["java", "-jar", "factory-1.0.jar"]