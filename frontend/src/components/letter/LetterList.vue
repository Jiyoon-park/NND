<template>
  <div>
    <NavBar />
    <div>
      <v-tabs fixed-tabs v-model="tab" color="purple">
        <v-tab v-for="item in items" :key="item.tab">{{ item.tab }}</v-tab>
      </v-tabs>
      <v-tabs-slider color="yellow"></v-tabs-slider>
      <v-tabs-items v-model="tab">
        <v-tab-item v-for="item in items" :key="item.tab">
          <v-card flat>
            <v-list>
              <v-list-item-group>
                <v-list-item
                  v-for="letter in item.letters"
                  :key="letter.letterNo"
                  class="letter py-3"
                  @click="onListDetail(`${letter.letterNo}`)"
                >
                  <div class="mr-4">
                    <v-avatar color="grey" size="60">
                      <span v-if="!letter.profile" class="white--text headline"></span>
                      <img v-else :src="letter.profile" />
                    </v-avatar>
                  </div>
                  <v-list-item-content>
                    <div>
                      <v-list-item-title v-text="letter.name" class="font-weight-black"></v-list-item-title>
                      <i
                        class="fas fa-envelope float-right"
                        style="font-size:20px; color: #7E57C2;"
                        v-if="!letter.read"
                      ></i>
                      <i
                        class="fas fa-envelope-open-text float-right"
                        style="font-size:20px; color: #BDBDBD;"
                        v-else
                      ></i>
                      <br />
                      <v-list-item-title v-text="letter.createDate" class="text--secondary"></v-list-item-title>
                    </div>
                  </v-list-item-content>
                </v-list-item>
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
import axios from "axios";

export default {
  name: "LetterList",
  components: { NavBar },
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
      dialog: false,
    };
  },
  created() {
    let token = window.$cookies.get("nnd"); //nnd가 key인 쿠키 가져옴
    if (token) {
      console.log(token.object.idx);
      axios
        .get(`http://localhost:8080/letter/read/receive/${token.object.idx}`)
        .then((res) => {
          this.items[0].letters = res.data;
          console.log(this.items[0].letters);
        })
        .catch((err) => {
          console.log(err);
        });

      axios
        .get(`http://localhost:8080/letter/read/send/${token.object.idx}`)
        .then((res) => {
          this.items[1].letters = res.data;
          console.log(this.items[1].letters);
        })
        .catch((err) => {
          console.log(err);
        });
    }
  },
  methods: {
    // 클릭한 편지의 편지 pk를 받아옴.
    onListDetail: function (letterNo) {
      alert(letterNo);
    },
  },
};
</script>

<style>
.v-list-item:not(.on-hover) {
  opacity: 0.6;
}
</style>