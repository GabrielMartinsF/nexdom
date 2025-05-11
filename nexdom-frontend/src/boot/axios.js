import { defineBoot } from '#q-app/wrappers'
import axios from 'axios'
import { createPinia } from 'pinia'

const api = axios.create({ baseURL: 'http://localhost:8080/' })
const pinia = createPinia()

export default defineBoot(({ app }) => {
  app.use(pinia)

  app.config.globalProperties.$axios = axios

  app.config.globalProperties.$api = api
})

export { api }
