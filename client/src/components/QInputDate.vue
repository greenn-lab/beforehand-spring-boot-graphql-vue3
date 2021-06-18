<template>
  <q-input
    :rules="['date']"
    dense
    mask="####-##-##"
    :model-value="date"
    @update:model-value="
      function ($event) {
        date = $event.target.value
        console.log($emit)
      }
    "
  >
    <template v-slot:append>
      <q-icon name="event">
        <q-popup-proxy
          transition-hide="scale"
          transition-show="scale"
        >
          <q-date v-model="date" no-unset />
        </q-popup-proxy>
      </q-icon>
    </template>
  </q-input>
</template>

<script setup="prop">
import { defineEmit, ref, watch } from 'vue'

defineEmit({
  date: function () {
    console.log('emit', arguments)
  }
})

// const date = ref(null)
// watch(date, next => console.log('in q-input-date', date))
</script>
