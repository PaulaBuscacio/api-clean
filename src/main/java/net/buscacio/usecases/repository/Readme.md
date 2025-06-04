# Pacote repository

## Neste pacote são definidas as interfaces dos repositórios responsáveis por acessar os dados. 
### Elas funcionam como gateways para a camada de infraestrutura, permitindo que a camada de usecase acesse os dados sem depender diretamente de implementações específicas.

#### Exemplos de repositórios:

```java
public interface ExampleRepository {
    ExampleEntity save(ExampleEntity entity);
    Optional<ExampleEntity> findById(Long id);
    List<ExampleEntity> findAll();
    void deleteById(Long id);
}
```