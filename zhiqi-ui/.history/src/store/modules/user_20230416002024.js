import { login, getToken } from '@/api/login'

const user = {
  state: {
    token: getToken(),
    name: '',
    avatar: '',
    roles: [],
    permissions: []
  },
  mutations: {
    SET_TOKEN: (state, token) {
      state.token = token
    },
    SET_NAME: (state, name) {
      state.name = name;
    },
    SET_AVATAR: (state, avatar) {
      state.avatar = avatar;
    },
    SET_ROLES: (state, roles) {
      state.roles = roles;
    },
    SET_PERMISSIONS: (state, permissions) {
      state.permissions = permissions;

    },
    actions: {
      Login({ commit }, userInfo) {
        const username = userInfo.username.trim()
        const password = userInfo.password
        const code = userInfo.code
        const uuid = userInfo.uuid
        return new Promise((resolve, reject) => {
          login(username, password, code, uuid).then(res => {

          })
          resolve()
        }).catch(error => {
          reject(error)
        })
      }
    }
  }

export default user;
