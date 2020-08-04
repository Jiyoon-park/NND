<template>
  <v-app>
    <router-view></router-view>
  </v-app>
</template>

<script>
export default {
  name: "App",

  components: {},

  created() {
    console.log("App.vue");
    let token = window.$cookies.get("nnd");
    if (token) {
      this.$store.commit("setToken", token);
      console.log("App.vue.afterSetToken");
      this.$store.dispatch("getUserFromServer");
    } else {
      this.onLogout();
    }
  },
  methods: {
    onLogout: function () {
      this.$store.commit("logout");
      window.$cookies.remove("nnd");
      this.$router.push("/login");
    },
  },
};
</script>

