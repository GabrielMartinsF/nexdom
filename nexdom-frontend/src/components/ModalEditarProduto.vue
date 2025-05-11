<template>
  <q-dialog v-model="show">
    <q-card>
      <q-card-section class="row justify-between">
        <div class="row items-center">
          <q-icon class="text-h6" name="edit"/>
          <div class="text-h6"> Editar Produto </div>
        </div>
        
        <q-icon
          class="text-red text-h6"
          name="delete"
          style="cursor: pointer;"
          @click="deletarProduto(id)"
        >
          <q-tooltip anchor="top middle" self="bottom middle" transition-show="scale" transition-hide="scale">
            Deletar produto
          </q-tooltip>
        </q-icon>
      </q-card-section>

      <q-card-section>
        <q-form @submit.prevent="onSubmit" @reset="onReset">
          <div class="row q-col-gutter-md">
            <q-input
              outlined
              class="col-12 col-md-6"
              v-model="descricao"
              label="Descrição"
              lazy-rules
              :rules="[val => !!val || 'Informe a descrição']"
            />

            <q-select
              outlined
              class="col-12 col-md-6"
              v-model="tipo"
              label="Tipo do Produto"
              :options="tipos"
              lazy-rules
              :rules="[val => !!val || 'Selecione um tipo']"
            />

            <q-input
              outlined
              class="col-12 col-md-6"
              v-model="valorFormatado"
              label="Valor no Fornecedor (R$)"
              lazy-rules
              :rules="[val => !!val || 'Informe o valor']"
              @update:model-value="formatarMoeda"
            />

            <q-input
              outlined
              class="col-12 col-md-6"
              v-model="quantidade"
              label="Quantidade em Estoque"
              type="number"
              min="0"
              @input="validarQuantidade"
              lazy-rules
              :rules="[val => val >= 0 || 'Quantidade não pode ser negativa']"
            />
          </div>

          <div class="q-mt-md flex justify-end">
            <q-btn label="Confirmar" type="submit" color="primary" />
            <q-btn label="Fechar" flat color="primary" class="q-ml-sm" @click="close" />
          </div>
        </q-form>
      </q-card-section>
    </q-card>
  </q-dialog>
</template>

<script setup>
import { ref } from 'vue'
import { useProdutos } from 'src/hooks/useProdutos';

const { updateProduto, deleteProduto } = useProdutos()
const tipos = ["ELETRONICO", "ELETRODOMESTICO", "MOVEL"]
const show = ref(false)

function open(data) {
  if(data) {
    id.value = data.id
    descricao.value = data.descricao,
    tipo.value = data.tipo,
    formatarMoeda(data.valorFornecedor),
    quantidade.value = data.quantidadeEstoque
  }
  show.value = true
}

function close() {
  show.value = false
}

async function onSubmit() {
  let payload = {
    "descricao": descricao.value,
    "tipo": tipo.value,
    "valorFornecedor": valor.value,
    "quantidadeEstoque": quantidade.value
  }
  console.log(id)
  await updateProduto(id.value, payload)
  onReset()
  close()
}

function onReset() {
  descricao.value = ''
  tipo.value = ''
  valor.value = ''
  valorFormatado.value = ''
  quantidade.value = ''
}

function formatarMoeda(valorStr) {
  const numero = typeof valorStr === 'string'
    ? parseFloat(valorStr.replace(/[^\d]/g, '')) / 100
    : Number(valorStr)

  valor.value = numero

  valorFormatado.value = numero.toLocaleString('pt-BR', {
    style: 'currency',
    currency: 'BRL'
  })
}

async function atualizarProduto(id) {
  await deleteProduto(id)
  close()
}

async function deletarProduto(id) {
  await deleteProduto(id)
  close()
}

const validarQuantidade = (val) => {
  if (val < 0) {
    quantidade.value = 0
  }
}

const id = ref(null)
const valorFormatado = ref('')
const descricao = ref('')
const tipo = ref('')
const valor = ref(null)
const quantidade = ref(0)

defineExpose({ open, close })
</script>