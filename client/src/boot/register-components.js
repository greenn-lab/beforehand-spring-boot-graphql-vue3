import { boot } from 'quasar/wrappers'
import GPage from 'components/core/GPage'
import QInputDate from 'components/QInputDate'

export default boot(({ app }) => {
  app.component('g-page', GPage)
  app.component('q-input-date', QInputDate)
})
