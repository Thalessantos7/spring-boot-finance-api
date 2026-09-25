# 💰 Spring Boot Finance API

Uma API RESTful desenvolvida em Java com Spring Boot para gestão de finanças pessoais. Este projeto foi criado com o foco em aplicar boas práticas de desenvolvimento backend, modelagem de banco de dados relacional e arquitetura de software em camadas.

## 🚀 Tecnologias e Ferramentas

* **Java 21**
* **Spring Boot** (Web, Data JPA, Validation)
* **PostgreSQL** (Banco de dados relacional)
* **Lombok** (Redução de código boilerplate)
* **Maven** (Gerenciamento de dependências)
* **Postman** (Testes de API)

## 📂 Estrutura do Projeto

A aplicação foi desenvolvida seguindo o padrão de arquitetura em camadas, garantindo a separação de responsabilidades e facilitando a manutenção e testes:

```text
src/main/java/com/thales/gestor_financas
├── controller/         # Porta de entrada da API (Endpoints REST)
│   └── TransacaoController.java
├── service/            # Regras de negócio da aplicação
│   └── TransacaoService.java
├── repository/         # Comunicação e consultas ao banco de dados (Spring Data JPA)
│   └── TransacaoRepository.java
└── model/              # Entidades mapeadas para o banco de dados
    ├── Transacao.java
    └── TipoTransacao.java (Enum)
```

## ⚙️ Funcionalidades

**Implementadas:**

* [x] Cadastro de nova transação (Receita/Despesa)
* [x] Listagem de todas as transações
* [x] Busca de transação por ID
* [x] Exclusão de transação

**Próximos passos (Em desenvolvimento):**

* [ ] Validação de dados de entrada (Bean Validation)
* [ ] Cálculo de saldo total (Receitas - Despesas)
* [ ] Autenticação e Segurança (Spring Security + JWT)
* [ ] Testes Automatizados (JUnit e Mockito)

## 🛠️ Como rodar o projeto localmente

### Pré-requisitos

* Java 17 ou superior instalado.
* PostgreSQL instalado e rodando.
* Maven instalado.

### Passo a passo

1. **Clone o repositório:**
   ```bash
   git clone https://github.com/Thalessantos7/spring-boot-finance-api
   ```

2. **Configure o Banco de Dados:**
   Abra o seu SGBD e crie um banco de dados chamado `financas_db`:
   ```sql
   CREATE DATABASE financas_db;
   ```

3. **Ajuste as credenciais:**
   No arquivo `src/main/resources/application.yml`, altere a senha e/ou o usuário do banco de dados para corresponder à sua instalação local do PostgreSQL:
   ```yaml
   spring:
     datasource:
       username: postgres
       password: sua_senha_aqui
   ```

4. **Inicie a aplicação:**
   Na raiz do projeto, execute o comando:
   ```bash
   ./mvnw spring-boot:run
   ```

A API estará disponível em `http://localhost:8080/api/transacoes`.

## 📌 Endpoints Principais

| Método | Rota | Descrição |
| ------ | ---- | --------- |
| `POST` | `/api/transacoes` | Cria uma nova transação |
| `GET` | `/api/transacoes` | Retorna todas as transações |
| `GET` | `/api/transacoes/{id}` | Retorna uma transação específica |
| `DELETE` | `/api/transacoes/{id}` | Deleta uma transação |

---
*Desenvolvido como projeto de portfólio para vagas de estágio em Backend.*