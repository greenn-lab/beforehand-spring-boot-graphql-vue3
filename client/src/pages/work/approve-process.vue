<template>
  <PageDefault>
    <div>
      test
      <b
        >ipsum lorem
        {{ [1, 2, 3, 4, 5, 6, 7, 8].join(' - ') }}</b
      >
    </div>
  </PageDefault>
</template>
<script setup>
import { defineComponent } from 'vue'
import PageDefault from 'components/page/PageDefault'

defineComponent({
  components: { PageDefault }
})
</script>
