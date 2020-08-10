<template>
  <v-container fluid>
    <v-flex xs12 md6 offset-sm3>
      <v-card outlined>
        <v-expansion-panels>
          <v-expansion-panel>
            <div class="d-flex mx-3 my-3 align-center">
              <v-avatar color="white" size="50" class="user-img mb-2">
                <img v-if="!profileURL" src="https://picsum.photos/200" />
                <img v-else :src="profileURL" />
              </v-avatar>
              <div class="d-flex flex-column ml-3">
                {{ teaminfo.teamName }}
                <div class="d-flex">
                  <v-chip
                    small
                    class="mr-2 mt-1"
                    color="indigo"
                    text-color="white"
                    v-for="stack in JSON.parse(stacks)"
                    :key="stack"
                    >{{ stack }}</v-chip
                  >
                </div>
              </div>
            </div>
            <v-img
              src="https://cdn.vuetifyjs.com/images/cards/mountain.jpg"
              height="194"
            ></v-img>
            <v-expansion-panel-header>
              {{ teaminfo.title }}
              <template v-slot:actions>
                <v-icon color="teal">mdi-check</v-icon>
              </template>
            </v-expansion-panel-header>
            <v-expansion-panel-content>
              {{ teaminfo.content }}
              <br />
              {{ teaminfo.kakaoLink }}
            </v-expansion-panel-content>
            <v-card-actions>
              <v-btn icon color="pink" v-if="!favorite" @click="addFavorite">
                <v-icon>mdi-star-outline</v-icon>
              </v-btn>
              <v-btn icon color="pink" v-if="favorite" @click="delFavorite">
                <v-icon>mdi-star</v-icon>
              </v-btn>
              <v-spacer></v-spacer>
              <v-btn color="green darken-1" text @click="applyform" right
                >신청하기</v-btn
              >
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
              ❝ {{ teaminfo.teamName }} 팀의 <br />팀원이 되고싶습니다 ❠
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
            headers: {},
            params: {},
          }
        )
        .then(() => {
          this.favorite = true;
          alert("즐겨찾기에 등록되었습니다.");
        });
    },
    delFavorite() {
      axios
        .delete("http://localhost:8080/liketeam/delete/" + this.teaminfo.likeno)
        .then(() => {
          this.favorite = false;
          alert("즐겨찾기에서 삭제되었습니다.");
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
      this.dialog = !this.dialog;
      let token = window.$cookies.get("nnd");
      if (token) {
        console.log("프로필주소 : " + token.object.profile);
        this.username = token.object.name;
        this.profileURL = token.object.profile;
        this.sendIdx = token.object.idx;
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
</style>
