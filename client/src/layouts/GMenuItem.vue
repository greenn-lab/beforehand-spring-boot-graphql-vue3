<template>
  <div v-for="menu in menus" :key="menu.id" class="g-menu__item">
    <q-item
      @click="toggleChild(menu.id)"
      v-ripple
      clickable
    >
      <q-item-section avatar>
        <q-icon :name="menu.icon || 'extension'" />
      </q-item-section>
      <q-item-section>{{ menu.name }}</q-item-section>
      <q-btn
        v-if="menu.children.length"
        class="g-menu__more"
        flat
        icon="ion-ios-arrow-forward"
      />
    </q-item>

    <q-slide-transition>
      <g-menu-child
        :menus="menu.children"
        v-if="menu.children.length"
        v-show="opened[menu.id]"
      />
    </q-slide-transition>
  </div>
</template>

<script setup>
import { defineComponent, defineProps, ref } from 'vue'
import GMenuChild from './GMenuChild'

defineComponent([GMenuChild])

defineProps(['menus'])

const opened = ref({})
const toggleChild = id => (opened.value[id] = !opened.value[id])
</script>
