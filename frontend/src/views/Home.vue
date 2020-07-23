<template>
  <div>
    <NavBar />
    <Tabs />
    <FAB />
  </div>
</template>

<script>
import NavBar from "../components/common/NavBar.vue";
import Tabs from "../components/common/Tabs.vue";
import FAB from "../components/common/FAB.vue";

// @ is an alias to /src

export default {
  name: "Home",
  components: {
    NavBar,
    Tabs,
    FAB,
  },
};
</script>
