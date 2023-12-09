<template>
  <div>
    <AyncNotification
      v-bind:class="{ active: showAsyncNotification }"
    />
    <div class="main">
      <CreateAndUpdateForm
        v-bind:class="{ active: formActive }"
        :openForm="setFormActive"
        @getCreatedRecord="handleGetCreatedRecord"
        @getUpdatedRecord="handleGetUpdatedRecord"
      />
      <PhoneBook
        :openForm="setFormActive"
        @setRecordForUpdate="handleSetRecordForUpdate"
        @asyncNotification="handleAsyncNotification"
      />
    </div>
  </div>
</template>

<script>
import PhoneBook from '@/components/PhoneBookTable.vue'
import CreateAndUpdateForm from '@/components/CreateAndUpdateForm.vue'
import AyncNotification from '@/components/AsyncCompletedNotification.vue'

export default {
  name: 'PhoneBookView',
  components: {
    PhoneBook,
    CreateAndUpdateForm,
    AyncNotification
  },
  data () {
    return {
      formActive: false,
      showAsyncNotification: false
    }
  },
  methods: {
    setFormActive (isActive) {
      this.formActive = isActive
    },
    handleGetCreatedRecord (record) {
      this.$emit('addCreatedRecord', record)
    },
    handleSetRecordForUpdate (record) {
      this.$emit('getRecordForUpdate', record)
    },
    handleGetUpdatedRecord (record) {
      this.$emit('updateRecord', record)
    },
    handleAsyncNotification (record) {
      this.showAsyncNotification = true
      setTimeout(() => {
        this.showAsyncNotification = false
      }, 4000)
    }
  }
}
</script>

<style scoped>

 .bg_form.active{
  display: block;
 }
 .notification.active {
  opacity: 1;
  visibility: visible;
 }
</style>
