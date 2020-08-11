<template>
  <v-card>
    <v-list class="transparent">
      <v-list-item-group>
        <teamPostItem
          :item="teamposts"
          v-for="(post,i) in teamposts"
          :key="i"
          :postinfo="item.teamposts[i]"
        />
      </v-list-item-group>
    </v-list>
  </v-card>
</template>

<script>
import TeamPostItem from "./teamPostItem.vue";
import axios from "axios";

export default {
  name: "TeamPost",
  components: {
    TeamPostItem,
  },
  data() {
    return {
      dialog: false,
      teamposts: [],
    };
  },
  props: {
    userinfo: {},
  },
  created() {
    let teamboardno = this.$route.params.teamboardno;
    let token = window.$cookies.get("nnd");
    axios
      .get(`http://localhost:8080/teammenu/post/${teamboardno}`, {
        headers: {
          Authorization: "Bearer " + token.data, // the token is a variable which holds the token
        },
      })
      .then((res) => {
        console.log({ res });
        this.teamposts = res;
      })
      .catch((err) => {
        console.log(err);
      });
  },
};
</script>

<style>
</style>