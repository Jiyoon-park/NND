<template>
  <v-app>
    <news-feed2 v-for="(board, i) in list" v-bind:teaminfo="list[i]" v-bind:key="i"></news-feed2>
    <infinite-loading @infinite="infiniteHandler"></infinite-loading>
  </v-app>
</template>


<script>
import InfiniteLoading from "vue-infinite-loading";
import NewsFeed2 from "./NewsFeed2.vue";
import axios from "axios";
export default {
  data() {
    return {
      list: [],
      page: 0,
      size: 10,
    };
  },

  methods: {
    infiniteHandler($state) {
      axios
        .get(`http://localhost:8080/teamboard/list`, {
          params: {
            page: this.page,
            size: this.size,
          },
        })
        .then(({ data }) => {
          setTimeout(() => {
            if (data.length) {
              this.page += 1;
              this.list.push(...data);
              $state.loaded();
            } else {
              $state.complete();
            }
          }, 1500);
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

