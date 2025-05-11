Nexdom - Desafio Fullstack

Backend: Spring Boot (Java 17)

Frontend: Quasar Framework (Vue 3)

Banco de Dados: H2 (em memória)

📁 Estrutura do Projeto

nexdom/
├── nexdom-backend/      # Backend em Spring Boot
├── nexdom-frontend/     # Frontend em Quasar
├── docker-compose.yml   # Orquestração dos containers

🚀 Como Executar

Pré-requisitos

Docker e Docker Compose

Execução com Docker

# Clone o repositório
git clone https://github.com/seu-usuario/nexdom.git
cd nexdom

# Construa os containers
docker-compose build

# Suba os serviços
docker-compose up

Backend: http://localhost:8080

Frontend: http://localhost:9000

Execução Manual (sem Docker)

Backend

cd nexdom-backend
./mvnw spring-boot:run

Frontend

cd nexdom-frontend
yarn install
quasar dev