import { defineStore } from 'pinia'
import { ref } from 'vue'
import { api } from 'boot/axios'
import Notify from './Notify'

export const useEstoque = defineStore('movimentacoes', () => {
  const movimentacoes = ref([])

  const fetchMovimentacoes = async () => {
    const { data } = await api.get('/estoque')
    movimentacoes.value = data
  }

  const movimentarProduto = async (payload) => {
    try {
      await api.post('/estoque/movimentarProduto', payload)
      await fetchMovimentacoes()
    } catch (error) {
      console.log(error.response.data.message)
      Notify.error(error.response.data.message)
    }
    
  }

  return { movimentacoes, fetchMovimentacoes, movimentarProduto }
})