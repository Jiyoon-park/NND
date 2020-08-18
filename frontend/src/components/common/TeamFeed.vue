<template>
  <v-app>
    <news-feed v-for="(board, i) in boards" v-bind:teaminfo="boards[i]" v-bind:key="i"></news-feed>
  </v-app>
</template>

<script>
import NewsFeed from "./NewsFeed.vue";
import axios from "axios";
export default {
  data: () => ({
    // object: {
    //   email: null,
    //   teamname: null,
    //   groupsize: null,
    //   deadline: null,
    //   title: null,
    //   content: null,
    //   boards: null,
    // },
    boards: null,
    page: 1,
    size: 10,
  }),
  created() {
    axios
      .get(`${process.env.VUE_APP_API_URL}/teamboard/list`, 
        {
          page: this.page,
          size: this.size,
        },
        {
          headers: { 
          Authorization: "Bearer " + token.data, // the token is a variable which holds the token
          }
        }
      )
      .then(({ data }) => {
        this.boards = data;
        console.log(this.boards);
      })
      .catch((err) => {
        console.log(err);
      });
  },
  methods: {},
  components: {
    NewsFeed: NewsFeed,
  },
};
</script>

<style></style>
