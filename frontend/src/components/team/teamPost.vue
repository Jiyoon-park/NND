<template>
  <div>
    <NavBar />
    <v-card>
      <v-list class="transparent">
        <v-list-item-group>
          <TeamPostItem
            :item="teamposts"
            v-for="(post, i) in teamposts"
            :key="i"
            :postinfo="item.teamposts[i]"
          />
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
    userinfo: {},
    teamboardno: {
      type: Object,
    },
  },
  created() {
    let token = window.$cookies.get("nnd");
    axios
      .get(
        `http://localhost:8080/teammenu/post/${this.teamboardno.teamboardno}`,
        {
          headers: {
            Authorization: "Bearer " + token.data, // the token is a variable which holds the token
          },
        }
      )
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

<style></style>
