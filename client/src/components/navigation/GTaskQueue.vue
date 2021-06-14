<template>
  <div class="g-task-queue col q-pl-xl">
    <q-tabs v-model="tab" align="right" inline-label>
      <q-tab
        v-for="task in tasks"
        :key="task.id"
        :label="task.name"
        name="task.id"
      />
      <q-tab label="Mails" name="mails" />
      <q-tab label="Alarms" name="alarms" />
      <q-tab label="Movies" name="movies" />
      <q-tab label="Photos" name="photos" />
      <q-tab label="Videos" name="videos" />
      <q-tab label="Address Book" name="addressbook" />
    </q-tabs>
  </div>
</template>

<script setup>
import { useStore } from 'vuex'
import { computed } from 'vue'

const store = useStore()

// computed
const tasks = computed(
  () => store.getters['navigation/tasks']
)
</script>
