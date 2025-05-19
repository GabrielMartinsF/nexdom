<template>
  <q-dialog v-model="show">
    <q-card>
      <q-card-section class="text-h6">
        <q-icon name="add"/>
        Cadastrar Movimentação
      </q-card-section>

      <q-card-section>
        <q-form @submit.prevent="onSubmit" @reset="onReset">
          <div class="row q-col-gutter-md">
            <q-select
              outlined
              class="col-12 col-md-6"
              v-model="produto"
              label="Produto"
              :options="produtoOptions"
              option-label="label"
              option-value="value"
              emit-value
              map-options
              lazy-rules
              :rules="[val => !!val || 'Selecione um produto']"
            >
              <template v-slot:no-option>
                <q-item>
                  <q-item-section class="text-italic text-grey">
                    Nenhum produto encontrado
                  </q-item-section>
                </q-item>
              </template>
            </q-select>
            <q-select
              outlined
              class="col-12 col-md-6"
              v-model="tipo"
              label="Tipo"
              :options="tipos"
              lazy-rules
              :rules="[val => !!val || 'Selecione um tipo']"
            />
            <q-input
              outlined
              class="col-12 col-md-4"
              v-model="valorFormatado"
              label="Valor (R$)"
              lazy-rules
              :rules="[val => !!val || 'Informe o valor']"
              @update:model-value="formatarMoeda"
            />
            <q-input
              outlined
              class="col-12 col-md-4"
              v-model="quantidade"
              label="Quantidade"
              @input="validarQuantidade"
              type="number"
              lazy-rules
              :rules="[val => val >= 0 || 'Quantidade não pode ser negativa']"
            />

            <q-input
              outlined
              class="col-12 col-md-4"
              v-model="dataMovimentacao"
              label="Data"
              mask="##/##/####"
              :rules="[val => !!val || 'Informe a data']"
            >
              <template v-slot:append>
                <q-icon name="event" class="cursor-pointer">
                  <q-popup-proxy cover transition-show="scale" transition-hide="scale">
                    <q-date
                      v-model="internalDate"
                      mask="YYYY-MM-DD"
                      @update:model-value="formatarData"
                    >
                      <div class="row items-center justify-end">
                        <q-btn v-close-popup label="Fechar" color="primary" flat />
                      </div>
                    </q-date>
                  </q-popup-proxy>
                </q-icon>
              </template>
            </q-input>
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
import { ref, onMounted, computed } from 'vue'
import { storeToRefs } from 'pinia'
import { useProdutos } from 'src/hooks/useProdutos'
import { useEstoque } from "src/hooks/useEstoque"

const show = ref(false)
const tipo = ref('')
const valor = ref(null)
const quantidade = ref(null)
const valorFormatado = ref('')
const dataMovimentacao = ref('')
const internalDate = ref('')

const produtoStore = useProdutos()
const { fetchProdutos } = produtoStore
const { produtos } = storeToRefs(produtoStore)

const estoqueStore = useEstoque()
const { movimentarProduto } = estoqueStore

const produto = ref(null)

const produtoOptions = computed(() =>
  produtos.value.map(p => ({
    label: p.descricao,
    value: p
  }))
)

const tipos = ["ENTRADA", "SAIDA"]

function open() {
  show.value = true
}

function close() {
  show.value = false
}

async function onSubmit() {
  let payload = {
    produto: produto.value,
    tipo: tipo.value,
    valorVenda: valor.value,
    dataVenda: formatarDataEn(dataMovimentacao.value),
    quantidadeMovimentada: quantidade.value
  }
  await movimentarProduto(payload)
  await fetchProdutos()
  onReset()
  close()
}

function onReset() {
  produto.value = null
  tipo.value = ''
  valor.value = null
  dataMovimentacao.value = null
  quantidade.value = null
  valorFormatado.value = ''
}

function formatarMoeda(valorStr) {
  const numero = parseFloat(valorStr.replace(/[^\d]/g, '')) / 100
  valor.value = numero

  valorFormatado.value = numero.toLocaleString('pt-BR', {
    style: 'currency',
    currency: 'BRL'
  })
}

function formatarData(val) {
  internalDate.value = val
  dataMovimentacao.value = new Date(val).toLocaleDateString('pt-BR')
}

function formatarDataEn(val) {
  const [dia, mes, ano] = val.split('/');
  const diaFormatado = dia.padStart(2, '0');
  const mesFormatado = mes.padStart(2, '0');
  return `${ano}-${mesFormatado}-${diaFormatado}`;
}

const validarQuantidade = (val) => {
  if (val < 0) {
    quantidade.value = 0
  }
}

onMounted(async () => {
  await fetchProdutos()
})

defineExpose({ open, close })
</script>