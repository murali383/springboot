FROM java:8

VOLUME /tmp
ARG JAR_FILE
EXPOSE 8080
ADD ${JAR_FILE} app.jar

ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "-jar","/app.jar"]