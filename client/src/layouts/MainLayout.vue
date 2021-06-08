<template>
  <q-layout view="lHh LpR lFf">
    <q-header
      :class="$q.dark.isActive ? 'header_dark' : 'header_normal'"
      reveal
    >
      <q-toolbar>
        <q-btn
          class="q-mr-sm"
          dense
          flat
          icon="menu"
          round
          @click="left = !left"
        />
        <q-toolbar-title>Client</q-toolbar-title>
        <q-btn
          :icon="$q.dark.isActive ? 'nights_stay' : 'wb_sunny'"
          class="q-mr-xs"
          flat
          round
          @click="$q.dark.toggle()"
        />
        <a
          class="float-right q-mr-sm"
          href="https://github.com/sponsors/mayank091193"
          style="font-size: 25px;"
          target="_blank"
          title="Donate"
        ><i class="fas fa-heart" style="color: #eb5daa"></i
        ></a>
        <q-btn class="q-mr-xs" dense flat icon="search" round/>
        <q-btn
          dense
          flat
          icon="fas fa-sign-out-alt"
          round
          @click="logoutNotify"
        />
      </q-toolbar>
    </q-header>
    <q-drawer
      v-model="left"
      class="left-navigation text-white"
      elevated
      show-if-above
      side="left"
    >
      <div
        :class="$q.dark.isActive ? 'drawer_dark' : 'drawer_normal'"
        class="full-height"
      >
        <q-scroll-area :thumb-style="{width:'3px'}" style="height:100%">
          <q-list padding>
            <q-item
              v-ripple
              active-class="tab-active"
              class="q-ma-sm navigation-item"
              clickable
              exact
              to="/dashboard"
            >
              <q-item-section avatar>
                <q-icon name="dashboard"/>
              </q-item-section>

              <q-item-section>
                Dashboard v1
              </q-item-section>
            </q-item>
            <q-item
              v-ripple
              active-class="tab-active"
              class="q-ma-sm navigation-item"
              clickable
              exact
              to="/dashboard"
            >
              <q-item-section avatar>
                <q-icon name="dashboard"/>
              </q-item-section>

              <q-item-section>
                Dashboard v1
              </q-item-section>
            </q-item>

            <q-item
              v-ripple
              active-class="tab-active"
              class="q-ma-sm navigation-item"
              clickable
              exact
              to="/dashboard_v2"
            >
              <q-item-section avatar>
                <q-icon name="dashboard"/>
              </q-item-section>

              <q-item-section>
                Dashboard v2
              </q-item-section>
            </q-item>

            <q-item
              v-ripple
              active-class="tab-active"
              class="q-ma-sm navigation-item"
              clickable
              exact
              to="/dashboard_v3"
            >
              <q-item-section avatar>
                <q-icon name="dashboard"/>
              </q-item-section>

              <q-item-section>
                Dashboard v3
              </q-item-section>
            </q-item>

            <q-item
              v-ripple
              active-class="tab-active"
              class="q-ma-sm navigation-item"
              clickable
              to="/customer_management"
            >
              <q-item-section avatar>
                <q-icon name="star"/>
              </q-item-section>

              <q-item-section>
                Customer Management
              </q-item-section>
            </q-item>

            <q-item
              v-ripple
              active-class="tab-active"
              class="q-ma-sm navigation-item"
              clickable
              to="/change_request"
            >
              <q-item-section avatar>
                <q-icon name="send"/>
              </q-item-section>

              <q-item-section>
                Change Request
              </q-item-section>
            </q-item>

            <q-item
              v-ripple
              active-class="tab-active"
              class="q-ma-sm navigation-item"
              clickable
              to="/sales_invoices"
            >
              <q-item-section avatar>
                <q-icon name="attach_money"/>
              </q-item-section>

              <q-item-section>
                Sales Invoices
              </q-item-section>
            </q-item>

            <q-item
              v-ripple
              active-class="tab-active"
              class="q-ma-sm navigation-item"
              clickable
              to="/quotes"
            >
              <q-item-section avatar>
                <q-icon name="money"/>
              </q-item-section>

              <q-item-section>
                Quotes
              </q-item-section>
            </q-item>

            <q-item
              v-ripple
              active-class="tab-active"
              class="q-ma-sm navigation-item"
              clickable
              to="/transactions"
            >
              <q-item-section avatar>
                <q-icon name="assignment"/>
              </q-item-section>

              <q-item-section>
                Transactions
              </q-item-section>
            </q-item>

            <q-item
              v-ripple
              active-class="tab-active"
              class="q-ma-sm navigation-item"
              clickable
              to="/employee_salary_list"
            >
              <q-item-section avatar>
                <q-icon name="list"/>
              </q-item-section>

              <q-item-section>
                Employee Salary List
              </q-item-section>
            </q-item>

            <q-item
              v-ripple
              active-class="tab-active"
              class="q-ma-sm navigation-item"
              clickable
              to="/calendar"
            >
              <q-item-section avatar>
                <q-icon name="calendar_today"/>
              </q-item-section>

              <q-item-section>
                Calendar
              </q-item-section>
            </q-item>

            <q-item
              v-ripple
              active-class="tab-active"
              class="q-ma-sm navigation-item"
              clickable
              to="/department"
            >
              <q-item-section avatar>
                <q-icon name="business"/>
              </q-item-section>

              <q-item-section>
                Department
              </q-item-section>
            </q-item>

            <q-item
              v-ripple
              active-class="tab-active"
              class="q-ma-sm navigation-item"
              clickable
              to="/my_profile"
            >
              <q-item-section avatar>
                <q-icon name="drafts"/>
              </q-item-section>

              <q-item-section>
                My Profile
              </q-item-section>
            </q-item>
          </q-list>
        </q-scroll-area>
      </div>
    </q-drawer>

    <q-page-container>
      <q-page class="row no-wrap">
        <div class="col">
          <div class="full-height">
            <q-scroll-area class="col q-pr-sm full-height" visible>
              <router-view/>
            </q-scroll-area>
          </div>
        </div>
      </q-page>
    </q-page-container>
  </q-layout>
</template>

<script setup>
import {ref} from 'vue'
import {useQuasar} from 'quasar'

const left = ref(false)

const {notify} = useQuasar()

const logoutNotify = function () {
  notify({
    position: 'top-right',
    message: 'Logged out'
  })
}
</script>

<style></style>
