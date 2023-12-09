const host = 'http://localhost:9005'

export function getAllRecords () {
  console.log('GET - http://localhost:9005/api/v1/record')
  const request = new XMLHttpRequest()
  return new Promise(function (resolve, reject) {
    request.open('GET', host + '/api/v1/record')
    request.onload = function () {
      if (request.status === 200) {
        resolve(JSON.parse(request.responseText))
      } else {
        reject(new Error('error ajax in getAllRecords'))
      }
    }
    request.send()
  })
}

export function createRecord (record) {
  console.log('POST - http://localhost:9005/api/v1/record')
  const request = new XMLHttpRequest()
  return new Promise(function (resolve, reject) {
    request.open('POST', host + '/api/v1/record')
    request.setRequestHeader('Content-Type', 'application/json')
    request.onload = function () {
      if (request.status === 201) {
        resolve(JSON.parse(request.responseText))
      } else {
        reject(new Error('error ajax in createRecord'))
      }
    }
    request.send(record)
  })
}

export function updateRecord (id, record) {
  console.log('PUT - http://localhost:9005/api/v1/record' + id)
  const request = new XMLHttpRequest()
  return new Promise(function (resolve, reject) {
    request.open('PUT', host + '/api/v1/record/' + id)
    request.setRequestHeader('Content-Type', 'application/json')
    request.onload = function () {
      if (request.status === 200) {
        resolve(JSON.parse(request.responseText))
      } else {
        reject(new Error('error ajax in updateRecord'))
      }
    }
    request.send(record)
  })
}

export function deleteRecordById (id) {
  console.log('DELETE - http://localhost:9005/api/v1/record' + id)
  const request = new XMLHttpRequest()
  return new Promise(function (resolve, reject) {
    request.open('DELETE', host + '/api/v1/record/' + id)
    request.onload = function () {
      if (request.status === 200) {
        console.log('Successfuly deleted')
      } else {
        reject(new Error('error ajax in updateRecord'))
      }
    }
    request.send()
  })
}

export function updateAllRecordsAsync () {
  console.log('async GET - http://localhost:9005/api/v1/record/async-update')
  const request = new XMLHttpRequest()
  return new Promise(function (resolve, reject) {
    request.open('GET', host + '/api/v1/record/async-update')
    request.onload = function () {
      if (request.status === 200) {
        resolve(JSON.parse(request.responseText))
      } else {
        reject(new Error('error ajax in updateAllRecordsAsync'))
      }
    }
    request.send()
  })
}
