# ---------- Build Stage ----------
FROM maven:3.9.6-eclipse-temurin-17 AS builder

WORKDIR /app

# Copy only pom first (IMPORTANT)
COPY pom.xml .

# Download dependencies first (cached layer)
RUN mvn dependency:go-offline

# Then copy source
COPY src ./src

# Build
RUN mvn clean package -DskipTests


# ---------- Run Stage ----------
FROM eclipse-temurin:17-jdk

WORKDIR /app

COPY --from=builder /app/target/*.jar app.jar

ENTRYPOINT ["java","-jar","app.jar"]