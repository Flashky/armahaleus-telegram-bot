FROM openjdk:8-jre-alpine

# copy application JAR
COPY target/armahaleus-telegram-bot-*.jar /app/armahaleus-telegram-bot.jar

ENV bot.username ""
ENV bot.token ""
ENV bot.creatorId "0"

# specify default command
CMD ["java", "-jar", "/app/armahaleus-telegram-bot.jar"]