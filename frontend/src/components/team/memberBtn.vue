<template>
  <div>
    <v-btn color="primary" @click="goSurvey(props)" :disabled="btnStatus">평가하기</v-btn>
    <v-dialog v-model="dialog" persistent max-width="600">
      <v-card>
        <v-card-title class="headline">팀워크</v-card-title>
        <div class="text-center">
          <v-rating v-model="teamworkship"></v-rating>
        </div>
        <v-card-title class="headline">만족도</v-card-title>
        <div class="text-center">
          <v-rating v-model="satisfaction"></v-rating>
        </div>
        <v-card-actions>
          <v-spacer></v-spacer>
          <v-btn color="green darken-1" text @click="dialog = false">취소</v-btn>
          <v-btn color="green darken-1" text @click="sendRating">확인</v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>
  </div>
</template>

<script>
import axios from "axios";

function getRandomInt(max) {
  return Math.floor(Math.random() * Math.floor(max));
}
export default {
  props: ["props"],
  data() {
    return {
      btnStatus: false,
      dialog: false,
      teamworkship: 0,
      satisfaction: 0,
      nowNum: 0,
    };
  },
  methods: {
    goSurvey(props) {
      this.dialog = true;
      console.log("props찍어보자");
      console.log(props);
      this.nowNum = props.row.memberIdx;
    },
    sendRating() {
      let token = window.$cookies.get("nnd");

      this.dialog = false;
      console.log(this.satisfaction);
      console.log(this.teamworkship);
      axios
        .put(
          `${process.env.VUE_APP_API_URL}/teammenu/rating/`,
          {
            ratingNo: "",
            idx: this.nowNum,
            commitCnt: getRandomInt(5),
            issueCnt: getRandomInt(5),
            attendRate: getRandomInt(5),
            satisfaction: this.satisfaction,
            teamworkship: this.teamworkship,
            rated: "",
            ratedIdx: token.object.idx,
            teamboardNo: this.$store.state.teamNo,
          },
          {
            headers: {
              Authorization: "Bearer " + token.data, // the token is a variable which holds the token
            },
          }
        )
        .then(() => {});
      this.btnStatus = true;
      console.log("btnStatus찍어보자");
      console.log(this.btnStatus);
    },
  },
};
</script>

<style></style>
