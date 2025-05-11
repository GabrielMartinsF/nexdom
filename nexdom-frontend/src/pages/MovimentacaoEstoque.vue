<template>
  <q-page>
    <ModalMovimentacao ref="modalMovimentacao"/>
    <!-- caso não existam movimentações cadastradas -->
    <div class="flex flex-center column q-pa-xl" v-if="movimentacoes.length === 0">
      <q-card flat bordered class="bg-grey-1 text-grey-8 q-pa-xl" style="max-width: 420px;">
        <div class="text-center">
          <q-icon name="inventory_2" size="56px" color="primary" class="q-mb-md" />
          <div class="text-h6 q-mb-sm">Nenhuma movimentação cadastrada</div>
          <div class="text-subtitle2">
            Ainda não há movimentações cadastradas
          </div>
        </div>
        <div class="q-mt-md flex flex-center">
          <q-btn 
            color="primary" 
            icon="add_circle_outline" 
            label="Nova movimentação" 
            unelevated 
            @click="openModalMovimentacao()"
          />
        </div>
      </q-card>
    </div>

    <!-- caso existam produtos cadastrados -->
    <div class="q-pa-md" v-else>
      <div class="header row justify-end q-gutter-xs">
        <q-btn color="secondary" icon-right="add" label="Nova movimentação" @click="openModalMovimentacao()"/>
      </div>

      <div id="movimentacoes" class="row wrap">
        <Movimentacoes
          v-for="movimentacao in movimentacoes"
          :key="movimentacao.id"
          :id="movimentacao.id"
          :nome="movimentacao.produto.descricao"
          :dataVenda="movimentacao.dataVenda"
          :tipo="movimentacao.tipo"
          :valor="movimentacao.valorVenda"
          :quantidade="movimentacao.quantidadeMovimentada"
          :quantidadeDisponivel="movimentacao.produto.quantidadeEstoque"
        />
      </div>
    </div>
  </q-page>
</template>

<script>
import { useEstoque } from "src/hooks/useEstoque"
import { storeToRefs } from 'pinia';
import { defineComponent, onMounted, ref } from 'vue';
import Movimentacoes from "src/components/Movimentacoes.vue";
import ModalMovimentacao from "src/components/ModalMovimentacao.vue";

export default defineComponent({
  name: 'Index',
  components: {
    Movimentacoes,
    ModalMovimentacao
  },
  setup () {
    const estoqueStore = useEstoque()
    const { fetchMovimentacoes } = estoqueStore
    const { movimentacoes } = storeToRefs(estoqueStore)

    const modalMovimentacao = ref(null)

    const openModalMovimentacao = () => {
      modalMovimentacao.value?.open()
    }

    onMounted(async () => {
      await fetchMovimentacoes()
    })

    return {
      movimentacoes,
      modalMovimentacao,
      openModalMovimentacao
    }
  }
})
</script>

<style>
</style>