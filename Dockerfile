FROM openjdk:10-jre

VOLUME /tmp
RUN mkdir -p /app
COPY textlines-server/build/libs/*.jar /app


ENTRYPOINT java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /app/*.jar
