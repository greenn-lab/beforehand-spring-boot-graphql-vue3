import { boot } from 'quasar/wrappers'
import QInputDate from 'components/QInputDate'

export default boot(async ({ app }) => {
  app.component('QInputDate', QInputDate)
})
