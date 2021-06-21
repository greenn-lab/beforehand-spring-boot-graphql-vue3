import { boot } from 'quasar/wrappers'
import GPage from 'components/core/GPage'
import GForm from 'components/core/GForm'
import QInputDate from 'components/QInputDate'

export default boot(({ app }) => {
  app.component('g-page', GPage)
  app.component('g-form', GForm)
  app.component('q-input-date', QInputDate)
})
