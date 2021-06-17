<template>
  <q-tabs
    v-model="tab"
    class="g-task"
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
import { computed, inject, ref, watch } from 'vue'
import { useStore } from 'vuex'

const store = useStore()
const routing = inject('routing')

// computed
const tasks = computed(
  () => store.getters['navigation/tasks']
)
const active = computed(
  () => store.getters['navigation/active']
)

// ref
const tab = ref(active.value.id)

// watch
watch(tab, next => {
  store.dispatch(
    'navigation/setActive',
    tasks.value.find(task => task.id === tab.value)
  )
})

watch(active, next => (tab.value = next.id))
</script>
