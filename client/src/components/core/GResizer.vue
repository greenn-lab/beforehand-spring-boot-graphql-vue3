<template>
  <div
    style="
      display: inline-block;
      overflow: hidden;
      resize: both;
    "
    @mouseup="resize($event)"
  >
    <slot />
  </div>
</template>
<script setup>
import { defineProps } from 'vue'

const { ruler } = defineProps({
  ruler: {
    type: Number,
    required: true
  }
})

const resize = e => {
  e.currentTarget.style.width = `${
    Math.round(e.currentTarget.offsetWidth / ruler) * ruler
  }px`
}
</script>
