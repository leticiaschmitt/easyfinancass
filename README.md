# Easy Finanças – Sistema de Controle Financeiro Pessoal

O **Easy Finanças** é uma aplicação web desenvolvida para facilitar o controle de finanças pessoais, permitindo gerenciar **contas**, **categorias** e **movimentações** (entradas, custos e despesas).  
O sistema foi desenvolvido como projeto integrador da FIAP, utilizando **Java + Spring Boot** no backend, **Oracle Database (instância FIAP)** para persistência e **React + Vite** no frontend.

---

## Estrutura Geral do Projeto

```

easyfinancas/
├── easyfinancas-api        → Backend (Java + Spring Boot + Oracle)
└── easyfinancas-frontend   → Frontend (React + Vite)

````

---

## Tecnologias Utilizadas

### 🔹 Backend
- **Java 17**
- **Spring Boot**
- **Spring Data JPA**
- **Oracle Database (instância da FIAP)**
- **Maven**

### 🔹 Frontend
- **React**
- **Vite**
- **React Router DOM**
- **Axios**
- **Recharts**

---

## Backend (API REST)

### Estrutura de Camadas
O backend foi desenvolvido seguindo o padrão **MVC** e dividido em:

- **Model (Entidades)** → Representa as tabelas do banco de dados  
  - `Conta`
  - `Categoria`
  - `Movimentacao`
- **Repository** → Interfaces estendendo `JpaRepository` para cada entidade  
- **Service** → Contém as regras de negócio e validações  
- **Controller** → Exposição dos endpoints REST (`GET`, `POST`, `PUT`, `DELETE`)

---

### Entidades Principais

#### Conta
Representa uma conta bancária ou carteira do usuário.
```java
Conta {
  id: Long,
  nome: String,
  saldo: BigDecimal
}
````

#### Categoria

Classifica as movimentações em tipos (Entrada, Custo ou Despesa).

```java
Categoria {
  id: Long,
  nome: String,
  corHex: String,
  tipo: String, // ENTRADA | CUSTO | DESPESA
  ativa: Boolean
}
```

#### Movimentação

Registra cada transação financeira do sistema.

```java
Movimentacao {
  id: Long,
  tipo: String, // ENTRADA | CUSTO | DESPESA
  valor: BigDecimal,
  data: LocalDate,
  descricao: String,
  contaId: Long,
  categoriaId: Long
}
```

---

### Regras de Negócio

* Atualização automática do **saldo da conta** após movimentações.
* Impede duplicidade de categorias (mesmo nome e tipo).
* Validações completas de existência de conta e categoria antes de inserir movimentação.
* Códigos de status HTTP implementados corretamente:

  * `200 OK` – Sucesso
  * `201 Created` – Criação
  * `204 No Content` – Exclusão
  * `400 Bad Request` – Erros de validação

---

### Como Executar o Backend

#### 1️⃣ Configurar ambiente Oracle

* Criar as tabelas na instância Oracle FIAP conforme o modelo abaixo:

  * `TB_CONTA`
  * `TB_CATEGORIA`
  * `TB_MOVIMENTACAO`
* Confirmar conexão Oracle via credenciais da FIAP.

#### 2️⃣ Executar aplicação

```bash
cd easyfinancas-api
export SPRING_PROFILES_ACTIVE=fiap
mvn spring-boot:run
```

#### 3️⃣ URL base da API

```
http://localhost:8080
```

---

### Endpoints Implementados

#### 🔸 Contas

| Método   | Endpoint           | Descrição                     |
| -------- | ------------------ | ----------------------------- |
| `GET`    | `/api/contas`      | Lista todas as contas         |
| `GET`    | `/api/contas/{id}` | Consulta uma conta específica |
| `POST`   | `/api/contas`      | Cria nova conta               |
| `PUT`    | `/api/contas/{id}` | Atualiza uma conta existente  |
| `DELETE` | `/api/contas/{id}` | Exclui uma conta              |

#### 🔸 Categorias

| Método | Endpoint               | Descrição                    |
| ------ | ---------------------- | ---------------------------- |
| `GET`  | `/api/categorias`      | Lista categorias existentes  |
| `POST` | `/api/categorias`      | Cria uma nova categoria      |
| `PUT`  | `/api/categorias/{id}` | Atualiza categoria existente |

#### 🔸 Movimentações

| Método   | Endpoint                        | Descrição                     |
| -------- | ------------------------------- | ----------------------------- |
| `GET`    | `/api/movimentacoes`            | Lista todas as movimentações  |
| `GET`    | `/api/movimentacoes/conta/{id}` | Lista movimentações por conta |
| `POST`   | `/api/movimentacoes`            | Cria nova movimentação        |
| `DELETE` | `/api/movimentacoes/{id}`       | Exclui uma movimentação       |

