<template>
  <div>
    <NavBar />
    <ActionButton />
  </div>
</template>

<script>
import NavBar from "../components/common/NavBar.vue";
// import Tabs from "../components/common/Tabs.vue";
import ActionButton from "../components/common/ActionButton.vue";

// @ is an alias to /src

export default {
  name: "Home",
  components: {
    NavBar,
    // Tabs,
    ActionButton,
  },
};
</script>
