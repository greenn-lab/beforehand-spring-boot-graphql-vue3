<template>
  <div class="full-height">
    <q-scroll-area class="col q-pr-sm full-height" visible>
      <header class="g-page__header">
        <span
          v-for="(path, i) in active.path"
          :key="path"
          class="g-page__path text-accent"
        >
          <q-icon
            v-if="i > 0"
            name="keyboard_arrow_right"
          />{{ path }}
        </span>
        <h1 class="g-page__name">{{ active.name }}</h1>
      </header>

      <main class="g-page">
        <slot />
      </main>
    </q-scroll-area>
  </div>
</template>

<script setup>
import { useStore } from 'vuex'
import { computed } from 'vue'

const store = useStore()

const active = computed(
  () => store.getters['navigation/active']
)
</script>
