## Pacote Controller

Este pacote contém as classes responsáveis por gerenciar as requisições HTTP e interagir com a camada de usecase. 
Como este é um projeto baseado em SpringBoot, as classes dentro deste pacote são anotadas com `@RestController`
,para indicar que elas lidam com requisições REST, `@RequestMapping` para definir o caminho base das rotas e
outas anotações do Spring para mapear os métodos HTTP às ações correspondentes, como `@GetMapping`, `@PostMapping`, `@PutMapping` e `@DeleteMapping`, além
de `@RequestBody` para receber o corpo da requisição e `@PathVariable` para extrair variáveis do caminho da URL, por exemplo
```java
@RestController
@RequestMapping("/example")
public class ExampleController {
    private final CreateExampleUseCase createExampleUseCase;
    private final GetExampleUseCase getExampleUseCase;
    
    public ExampleController(CreateExampleUseCase createExampleUseCase, GetExampleUseCase getExampleUseCase) {
        this.createExampleUseCase = createExampleUseCase;
        this.getExampleUseCase = getExampleUseCase;
    }
    @GetMapping("/{id}")
    public ResponseEntity<ExampleDto> getExample(@PathVariable Long id) {
        // Lógica para obter o exemplo
    }

    @PostMapping
    public ResponseEntity<ExampleDto> createExample(@RequestBody ExampleDto exampleDto) {
        // Lógica para criar um novo exemplo
    }
}
```