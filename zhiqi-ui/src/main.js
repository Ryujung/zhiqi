import Vue from 'vue'
import Cookies from 'js-cookie'
import Element from 'element-ui'
import App from './App.vue'
import store from './store'
import router from './router'

// 头部标签组件
import VueMeta from 'vue-meta'

Vue.use(VueMeta)

/**
 * If you don't want to use mock-server
 * you want to use MockJs for mock api
 * you can execute: mockXHR()
 *
 * Currently MockJs will be used in the production environment,
 * please remove it before going online! ! !
 */
Vue.use(Element, {
  size: Cookies.get('size') || 'medium',
  zIndex: 3000
})

Vue.config.productionTip = false

new Vue({
  el: '#app',
  router,
  store,
  render: h => h(App)
})
