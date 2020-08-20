<template>
  <div>
    <v-img :aspect-ratio="16 / 9" :src="this.url" @click="goImage()"></v-img>
    <v-dialog v-model="dialog" max-width="600px">
      <v-img :src="this.url"></v-img>
    </v-dialog>
  </div>
</template>

<script>
export default {
  props: ["url"],
  data() {
    return {
      dialog: false,
    };
  },
  methods: {
    goImage() {
      this.dialog = !this.dialog;
    },
  },
};
</script>

<style></style>
