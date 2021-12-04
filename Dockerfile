
# Start with a base image containing Java runtime
FROM meisterplan/jdk-base:11

# Add Maintainer Info
LABEL maintainer="rinkesh.k.mehar@gmail.com"

# Add a volume pointing to /tmp
VOLUME /temp

# The application's jar file
ARG JAR_FILE=target/kafka-producer-v1.0.jar

# Add the application's jar to the container
ADD ${JAR_FILE} kafka-producer-v1.0.jar

# Run the jar file
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom", "-jar", "kafka-producer-v1.0.jar"]