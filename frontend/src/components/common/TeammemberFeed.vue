<template>
  <v-app>
    <news-feed2
      v-for="(board, i) in list"
      v-bind:teaminfo="list[i]"
      v-bind:key="i"
    >
    </news-feed2>
    <infinite-loading
      @infinite="infiniteHandler"
      ref="InfiniteLoading"
    ></infinite-loading>
  </v-app>
</template>

<script>
import InfiniteLoading from "vue-infinite-loading";
import NewsFeed2 from "./NewsFeed2.vue";
import axios from "axios";
import { EventBus } from "../../main.js";

export default {
  data() {
    return {
      list: [],
      page: 0,
      size: 5,
      option: {},
      type: "team",
      query: [],
      category: [],
      skills: [],
      st: null,
    };
  },
  created() {
    EventBus.$on("search", (obj) => {
      console.log("버스시작");
      this.type = obj.typeSelection;
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
      axios
        .put(
          "http://localhost:8080/" + this.type + "board/search",
          {
            query: this.query,
            category: this.category,
            skills: this.skills,
          },
          {
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

    InfiniteLoading,
  },
};
</script>

<style></style>
