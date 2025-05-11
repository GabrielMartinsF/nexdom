<template>
  <q-card class="produto-card">
    <div class="header">
      <p class="nome">{{ nome }}</p>
    </div>

    <q-separator spaced />

    <div class="info lucro-container">
      <p class="label">Lucro</p>
      <p class="preco">{{ precoFormatado(lucro) }}</p>
    </div>

    <q-separator spaced />

    <div class="info row justify-between detalhes">
      <div class="col">
        <p class="label">Tipo</p>
        <p class="valor">{{ tipo }}</p>
      </div>
      <div class="col text-right">
        <p class="label">Quantidade</p>
        <p class="valor">{{ saida }}</p>
      </div>
    </div>
  </q-card>
</template>

<script>
import { defineComponent } from 'vue';

export default defineComponent({
  props: {
    id: Number,
    nome: String,
    tipo: String,
    lucro: Number,
    saida: Number
  },
  methods: {
    precoFormatado(valor) {
      return new Intl.NumberFormat("pt-BR", {
        style: "currency",
        currency: "BRL"
      }).format(Number(valor));
    }
  }
});
</script>

<style scoped>
.produto-card {
  max-width: 440px;
  width: 100%;
  margin: 24px auto;
  padding: 24px;
  border-radius: 16px;
  cursor: pointer;
  background: #f9f9fb;
  border-left: 6px solid #6CCA98;
  box-shadow: 0 6px 20px rgba(0, 0, 0, 0.06);
  transition: transform 0.2s ease, box-shadow 0.3s ease;
}

.produto-card:hover {
  transform: translateY(-2px);
  box-shadow: 0 10px 28px rgba(0, 0, 0, 0.1);
  border-left: 6px solid #004F51;
}

.nome {
  font-size: 1.5rem;
  font-weight: 700;
  text-align: center;
  color: #222;
  margin-bottom: 6px;
}

.label {
  font-size: 0.75rem;
  font-weight: 600;
  color: #777;
  margin-bottom: 2px;
  text-transform: uppercase;
  letter-spacing: 0.6px;
}

.preco {
  font-size: 1.4rem;
  font-weight: 600;
  color: #388E3C;
  text-align: center;
}

.valor {
  font-size: 1rem;
  font-weight: 500;
  color: #333;
}

.detalhes {
  margin-top: 12px;
}

.lucro-container {
  margin-top: 10px;
}
</style>