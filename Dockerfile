FROM openjdk:8
VOLUME /tmp
EXPOSE 9090
COPY store-runners-back-1.0.0.jar /store-runners-back-1.0.0.jar
ENTRYPOINT ["java","-jar","store-runners-back-1.0.0.jar"]