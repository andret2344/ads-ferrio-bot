FROM amazoncorretto:25-alpine-jdk

LABEL org.opencontainers.image.title="ads-ferrio-bot"
LABEL org.opencontainers.image.authors="Andret2344"
LABEL org.opencontainers.image.description="Discord bot 'ads-ferrio-bot' written in Java 25, packaged as a runnable JAR and intended to run inside a container."
LABEL org.opencontainers.image.url="https://github.com/Andret2344/ads-ferrio-bot"
LABEL org.opencontainers.image.source="https://github.com/Andret2344/ads-ferrio-bot"
LABEL org.opencontainers.image.licenses="CC-BY-SA 4.0"


COPY build/libs/ads-ferrio-bot.jar ferrio.jar
ENTRYPOINT ["java", "-jar", "/ferrio.jar"]
