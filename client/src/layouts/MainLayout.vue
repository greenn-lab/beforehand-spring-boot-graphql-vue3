<template>
  <q-layout view="lHh LpR lFf">
    <main-layout-drawer :open="opened" />

    <main-layout-header @menuToggle="toggle" />

    <q-page-container>
      <q-page class="row no-wrap">
        <div class="col">
          <div class="full-height">
            <q-scroll-area class="col q-pr-sm full-height" visible>
              <router-view />
            </q-scroll-area>
          </div>
        </div>
      </q-page>
    </q-page-container>
  </q-layout>
</template>

<script setup>
import { defineComponent, ref } from 'vue'
import MainLayoutHeader from 'layouts/MainLayoutHeader'
import MainLayoutDrawer from 'layouts/MainLayoutDrawer'

defineComponent({
  components: {
    MainLayoutHeader,
    MainLayoutDrawer
  }
})

const opened = ref(true)

const toggle = function () {
  opened.value = !opened.value
}
</script>
