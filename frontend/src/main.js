import Vue from 'vue'
import App from './App.vue'
import Router from 'vue-router'
import iView from 'iview'
import 'iview/dist/styles/iview.css'
import request from '@/utils/request'

Vue.config.productionTip = false
Vue.use(Router)
Vue.use(iView)
Vue.prototype.$http = request

const router = new Router({
  routes: [
    {
      path: '/',
      name: 'login',
      component: () => import('@/view/login/index')
    },
    {
      path: '/main',
      name: 'main',
      component: () => import('@/view/main/index')
    }
  ]
})
new Vue({
  render: h => h(App),
  router
}).$mount('#app')
