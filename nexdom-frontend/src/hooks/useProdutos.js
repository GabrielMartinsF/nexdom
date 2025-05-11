import { defineStore } from 'pinia'
import { ref } from 'vue'
import { api } from 'boot/axios'

export const useProdutos = defineStore('produto', () => {
  const produtos = ref([])
  const produtoLucro = ref(null)

  const fetchProdutos = async () => {
    const { data } = await api.get('/produtos')
    produtos.value = data
  }

  const createProduto = async (payload) => {
    await api.post('/produtos', payload)
    await fetchProdutos()
  }

  const updateProduto = async (id, payload) => {
    await api.put(`/produtos/${id}`, payload)
    await fetchProdutos()
  }

  const deleteProduto = async (id) => {
    await api.delete(`/produtos/${id}`)
    await fetchProdutos()
  }

  const fetchByTipo = async (tipo) => {
    const { data } = await api.get(`/produtos/tipo/${tipo}`)
    produtos.value = data
  }

  const fetchByLucro = async (id) => {
    const { data } = await api.get(`/produtos/${id}/lucro`)
    produtoLucro.value = data
  }

  return { produtos, produtoLucro, fetchProdutos, createProduto, updateProduto, deleteProduto, fetchByTipo, fetchByLucro }
})