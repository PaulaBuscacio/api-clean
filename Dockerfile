# Usa imagem com Java 17 e Maven já instalados
FROM maven:3.9.6-eclipse-temurin-17

WORKDIR /app

# Copia todo o projeto para dentro do container
COPY . .

# Garante que a pasta resources estará acessível no runtime
COPY src/main/resources/archetype /root/.m2/repository

# Gera o JAR da aplicação (opcional, se quiser buildar na imagem)
RUN mvn clean package

# Expõe a porta padrão do Spring Boot
EXPOSE 8080

# Inicia a aplicação
ENTRYPOINT ["java", "-jar", "target/api-clean-1.0-SNAPSHOT.jar"]