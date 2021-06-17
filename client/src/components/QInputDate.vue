<template>
  <q-input
    v-model="date"
    mask="date"
    :rules="[]"
    @focus="$refs.qDateProxy.show()"
  >
  </q-input>
</template>

<script setup>
import { ref, watch } from 'vue'

const date = ref(null)

const name = ref('data')

watch(date, next => console.log('date', date))
</script>
