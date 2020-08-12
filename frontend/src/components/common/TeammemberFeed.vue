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
import * as firebase from "firebase/app";

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
              console.log(`list : ${this.list}`);

              // 여기서 파이어베이스에서 이미지를 얻기 위해 url을 변환한다 
              for (let i = 0; i < this.list.length; i++) {
                var card = this.list[i];

                if (card.url == null) { // url이 비어있으면 pass, 있으면 아래 코드를 진행한다.
                  continue;
                }

                card.url = `images/${this.type}/${card.teamboardno}/${card.url}`;
                console.log(`before url : ${card.url}`)
                firebase.storage().ref().child(card.url).getDownloadURL().then(function(url) {
                  card.url = url;
                  console.log(`after url : ${card.url}`);
                }).catch(function(error) {
                  // A full list of error codes is available at
                  // https://firebase.google.com/docs/storage/web/handle-errors
                  switch (error.code) {
                    case 'storage/object-not-found':
                      // File doesn't exist
                      break;

                    case 'storage/unauthorized':
                      // User doesn't have permission to access the object
                      break;

                    case 'storage/canceled':
                      // User canceled the upload
                      break;

                    case 'storage/unknown':
                      // Unknown error occurred, inspect the server response
                      break;
                  }
                });
                
              this.list[i].url = card.url;
              console.log(`원본 url : ${this.list[i].url}`);
              }

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
