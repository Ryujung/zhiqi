import request from '@/util/request'

export function login(username, password, code, uuid) {
  const data = {
    username, password, code, uuid
  }
  return request({
    data,
    url: '/login',
    header: {},
    method: 'post',
  })
}

export function register(data) {

  return request({
    data,
    url: '/register',
    header: {},
    method: 'post',
  })
}

export function getInfo() {
  return request({
    url: '/getInfo',
    method: 'get',
  })
}

export function logout() {
  return request({
    url: '/logout',
    method: 'post',
  })
}

export function getCodeImg() {
  return request({
    url: '/getCodeImg',
    header: {},
    method: 'get',
    timeout: 20000
  })
}
