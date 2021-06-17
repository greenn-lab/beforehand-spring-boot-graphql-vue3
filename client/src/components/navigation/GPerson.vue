<template>
  <div class="g-person">
    <div class="g-person__header">
      <div class="g-person__logo">
        <q-icon name="science" size="md" />
      </div>
      <q-btn
        flat
        icon="ion-ios-notifications-outline"
        round
      >
        <q-badge color="negative" floating rounded
          >3</q-badge
        >
      </q-btn>
      <q-btn flat icon="ion-ios-contact" round>
        <q-menu anchor="bottom right" self="top right">
          <q-list style="width: 248px">
            <q-item>
              <q-item-section class="g-person__logged">
                <span class="text-caption"
                  >Logged in as</span
                >
                <span class="text-subtitle2"
                  >greensrum@gmail.com</span
                >
              </q-item-section>
            </q-item>
            <q-separator />
            <q-item v-close-popup clickable>
              <q-item-section avatar>
                <q-icon name="ion-ios-finger-print" />
              </q-item-section>
              <q-item-section>Edit profile</q-item-section>
            </q-item>
            <q-item v-close-popup clickable>
              <q-item-section avatar>
                <q-icon name="ion-ios-cog"></q-icon>
              </q-item-section>
              <q-item-section>Settings</q-item-section>
            </q-item>
            <q-item v-close-popup clickable>
              <q-item-section avatar>
                <q-icon name="ion-ios-flame" />
              </q-item-section>
              <q-item-section>Status</q-item-section>
            </q-item>
            <q-separator />
            <q-item v-close-popup clickable>
              <q-item-section avatar>
                <q-icon name="ion-ios-log-out" />
              </q-item-section>
              <q-item-section>Logout</q-item-section>
            </q-item>
          </q-list>
        </q-menu>
      </q-btn>
    </div>
    <div class="g-person__main">
      <q-avatar color="secondary">
        <span>A</span>
        <!--<img src="~assets/person.png" alt="It's you!"/>-->
      </q-avatar>
      <div class="g-person__name">Anonymous Member</div>
      <div class="g-person__team">@PR Team</div>
    </div>
  </div>
</template>
