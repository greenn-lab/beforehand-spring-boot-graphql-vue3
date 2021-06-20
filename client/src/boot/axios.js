import { boot } from 'quasar/wrappers'
import axios from 'axios'

// Be careful when using SSR for cross-request state pollution
// due to creating a Singleton instance here;
// If any client changes this (global) instance, it might be a
// good idea to move this instance creation inside of the
// "export default () => {}" function below (which runs individually
// for each client)
const api = axios.create({
  baseURL: '/api'
})

const graphql = (() => {
  const req = axios.create({
    baseURL: '/api/graphql',
    method: 'post'
  })

  return function (query, variables) {
    return req
      .post('', {
        query,
        variables
      })
      .then(response => response.data.data)
  }
})()

export default boot(({ app }) => {
  app.config.globalProperties.$axios = axios
  app.config.globalProperties.$api = api
  app.config.globalProperties.$graphql = graphql
})

export { api, graphql }
