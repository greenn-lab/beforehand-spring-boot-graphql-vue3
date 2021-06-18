<template>
  <q-layout view="lHh LpR lFf">
    <main-layout-drawer :open="open" />
    <main-layout-header />

    <q-page-container>
      <q-page class="row no-wrap">
        <div class="col">
          <router-view />
          <!-- Remain content from changed route.
          <router-view v-slot="{ Component }">
            <keep-alive>
              <component :is="Component" />
            </keep-alive>
          </router-view>
          -->
        </div>
      </q-page>
    </q-page-container>
  </q-layout>
</template>

<script setup>
import { defineComponent, provide, ref } from 'vue'
import { useRouter } from 'vue-router'

import MainLayoutHeader from './MainLayoutHeader'
import MainLayoutDrawer from './MainLayoutDrawer'

defineComponent({
  components: {
    MainLayoutHeader,
    MainLayoutDrawer
  }
})

const router = useRouter()

// ref
const open = ref(true)

// provides
provide('drawerOpen', open)
provide('routing', menu => {
  router.push({
    path: menu.uri
  })

  document.title = menu.name
})
</script>
