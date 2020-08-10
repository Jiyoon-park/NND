<template>
  <v-app class="mt-15">
    <div v-if="this.type == 'team'">
      <news-feed2
        v-for="(board, i) in list"
        v-bind:teaminfo="list[i]"
        v-bind:key="i"
      >
      </news-feed2>
    </div>
    <div v-if="this.type == 'member'">
      <news-feed
        v-for="(board, i) in list"
        v-bind:teaminfo="list[i]"
        v-bind:key="i"
      >
      </news-feed>
    </div>
    <infinite-loading
      @infinite="infiniteHandler"
      ref="InfiniteLoading"
    ></infinite-loading>
  </v-app>
</template>

<script>
import InfiniteLoading from "vue-infinite-loading";
import NewsFeed2 from "./NewsFeed2.vue";
import NewsFeed from "./NewsFeed.vue";
import axios from "axios";
import { EventBus } from "../../main.js";

export default {
  data() {
    return {
      list: [],
      page: 0,
      size: 10,
      option: {},
      type: "team",
      query: [],
      category: [],
      skills: [],
    };
  },
  created() {
    EventBus.$on("search", (obj) => {
      this.type = obj.typeSelection;
      console.log(this.type);
      this.query = obj.search;
      this.category = obj.categorySelection;
      this.skills = obj.newSkill;
      this.list = [];
      this.$refs.InfiniteLoading.stateChanger.reset();
      this.page = 0;
    });
  },

  methods: {
    infiniteHandler($state) {
      let token = window.$cookies.get("nnd");
      axios
        .put(
          "http://localhost:8080/" + this.type + "board/search",
          {
            query: this.query,
            category: this.category,
            skills: this.skills,
          },
          {
            headers: {
              Authorization: "Bearer " + token.data, // the token is a variable which holds the token
            },
            params: {
              page: this.page,
              size: this.size,
            },
          }
        )
        .then(({ data }) => {
          setTimeout(() => {
            if (data.length) {
              console.log("기존 스크롤 push " + this.page);
              this.page += 1;
              this.list.push(...data);
              $state.loaded();
            } else {
              $state.complete();
            }
          }, 1000);
        });
    },
  },
  components: {
    NewsFeed2: NewsFeed2,
    NewsFeed: NewsFeed,

    InfiniteLoading,
  },
};
</script>

<style></style>
