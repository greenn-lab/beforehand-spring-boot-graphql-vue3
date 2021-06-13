<template>
  <q-list v-for="menu in menus" :key="menu.id" class="g-menu" padding>
    <g-menu-title :menu="menu"/>
    <g-menu-item :menus="menu.children"/>
  </q-list>
</template>

<script setup>
import {computed, defineComponent} from 'vue'
import {useStore} from 'vuex'
import GMenuTitle from './GMenuTitle'
import GMenuItem from './GMenuItem'

defineComponent({
  components: [GMenuTitle, GMenuItem]
})

const store = useStore()
const menus = computed(() => store.getters.menus)
</script>
