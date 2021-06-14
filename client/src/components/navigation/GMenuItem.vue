<template>
  <slot>
    <q-item
      v-ripple
      :active="menu.id === active"
      clickable
      :depth="depth"
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
      <div v-show="open" class="g-menu__child">
        <div
          v-for="childMenu in menu.children"
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
import { computed, defineProps, ref } from 'vue'
import { useStore } from 'vuex'

const { menu, depth } = defineProps(['menu', 'depth'])
const store = useStore()

// ref
const open = ref(false)

// computed
const hasChild = computed(() => !!menu.children?.length)
const active = computed(
  () => store.getters['navigation/activeMenu']
)

// methods
const select = () => {
  open.value = !open.value

  if (!hasChild.value) {
    store.dispatch('navigation/setActiveMenu', menu.id)
    store.dispatch('navigation/addTask', menu)
  }
}
</script>
