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
      @click="$vuetify.goTo(target)"
    >
      <v-icon v-if="yyy" dark>mdi-chevron-up</v-icon>
      <v-icon v-else dark>mdi-plus</v-icon>
    </v-btn>
  </div>
</template>

<script>
export default {
  data: () => ({
    number: 9999,
    yyy: false,
  }),
  mounted() {
    this.myMethod();
  },
  methods: {
    myMethod() {
      var cnt = 0;
      window.addEventListener("scroll", function() {
        console.log(cnt++);
      });
    },
  },

  computed: {
    target() {
      const value = this[this.type];
      return Number(value);
    },
  },
};
</script>
<style></style>
