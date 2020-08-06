<template>
  <div>
    <NavBar />
    <div class="mt-13">
      <v-tabs fixed-tabs v-model="tab" color="purple">
        <v-tab v-for="item in items" :key="item.tab">{{ item.tab }}</v-tab>
      </v-tabs>
      <v-tabs-slider color="yellow"></v-tabs-slider>
      <v-tabs-items v-model="tab">
        <v-tab-item v-for="item in items" :key="item.tab">
          <v-card flat>
            <v-list>
              <v-list-item-group>
                <LetterListItem
                  :item="item"
                  v-for="(letter, i) in item.letters"
                  :key="i"
                  :letterinfo="item.letters[i]"
                />
              </v-list-item-group>
            </v-list>
          </v-card>
        </v-tab-item>
      </v-tabs-items>
    </div>
  </div>
</template>

<script>
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
    };
  },
  created() {
    let token = window.$cookies.get("nnd"); //nnd가 key인 쿠키 가져옴
    if (token) {
      console.log(token.object.idx);
      axios
        .get(`http://localhost:8080/letter/list/receive/${token.object.idx}`)
        .then((res) => {
          this.items[0].letters = res.data;
          console.log(this.items[0].letters);
        })
        .catch((err) => {
          console.log(err);
        });

      axios
        .get(`http://localhost:8080/letter/list/send/${token.object.idx}`)
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

<style>
</style>