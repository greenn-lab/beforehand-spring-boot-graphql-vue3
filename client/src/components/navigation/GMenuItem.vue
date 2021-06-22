<template>
  <slot>
    <q-item
      v-ripple
      :active="menu.isActive"
      :depth="depth"
      clickable
      @click="select($event)"
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
      <div v-show="isSpread" class="g-menu__child">
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

const { menu, depth } = defineProps(['menu', 'depth'])
const routing = inject('routing')

// data
const isSpread = ref(false)

// computed
const hasChild = computed(() => !!menu.branches?.length)

// methods
const select = e => {
  if (!hasChild.value) {
    routing(menu)
  } else {
    isSpread.value =
      e.currentTarget.classList.toggle('g-menu--open')
  }
}
</script>
