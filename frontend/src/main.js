import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import vuetify from './plugins/vuetify';
import VueCookie from 'vue-cookies';

Vue.config.productionTip = false
Vue.use(VueCookie)

new Vue({
  router,
  store,
  components: { App },
  vuetify,
  render: h => h(App)
}).$mount('#app')
