<template>
  <q-page>
    <div class="q-pa-md">
      <div class="header row justify-end q-gutter-xs">
        <q-select
          outlined
          class="col-md-3"
          v-model="filtroProduto"
          label="Filtrar por produto"
          :options="produtoOptions"
          option-label="label"
          option-value="value"
          @update:model-value="buscarPorLucro(filtroProduto)"
        />
      </div>

      <div v-if="produtoLucro === null && filtroProduto != null" class="q-pa-md flex flex-center">
        <q-card flat bordered class="bg-grey-1 text-grey-8 q-pa-xl" style="max-width: 400px;">
          <div class="text-center">
            <q-icon name="info" size="48px" color="primary" class="q-mb-md" />
            <div class="text-h6 q-mb-sm">Nenhum produto encontrado</div>
            <div class="text-subtitle2">
              Não há itens cadastrados.
            </div>
          </div>
        </q-card>
      </div>

      <div id="produtos" class="row wrap" v-if="produtoLucro">
        <produtoLucro
          :id="produtoLucro.id"
          :nome="produtoLucro.descricao"
          :tipo="produtoLucro.tipo"
          :lucro="produtoLucro.lucro"
          :saida="produtoLucro.quantidadeSaida"
        />
      </div>
    </div>
  </q-page>
</template>

<script>
import { useProdutos } from 'src/hooks/useProdutos';
import { storeToRefs } from 'pinia';
import { computed, defineComponent, onMounted, ref } from 'vue';
import produtoLucro from "components/ProdutoLucro.vue"

export default defineComponent({
  name: 'Index',
  components: {
    produtoLucro,
    
  },
  setup () {
    const produtoStore = useProdutos()
    const { fetchProdutos, fetchByLucro } = produtoStore
    const { produtos, produtoLucro } = storeToRefs(produtoStore)
    const filtroProduto = ref("")

    fetchProdutos()

    const buscarPorLucro = (id) => {
      fetchByLucro(id.value)
    }

    const produtoOptions = computed(() =>
      produtos.value.map(p => ({
        label: p.descricao,
        value: p.id
      }))
    )

    return {
      produtos,
      produtoLucro,
      filtroProduto,
      produtoOptions,
      buscarPorLucro
    }
  }
})
</script>

<style>
</style>