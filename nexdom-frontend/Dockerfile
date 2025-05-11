# Usar a imagem base do Node.js
FROM node:18

# Definir o diretório de trabalho dentro do container
WORKDIR /app

# Copiar os arquivos do seu projeto para o diretório de trabalho no container
COPY . /app

# Instalar as dependências do Quasar (e outras dependências do Node.js)
RUN npm install

# Expor a porta do Quasar (geralmente é 8080 para dev)
EXPOSE 8080

# Comando para rodar o Quasar em modo de desenvolvimento
CMD ["npm", "run", "dev"]