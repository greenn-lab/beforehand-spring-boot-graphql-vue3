import {store} from 'quasar/wrappers'
import {createStore} from 'vuex'
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

export default store(async function (/* { ssrContext } */) {
  const Store = createStore({
    modules: {},
    state: {
      menu: []
    },
    getters: {
      menu(state) {
        return state.menu
      }
    },
    mutations: {
      setMenu(state, payload) {
        state.menu = payload
      }
    },
    actions: {
      fetchMenu({commit}) {
        import('./menu.json')
          .then(res => commit('setMenu', res))

        /*axios.post('./menu.json', {
          query: menu.MENUS
        })
        .then(({data}) => {
          commit('setMenu', data)
        })*/
      }
    },

    // enable strict mode (adds overhead!)
    // for dev mode and --debug builds only
    strict: process.env.DEBUGGING
  })

  return Store
})
