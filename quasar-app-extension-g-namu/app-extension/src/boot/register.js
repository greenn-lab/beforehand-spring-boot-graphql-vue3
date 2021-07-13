import { boot } from 'quasar/wrappers'
import VuePlugin from 'quasar-ui-quasar-app-extension-g-namu'

export default boot(({ app }) => {
  app.use(VuePlugin)
})
