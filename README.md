
# Nexdom - Desafio Fullstack

## Backend: Spring Boot (Java 17)
## Frontend: Quasar Framework (Vue 3)
## Banco de Dados: H2 (em memória)

---

## 📁 Estrutura do Projeto

```
nexdom/
├── nexdom-backend/     # Backend em Spring Boot
├── nexdom-frontend/    # Frontend em Quasar
├── docker-compose.yml  # Orquestração dos containers
```

---

## 🚀 Como Executar

### Pré-requisitos

- Docker e Docker Compose

### Execução com Docker

1. Clone o repositório
```bash
git clone https://github.com/seu-usuario/nexdom.git
cd nexdom
```

2. Construa os containers
```bash
docker-compose build
```

3. Suba os serviços
```bash
docker-compose up
```

- **Backend**: [http://localhost:8080](http://localhost:8080)
- **Frontend**: [http://localhost:9000](http://localhost:9000)

### Execução Manual (sem Docker)

#### Backend

1. Acesse a pasta do backend
```bash
cd nexdom-backend
```

2. Rode o backend com o comando Maven
```bash
./mvnw spring-boot:run
```

#### Frontend

1. Acesse a pasta do frontend
```bash
cd nexdom-frontend
```

2. Instale as dependências
```bash
yarn install
```

3. Rode o frontend
```bash
quasar dev
```

---

## 🌐 Explicando o Projeto

### Backend
O backend foi desenvolvido com Spring Boot e inclui a integração com Swagger. Para visualizar todos os endpoints da API, basta acessar: [http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html).

### Frontend
O frontend foi desenvolvido com Vue 3 utilizando o Quasar Framework. Ele possui três páginas principais:

1. **Produto**: Permite cadastrar, visualizar, editar e deletar produtos. Também é possível filtrar produtos por tipo e visualizar as saídas de cada produto.

2. **Movimentações**: Permite cadastrar e visualizar as movimentações de estoque de produtos.

3. **Dashboard**: Permite filtrar as movimentações de produtos e visualizar o lucro obtido com as transações realizadas.

---

## 📦 Tecnologias Usadas

- **Backend**: 
    - Spring Boot
    - Swagger para documentação da API
    - H2 para banco de dados em memória

- **Frontend**: 
    - Vue 3
    - Quasar Framework para a interface
    - Yarn para gerenciamento de dependências

---