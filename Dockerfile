
# Start with a base image containing Java runtime
FROM adoptopenjdk/openjdk11

# Add Maintainer Info
LABEL maintainer="rinkesh.k.mehar@gmail.com"

# Add a volume pointing to /tmp
VOLUME /tmp

# Make port 8080 available to the world outside this container
#EXPOSE 8082

# The application's jar file
ARG JAR_FILE=/target/kafka-producer-v1.0.jar

# Add the application's jar to the container
ADD ${JAR_FILE} kafka-producer-v1.0.jar

# Run the jar file
ENTRYPOINT ["java","-jar","kafka-producer-v1.0.jar"]