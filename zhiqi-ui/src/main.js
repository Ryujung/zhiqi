import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import Element from 'element-ui'
import Cookies from 'js-cookie'

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
Vue.config.productionTip = false
Vue.use(Element, {
  size: Cookies.get('size') || 'medium'
})

new Vue({
  el: '#app',
  router,
  store,
  render: h => h(App)
})
