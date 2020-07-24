<template>
  <div id="create">
    <!--
    <v-btn
      fab
      dark
      large
      color="primary"
      fixed
      right
      bottom
      @click="$vuetify.goTo(target)"
    >
      <v-icon v-if="visible" dark>mdi-chevron-up</v-icon>
      <v-icon v-if="!visible" dark>mdi-plus</v-icon>
    </v-btn>
    -->
  </div>
</template>

<script>
import { addBackToTop } from "vanilla-back-to-top";

export default {
  created: function() {
    addBackToTop({
      backgroundColor: "red",
      diameter: 40,
    });
  },
  methods: {},
};
</script>
<!--
npm install --save vanilla-back-to-top
 -->
<style></style>
