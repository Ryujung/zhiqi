import { login, getToken, getInfo } from '@/api/login'
import { getToken, setToken } from '@/util/auth'
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
            setToken(res.token)
            commit('SET_TOKEN', res.token)
            resolve()
          }).catch(error => {
            reject(error)
          })
        })
      },
      GetInfo({ commit, state }) {
        return new Promise((resolve, reject => {

        }))
      }
    }
  }
}

export default user;
