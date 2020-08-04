import Vue from "vue";
import App from "./App.vue";
import router from "./router";
import axios from "axios";
import store from "./store";
import vuetify from "./plugins/vuetify";
import VueCookie from "vue-cookies";
import firebase from "firebase";

Vue.prototype.$http = axios;
Vue.config.productionTip = false;
Vue.use(VueCookie);

Vue.config.productionTip = false;
var config = {
  apiKey: "<API_KEY>",
  authDomain: "<PROJECT_ID>.firebaseapp.com",
  databaseURL: "https://<DATABASE_NAME>.firebaseio.com",
  storageBucket: "<BUCKET>.appspot.com",
};
firebase.initializeApp(config);

new Vue({
  router,
  store,
  components: { App },
  vuetify,
  render: (h) => h(App),
}).$mount("#app");
