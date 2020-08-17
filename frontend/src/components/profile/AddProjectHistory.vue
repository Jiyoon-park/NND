<template>
  <v-dialog v-model="dialog" persistent max-width="600px">
    <template v-slot:activator="{ on, attrs }">
      <v-btn color="#0277BD" dark v-bind="attrs" v-on="on" fab x-small class="mr-1">
        <v-icon>mdi-plus</v-icon>
      </v-btn>
    </template>
    <ProjectHistoryCard @changeDialog="dialog = false" />
  </v-dialog>
</template>

<script>
// import axios from "axios";
import ProjectHistoryCard from "./ProjectHistoryCard.vue";
export default {
  components: {
    ProjectHistoryCard,
  },
  created() {
    let token = window.$cookies.get("nnd");
    if (token) {
      //토큰 존재하면
      this.user = token.object;
      this.profileURL = this.user.profile;
    }
  },
  data() {
    return {
      dialog: false,
      user: "",
    };
  },
};
</script>

<style></style>
