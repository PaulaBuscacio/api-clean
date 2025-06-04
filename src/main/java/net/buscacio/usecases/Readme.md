# Pacote usecases

## Contém todos os casos de uso da aplicação, que são as regras de negócio que a aplicação deve cumprir. É a camada intermediária entre o domínio e as camadas externas app e infrastructure.

### Este pacote é destinado às interfaces correspondentes a cada caso de uso. 

### Exemplo de um caso de uso:

```java
public interface RequestExampleUseCase {
    ExampleEntity executeRequest(ExampleRequest request);
}
```