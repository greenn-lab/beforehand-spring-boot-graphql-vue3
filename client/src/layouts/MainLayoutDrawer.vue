<template>

  <q-drawer
    v-model="open"
    :breakpoint="400"
    :width="280"
    show-if-above
  >
    <q-scroll-area>
      <g-person/>
      <g-menu/>
    </q-scroll-area>
  </q-drawer>
</template>

<script setup>
import {defineComponent, defineProps} from 'vue'
import GPerson from './GPerson'
import GMenu from './GMenu'

defineComponent([GPerson, GMenu])

defineProps(['open'])
</script>
