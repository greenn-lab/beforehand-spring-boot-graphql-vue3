<template>
  <g-page>
    test
    <b>ipsum lorem {{ [1, 2, 3, 4, 5].join(' - ') }}</b>

    <q-form
      class="q-gutter-md"
      @submit="() => console.log('ok')"
    >
      <q-input-date
        :rules="[value => 'are you crazy?']"
        v-model="partyTonight"
        style="width: 200px"
      >
      </q-input-date>

      <div>
        <q-btn
          color="primary"
          label="Submit"
          type="submit"
        />
      </div>
    </q-form>
  </g-page>
</template>
<script setup="props">
import { ref, watch } from 'vue'

const partyTonight = ref(null)
const time = ref(null)

watch(partyTonight, next => {
  console.log(
    'partyTonight approve-process',
    partyTonight.value
  )
})
watch(time, next => console.log('time', time.value))
</script>
