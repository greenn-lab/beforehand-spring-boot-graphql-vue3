<template>
  <header class="g-page__header">
    <span
      v-for="(path, i) in active.path"
      :key="path"
      class="g-page__path text-accent"
    >
      <q-icon v-if="i > 0" name="keyboard_arrow_right" />{{
        path
      }}
    </span>
    <h1 class="g-page__name">{{ active.name }}</h1>
  </header>
</template>

<script setup>
import { useStore } from 'vuex'
import { computed } from 'vue'

const store = useStore()

const active = computed(
  () => store.getters['navigation/active']
)
</script>
