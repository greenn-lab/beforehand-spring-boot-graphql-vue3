<template>
  <router-view/>

  <button @click="go('/test')">test</button>
  <button @click="go('/about')">about</button>
</template>


<script setup>
import { useRouter } from 'vue-router'
import {onMounted} from 'vue'
import {useStore} from 'vuex'

const $store = useStore()

onMounted(() => {
  $store.dispatch('fetchMenu')
})

const $router = useRouter()

const go = function (r) {
  $router.push(r)
}
</script>
