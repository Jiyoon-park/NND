import Vue from "vue";
import App from "./App.vue";
import router from "./router";
import axios from "axios";
import store from "./store";
import vuetify from "./plugins/vuetify";
import VueCookie from "vue-cookies";

import * as firebase from "firebase/app";
  
  require('dotenv').config();

  console.log(process.env);
  console.log(`value : ${process.env.VUE_APP_APIKEY}`);
  console.log(`value : ${process.env.VUE_APP_AUTHDOMAIN}`);
  console.log(`value : ${process.env.VUE_APP_DATABASEURL}`);
  console.log(`value : ${process.env.VUE_APP_PROJECTID}`);
  console.log(`value : ${process.env.VUE_APP_STORAGEBUCKET}`);
  console.log(`value : ${process.env.VUE_APP_MESSAGINGSENDERID}`);
  console.log(`value : ${process.env.VUE_APP_APPID}`);
  console.log(`value : ${process.env.VUE_APP_MEASUREMENTID}`);
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
  console.log(`length : ${firebase.apps.length}`);
  if (!firebase.apps.length) {
    firebase.initializeApp(firebaseConfig);
  } else {
    firebase.app();
  }

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
