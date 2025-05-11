<template>
  <div class="produto-card">
    <div class="header">
      <p class="nome">{{ nome }}</p>
    </div>

    <div class="info preco-container">
      <p class="preco">{{ precoFormatado(valor) }}</p>
    </div>

    <div class="info detalhes">
      <div>
        <p class="label">Tipo</p>
        <p class="valor">{{ tipo }}</p>
      </div>
      <div>
        <p class="label">Quantidade</p>
        <p class="valor">{{ quantidade }}</p>
      </div>
      <div v-if="saida != null">
        <p class="label">Saída</p>
        <p class="valor">{{ saida }}</p>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  props: {
    nome: String,
    valor: Number,
    tipo: String,
    quantidade: Number,
    saida: Number
  },
  methods: {
    precoFormatado(valor) {
      return new Intl.NumberFormat("pt-BR", {
        style: "currency",
        currency: "BRL",
      }).format(Number(valor));
    }
  }
}
</script>

<style scoped>
.produto-card {
  width: 100%;
  max-width: 300px;
  padding: 16px;
  margin: 12px;
  border-radius: 12px;
  cursor: pointer;
  background: #ffffff;
  border: 1px solid #e0e0e0;
  box-shadow: 0 4px 10px rgba(0, 0, 0, 0.04);
  display: flex;
  flex-direction: column;
  justify-content: space-between;
  transition: transform 0.2s ease, box-shadow 0.2s ease;
  border-left: 6px solid #6CCA98;
}

.produto-card:hover {
  transform: translateY(-3px);
  box-shadow: 0 8px 20px rgba(0, 0, 0, 0.08);
  border-left: 6px solid #004F51;
}

.nome {
  font-size: 1.2rem;
  font-weight: 600;
  margin-bottom: 10px;
  color: #333;
  text-align: center;
}

.preco {
  font-size: 1.3rem;
  font-weight: bold;
  color: #2E7D32;
  text-align: center;
}

.detalhes {
  display: flex;
  flex-wrap: wrap;
  justify-content: space-between;
  margin-top: 12px;
  gap: 8px;
}

.label {
  font-size: 0.7rem;
  font-weight: 600;
  color: #888;
  text-transform: uppercase;
}

.valor {
  font-size: 0.9rem;
  font-weight: 500;
  color: #333;
}
</style>
