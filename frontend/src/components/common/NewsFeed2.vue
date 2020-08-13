<template>
  <v-container fluid>
    <v-flex xs12 md6 offset-sm3>
      <v-card flat style="position:relative;">
        <div class="ribbon" v-if="favorite"></div>
        <v-expansion-panels>
          <v-expansion-panel>
            <div class="d-flex mx-3 my-3 align-center">
              <v-avatar
                color="white"
                size="50"
                class="user-img mb-2"
                @click="
                  $router
                    .push({
                      name: 'userProfile',
                      params: { idx: teaminfo.idx },
                    })
                    .catch(() => {})
                "
              >
                <img v-if="!profileURL" src="https://picsum.photos/200" />
                <img v-else :src="profileURL" />
              </v-avatar>
              <div class="d-flex flex-column ml-3">
                <span>{{ teaminfo.name }}</span>
                <div>
                  <span>
                    {{ $moment(teaminfo.createdate).format("YYYY-MM-DD") }}
                  </span>
                  <small class="deadline">
                    ~ {{ teaminfo.deadline }}
                    <span style="color:#555">마감</span>
                  </small>
                </div>
              </div>
            </div>
            <div style="position:relative;">
              <div v-if="!teaminfo.imageurl">
                <v-img
                  v-if="teaminfo.category === '스터디'"
                  src="../../assets/images/study.jpg"
                  height="194"
                ></v-img>
                <v-img
                  v-else-if="teaminfo.category === '프로젝트'"
                  src="../../assets/images/project.jpg"
                  height="194"
                ></v-img>
                <v-img
                  v-else
                  src="../../assets/images/competition.jpg"
                  height="194"
                ></v-img>
              </div>
              <div v-else>
                <v-img :src="teaminfo.imageurl" height="194"></v-img>
              </div>
              <span
                class="mr-3 mt-1 d-flex flex-column align-end"
                style="position:absolute; top:0; right:0; font-weight:bold; font-style:italic;"
              >
                <span
                  style="text-shadow:1px 1px black; color:#eeeeee; font-size:18px;"
                  >{{ teaminfo.category }}</span
                >

                <small
                  style="background-color:#eeeeee; opacity:0.7;"
                  class="px-1"
                  >모집 인원 {{ teaminfo.groupsize }}</small
                >
              </span>
            </div>

            <v-expansion-panel-header class="mt-2">
              <div class="d-flex flex-column">
                <span class="font-weight-black mb-1">{{ teaminfo.title }}</span>
                <div class="d-flex">
                  <v-chip
                    small
                    class="mr-2 mt-1"
                    color="#3949ab"
                    text-color="white"
                    v-for="stack in JSON.parse(stacks)"
                    :key="stack"
                    style="opacity:0.7;"
                    ># {{ stack }}</v-chip
                  >
                </div>
              </div>
            </v-expansion-panel-header>
            <v-expansion-panel-content>
              <div>{{ teaminfo.content }}</div>
            </v-expansion-panel-content>
            <v-card-actions>
              <v-spacer></v-spacer>

              <v-btn text color="indigo" v-if="!favorite" @click="addFavorite">
                <v-icon>mdi-star-outline</v-icon>
              </v-btn>

              <v-btn text color="indigo" v-if="favorite" @click="delFavorite">
                <v-icon>mdi-star</v-icon>
              </v-btn>

              <v-btn
                text
                class="ml-0"
                color="indigo darken-1 accent-4 font-weight-bold"
                @click="applyform"
              >
                <i class="fas fa-paper-plane mr-1"></i> 지원
              </v-btn>
            </v-card-actions>
          </v-expansion-panel>
        </v-expansion-panels>

        <v-dialog v-model="dialog" max-width="600px">
          <v-card>
            <v-img
              class="header"
              height="200px"
              src="../../assets/images/team2.jpg"
            ></v-img>
            <v-card-title
              class="header-text text-center justify-center font-italic"
            >
              ❝ {{ teaminfo.teamname }} 팀의 <br />팀원이 되고싶습니다 ❠
            </v-card-title>

            <v-card-text class="mt-5 pb-0">
              <div class="mt-3">
                <p class="mb-0 pl-1">팀장에게 보내는 어필 한마디 🙈🙉</p>
                <v-textarea
                  filled
                  v-model="content"
                  name="content"
                  placeholder="내용을 작성해주세요."
                ></v-textarea>
              </div>
            </v-card-text>
            <v-card-actions>
              <v-btn color="blue darken-1" text @click="dialog = false"
                >취소</v-btn
              >
              <v-spacer></v-spacer>
              <v-btn color="blue darken-1" text @click="submit">지원하기</v-btn>
            </v-card-actions>
          </v-card>
        </v-dialog>
      </v-card>
    </v-flex>
  </v-container>
</template>

