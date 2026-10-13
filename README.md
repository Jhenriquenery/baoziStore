# Baozi Store – API REST

API REST simples para controle de **clientes**, **produtos** e **pedidos** da Baozi Store (pão chinês).

**Tecnologias:** Java 17, Spring Boot 3, Spring Data JPA, H2 (padrão) ou MySQL, Postman.

## Estrutura

```
src/main/java/br/com/baozi
├── BaoziStoreApplication.java
├── model        -> Cliente, Produto, Pedido (entidades JPA)
├── repository   -> ClienteRepository, ProdutoRepository, PedidoRepository
└── controller   -> ClienteController, ProdutoController, PedidoController
```

## Como rodar

Requisitos: JDK 17+ e Maven (ou use a sua IDE: IntelliJ / Eclipse / VS Code).

```bash
mvn spring-boot:run
```

A API sobe em `http://localhost:8080`.

Por padrão usa o banco **H2 em memória** (não precisa instalar nada). O console do banco fica em
`http://localhost:8080/h2-console` (JDBC URL: `jdbc:h2:mem:baozidb`, usuário `sa`, senha em branco).

Para usar **MySQL**, edite `src/main/resources/application.properties`
(instruções dentro do próprio arquivo).

## Endpoints

| Método | URL | Descrição |
|--------|-----|-----------|
| POST   | `/clientes`      | Cadastrar cliente |
| GET    | `/clientes`      | Listar todos os clientes |
| GET    | `/clientes/{id}` | Consultar cliente por ID |
| PUT    | `/clientes/{id}` | Atualizar cliente (opcional) |
| DELETE | `/clientes/{id}` | Apagar cliente |
| POST   | `/produtos`      | Cadastrar produto |
| GET    | `/produtos`      | Listar todos os produtos |
| GET    | `/produtos/{id}` | Consultar produto por ID |
| PUT    | `/produtos/{id}` | Atualizar produto (opcional) |
| DELETE | `/produtos/{id}` | Apagar produto |
| POST   | `/pedidos`       | Registrar pedido |
| GET    | `/pedidos`       | Listar todos os pedidos |
| GET    | `/pedidos/{id}`  | Consultar pedido por ID |
| PUT    | `/pedidos/{id}`  | Atualizar pedido (opcional) |
| DELETE | `/pedidos/{id}`  | Apagar pedido |

## Exemplos de JSON

**POST /clientes**
```json
{ "nome": "SEUNOME123456", "clienteDesde": "2026-10-12" }
```

**POST /produtos**
```json
{ "nome": "Baozi de Carne", "preco": 8.50, "estoque": true }
```

**POST /pedidos**
```json
{ "clienteId": 1, "produtoId": 1, "quantidade": 3 }
```
