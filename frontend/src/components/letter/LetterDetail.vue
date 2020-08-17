<template>
  <v-card>
    <v-toolbar flat dark color="#38ada9" v-if="letterinfo.letterType == 'tboard'">
      <v-toolbar-title v-if="item.tab == '받은 편지함'">받은 편지</v-toolbar-title>
      <v-toolbar-title v-else>보낸 편지</v-toolbar-title>
      <v-spacer></v-spacer>
      <i class="fas fa-trash" @click="deleteLetter"></i>
    </v-toolbar>
    <v-toolbar flat dark color="#706fd3" v-else>
      <v-toolbar-title v-if="item.tab == '받은 편지함'">받은 편지</v-toolbar-title>
      <v-toolbar-title v-else>보낸 편지</v-toolbar-title>
      <v-spacer></v-spacer>
      <i class="fas fa-trash" @click="deleteLetter"></i>
    </v-toolbar>
    <v-card-text class="mt-5 px-5">
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
          <p class="mb-1" v-if="item.tab == '받은 편지함'">받은 날짜</p>
          <p class="mb-1" v-else>보낸 날짜</p>
        </div>
        <div class="ml-3">
          <p class="mb-1">{{ letterinfo.name }}</p>
          <p class="mb-1">{{ letterDate }}</p>
        </div>
      </div>
      <div class="rounded grey lighten-3 pa-3 mt-3">
        <span class="subheader" v-if="letterinfo.letterType == 'mboard'">✔ 팀 영입 제안입니다.</span>
        <span class="subheader" v-else>✔ 팀원 지원입니다.</span>
        <div style="height:300px; width:300px; margin:auto;" v-if="item.tab == '받은 편지함'">
          <MemberChart :axiostype="axiostype" :letteridx="letterinfo" />
        </div>
        <p class="mt-2">{{ letterinfo.content }}</p>
      </div>
    </v-card-text>
    <v-divider></v-divider>
    <v-card-actions v-if="item.tab == '받은 편지함'">
      <v-btn color="grey" class="font-weight-bold" text @click="changeDialog">닫기</v-btn>
      <v-spacer></v-spacer>
      <v-btn
        color="#38ada9"
        text
        class="font-weight-bold"
        @click="teamAccept(letterinfo.sendIdx, letterinfo.teamboardNo)"
        v-if="letterinfo.letterType == 'tboard'"
      >지원수락</v-btn>
      <v-btn
        color="#706fd3"
        text
        class="font-weight-bold"
        @click="memberAccept(letterinfo.teamboardNo, letterinfo.receiveIdx)"
        v-else
      >제안수락</v-btn>
    </v-card-actions>
  </v-card>
</template>

<script>
import axios from "axios";
import MemberChart from "../team/memberChart.vue";
export default {
  components: {
    MemberChart,
  },
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
      axiostype: "memberlist",
    };
  },
  methods: {
    deleteLetter() {},
    teamAccept(sendidx, teamboardno) {
      let token = window.$cookies.get("nnd");
      axios
        .post(
          `${process.env.VUE_APP_API_URL}/letter/teamaccept/${sendidx}/${teamboardno}`,
          {},
          {
            headers: {
              Authorization: "Bearer " + token.data, // the token is a variable which holds the token
            },
          }
        )
        .then((res) => {
          console.log(res);
          if (res.data == "success") {
            alert("수락완료");
          } else if (res.data == "already full") {
            alert("멤버 모집이 끝났습니다.");
          } else if (res.data == "already joined") {
            alert("이미 가입했습니다.");
          }
        })
        .catch((err) => {
          console.log(err);
          alert("멤버가 없거나 팀이 삭제되었습니다.");
        });
      this.changeDialog();
    },
    memberAccept(teamboardno, receiveidx) {
      let token = window.$cookies.get("nnd");
      axios
        .post(
          `${process.env.VUE_APP_API_URL}/letter/memberaccept/${teamboardno}/${receiveidx}`,
          {},
          {
            headers: {
              Authorization: "Bearer " + token.data, // the token is a variable which holds the token
            },
          }
        )
        .then((res) => {
          console.log(res);
          if (res.data == "success") {
            alert("수락완료");
          } else if (res.data == "already full") {
            alert("멤버 모집이 끝났습니다.");
          } else if (res.data == "already joined") {
            alert("이미 가입했습니다.");
          }
        })
        .catch((err) => {
          console.log(err);
          alert("멤버가 없거나 팀이 삭제되었습니다.");
        });
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

<style scoped>
.subheader {
  padding: 3px 5px;
  background-color: rgb(188, 190, 188);
  color: white;
  font-style: italic;
}
</style>
