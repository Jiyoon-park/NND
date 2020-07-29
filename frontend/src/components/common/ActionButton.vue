<template>
  <div id="create">
    <v-btn
      fab
      dark
      large
      color="primary"
      fixed
      right
      bottom
      @click="$router.push('/addboard')"
    >
      <v-icon dark>mdi-plus</v-icon>
    </v-btn>
  </div>
</template>

<script>
import { addBackToTop } from "vanilla-back-to-top";

export default {
  created: function() {
    addBackToTop({
      backgroundColor: "red",
      diameter: 64,
    });
  },
  methods: {},
};
</script>
<!--
npm install --save vanilla-back-to-top
 -->
<style>
#back-to-top {
  margin-bottom: 70px;
  margin-right: -4px;
}
</style>
