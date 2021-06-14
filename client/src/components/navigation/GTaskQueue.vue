<template>
  <q-tabs
    v-model="tab"
    class="g-task-queue"
    inline-label
    shrink
    stretch
  >
    <q-tab
      v-for="tab in tasks"
      :key="tab.name"
      v-bind="tab"
    />
  </q-tabs>
</template>

<script setup>
import { computed, ref, watch } from 'vue'
import { useStore } from 'vuex'

const store = useStore()
const storedTasks = computed(
  () => store.getters['navigation/tasks']
)

const tasks = ref(
  storedTasks.value.map(i => ({
    name: i.id,
    label: i.name
  }))
)
const tab = ref('-2')

watch(storedTasks, next => {
  tasks.value = next.map(i => ({
    name: i.id,
    label: i.name
  }))

  tab.value = tasks.value[0].name
})
</script>
