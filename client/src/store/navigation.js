import axios from 'axios'
import menu from 'src/gqls/menu'

export default {
  namespaced: true,
  state: {
    active: 0,
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
    setActive(state, id) {
      state.active = id
    },
    setMenus(state, payload) {
      state.menus = payload.menus
    },
    addTask(state, task) {
      if (state.tasks.some(i => task.id === i.id)) {
        return
      }

      state.tasks = [task, ...state.tasks]
    },
    removeLastTask(state, task) {
      state.tasks = state.tasks.splice(1)
    }
  },
  actions: {
    setActive({ commit }, id) {
      commit('setActive', id)
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
