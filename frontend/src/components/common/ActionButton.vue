<template>
  <v-dialog v-model="dialog" width="50%" :fullscreen="$vuetify.breakpoint.mobile" hide-overlay>
    <template v-slot:activator="{ on, attrs }">
      <v-btn v-bind="attrs" v-on="on" fab dark color="#0277BD" fixed right bottom>
        <i class="fas fa-pencil-alt fa-lg"></i>
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
      diameter: 56,
      innerHTML:
        '<svg viewBox="0 0 24 24"><path d="M4 12l1.41 1.41L11 7.83V20h2V7.83l5.58 5.59L20 12l-8-8-8 8z"/></svg>',
      backgroundColor: "#eeeeee",
      textColor: "#0277BD",
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
  margin-bottom: 60px;
  margin-right: -4px;
  z-index: 3;
}
</style>
