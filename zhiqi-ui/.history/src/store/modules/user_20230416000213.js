
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
    }
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
    Login() {

    }
  }
}

export default user;
