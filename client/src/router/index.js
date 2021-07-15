import { createRouter, createWebHistory } from 'vue-router'
import routes from './routes'

const _addRoutes = (_routes, menus) => {
  menus.forEach(menu => {
    _routes.push({
      path: menu.uri,
      name: menu.uri.toLowerCase().replace(/\//g, '-'),
      meta: {
        info: menu
      },
      component: () =>
        import(`../pages${menu.uri}.vue`).catch(() =>
          import('../pages/Error404.vue')
        )
    })

    menu?.children.length &&
      _addRoutes(_routes, menu.children)
  })
}

export default async ({ store }) => {
  await store.dispatch('navigation/fetchMenus')

  _addRoutes(
    routes[0].children,
    store.getters['navigation/menus']
  )

  const router = createRouter({
    scrollBehavior: () => ({ left: 0, top: 0 }),
    routes,
    history: createWebHistory()
  })

  router.beforeEach((to, _from) => {
    store.dispatch('navigation/setActive', to.meta.info)
    return true
  })

  return router
}
