<template>
  <div class="movimentacao" :style="{ backgroundColor: tipo === 'SAIDA' ? '#FF5722' : '#4CAF50' }">
    <div class="header row justify-between">
      <p class="nome">{{ nome }}</p>
      <p class="data text-center">{{ formatarData(dataVenda) }}</p>
    </div>

    <div class="info row justify-between">
      <p class="preco">{{ precoFormatado(valor) }}</p>
    </div>

    <div class="info row justify-between">
      <p class="quantidade">Quantidade movimentada: {{ quantidade }}</p>
      <p class="quantidade">Quantidade disponível: {{ quantidadeDisponivel }}</p>
    </div>

    <div class="tipo">
      <p>{{ tipo }}</p>
    </div>
  </div>
</template>

<script>
import { defineComponent } from 'vue';

export default defineComponent({
  props: {
    id: Number,
    nome: String,
    valor: Number,
    quantidade: Number,
    quantidadeDisponivel: Number,
    dataVenda: String,
    tipo: String
  },
  methods: {
    precoFormatado(valor) {
      return new Intl.NumberFormat("pt-BR", {
        style: "currency",
        currency: "BRL"
      }).format(Number(valor));
    },
    formatarData(dataISO) {
      if (!dataISO) return '';
      const data = new Date(dataISO);
      return data.toLocaleDateString('pt-BR');
    }
  }
});
</script>

<style scoped>
.movimentacao {
  width: 100%;
  max-width: 48%;
  height: 180px;
  padding: 20px;
  margin: 10px;
  cursor: pointer;
  border-radius: 15px;
  box-shadow: 0 6px 18px rgba(0, 0, 0, 0.1);
  transition: transform 0.2s ease, box-shadow 0.3s ease;
  display: flex;
  flex-direction: column;
  justify-content: space-between;
}

.movimentacao:hover {
  transform: translateY(-6px);
  box-shadow: 0 12px 30px rgba(0, 0, 0, 0.2);
}

.header {
  display: flex;
  justify-content: space-between;
  margin-bottom: 8px;
}

.nome {
  font-size: 1.4rem;
  font-weight: 600;
  color: #ffffff;
  text-align: left;
}

.data {
  font-size: 0.9rem;
  font-weight: 500;
  color: #eeeeee;
}

.info {
  display: flex;
  justify-content: space-between;
  margin-bottom: 8px;
}

.preco {
  font-size: 1.3rem;
  font-weight: 600;
  color: #ffffff;
}

.quantidade {
  font-size: 1rem;
  font-weight: 500;
  color: #eeeeee;
}

.tipo {
  text-align: center;
  margin-top: 10px;
  font-size: 1rem;
  font-weight: 700;
  color: #ffffff;
  text-transform: uppercase;
}

p {
  margin: 0;
}
</style>