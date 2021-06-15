import {
  createRouter,
  createWebHistory,
  useRouter
} from 'vue-router'
import routes from './routes'

const _addRoutes = (_routes, menus) => {
  menus.forEach(menu => {
    _routes.push({
      path: menu.uri,
      name: menu.uri.toLowerCase().replace(/\//g, '-'),
      component: () =>
        import(`../pages${menu.uri}.vue`).catch(() =>
          import('../pages/Error404.vue')
        )
    })

    menu.branches &&
      menu.branches.length &&
      _addRoutes(_routes, menu.branches)
  })
}

export const routing = function (router, menu) {
  router.push({
    path: menu.uri
  })

  document.title = menu.name
}

export default async ({ store }) => {
  await store.dispatch('navigation/fetchMenus')

  _addRoutes(
    routes[0].children,
    store.getters['navigation/menus']
  )

  return createRouter({
    scrollBehavior: () => ({ left: 0, top: 0 }),
    routes,
    history: createWebHistory()
  })
}
