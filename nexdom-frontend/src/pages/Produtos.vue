<template>
  <q-page>
    <ModalProduto ref="modalProduto" />
    <ModalEditarProduto ref="modalEditProduto"/>
    <!-- caso não existam produtos cadastrados -->
    <div class="flex flex-center column q-pa-xl" v-if="produtos.length === 0 && this.filtroTipo == 'TODOS' ">
      <q-card flat bordered class="bg-grey-1 text-grey-8 q-pa-xl" style="max-width: 420px;">
        <div class="text-center">
          <q-icon name="inventory_2" size="56px" color="primary" class="q-mb-md" />
          <div class="text-h6 q-mb-sm">Nenhum produto cadastrado</div>
          <div class="text-subtitle2">
            Cadastre seu primeiro item para começar a controlar o estoque.
          </div>
        </div>
        <div class="q-mt-md flex flex-center">
          <q-btn 
            color="secondary" 
            icon="add_circle_outline" 
            label="Cadastrar Produto" 
            @click="openNewProdutoDialog"
            unelevated 
          />
        </div>
      </q-card>
    </div>

    <!-- caso existam produtos cadastrados -->
    <div class="q-pa-md" v-else>
      <div class="header row justify-end q-gutter-xs">
        <q-select
          outlined
          class="col-md-2"
          v-model="filtroTipo"
          label="Filtrar por tipo"
          :options="tipos"
          @update:model-value="buscarPorTipo()"
        />
        <q-btn color="secondary" icon-right="add" label="Cadastrar produto" @click="openNewProdutoDialog" />
      </div>

      <div v-if="produtos.length === 0 && filtroTipo != null" class="q-pa-md flex flex-center">
        <q-card flat bordered class="bg-grey-1 text-grey-8 q-pa-xl" style="max-width: 400px;">
          <div class="text-center">
            <q-icon name="info" size="48px" color="primary" class="q-mb-md" />
            <div class="text-h6 q-mb-sm">Nenhum produto encontrado</div>
            <div class="text-subtitle2">
              Não há itens cadastrados para o tipo <strong>{{ filtroTipo }}</strong>.
            </div>
          </div>
        </q-card>
      </div>

      <div id="produtos" class="row wrap">
        <Produto
          v-for="produto in produtos"
          :key="produto.id"
          :id="produto.id"
          :nome="produto.descricao"
          :tipo="produto.tipo"
          :valor="produto.valorFornecedor"
          :quantidade="produto?.quantidadeDisponivel ?? produto?.quantidadeEstoque"
          :saida="produto.quantidadeSaida"
          @click="openEditDialog(produto)"
        />
      </div>
    </div>
  </q-page>
</template>

<script>
import { useProdutos } from 'src/hooks/useProdutos';
import { storeToRefs } from 'pinia';
import { defineComponent, onMounted, ref } from 'vue';
import Produto from "components/Produto.vue"
import ModalProduto from 'src/components/ModalCadastroProduto.vue';
import ModalEditarProduto from 'src/components/ModalEditarProduto.vue';

export default defineComponent({
  name: 'Index',
  components: {
    Produto,
    ModalProduto,
    ModalEditarProduto
  },
  setup () {
    const produtoStore = useProdutos()
    const { fetchProdutos, fetchByTipo } = produtoStore
    const { produtos } = storeToRefs(produtoStore)
    const tipos = ["TODOS", "ELETRONICO", "ELETRODOMESTICO", "MOVEL"]
    const modalProduto = ref(null)
    const modalEditProduto = ref(null)
    const filtroTipo = ref("TODOS")

    const openEditDialog = (data) => {
      modalEditProduto.value?.open(data)
    }

    const openNewProdutoDialog = () => {
      modalProduto.value?.open()
    }

    const buscarPorTipo = () => {
      if(filtroTipo.value == "TODOS"){
        fetchProdutos()
      } else {
        fetchByTipo(filtroTipo.value)
      }
    }

    onMounted(async () => {
      await fetchProdutos()
    })

    return {
      produtos,
      modalProduto,
      modalEditProduto,
      filtroTipo,
      tipos,
      openNewProdutoDialog,
      openEditDialog,
      buscarPorTipo
    }
  }
})
</script>

<style>
</style>