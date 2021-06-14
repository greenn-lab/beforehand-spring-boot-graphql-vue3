<template>
  <q-list
    v-for="menu in menus"
    :key="menu.id"
    class="g-menu"
    padding
  >
    <g-menu-title :menu="menu" />

    <div
      v-for="childMenu in menu.children"
      :key="childMenu.id"
      class="g-menu__item"
    >
      <g-menu-item :menu="childMenu" :depth="1" />
    </div>
  </q-list>
</template>

<script setup>
import { computed, defineComponent } from 'vue'
import { useStore } from 'vuex'
import GMenuTitle from './GMenuTitle'
import GMenuItem from './GMenuItem'

defineComponent({
  components: [GMenuTitle, GMenuItem]
})

const store = useStore()
const menus = computed(
  () => store.getters['navigation/menus']
)
</script>
