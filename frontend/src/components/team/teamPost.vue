<template>
  <div>
    <NavBar />
    <v-card>
      <v-list class="transparent">
        <div class="my-2">
          <v-btn color="primary" fab small dark @click="addPost()">
            <v-icon>mdi-pencil</v-icon>
          </v-btn>
        </div>
        <v-list-item-group>
          <TeamPostItem v-for="(post, i) in teamposts" :key="i" :postinfo="teamposts[i]" />
        </v-list-item-group>
      </v-list>
    </v-card>
  </div>
</template>

<script>
import NavBar from "../common/NavBar.vue";
import TeamPostItem from "./teamPostItem.vue";
import axios from "axios";

export default {
  name: "TeamPost",
  components: {
    NavBar,
    TeamPostItem,
  },
  data() {
    return {
      dialog: false,
      teamposts: [],
    };
  },
  props: {
    userinfo: {
      type: Object,
    },
    teaminfo: {
      type: Object,
    },
  },
  created() {
    let token = window.$cookies.get("nnd");
    axios
      .get(`http://localhost:8080/teammenu/post/${this.teaminfo.teamboardNo}`, {
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
  methods: {
    addPost() {},
  },
};
</script>

<style></style>
