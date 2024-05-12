# Utiliser une image JDK 17
FROM amazoncorretto:17-alpine-jdk

ARG JAR_FILE=target/*.jar

# Copier le JAR de l'application dans le conteneur
COPY ${JAR_FILE} back-skeleton.jar

# Commande pour exécuter l'application Spring Boot
ENTRYPOINT ["java", "-jar", "/back-skeleton.jar"]

# Exposer le port 8081
EXPOSE 8081

#A executer dans le terminal

#Build the image
#docker build -t priscafokou/back-skeleton:0.0.1 .

#Run the image
# docker run -p 8081:8081 priscafokou/back-skeleton:0.0.1

#Push the image
# docker push priscafokou/back-skeleton:0.0.1