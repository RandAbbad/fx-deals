FROM openjdk:17
WORKDIR /fx-deals
COPY target/fx-deals.jar /fx-deals/
CMD ["java", "-jar", "fx-deals.jar"]