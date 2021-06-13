<template>
  <div class="g-menu__child">
    <slot v-for="menu in menus" :key="menu.id">
      <q-item @click="toggleChild(menu.id)" v-ripple clickable>
        <q-item-section>{{ menu.name }}</q-item-section>
        <q-btn
          v-if="menu.children && menu.children.length"
          class="g-menu__more"
          flat
          icon="ion-ios-arrow-forward"
        />
      </q-item>

      <q-slide-transition>
        <g-menu-child
          :menus="menu.children"
          v-if="menu.children && menu.children.length"
          v-show="opened[menu.id]"
        />
      </q-slide-transition>
    </slot>
  </div>
</template>

<script setup>
import { defineComponent, defineProps, ref } from 'vue'

defineComponent({
  name: 'GMenuChild'
})

defineProps(['menus'])

const opened = ref({})
const toggleChild = id => (opened.value[id] = !opened.value[id])
</script>
