import {store} from 'quasar/wrappers'
import {createStore} from 'vuex'
import axios from 'axios'

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
      getMenu(state) {
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
        axios.post('/api/graphql', {
          query: `query {
            menus {
              id
              name
              nameEn
              uri
              description
              children {
                id
                name
                children {
                  id
                  name
                  children {
                    id
                    name
                    children {
                      id
                      name
                    }
                  }
                }
              }
            }
          }`
        })
        .then(({data}) => {
          commit('setMenu', data)
        })
      }
    },

    // enable strict mode (adds overhead!)
    // for dev mode and --debug builds only
    strict: process.env.DEBUGGING
  })

  return Store
})
