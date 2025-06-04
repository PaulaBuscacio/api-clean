# Pacote mapper

## O pacote mapper é responsável por converter objetos entre diferentes camadas da aplicação. 
### Neste pacote ficam as interfaces de mapper que serão implementadas no pacote infrastructure.mapper.

## Exemplo de mapper:

```java
public interface ExampleMapperInterface {

    ExampleEntity toEntity(ExampleRequestDto dto);

    ExampleResponseDto toResponseDto(ExampleEntity entity);
}
```