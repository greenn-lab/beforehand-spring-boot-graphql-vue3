import axios from 'axios'
import menu from 'src/gqls/menu'
import { SessionStorage } from 'quasar'

export default {
  namespaced: true,
  state: {
    active: {},
    menus: [],
    tasks: []
  },
  getters: {
    active(state) {
      return state.active
    },
    menus(state) {
      return state.menus
    },
    tasks(state) {
      return state.tasks
    }
  },
  mutations: {
    setActive(state, payload) {
      state.active = payload

      if (state.tasks.every(i => payload.id !== i.id)) {
        state.tasks = [payload, ...state.tasks]
      }

      SessionStorage.set('navigation.active', payload)
    },
    setMenus(state, payload) {
      const calculatePath = (menus, upper) => {
        return menus.map(i => {
          i.path = upper
            ? upper.path.concat(upper.name)
            : []
          i.branches.length && calculatePath(i.branches, i)

          return i
        })
      }

      state.menus = calculatePath(payload.menus)
    },
    removeLastTask(state, task) {
      state.tasks = state.tasks.splice(1)
    }
  },
  actions: {
    setActive({ commit }, payload) {
      commit('setActive', payload)
    },
    async fetchMenus({ commit }) {
      await axios
        .post('/api/graphql', {
          query: menu.MENUS
        })
        .then(({ data }) => {
          commit('setMenus', data.data)
        })
    },
    removeLastTask({ commit }, task) {
      commit('removeLastTask', task)
    }
  }
}
