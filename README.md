CRUD de Produtos — Testes Unitários com Spring Boot, JUnit e Mockito

Este projeto demonstra como implementar testes unitários e de integração para uma aplicação CRUD de produtos desenvolvida em Spring Boot.  
Foram testadas as camadas de serviço e de controle, seguindo boas práticas com Mockito e MockMvc.

Tecnologias utilizadas
Java 17+
Spring Boot 3+
JUnit 5
Mockito
MockMvc
Maven

Estrutura do projeto

crud-produtos-teste/
├── src/main/java/br/com/anm/produtos/crud_produtos/
│ ├── modelo/ProdutoModelo.java
│ ├── repositorio/ProdutoRepositorio.java
│ ├── servico/ProdutoServico.java
│ └── controle/ProdutoControle.java
└── src/test/java/br/com/anm/produtos/crud_produtos/
├── servico/ProdutoServicoTest.java
└── controle/ProdutoControleTest.java

Testes criados

Camada de Serviço (`ProdutoServicoTest`)
| Método | Cenário Testado | Resultado Esperado |
|--------|------------------|--------------------|
| `cadastrarAlterar()` | Cadastro válido | `201 CREATED` |
| `cadastrarAlterar()` | Nome vazio | `400 BAD REQUEST` |
| `cadastrarAlterar()` | Marca vazia | `400 BAD REQUEST` |
| `cadastrarAlterar()` | Alteração válida | `200 OK` |
| `remover()` | Remoção com sucesso | `200 OK` |
| `remover()` | Produto inexistente | `404 NOT FOUND` |

---

Camada de Controle (`ProdutoControleTest`)
| Endpoint | Descrição | Status Esperado |
|-----------|------------|----------------|
| `GET /listar` | Lista todos os produtos | `200 OK` |
| `POST /cadastrar` | Cadastra um novo produto | `201 CREATED` |
| `PUT /alterar` | Altera produto existente | `200 OK` |
| `DELETE /remover/{codigo}` | Remove produto existente | `200 OK` |
| `DELETE /remover/{codigo}` | Tenta remover produto inexistente | `404 NOT FOUND` |

---

Como executar os testes

*Clonar o repositório
```bash
git clone https://github.com/Luks-art/crud-produtos-teste.git
cd crud-produtos-teste

*Executar os testes via Maven
mvn test

*Ver resultado dos testes
Tests run: 8, Failures: 0, Errors: 0, Skipped: 0
BUILD SUCCESS

*Conceitos aplicados

Testes unitários com JUnit 5 e Mockito

Isolamento de dependências com mocks

Testes de Controller com MockMvc

Estrutura Arrange → Act → Assert (AAA)

Cobertura de código dos principais fluxos do CRUD

*Referências

Spring Boot Testing Documentation

Mockito Docs

JUnit 5


*Licença

Este projeto é distribuído sob a licença MIT.
Sinta-se à vontade para usar, estudar e modificar.

*Ver no GitHub
