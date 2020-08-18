import Vue from "vue";
import App from "./App.vue";
import router from "./router";
import axios from "axios";
import store from "./store";
import vuetify from "./plugins/vuetify";
import VueCookie from "vue-cookies";
import vuemoment from "vue-moment";

import * as firebase from "firebase/app";
  
  require('dotenv').config();

  // Your web app's Firebase configuration
  var firebaseConfig = {
    apiKey: process.env.VUE_APP_APIKEY,
    authDomain: process.env.VUE_APP_AUTHDOMAIN,
    databaseURL: process.env.VUE_APP_DATABASEURL,
    projectId: process.env.VUE_APP_PROJECTID,
    storageBucket: process.env.VUE_APP_STORAGEBUCKET,
    messagingSenderId: process.env.VUE_APP_MESSAGINGSENDERID,
    appId: process.env.VUE_APP_APPID,
    measurementId: process.env.VUE_APP_MEASUREMENTID
  };
  // Initialize Firebase
  if (!firebase.apps.length) {
    firebase.initializeApp(firebaseConfig);
  } else {
    firebase.app();
  }

Vue.use(vuemoment);

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