---

## Exemplo de Teste via cURL

```bash
# Criar conta
curl -X POST http://localhost:8080/api/contas \
  -H "Content-Type: application/json" \
  -d '{"nome":"Conta Corrente 2","saldoInicial":2000.00}'

# Criar categoria
curl -X POST http://localhost:8080/api/categorias \
  -H "Content-Type: application/json" \
  -d '{"nome":"Aluguel","corHex":"#00BFFF","tipo":"DESPESA"}'

# Criar movimentação
curl -X POST http://localhost:8080/api/movimentacoes \
  -H "Content-Type: application/json" \
  -d '{
    "tipo":"DESPESA",
    "valor":120.50,
    "data":"2025-11-06",
    "descricao":"Conta de luz",
    "contaId":62,
    "categoriaId":1
  }'
```

✅ Todos os testes retornaram status **201 Created** ou **200 OK**, confirmando integração entre API e Oracle.

---

## Banco de Dados (Oracle)

### Modelo Físico

| Tabela              | Campos                                                                      |
| ------------------- | --------------------------------------------------------------------------- |
| **TB_CONTA**        | ID_CONTA (PK), NM_CONTA, SALDO                                              |
| **TB_CATEGORIA**    | ID_CATEGORIA (PK), NM_CATEGORIA, COR_HEX, TIPO, ATIVA                       |
| **TB_MOVIMENTACAO** | ID_MOV (PK), TIPO, VALOR, DATA, DESCRICAO, ID_CONTA (FK), ID_CATEGORIA (FK) |

### Relacionamentos

* **TB_CONTA (1)** → **(N) TB_MOVIMENTACAO**
* **TB_CATEGORIA (1)** → **(N) TB_MOVIMENTACAO**

---

## Frontend (React + Vite)

### 📁 Estrutura

* **Páginas:**

  * `Login`
  * `Dashboard`
  * `Contas`
  * `Categorias`
  * `Movimentações`

* **Componentes:**

  * `Header`, `Card`, `Formulário`, `Lista`, `Gráfico (Recharts)`

* **Rotas:** configuradas com `React Router DOM`

* **Comunicação com API:** via `Axios` (`http://localhost:8080/api`)

---

### Como Executar o Frontend

```bash
cd easyfinancas-frontend
npm install
npm run dev
```

Acesse em:

```
http://localhost:5173
```

---

### Login (Simulado)

A autenticação é apenas **simulada** (sem backend real de login).
Ao preencher qualquer e-mail e senha, é gerado um token fictício no `localStorage` (`ef_token`).

#### Para testar:

1. Vá até `/login`
2. Informe qualquer e-mail e senha
3. Clique em **Entrar**
4. O sistema redirecionará para o **Dashboard**

Para deslogar:

```js
localStorage.removeItem('ef_token');
location.reload();
```

---

## Funcionalidades Implementadas

| Funcionalidade                      | Status |
| ----------------------------------- | ------ |
| CRUD de Contas                      | ✅      |
| CRUD de Categorias                  | ✅      |
| CRUD de Movimentações               | ✅      |
| Dashboard com saldo total e gráfico | ✅      |
| Histórico de transações             | ✅      |
| Página de Login (simulada)          | ✅      |
| Conexão com Oracle FIAP             | ✅      |

---

## Requisitos Atendidos

### Backend

* [x] Entidades Modeladas
* [x] Repository JPA
* [x] Camada Service com regras de negócio
* [x] Controllers com métodos GET, POST, PUT, DELETE
* [x] Códigos de status HTTP corretos
* [x] Banco Oracle FIAP conectado
* [x] Três entidades completas: Conta, Categoria, Movimentação

### Frontend

* [x] Componentização
* [x] Rotas SPA (`React Router DOM`)
* [x] Uso de Hooks (`useState`, `useEffect`)
* [x] Página de Login
* [x] Dashboard e páginas de CRUD
* [x] Conexão API REST com Backend

---

## Testes Realizados

* **Testes de API:** via `cURL` e Postman
* **Testes de integração:** Oracle + Spring Boot
* **Testes de UI:** inserção e exclusão via frontend
* **Dashboard dinâmico:** atualização automática do saldo

---

## Conclusão

O projeto **Easy Finanças** cumpre integralmente os requisitos técnicos exigidos pela FIAP para integração entre **Java + Spring Boot + Oracle + React**.
Todas as camadas foram implementadas com boas práticas, garantindo a comunicação ponta a ponta entre backend, banco de dados e frontend.

---

## Autora

**Letícia Schmitt Rocha**

📍 Analista de Integrações | Desenvolvedora Full Stack em formação

🎓 FIAP – Análise e Desenvolvimento de Sistemas

📧 [leticiaschmitt304@gmail.com](mailto:leticiaschmitt304@gmail.com)

---