<template>
  <v-card flat>
    <NavBar />
    <v-container class="mt-11">
      <v-tabs fixed-tabs v-model="tab" color="#0277BD">
        <v-tab v-for="item in items" :key="item.tab" class="font-weight-bold">
          {{
          item.tab
          }}
        </v-tab>
      </v-tabs>
      <v-tabs-slider></v-tabs-slider>

      <v-tabs-items v-model="tab">
        <v-tab-item v-for="item in items" :key="item.tab">
          <v-card flat>
            <v-list class="pt-0">
              <v-list-item-group>
                <v-row justify="center">
                  <v-col cols="12" lg="8" class="px-0">
                    <p class="mb-3 ml-3 body-2">
                      <v-avatar color="#706fd3" size="12" class="mr-1"></v-avatar>영입 제안 편지
                      <v-avatar color="#38ada9" size="12" class="ml-2 mr-1"></v-avatar>팀원 지원 편지
                    </p>
                    <LetterListItem
                      :item="item"
                      v-for="(letter, i) in item.letters"
                      :key="i"
                      :letterinfo="item.letters[i]"
                    />
                    <!-- <infinite-loading @infinite="infiniteHandler"></infinite-loading> -->
                  </v-col>
                </v-row>
              </v-list-item-group>
            </v-list>
          </v-card>
        </v-tab-item>
      </v-tabs-items>
    </v-container>
  </v-card>
</template>

<script>
// import InfiniteLoading from "vue-infinite-loading";
import NavBar from "../common/NavBar.vue";
import LetterListItem from "./LetterListItem.vue";
import axios from "axios";

export default {
  name: "LetterList",
  components: { NavBar, LetterListItem },
  data() {
    return {
      tab: null,
      model: 1,
      items: [
        {
          tab: "받은 편지함",
          letters: [],
        },
        {
          tab: "보낸 편지함",
          letters: [],
        },
      ],
      // list: [],
      // page: 0,
      // size: 10,
    };
  },
  created() {
    let token = window.$cookies.get("nnd"); //nnd가 key인 쿠키 가져옴
    if (token) {
      console.log(token.object.idx);
      axios
        .get(
          `${process.env.VUE_APP_API_URL}/letter/list/receive/${token.object.idx}`,
          {
            headers: {
              Authorization: "Bearer " + token.data, // the token is a variable which holds the token
            },
          }
        )
        .then((res) => {
          this.items[0].letters = res.data;
          console.log(this.items[0].letters);
        })
        .catch((err) => {
          console.log(err);
        });

      axios
        .get(
          `${process.env.VUE_APP_API_URL}/letter/list/send/${token.object.idx}`,
          {
            headers: {
              Authorization: "Bearer " + token.data, // the token is a variable which holds the token
            },
          }
        )
        .then((res) => {
          this.items[1].letters = res.data;
          console.log(this.items[1].letters);
        })
        .catch((err) => {
          console.log(err);
        });
    }
  },
  methods: {},
};
</script>

<style></style>
