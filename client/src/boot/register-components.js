import { boot } from 'quasar/wrappers'
import GPage from 'components/core/GPage'
import QInputDate from 'components/QInputDate'

export default boot(async ({ app }) => {
  app.component('GPage', GPage)

  app.component('QInputDate', QInputDate)
})
