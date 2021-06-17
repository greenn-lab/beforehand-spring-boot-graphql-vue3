<template>
  <main class="g-page">
    test
    <b>ipsum lorem {{ [1, 2, 3, 4, 5].join(' - ') }}</b>

    <q-form
      @submit="() => console.log('ok')"
      class="q-gutter-md"
    >
      <q-input
        filled
        v-model="date"
        mask="date"
        :rules="['date']"
      >
        <template v-slot:append>
          <q-icon name="event" class="cursor-pointer">
            <q-popup-proxy
              ref="qDateProxy"
              transition-show="scale"
              transition-hide="scale"
            >
              <q-date v-model="date">
                <div class="row items-center">
                  <q-btn
                    v-close-popup
                    label="Close"
                    color="primary"
                    flat
                  />
                  <q-btn
                    v-close-popup
                    label="Close"
                    color="primary"
                    flat
                  />
                </div>
              </q-date>
            </q-popup-proxy>
          </q-icon>
        </template>
      </q-input>

      <div>
        <q-btn
          label="Submit"
          type="submit"
          color="primary"
        />
      </div>
    </q-form>
  </main>
</template>
<script setup>
import { ref, watch } from 'vue'

const date = ref(null)
const time = ref(null)

watch(date, next => console.log('date', date.value))
watch(time, next => console.log('time', time.value))
</script>
