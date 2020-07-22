<template>
  <v-app>
    <NavBar />
    <Tabs />
  </v-app>
</template>

<script>
import NavBar from "../components/common/NavBar.vue";
import Tabs from "../components/common/Tabs.vue";
// @ is an alias to /src

export default {
  name: "Home",
  components: {
    NavBar,
    Tabs
  }
};
</script>
