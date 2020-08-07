<template>
  <div>
    <v-list-item
      class="letter py-3"
      @click="onLetterDetail(`${letterinfo.letterNo}`)"
      @click.stop="dialog = true"
    >
      <div class="mr-4">
        <v-avatar color="grey" size="60">
          <span v-if="!letterinfo.profile" class="white--text headline"></span>
          <img v-else :src="letterinfo.profile" />
        </v-avatar>
      </div>
      <v-list-item-content>
        <div>
          <v-list-item-title v-text="letterinfo.name" class="font-weight-black"></v-list-item-title>
          <i
            class="fas fa-envelope float-right"
            style="font-size:20px; color: #7E57C2;"
            v-if="!letterinfo.read"
          ></i>
          <i
            class="fas fa-envelope-open-text float-right"
            style="font-size:20px; color: #BDBDBD;"
            v-else
          ></i>
          <br />
          <div class="d-flex">
            <v-list-item-title
              v-text="letterinfo.content"
              class="text--secondary d-inline-block text-truncate"
              style="max-width: 160px;"
            ></v-list-item-title>
            <p class="mb-0">{{ letterDate }}</p>
          </div>
        </div>
      </v-list-item-content>
    </v-list-item>
    <v-dialog v-model="dialog" max-width="300">
      <LetterDetail
        :item="item"
        :letterinfo="letterinfo"
        :dialog="dialog"
        @changeDialog="dialog = false"
      />
    </v-dialog>
  </div>
</template>

<script>
import LetterDetail from "./LetterDetail.vue";

import axios from "axios";

export default {
  components: {
    LetterDetail,
  },
  data() {
    return {
      dialog: false,
      letterDate: "",
    };
  },
  props: {
    item: {
      type: Object,
    },
    letterinfo: {
      type: Object,
    },
  },
  created() {
    this.letterDate = this.dateFormatted(this.letterinfo.createDate);
  },
  methods: {
    // 클릭한 편지의 편지 pk를 받아옴.
    onLetterDetail(letterNo) {
      let token = window.$cookies.get('nnd');
      // 클릭한 편지는 읽음 처리
      this.letterinfo.read = 1;
      axios
        .post(`http://localhost:8080/letter/update/${letterNo}`,
        {
        headers: { 
          Authorization: "Bearer " + token.data, // the token is a variable which holds the token
         }
        })
        .then((res) => console.log(res))
        .catch((err) => console.log(err));
    },
    dateFormatted: function (dt) {
      console.log("dt : " + dt);
      var min = 60 * 1000;
      var c = new Date();
      var d = new Date(dt);
      var minsAgo = Math.floor((c - d) / min);
      if (minsAgo < 0) {
        minsAgo += 540; // 9시간이 역전되는 버그를 막는 임시 방편소스
      }

      var result = {
        raw:
          d.getFullYear() +
          "-" +
          (d.getMonth() + 1 > 9 ? "" : "0") +
          (d.getMonth() + 1) +
          "-" +
          (d.getDate() > 9 ? "" : "0") +
          d.getDate() +
          " " +
          (d.getHours() > 9 ? "" : "0") +
          d.getHours() +
          ":" +
          (d.getMinutes() > 9 ? "" : "0") +
          d.getMinutes() +
          ":" +
          (d.getSeconds() > 9 ? "" : "0") +
          d.getSeconds(),
        formatted: "",
      };

      console.log(result.raw); // 결과값

      if (minsAgo < 60) {
        // 1시간 내(ex: 1분 전, 3분 전, 45분 전)
        result.formatted = minsAgo + "분 전";
      } else if (minsAgo < 60 * 24) {
        // 하루 내(ex: 1시간 전, 5시간 전, 22시간 전)
        result.formatted = Math.floor(minsAgo / 60) + "시간 전";
      } else if (60 * 24 <= minsAgo < 60 * 24 * 7) {
        // 일주일 내(ex: 1일 전, 4일 전 6일 전)
        result.formatted = Math.floor(minsAgo / 60 / 24) + "일 전";
      } else if (c.getFullYear == d.getFullYear) {
        // 올해 안(ex: 8월 4일, 9월 11일)
        result.formatted = d.getMonth() + 1 + "월 " + d.getDate() + "일";
      } else {
        // 올해 밖(ex: 2021-04-21, 2021-08-11)
        result.formatted =
          d.getFullYear() +
          "-" +
          (d.getMonth() + 1 > 9 ? "" : "0") +
          (d.getMonth() + 1) +
          "-" +
          (d.getDate() > 9 ? "" : "0") +
          d.getDate();
      }
      return result.formatted;
    },
  },
};
</script>

<style>
</style>