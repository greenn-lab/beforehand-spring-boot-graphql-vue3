<template>
  <q-list class="g-menu" padding>
    <slot>
      <div class="g-menu__title">
        <div class="g-menu__title-name">Main Works</div>
        <div class="g-menu__title-desc">Remember an earning yours meal!</div>
      </div>

      <q-item v-ripple clickable>
        <q-item-section avatar>
          <q-icon name="inbox"/>
        </q-item-section>
        <q-item-section>Inbox</q-item-section>
        <q-btn class="g-menu__more" flat icon="ion-ios-arrow-forward"/>
      </q-item>

      <div class="g-menu__child">
        <q-item v-ripple clickable data-depth="1">
          <q-item-section>unread</q-item-section>
        </q-item>
        <q-item v-ripple clickable data-depth="1">
          <q-item-section>sent</q-item-section>
        </q-item>
        <q-item v-ripple clickable data-depth="1">
          <q-item-section>spams</q-item-section>
          <q-btn class="g-menu__more" flat icon="ion-ios-arrow-forward"/>
        </q-item>

        <div class="g-menu__child">
          <q-item v-ripple clickable data-depth="1">
            <q-item-section>unread 1</q-item-section>
          </q-item>

          <div class="g-menu__child">
            <q-item v-ripple clickable data-depth="1">
              <q-item-section>unread 2</q-item-section>
            </q-item>
          </div>
        </div>
      </div>
    </slot>

    <q-item v-ripple active clickable>
      <q-item-section avatar>
        <q-icon name="star"/>
      </q-item-section>

      <q-item-section>
        Star
      </q-item-section>
    </q-item>

    <q-item v-ripple clickable>
      <q-item-section avatar>
        <q-icon name="send"/>
      </q-item-section>

      <q-item-section>
        Send
      </q-item-section>
    </q-item>

    <q-item v-ripple clickable>
      <q-item-section avatar>
        <q-icon name="drafts"/>
      </q-item-section>

      <q-item-section>
        Drafts
      </q-item-section>
    </q-item>
  </q-list>
</template>

<script setup>
import {onMounted} from 'vue'
import {useStore} from 'vuex'

const store = useStore()

onMounted(() => {
  console.log(store.getters.menu[0])
})

</script>
