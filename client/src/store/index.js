import {store} from 'quasar/wrappers'
import {createStore} from 'vuex'

// import example from './module-example'

import navigation from './navigation'

export default store(function () {
  return createStore({
    modules: {
      navigation
    },
    state: {},
    getters: {},
    mutations: {},
    actions: {},

    // enable strict mode (adds overhead!)
    // for dev mode and --debug builds only
    strict: process.env.DEBUGGING
  })
})
