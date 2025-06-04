# Arquitetura Limpa

### A Arquitetura Limpa (Clean Architecture) é um padrão arquitetural proposto por Robert C. Martin (também chamado Uncle Bob) pela primeira vez em seu blog em 2012, cujo post pode ser lido em:
https://blog.cleancoder.com/uncle-bob/2012/08/13/the-clean-architecture.html

#### Este modelo se baseia em algumas regras fundamentais:

#### Princípios Básicos
- Independência de frameworks
- Testability
- Independência de interface de usuário
- Independência de banco de dados
- Independência de qualquer agente externo

#### Organização em camadas
O desenvolvimento deve seguir o fluxo da mais interna a mais externa, respectivamente:
- Domain (Entidades)
- Use Cases (Casos de Uso)
- Interface Adapters (Adaptadores de Interface) com Controllers (com as rotas da aplicação), Presenters (responsável pelo formato em que os dados serão apresentados - xml ou json por exemplo) ou Gateways (com interfaces para acesso a recursos externos)
- Infrastructure (Infraestrutura) com frameworks, drivers, configurações, bancos de dados, serviços externos, dispositivos e interface de usuário.

#### Regra de Dependência
- As dependências de código-fonte só podem apontar para dentro. Por exemplo: controller -> usecase -> domain
- Camadas internas não conhecem nada sobre as camadas externas
- Comunicação entre camadas por meio de contratos usando interfaces (inversão de dependência)