<template>
  <q-layout view="lHh LpR lFf">
    <main-layout-drawer/>

    <main-layout-header/>

    <q-page-container>
      <q-page class="row no-wrap">
        <div class="col">
          <div class="full-height">
            <q-scroll-area class="col q-pr-sm full-height" visible>
              <router-view/>
            </q-scroll-area>
          </div>
        </div>
      </q-page>
    </q-page-container>
  </q-layout>
</template>

<script setup>
import {defineComponent} from 'vue'
import MainLayoutHeader from 'layouts/MainLayoutHeader'
import MainLayoutDrawer from 'layouts/MainLayoutDrawer'

defineComponent({
  components: {
    MainLayoutHeader,
    MainLayoutDrawer
  }
})
</script>

<style></style>
