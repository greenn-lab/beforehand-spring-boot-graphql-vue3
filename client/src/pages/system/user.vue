<template>
  <g-page>
    <q-table
      title="Users"
      :columns="columns"
      :rows="users"
      row-key="id"
    />
    {{ users }}
  </g-page>
</template>

<script setup>
import { ref } from 'vue'
import { graphql } from 'boot/axios'

const columns = ref([
  {
    field: row => row.username,
    label: 'ID',
    align: 'left',
    required: true
  },
  {
    field: row => row.email,
    label: 'E-mail'
  },
  {
    field: row => row.named.name,
    label: 'Name'
  },
  {
    field: row => row.locked,
    label: 'Locked'
  },
  {
    field: row => row.created,
    label: 'Join'
  },
  {
    field: row => row.passwordExpired,
    label: 'expired'
  }
])

const users = ref([])

;(async () => {
  await graphql(`
    query {
      users(page: 0) {
        id
        email
        named {
          name
          familyName
          nickname
        }
        username
        locked
        passwordExpired
      }
    }
  `).then(data => {
    users.value = data.users
  })
})()
</script>
