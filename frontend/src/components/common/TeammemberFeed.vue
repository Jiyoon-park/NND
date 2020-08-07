<template>
  <v-app>
    <news-feed2
      v-for="(board, i) in list"
      v-bind:teaminfo="list[i]"
      v-bind:key="i"
    >
    </news-feed2>
    <infinite-loading @infinite="infiniteHandler"></infinite-loading>
    <!-- 팀일때랑 팀원일때랑 피드 두개로 구분해주자 -->
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
      this.type = obj.typeSelection;
      this.query = obj.search;
      this.category = obj.categorySelection;
      this.skills = obj.newSkill;
      this.page = 0;
      if (this.page == 0) {
        this.list = [];
      }
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
          this.page += 1;
          this.list.push(...data);
        });
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
