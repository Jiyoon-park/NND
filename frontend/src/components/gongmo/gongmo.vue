<template>
  <v-row class="mt-16">
    <NavBar />
    <v-col>
      <v-sheet height="64">
        <v-toolbar flat color="white">
          <v-btn outlined class="mr-4" color="grey darken-2" @click="setToday">오늘</v-btn>
          <v-btn fab text small color="grey darken-2" @click="prev">
            <v-icon small>mdi-chevron-left</v-icon>
          </v-btn>
          <v-btn fab text small color="grey darken-2" @click="next">
            <v-icon small>mdi-chevron-right</v-icon>
          </v-btn>
          <v-toolbar-title v-if="$refs.calendar">{{ $refs.calendar.title }}</v-toolbar-title>
          <v-spacer />
          <v-btn outlined class="mr-4" color="grey darken-2" @click="$router.push('/')">메인페이지</v-btn>
        </v-toolbar>
      </v-sheet>
      <v-sheet height="600">
        <v-calendar ref="calendar" v-model="focus" color="primary" :type="type"></v-calendar>
        <v-menu :close-on-content-click="false" offset-x></v-menu>
      </v-sheet>
    </v-col>
  </v-row>
</template>

<script>
import NavBar from "../common/NavBar.vue";
import axios from "axios";

export default {
  name: "Gongmo",
  components: {
    NavBar,
  },
  created() {
    let token = window.$cookies.get("kakao"); //nnd가 key인 쿠키 가져옴
    console.log(token);
    if (token) {
      // kakao 토큰이
      axios
        .get("http://localhost:8080/userinfo", {
          headers: {
            Authorization: "Bearer " + token.data, // the token is a variable which holds the token
          },
        })
        .then(
          (response) => {
            console.log(response);
            window.$cookies.remove("kakao"); //kakao 쿠키 없애고 새로생성
            window.$cookies.set("nnd", response.data, "2d"); //로그인시 쿠키 저장
            //console.log(response.data.object.idx)
            location.reload();
          },
          () => {
            console.log("failed");
          }
        );
    }
  },
  data: () => ({
    focus: "",
    type: "month",
    selectedEvent: {},
    selectedElement: null,
  }),
  mounted() {
    this.$refs.calendar.checkChange();
  },
  methods: {
    setToday() {
      this.focus = "";
    },
    prev() {
      this.$refs.calendar.prev();
    },
    next() {
      this.$refs.calendar.next();
    },
  },
};
</script>