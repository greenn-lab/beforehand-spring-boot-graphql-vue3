<template>
  <div class="g-menu__title">
    <div class="g-menu__title-name">{{ menu.name }}</div>
    <div class="g-menu__title-desc">{{ menu.description }}</div>
  </div>
</template>

<script setup>
import { defineProps } from 'vue'

defineProps(['menu'])
</script>
