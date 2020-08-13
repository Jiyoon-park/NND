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
                  <span>{{
                    $moment(teaminfo.createdate).format("YYYY-MM-DD")
                  }}</span>
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
                class="mr-3 mt-1"
                style="position:absolute; top:0; right:0; font-weight:bold; font-style:italic;"
              >
                <span
                  style="text-shadow:1px 1px black; color:#eeeeee; font-size:18px;"
                  >{{ teaminfo.category }}</span
                >
              </span>

              <div
                style="position:absolute; right:15px; bottom:-32px; z-index:2;"
              >
                <i
                  class="far fa-bookmark"
                  v-if="!favorite"
                  @click="addFavorite"
                ></i>
                <i class="fas fa-bookmark" v-else @click="delFavorite"></i>
              </div>
              <div
                style="position:absolute; left:15px; bottom:-32px; z-index:2;"
              >
                <i @click="applyform" class="fas fa-paper-plane"
                  ><small class="ml-1">지원하기</small></i
                >
              </div>
            </div>

            <div class="shrink mt-10 mx-4 mb-6">
              <div class="d-flex justify-space-between align-center">
                <span class="font-weight-black mb-1">{{ teaminfo.title }}</span>
                <small
                  @click="expand = !expand"
                  style="cursor:pointer; color:primary"
                >
                  더보기
                </small>
              </div>
              <v-expand-transition>
                <v-card flat v-show="expand" class="mx-auto"
                  >{{ teaminfo.content }}
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
                </v-card>
              </v-expand-transition>
            </div>
          </v-expansion-panel>
        </v-expansion-panels>

        <v-dialog v-model="dialog" max-width="600px">
          <v-card>
            <v-img
              class="header"
              height="200px"
              src="../../assets/images/member2.jpg"
            ></v-img>
            <v-card-title
              v-if="this.teamlist.length !== 0"
              class="header-text text-center justify-center font-italic"
            >
              ❝ {{ teaminfo.teamname }}팀으로
              <br />
              {{ teaminfo.name }}님을 영입합니다 ❠
            </v-card-title>
            <v-card-title
              v-else
              class="header-text text-center justify-center font-italic"
            >
              ❝ 아쉽지만 등록된 팀이 없어 영업이 불가합니다. ❠
            </v-card-title>

            <v-card-text v-if="this.teamlist.length !== 0" class="mt-5 pb-0">
              <div class="mt-3">
                <v-row class="mb-0">
                  <v-col class="mb-0" cols="12">
                    <v-overflow-btn
                      filled
                      v-model="teamno"
                      :items="teamlist"
                      label="영입할 팀을 선택하세요"
                      item-text="teamName"
                      item-value="teamboardNo"
                    ></v-overflow-btn>
                  </v-col>
                </v-row>
                <p class="mb-0 pl-1">
                  {{ teaminfo.name }}에게 보내는 어필 한마디 🙈🙉
                </p>

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
              <v-btn
                v-if="this.teamlist.length !== 0"
                color="blue darken-1"
                text
                @click="submit"
                >영입하기</v-btn
              >
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
  name: "NewsFeed",
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
      letterType: "mboard",
      letterNo: "",
      createDate: "",
      teamlist: [],
      memberidx: this.teaminfo.idx,
      teamno: "",
      mlikeno: this.teaminfo.likeno,
      expand: false,
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

      axios
        .put(
          `${process.env.VUE_APP_API_URL}/likemember/save/` +
            this.$store.state.myToken.idx +
            "/" +
            this.teaminfo.boardno,
          {
            headers: {
              Authorization: "Bearer " + token.data, // the token is a variable which holds the token
            },
          }
        )
        .then((data) => {
          this.favorite = true;

          this.mlikeno = data.data;
        });
    },
    delFavorite() {
      let token = window.$cookies.get("nnd");

      axios
        .delete(`${process.env.VUE_APP_API_URL}/likemember/delete/${this.mlikeno}`, {
          headers: {
            Authorization: "Bearer " + token.data, // the token is a variable which holds the token
          },
        })
        .then(() => {
          this.favorite = false;
        });
    },
    submit() {
      let token = window.$cookies.get("nnd");
      this.dialog = false;
      console.log(this.sendIdx + " send");
      console.log(this.teaminfo.idx + " receive");
      console.log(this.letterType + " type");
      console.log(this.teamno);
      axios
        .put(
          `${process.env.VUE_APP_API_URL}/letter/create/${this.lettertype}`,
          {
            sendIdx: this.sendIdx,
            receiveIdx: this.teaminfo.idx,
            content: this.content,
            letterNo: this.letterNo,
            createDate: this.createDate,
            teamboardNo: this.teamno,
          },
          {
            headers: {
              Authorization: "Bearer " + token.data, // the token is a variable which holds the token
            },
          }
        )
        .then((response) => {
          console.log(response);
          alert("등록성공");
        })
        .catch((error) => {
          console.log(error.response);
          alert("실패");
        });
      //alert("신청되었습니다.");
    },
    applyform() {
      this.dialog = !this.dialog;
      let token = window.$cookies.get("nnd");
      if (token) {
        console.log(token.object.idx);
        this.username = token.object.name;
        this.profileURL = token.object.profile;
        this.sendIdx = token.object.idx;
      }

      axios
        .get(
          `${process.env.VUE_APP_API_URL}/letter/member/teamlist/${this.$store.state.myToken.idx}`,
          {
            headers: {
              Authorization: "Bearer " + token.data, // the token is a variable which holds the token
            },
          }
        )
        .then((data) => {
          this.teamlist = data.data;
          console.log(this.teamlist);
        });
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
