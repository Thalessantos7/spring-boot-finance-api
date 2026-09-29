# 💰 Spring Boot Finance API

Uma API RESTful desenvolvida em Java com Spring Boot para gestão de finanças pessoais. Este projeto foi criado com o foco em aplicar boas práticas de desenvolvimento backend, modelagem de banco de dados relacional e arquitetura de software em camadas.

## 🚀 Tecnologias e Ferramentas

* **Java 21**
* **Spring Boot** (Web, Data JPA, Validation)
* **Spring Security** (Autenticação Stateless e filtros de segurança)
* **JWT (Auth0)** (Geração e validação de tokens de acesso)
* **BCrypt** (Hashing seguro de senhas)
* **PostgreSQL** (Banco de dados relacional)
* **Lombok** (Redução de código boilerplate)
* **Maven** (Gerenciamento de dependências)
* **Postman** (Testes de API)

## 📂 Estrutura do Projeto

A aplicação foi desenvolvida seguindo o padrão de arquitetura em camadas, garantindo a separação de responsabilidades e facilitando a manutenção e testes:

```text
src/main/java/com/thales/gestor_financas
├── controller/         # Portas de entrada da API (Endpoints REST protegidos e públicos)
├── service/            # Regras de negócio da aplicação
├── repository/         # Comunicação e consultas ao banco de dados (Spring Data JPA)
├── entity/             # Entidades mapeadas para o banco de dados
├── dto/                # Objetos de Transferência de Dados (Request e Response DTOs)
├── enums/              # Constantes estritas e seguras da aplicação
└── security/           # Configurações de segurança, filtros JWT e serviços de token
```

## ⚙️ Funcionalidades

**Implementadas:**

* [x] **Autenticação e Segurança:** Registo de utilizadores, login seguro com geração de Token JWT e rotas protegidas por filtros.
* [x] **Criptografia:** Segurança de senhas através de `BCryptPasswordEncoder`.
* [x] **Gestão de Categorias:** Criação e listagem de categorias associadas a utilizadores com validação de dados (`Bean Validation`).
* [x] **Padrão DTO:** Separação clara entre os dados recebidos nas requisições e as respostas devolvidas pela API (protegendo dados sensíveis).
* [x] **Gestão de Transações:** Registo de receitas e despesas, listagem, busca por ID e exclusão.
* [x] **Resumo Financeiro:** Cálculo dinâmico do saldo total (Receitas - Despesas) através do endpoint `/api/transacoes/resumo`.
* [x] **Testes Automatizados:** Cobertura de testes unitários para regras de negócio (camada de Service) utilizando JUnit e Mockito.

## 🛠️ Como rodar o projeto localmente

### Pré-requisitos

* Java 21 instalado.
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
| `GET` | `/api/transacoes/resumo` | Retorna o resumo financeiro (Receitas, Despesas e Saldo) |
| `POST` | `/api/usuarios/registrar` | Registra um novo usuário |
| `POST` | `/api/usuarios/login` | Efetua login e devolve o Token JWT |
| `POST` | `/api/categorias` | Cria uma nova categoria |
| `GET` | `/api/usuarios/usuario/{id}` | Lista categorias de um usuário |

---

*Desenvolvido como projeto de portfólio para vagas de estágio em Backend.*