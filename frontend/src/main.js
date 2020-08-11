import Vue from "vue";
import App from "./App.vue";
import router from "./router";
import axios from "axios";
import store from "./store";
import vuetify from "./plugins/vuetify";
import VueCookie from "vue-cookies";

export const EventBus = new Vue();   

Vue.prototype.$http = axios;
Vue.config.productionTip = false;
Vue.use(VueCookie);

Vue.config.productionTip = false;

new Vue({
  router,
  store,
  components: { App },
  vuetify,
  render: (h) => h(App),
}).$mount("#app");
