<template>
  <g-page>
    <div class="q-pa-md row q-col-gutter-sm">
      <q-tree
        v-model:selected="selected"
        v-model:expanded="expanded"
        v-model:ticked="ticked"
        :nodes="simple"
        class="col-12 col-sm-6"
        default-expand-all
        node-key="id"
        tick-strategy="leaf-filtered"
      />
      <div class="col-12 col-md-6">
        <div class="text-h6 q-mt-md">Ticked</div>
        <div>
          <div
            v-for="tick in ticked"
            :key="`ticked-${tick}`"
          >
            {{ tick }}
            <hr />
            {{ selected }}
          </div>
        </div>
      </div>
    </div>
  </g-page>
</template>

<script setup>
import { computed, ref } from 'vue'
import { useStore } from 'vuex'

const store = useStore()

// data
const selected = ref(null)
const ticked = ref([])
const expanded = ref([])

// computed
const simple = computed(() => {
  const menuToTree = (menus = []) =>
    menus.map(menu => ({
      id: menu.id,
      label: menu.name,
      children: menuToTree(menu.branches)
    }))

  const children = menuToTree(
    store.getters['navigation/menus']
  )

  return [
    {
      id: '0',
      label: 'root',
      children
    }
  ]
})
</script>
