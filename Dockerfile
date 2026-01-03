FROM maven:3.9.6-eclipse-temurin-17

WORKDIR /app

# Copy pom.xml first to leverage Docker layer caching
COPY pom.xml .
RUN mvn dependency:go-offline

# Copy your source code
COPY src ./src

# Copy the test-config.yml into the container
COPY test-config.yml ./test-config.yml

# Run tests by default
CMD ["mvn", "test"]
