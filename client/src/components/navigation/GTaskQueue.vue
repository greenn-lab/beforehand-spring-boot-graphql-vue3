<template>
  <q-tabs
    v-model="active"
    class="g-task-queue"
    inline-label
    shrink
    stretch
  >
    <q-tab
      v-for="task in tasks"
      :key="task.id"
      v-bind="task"
      :label="task.name"
      :name="task.id"
      @click="routing(task)"
    />
  </q-tabs>
</template>

<script setup>
import { computed, inject } from 'vue'
import { useStore } from 'vuex'

const store = useStore()
const routing = inject('routing')

const tasks = computed(
  () => store.getters['navigation/tasks']
)
const active = computed({
  get: () => store.getters['navigation/active'],
  set: tab => store.dispatch('navigation/setActive', tab)
})
</script>
