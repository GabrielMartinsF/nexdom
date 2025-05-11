# Nexdom - Desafio Fullstack

## 🛠️ Tecnologias Utilizadas

- **Backend:** Spring Boot (Java 17)
- **Frontend:** Quasar Framework (Vue 3)
- **Banco de Dados:** H2 (em memória)
- **Containerização:** Docker e Docker Compose

---

## 📁 Estrutura do Projeto

```
nexdom/
├── nexdom-backend/     # Backend em Spring Boot
├── nexdom-frontend/    # Frontend em Quasar
└── docker-compose.yml  # Orquestração dos containers
```

---

## 🚀 Como Executar o Projeto

### ✅ Pré-requisitos

- [Docker](https://www.docker.com/)
- [Docker Compose](https://docs.docker.com/compose/)

---

### ▶️ Execução com Docker

1. **Clone o repositório:**

```bash
git clone https://github.com/seu-usuario/nexdom.git
cd nexdom
```

2. **Construa os containers:**

```bash
docker-compose build
```

3. **Suba os serviços:**

```bash
docker-compose up
```

- Backend disponível em: [http://localhost:8080](http://localhost:8080)
- Frontend disponível em: [http://localhost:9000](http://localhost:9000)

---

### 🔧 Execução Manual (sem Docker)

#### Backend

```bash
cd nexdom-backend
./mvnw spring-boot:run
```

#### Frontend

```bash
cd nexdom-frontend
yarn install
quasar dev
```