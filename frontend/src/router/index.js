import Vue from "vue";
import VueRouter from "vue-router";
import Login from "../views/user/Login.vue";
import Signup from "../views/user/Signup.vue";
import Findpw from "../views/user/Findpw.vue";

Vue.use(VueRouter);

const routes = [
  {
    path: "/login",
    name: "Login",
    component: Login,
  },
  {
    path: "/signup",
    name: "Signup",
    component: Signup,
  },
  {
    path: "/findpw",
    name: "Findpw",
    component: Findpw,
  },
];

const router = new VueRouter({
  mode: "history",
  base: process.env.BASE_URL,
  routes,
});

export default router;
