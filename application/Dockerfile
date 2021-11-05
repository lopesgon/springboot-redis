FROM adoptopenjdk/maven-openjdk11 AS build

# RUN mkdir /app 
WORKDIR /app

COPY . .
RUN mvn clean install

