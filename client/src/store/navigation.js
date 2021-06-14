import axios from 'axios'
import menu from 'src/gqls/menu'

export default {
  namespaced: true,
  state: {
    activeMenu: 0,
    menus: [],
    activeTask: '',
    tasks: []
  },
  getters: {
    activeMenu(state) {
      return state.activeMenu
    },
    menus(state) {
      return state.menus
    },
    activeTask(state) {
      return state.activeTask
    },
    tasks(state) {
      return state.tasks
    }
  },
  mutations: {
    setActiveMenu(state, payload) {
      state.activeMenu = payload

      console.table(state)
    },
    setMenus(state, payload) {
      state.menus = payload.menus
    },
    addTask(state, task) {
      state.tasks.push(task)
      this.setActiveTask(task)
    },
    removeTask(state, task) {
      state.tasks = state.tasks.filter(
        t => t.id !== task.id
      )
      this.setActiveTask(state.tasks[0])
    },
    setActiveTask(state, task) {
      state.activeTask = task.id
    }
  },
  actions: {
    setActiveMenu({ commit }, id) {
      commit('setActiveMenu', id)
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
    }
  }
}
