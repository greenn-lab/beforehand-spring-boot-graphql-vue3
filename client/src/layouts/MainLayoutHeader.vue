<template>
  <q-header>
    <q-toolbar>
      <div class="col">
        <q-btn
          class="q-ml-md"
          dense
          flat
          icon="menu"
          round
          @click="$emit('menuToggle')"
        />
      </div>
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
    </q-toolbar>
  </q-header>
</template>

<script setup></script>

<style lang="scss" scoped>
.col-shrink {
  .q-btn {
    margin-right: 16px;
  }
}
</style>
