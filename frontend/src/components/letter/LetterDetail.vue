<template>
  <v-card>
    <v-toolbar dark color="purple lighten-2">
      <v-btn icon dark @click="changeDialog">
        <v-icon>mdi-close</v-icon>
      </v-btn>
      <v-toolbar-title>{{ item.tab }}</v-toolbar-title>
      <v-spacer></v-spacer>
    </v-toolbar>
    <v-card-text class="mt-5">
      <div class="d-flex align-center">
        <div>
          <v-avatar color="grey" size="48">
            <span v-if="!letterinfo.profile" class="white--text headline"></span>
            <img v-else :src="letterinfo.profile" />
          </v-avatar>
        </div>
        <div class="ml-3">
          <p class="mb-1" v-if="item.tab == '받은 편지함'">보낸 사람</p>
          <p class="mb-1" v-else>받는 사람</p>
          <p class="mb-1">보낸 날짜</p>
        </div>
        <div class="ml-3">
          <p class="mb-1">{{ letterinfo.name }}</p>
          <!-- <p class="mb-1">{{ letterinfo.createDate }}</p> -->
          <p class="mb-1">{{ letterDate }}</p>
        </div>
      </div>
      <div class="rounded grey lighten-2 pa-5 mt-3">
        <p class="p-3">{{ letterinfo.content }}</p>
      </div>
    </v-card-text>
    <v-divider></v-divider>
    <v-card-actions v-if="item.tab == '받은 편지함'">
      <v-spacer></v-spacer>
      <v-btn color="green darken-1" text @click="changeDialog">닫기</v-btn>
      <!-- 나를 영입하는 편지인지/다른 사람이 우리 팀에 지원하는 편지인지 분기 해서 둘 중 하나만 보여줘야 함 -->
      <v-btn
        v-if="letterinfo.letterType=='TBOARD'"
        color="green darken-1"
        text
        @click="teamAccept(letterinfo.sendIdx, letterinfo.teamboardNo)"
      >지원 수락하기</v-btn>
      <v-btn
        v-else
        color="green darken-1"
        text
        @click="memberAccept(letterinfo.teamboardNo, letterinfo.receiveIdx)"
      >영입 수락하기</v-btn>
    </v-card-actions>
  </v-card>
</template>

<script>
import axios from "axios";
export default {
  components: {},
  props: {
    letterinfo: {
      type: Object,
    },
    item: {
      type: Object,
    },
    dialog: {
      type: Boolean,
    },
  },
  created() {
    this.letterDate = this.dateFormatted(this.letterinfo.createDate);
  },
  data() {
    return {
      letterDate: "",
    };
  },
  methods: {
    teamAccept(sendidx, teamboardno) {
      let token = window.$cookies.get("nnd");
      axios
        .post(
          `http://localhost:8080/letter/teamaccept/${sendidx}/${teamboardno}`,
          {
            headers: {
              Authorization: "Bearer " + token.data, // the token is a variable which holds the token
            },
          }
        )
        .then((res) => console.log(res))
        .catch((err) => console.log(err));
      this.changeDialog();
    },
    memberAccept(teamboardno, receiveidx) {
      let token = window.$cookies.get("nnd");
      axios
        .post(
          `http://localhost:8080/letter/memberaccept/${teamboardno}/${receiveidx}`,
          {
            headers: {
              Authorization: "Bearer " + token.data, // the token is a variable which holds the token
            },
          }
        )
        .then((res) => console.log(res))
        .catch((err) => console.log(err));
      this.changeDialog();
    },
    changeDialog() {
      this.$emit("changeDialog");
    },
    dateFormatted: function (dt) {
      console.log("dt : " + dt);
      var d = new Date(dt);

      var result =
        d.getFullYear() +
        "-" +
        (d.getMonth() + 1 > 9 ? "" : "0") +
        (d.getMonth() + 1) +
        "-" +
        (d.getDate() > 9 ? "" : "0") +
        d.getDate() +
        " ";
      // (d.getHours() > 9 ? "" : "0") +
      // d.getHours() +
      // ":" +
      // (d.getMinutes() > 9 ? "" : "0") +
      // d.getMinutes() +
      // ":" +
      // (d.getSeconds() > 9 ? "" : "0") +
      // d.getSeconds();
      return result;
    },
  },
};
</script>

<style></style>
