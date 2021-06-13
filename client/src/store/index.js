import { store } from 'quasar/wrappers'
import { createStore } from 'vuex'
import axios from 'axios'
import menu from 'src/gqls/menu'

// import example from './module-example'

/*
 * If not building with SSR mode, you can
 * directly export the Store instantiation;
 *
 * The function below can be async too; either use
 * async/await or return a Promise which resolves
 * with the Store instance.
 */

export default store(function () {
  return createStore({
    modules: {},
    state: {
      menus: []
    },
    getters: {
      menus(state) {
        return state.menus
      }
    },
    mutations: {
      setMenus(state, payload) {
        console.log('payload', payload)
        state.menus = payload.menus
      }
    },
    actions: {
      async fetchMenus({ commit }) {
        await axios
          .post('/api/graphql', {
            query: menu.MENUS
          })
          .then(({ data }) => {
            commit('setMenus', data.data)
          })
      }
    },

    // enable strict mode (adds overhead!)
    // for dev mode and --debug builds only
    strict: process.env.DEBUGGING
  })
})
