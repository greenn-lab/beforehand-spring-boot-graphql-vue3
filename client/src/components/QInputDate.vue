<template>
  <q-input
    dense
    mask="####-##-##"
    @click="this.$refs.popup.show()"
  >
    <template v-slot:append>
      <q-icon name="event">
        <q-popup-proxy
          ref="popup"
          transition-hide="scale"
          transition-show="scale"
        >
          <q-date
            v-model="date"
            no-unset
            :locale="dayAndMonthNames"
          />
        </q-popup-proxy>
      </q-icon>
    </template>
  </q-input>
</template>

<script>
const DAYS = '일월화수목금토'.split('')
const MONTHS = [
  '정월',
  '이월',
  '삼월',
  '사월',
  '오월',
  '유월',
  '칠월',
  '팔월',
  '구월',
  '시월',
  '동짓달',
  '섣달'
]

export default {
  data() {
    return {
      date: null,
      dayAndMonthNames: {
        days: DAYS,
        daysShort: DAYS,
        months: MONTHS,
        monthsShort: MONTHS,
        firstDayOfWeek: 0
      }
    }
  },
  watch: {
    date(next) {
      this.$emit('update:modelValue', next)
      this.$refs.popup.hide()
    }
  }
}
</script>
