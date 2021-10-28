From java:8
EXPOSE 8082
ADD /target/jobrecrutement.jar jobrecrutement.jar
ENTRYPOINT ["java", "-jar", "/jobrecrutement.jar"]