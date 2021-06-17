<template>
  <q-drawer
    v-model="open"
    :breakpoint="800"
    :width="280"
    show-if-above
  >
    <q-scroll-area>
      <g-person />
      <g-menu />
    </q-scroll-area>
  </q-drawer>
</template>

<script setup>
import { defineComponent, defineProps } from 'vue'
import GPerson from 'components/navigation/GPerson'
import GMenu from 'components/navigation/GMenu'

defineComponent([GPerson, GMenu])

defineProps(['open'])
</script>
