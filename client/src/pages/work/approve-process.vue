<template>
  <g-page>
    test
    <b>ipsum lorem {{ [1, 2, 3, 4, 5].join(' - ') }}</b>

    <q-form
      class="q-gutter-md"
      @submit="() => console.log('ok')"
    >
      <q-input-date v-model="date" hi="hello" />

      <div>
        <q-btn
          color="primary"
          label="Submit"
          type="submit"
        />
      </div>
    </q-form>
  </g-page>
</template>
<script setup="props">
import { ref, watch } from 'vue'

const date = ref(null)
const time = ref(null)

watch(date, next => {
  console.log('date', date.value)
})
watch(time, next => console.log('time', time.value))
</script>
