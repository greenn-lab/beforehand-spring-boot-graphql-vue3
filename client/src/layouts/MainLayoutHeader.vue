<template>
  <q-header>
    <q-toolbar>
      <q-btn
        dense
        flat
        icon="menu"
        round
        @click="drawerOpen = !drawerOpen"
      />
      <q-space />
      <g-task-queue />
    </q-toolbar>
  </q-header>
</template>

<script setup>
import { defineComponent, inject } from 'vue'
import GTaskQueue from '../components/navigation/GTaskQueue'

defineComponent([GTaskQueue])

const drawerOpen = inject('drawerOpen')
</script>
