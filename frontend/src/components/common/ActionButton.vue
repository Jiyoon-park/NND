<template>
  <v-dialog v-model="dialog" width="50%" :fullscreen="$vuetify.breakpoint.mobile" hide-overlay>
    <template v-slot:activator="{ on, attrs }">
      <v-btn v-bind="attrs" v-on="on" fab dark large color="primary" fixed right bottom>
        <v-icon dark>mdi-plus</v-icon>
      </v-btn>
    </template>
    <AddBoard @changeDialog="dialog = false" />
  </v-dialog>
</template>

<script>
import { addBackToTop } from "vanilla-back-to-top";
import AddBoard from "./AddBoard.vue";

export default {
  components: {
    AddBoard,
  },
  data() {
    return {
      dialog: false,
      isMobile: false,
    };
  },
  beforeDestroy() {
    if (typeof window !== "undefined") {
      window.removeEventListener("resize", this.onResize, { passive: true });
    }
  },

  mounted() {
    this.onResize();
    window.addEventListener("resize", this.onResize, { passive: true });
  },
  created: function () {
    addBackToTop({
      backgroundColor: "red",
      diameter: 64,
    });
  },
  methods: {
    onResize() {
      this.isMobile = window.innerWidth < 600;
    },
  },
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
