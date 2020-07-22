import Vue from "vue";
import VueRouter from "vue-router";
import Home from "../views/Home.vue";
import NavBar from "../components/common/NavBar.vue";
import NewsFeed from "../components/common/NewsFeed.vue";

Vue.use(VueRouter);

const routes = [
  {
    path: "/",
    name: "Home",
    component: Home,
  },
  {
    path: "/about",
    name: "About",
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () =>
      import(/* webpackChunkName: "about" */ "../views/About.vue"),
  },
  {
    path: "/navbar",
    name: "NavBar",
    component: NavBar,
  },
  {
    path:"/newsfeed",
    name: "NewsFeed",
    component: NewsFeed,
  },
];

const router = new VueRouter({
  mode: "history",
  base: process.env.BASE_URL,
  routes,
});

export default router;
