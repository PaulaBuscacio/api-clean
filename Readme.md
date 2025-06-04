# Clean Archetype

### Este é um archetype maven para projetos java baseado em arquitetura limpa com o objetivo de facilitar o desenvolvimento e a compreensão deste padrão de arquitetura desenvolvedores.
#### Ele propõe uma estrutura de pastas e arquivos e em cada uma delas há um arquivo README.md explicando o que é esperado naquela pasta. É importante salientar que a organização proposta é apenas uma sugestão e pode ser adaptada conforme a necessidade do projeto.

### Estrutura de pastas

```
\---clean_archetype
    |   CleanArchetypeApplication.java
    |   Readme.md
    |
    +---app
    |   |   Readme.md
    |   |
    |   +---controller
    |   |       Readme.md
    |   |
    |   \---dto
    |           Readme.md
    |
    +---domain
    |   |   Readme.md
    |   |
    |   +---entity
    |   |       Readme.md
    |   |
    |   \---vo
    |           Readme.md
    |
    +---infrastructure
    |   |   Readme.md
    |   |
    |   +---configuration
    |   |       Readme.md
    |   |
    |   +---data
    |   |       Readme.md
    |   |
    |   +---mapper
    |   |       Readme.md
    |   |
    |   \---repositoryImpl
    |           Readme.md
    |
    \---usecases
        |   Readme.md
        |
        +---mapper
        |       Readme.md
        |
        +---repository
        |       Readme.md
        |
        \---usecaseImpl
                Readme.md
             
```

### Especificações
- Java 17
- Maven 3.9.9
- Spring Boot 3.4.5

### Como usar
Para instalar e rodar o projeto, você pode usar a sua ide favorita ou rodar os seguintes comandos no terminal:

```bash
mvn clean install
mvn spring-boot:run
```

