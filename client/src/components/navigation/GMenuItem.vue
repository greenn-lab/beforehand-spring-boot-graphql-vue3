<template>
  <slot>
    <q-item
      v-ripple
      :active="menu.id === active"
      :depth="depth"
      clickable
      @click="select"
    >
      <q-item-section v-if="depth === 1" avatar>
        <q-icon :name="menu.icon || 'extension'" />
      </q-item-section>
      <q-item-section>{{ menu.name }}</q-item-section>
      <q-btn
        v-if="hasChild"
        class="g-menu__more"
        flat
        icon="ion-ios-arrow-forward"
      />
    </q-item>

    <q-slide-transition>
      <div v-show="isSpreadBranches" class="g-menu__child">
        <div
          v-for="childMenu in menu.branches"
          :key="childMenu.id"
          class="g-menu__item"
        >
          <g-menu-item
            :depth="depth + 1"
            :menu="childMenu"
          />
        </div>
      </div>
    </q-slide-transition>
  </slot>
</template>

<script setup>
import { computed, defineProps, inject, ref } from 'vue'
import { useStore } from 'vuex'

const { menu, depth } = defineProps(['menu', 'depth'])
const store = useStore()
const routing = inject('routing')

// ref
const isSpreadBranches = ref(false)

// computed
const hasChild = computed(() => !!menu.branches?.length)
const active = computed(
  () => store.getters['navigation/active'].id
)

// methods
const select = () => {
  isSpreadBranches.value = !isSpreadBranches.value

  if (!hasChild.value) {
    store.dispatch('navigation/setActive', menu)
  }

  routing(menu)
}
</script>
