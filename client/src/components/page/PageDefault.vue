<template>
  <main>
    <PageHeader />
    <section class="g-page">
      <slot />
    </section>
  </main>
</template>

<script setup>
import { defineComponent } from 'vue'
import PageHeader from './PageHeader'

defineComponent({
  PageHeader
})
</script>
