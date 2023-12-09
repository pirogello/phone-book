<template>
  <div class="bg_form">
    <div class="container_form">
      <div class="record_data">
        <div class="container_input">
          <label for="name">Name:</label>
          <input v-model="name" id="name" pattern="^([A-z]{3,50}|[А-я]{3,50})$" placeholder="Александр">
          <p v-bind:class="{ active: nameError }">Name must be 3-50 characters in length.</p>
        </div>
        <div class="container_input">
          <label for="phone">Phone:</label>
          <input v-model="phone" id="phone" pattern="^((8|\+7)\(\d{3}\)\d{3}-\d{2}-\d{2})$" placeholder="+7(999)999-99-99">
          <p v-bind:class="{ active: phoneError }">Phone number must be like +7(999)999-99-99</p>
        </div>
      </div>
      <div class="func_btns">
        <button class="button btn_close" v-on:click="closeForm">Отмена</button>
        <button class="button btn_save" v-on:click="id?update():create()">Cохранить</button>
      </div>
    </div>
  </div>
</template>

<script>
import * as api from '@/api/ajax-response.js'

export default {
  name: 'CreateAndUpdateForm',
  props: {
    openForm: {
      type: Function
    }
  },
  data () {
    return {
      id: '',
      name: '',
      phone: '',
      phoneError: false,
      nameError: false
    }
  },
  methods: {
    validatePhone () {
      const str = this.phone
      // eslint-disable-next-line
      let res = str.match(/^((8|\+7)\(\d{3}\)\d{3}-\d{2}-\d{2})$/)
      if (res == null) return false
      else return true
    },
    validateName () {
      const str = this.name
      // eslint-disable-next-line
      let res = str.match(/^([A-z]{3,50}|[А-я]{3,50})$/)
      if (res == null) return false
      else return true
    },
    closeForm () {
      this.id = ''
      this.name = ''
      this.phone = ''
      this.openForm(false)
    },
    create: async function () {
      if (!this.validatePhone()) this.phoneError = true
      if (!this.validateName()) this.nameError = true
      if (!this.phoneError && !this.nameError) {
        const record = {
          name: this.name,
          phoneNumber: this.phone
        }
        const response = await api.createRecord(JSON.stringify(record))
        this.closeForm()
        this.$emit('getCreatedRecord', {
          id: response.id,
          name: response.name,
          phoneNumber: response.phoneNumber,
          lastUpdate: response.updated
        })
      }
    },
    update: async function () {
      if (!this.validatePhone()) this.phoneError = true
      if (!this.validateName()) this.nameError = true
      if (!this.phoneError && !this.nameError) {
        const record = {
          name: this.name,
          phoneNumber: this.phone
        }
        const response = await api.updateRecord(this.id, JSON.stringify(record))
        this.closeForm()
        this.$emit('getUpdatedRecord', {
          id: response.id,
          name: response.name,
          phoneNumber: response.phoneNumber,
          lastUpdate: response.updated
        })
      }
    },
    openUpdateRecordForm (record) {
      this.id = record.id
      this.name = record.name
      this.phone = record.phoneNumber
      this.openForm(true)
    }
  },
  watch: {
    phone: function () {
      this.phoneError = false
    },
    name: function () {
      this.nameError = false
    }
  },
  created: function () {
    this.$parent.$on('getRecordForUpdate', this.openUpdateRecordForm)
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
.container_form {
  width: 400px;
  border-radius: 20px;
  background-color: #EBE6E6;
  box-shadow: 0px 0px 30px rgba(0, 0, 0, .5);
  position: absolute;
  z-index: 9999;
  left: 50%;
  top: 150px;
  transform: translateX(-50%);
  padding: 30px 20px;
}

.record_data {
  display: flex;
  flex-direction: column;
}
.container_input{
  display: grid;
  grid-template-columns: 20% 1fr;
  margin-bottom: 15px;
  align-items: center;
  grid-template-areas:
    "label input"
    ". p";
}
label{
  margin-right: 20px;
  grid-area: label;
}
input{
  grid-area: input;
  max-width: 288px;
  padding: 8px 13px;
  outline: none;
  border-radius: 10px;
  border: 2px solid #000;
  width: 100%;
}

input:invalid {
  border: red solid 3px;
}

p {
  grid-area: p;
  margin-top: 7px;
  font-size: 12px;
  text-align: start;
  display: none;
}

.active{
  display: block;
}

.bg_form{
  position: absolute;
  z-index: 3;
  top: 0;
  left: 0;
  height: 100%;
  width: 100%;
  background: #ff896bcb;
  display: none;
  border-radius: 20px;
}
.btn_close{
  background: #ff5e5e;
}
</style>