<script>
import axios from "axios";
export default {
  name: "NewsFeed2",
  props: ["teaminfo"],

  data() {
    return {
      show: false,
      favorite: false,
      dialog: false,
      stacks: this.teaminfo.techstack,
      username: "",
      profileURL: "",
      ///쪽찌보낼내용
      sendIdx: "",
      receiveIdx: "",
      content: "",
      lettertype: "tboard",
      letterNo: "",
      createDate: "",
      teamboardNo: this.teaminfo.teamboardno,
      tlikeno: this.teaminfo.likeno,
    };
  },
  // mounted(){
  //   this.teamboardno = this.teaminfo.teamboardNo;
  // },
  created() {
    if (this.teaminfo.mno == this.$store.state.myToken.idx) {
      console.log("즐겨찾기 상태");
      this.favorite = true;
    } else {
      console.log("즐겨찾기 아닌상태");
      this.favorite = false;
    }
  },
  methods: {
    addFavorite() {
      let token = window.$cookies.get("nnd");

      console.log("팀 번호: " + this.teaminfo.teamboardno);
      console.log("토큰: " + this.$store.state.myToken.idx);
      //// teaminfo.mno가 숫자가 있으면 즐겨찾기 된거 or null이면 추가 안된거
      axios
        .put(
          "http://localhost:8080/liketeam/save/" +
            this.$store.state.myToken.idx +
            "/" +
            this.teaminfo.teamboardno,
          {
            headers: {
              Authorization: "Bearer " + token.data, // the token is a variable which holds the token
            },
            params: {},
          }
        )
        .then((data) => {
          this.favorite = true;

          this.tlikeno = data.data;
        });
    },
    delFavorite() {
      let token = window.$cookies.get("nnd");
      axios
        .delete("http://localhost:8080/liketeam/delete/" + this.tlikeno, {
          headers: {
            Authorization: "Bearer " + token.data, // the token is a variable which holds the token
          },
        })
        .then(() => {
          this.favorite = false;
        });
    },
    submit() {
      this.dialog = false;
      let token = window.$cookies.get("nnd");
      console.log(this.sendIdx + " send");
      console.log(this.teaminfo.idx + " receive");
      console.log(this.lettertype + " type");
      axios
        .put("http://localhost:8080/letter/create/" + this.lettertype, {
          headers: {
            Authorization: "Bearer " + token.data, // the token is a variable which holds the token
          },
          sendIdx: this.sendIdx,
          receiveIdx: this.teaminfo.idx,
          content: this.content,
          letterNo: this.letterNo,
          createDate: this.createDate,
          lettertype: this.lettertype,
          teamboardNo: this.teamboardNo,
        })
        .then(() => {
          console.log(this.sendIdx);
          alert("등록성공");
        })
        .catch((error) => {
          console.log(error.response);
          alert("실패");
        });
      alert("신청되었습니다.");
    },
    applyform() {
      // 지원을 받기전 마감시간이 지났는지 체크하도록 한다.
      // 지났다 = 현재시간 - 마감시간 > 0 
      // 안지났다 = 반대
      var curTime = new Date();
      var endTime = new Date(this.teaminfo.deadline);

      console.log(`현재시간 : ${curTime}`);
      console.log(`마감시간 : ${endTime}`);
      console.log(`차이 : ${curTime.getTime() - endTime.getTime()}`);
      
      if (curTime.getTime() - endTime.getTime() > 0) {
        alert("마감되었습니다!!!");
      } else {
        this.dialog = !this.dialog;
        let token = window.$cookies.get("nnd");
        if (token) {
          console.log("프로필주소 : " + token.object.profile);
          this.username = token.object.name;
          this.profileURL = token.object.profile;
          this.sendIdx = token.object.idx;
        }
      }
    },
  },
};
</script>

<style scoped>
.header {
  filter: brightness(60%);
  position: relative;
}

.header-text {
  position: absolute;
  top: 60px;
  left: 0;
  right: 0;
  color: #eeeeee;
}

.deadline {
  color: #222;
  font-weight: bold;
  background-color: #eeeeee;
  margin-left: 5px;
}

/* .ribbon {
  width: 0px;
  height: 35px;
  background-color: transparent;
  position: absolute;
  top: -5px;
  right: 19px;
  border: solid 13px #3a52db;
  border-bottom: solid 5px transparent;
  border-top-left-radius: 5px;
  border-top-right-radius: 5px;
  transition: all 0.2s;
  cursor: pointer;
  z-index: 2;
  opacity: 0.6;
  box-shadow: 2px 0 0 0 rgb(62, 35, 138);
} */

.ribbon {
  display: block;
  top: -10px;
  right: 12px;
  position: absolute;
  width: 0;
  height: 45px;
  padding: 10px 10px;
  text-decoration: none;
  transition: 1s;
  background: #f5f5f5;
  box-shadow: 1px 2px 2px rgba(0, 0, 0, 0.4);
  z-index: 2;
  color: #e0e0e0;
  border-top: 10px solid #0d47a1;
}
</style>
