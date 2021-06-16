<template>
  <header class="q-ma-lg">
    <span
      v-for="(path, i) in active.path"
      :key="path"
      class="g-page__path text-accent"
    >
      <q-icon v-if="i > 0" name="keyboard_arrow_right" />
      {{ path }}
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

<style lang="scss" scoped>
h1 {
  font-size: 2rem;
  font-weight: 700;
  line-height: 2rem;
  margin: 0.3rem 0;
}

span {
  font-size: 0.8rem;
}

.q-icon {
  font-size: 1.5rem;
  margin-top: -3px;
}
</style>
