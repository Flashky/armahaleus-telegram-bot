FROM openjdk:8-jre-alpine

# copy application JAR
COPY target/armahaleus-telegram-bot-*.jar /app/armahaleus-telegram-bot.jar

# specify default command
CMD ["java", "-jar", "/app/armahaleus-telegram-bot"]