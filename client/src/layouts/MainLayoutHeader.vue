<template>
  <q-header>
    <q-toolbar class="q-gutter-none">
      <q-btn
        dense
        flat
        icon="menu"
        round
        @click="$emit('menuToggle')"
      />
      <q-space />
      <g-task-queue />

      <!--
      <div class="col-shrink text-right">
        <q-btn
          dense
          flat
          icon="ion-ios-search"
          round
          @click="$emit('menuToggle')"
        />
        <q-btn
          dense
          flat
          icon="ion-ios-bookmark"
          round
          @click="$emit('menuToggle')"
        >
          <q-tooltip anchor="center start" self="center end">
            Favorite your menus
          </q-tooltip>
        </q-btn>
        <q-btn
          dense
          flat
          icon="ion-ios-chatboxes"
          round
          @click="$emit('menuToggle')"
        >
          <q-tooltip anchor="center start" self="center end"
            >Live chat</q-tooltip
          >
        </q-btn>
      </div>
-->
    </q-toolbar>
  </q-header>
</template>

<script setup>
import { defineComponent } from 'vue'
import GTaskQueue from '../components/navigation/GTaskQueue'

defineComponent([GTaskQueue])
</script>
