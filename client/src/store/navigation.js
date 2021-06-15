import axios from 'axios'
import menu from 'src/gqls/menu'

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
    },
    setMenus(state, payload) {
      const calculatePath = (menus, upper) => {
        return menus.map(i => {
          i.path = (upper ? upper.path : []).concat(i.name)
          i.branches.length && calculatePath(i.branches, i)
          return i
        })
      }

      state.menus = calculatePath(payload.menus)
    },
    addTask(state, payload) {
      if (state.tasks.every(i => payload.id !== i.id)) {
        state.tasks = [payload, ...state.tasks]
      }
    },
    removeLastTask(state, task) {
      state.tasks = state.tasks.splice(1)
    }
  },
  actions: {
    setActive({ commit }, payload) {
      commit('setActive', payload)
      commit('addTask', payload)
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
    addTask({ commit }, task) {
      commit('addTask', task)
    },
    removeLastTask({ commit }, task) {
      commit('removeLastTask', task)
    }
  }
}
