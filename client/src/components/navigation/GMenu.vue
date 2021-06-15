<template>
  <q-list
    v-for="menu in menus"
    :key="menu.id"
    class="g-menu"
    padding
  >
    <g-menu-title :menu="menu" />

    <div
      v-for="childMenu in menu.branches"
      :key="childMenu.id"
      class="g-menu__item"
    >
      <g-menu-item :depth="1" :menu="childMenu" />
    </div>
  </q-list>
</template>

<script setup>
import { computed, defineComponent, provide } from 'vue'
import { useStore } from 'vuex'
import { useRouter } from 'vue-router'
import GMenuTitle from './GMenuTitle'
import GMenuItem from './GMenuItem'

defineComponent({
  components: [GMenuTitle, GMenuItem]
})

const store = useStore()
const router = useRouter()

const menus = computed(
  () => store.getters['navigation/menus']
)
</script>
