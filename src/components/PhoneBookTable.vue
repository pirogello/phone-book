<template>
  <div class="container">
    <div class="func_btn">
      <button class="button" v-on:click="updateAll()">Обновить все</button>
      <button class="button" v-on:click="openForm(true)">Создать запись</button>
    </div>
    <table>
      <tr>
        <td class="name_of_column" v-for="name in namesOfTableColumn" v-bind:key="name">
          {{ name }}
        </td>
      </tr>
      <tr class="row_record" v-for="record in records" v-bind:key="record.id">
        <td class="column_record">
          {{record.name}}
        </td>
        <td class="column_record">
          {{record.phoneNumber}}
        </td>
        <td class="column_record">
          {{record.lastUpdate}}
        </td>
        <td class="no_border">
          <button class="button btn-edit" v-on:click="setRecordForUpdate(record)">Редактировать</button>
        </td>
        <td class="no_border">
          <button class="button btn-del" v-on:click="deleteRecord(record.id)">Удалить</button>
        </td>
      </tr>
    </table>
  </div>
</template>

<script>
import * as api from '@/api/ajax-response.js'
import Vue from 'vue'

export default {
  name: 'PhoneBook',
  props: {
    openForm: {
      type: Function
    }
  },
  data () {
    return {
      namesOfTableColumn: [
        'Имя',
        'Номер',
        'Последнее обновление'
      ],
      records: []
    }
  },
  methods: {
    updateAll: async function () {
      const response = await api.updateAllRecordsAsync()
      const res = []
      for (let i = 0; i < response.length; i++) {
        res[i] = this.mapToRecord(response[i])
      }
      this.records = res
      this.$emit('asyncNotification')
    },
    mapToRecord: function (responseRecord) {
      return {
        id: responseRecord.id,
        name: responseRecord.name,
        phoneNumber: responseRecord.phoneNumber,
        lastUpdate: responseRecord.updated
      }
    },
    addCreatedRecord: function (createRecord) {
      this.records.push(createRecord)
    },
    updateRecord (record) {
      for (let i = 0; i < this.records.length; i++) {
        if (this.records[i].id === record.id) {
          Vue.set(this.records, i, record)
          break
        }
      }
    },
    deleteRecord (id) {
      this.records.splice(id, 1)
      api.deleteRecordById(id)
    },
    setRecordForUpdate (record) {
      this.$emit('setRecordForUpdate', {
        id: record.id,
        name: record.name,
        phoneNumber: record.phoneNumber
      })
    }
  },
  beforeCreate: async function () {
    const response = await api.getAllRecords()
    const res = []
    for (let i = 0; i < response.length; i++) {
      res[i] = this.mapToRecord(response[i])
    }
    this.records = res
  },
  created: function () {
    this.$parent.$on('addCreatedRecord', this.addCreatedRecord)
    this.$parent.$on('updateRecord', this.updateRecord)
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
.container {
  display: inline-flex;
  flex-direction: column;
  align-items: start;
}

table {
  border-collapse: collapse;
}

.name_of_column {
  background-color: #e6e0e0;
  padding: 10px 30px;
  font-weight: bold;
  text-transform: uppercase;
  color: #4b4b4b;
}

.column_record {
  background-color: rgb(240, 240, 240);
  padding: 10px 10px;
}

.reload_btn {
  height: 1em;
}

td {
  border: 4px solid #E8D4B4;
  background: #F3F3F3;
}
tr:last-child > td:nth-child(1){
  border-radius: 0 0 0 20px;
}
tr:last-child > td:nth-child(4){
  border-radius: 0 0 20px 0;
}

tr:first-child > td:nth-child(1){
  border-radius: 20px 0 0 0;
}
tr:first-child > td:nth-child(4){
  border-radius: 0 20px 0 0;
}

.no_border {
  border: none;
  background: none;
}
.func_btn {
  /* margin: 5px; */
  margin-bottom: 10px;
}
.btn-edit {
  font-size: 14px;
  margin-right: 5px;
  padding: 5px 10px;
}
.btn-del {
  font-size: 14px;
  padding: 5px 10px;
  background: #ff5e5e;
}
</style>
