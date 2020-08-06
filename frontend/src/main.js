import Vue from "vue";
import App from "./App.vue";
import router from "./router";
import axios from "axios";
import store from "./store";
import vuetify from "./plugins/vuetify";
import VueCookie from "vue-cookies";
import firebase from "firebase";

const eventBus = new Vue();
export default eventBus;

Vue.prototype.$http = axios;
Vue.config.productionTip = false;
Vue.use(VueCookie);

Vue.config.productionTip = false;
var config = {
  apiKey: "AIzaSyDrGjK4k58OdMBChAfYWlml_m6LwPF-vh0",
  authDomain: "signup-95e15.firebaseapp.com",
  databaseURL: "https://signup-95e15.firebaseio.com",
  projectId: "signup-95e15",
  storageBucket: "signup-95e15.appspot.com",
  messagingSenderId: "779627961819",
  appId: "1:779627961819:web:6be596853eb5be2b67dbfe",
  measurementId: "G-G6LZ10T7SW",
};
firebase.initializeApp(config);

new Vue({
  router,
  store,
  components: { App },
  vuetify,
  render: (h) => h(App),
}).$mount("#app");
