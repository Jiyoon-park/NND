<template>
  <v-container fluid>
    <v-row justify="center">
      <v-col cols="12" sm="12" md="6" class="py-0">
        <v-card flat style="position:relative;">
          <div class="ribbon" v-if="favorite"></div>
          <v-expansion-panels>
            <v-expansion-panel>
              <div class="d-flex mx-3 my-3 align-center">
                <v-avatar
                  style="cursor:pointer;"
                  color="#eeeeee"
                  size="50"
                  class="user-img mb-2"
                  @click="profileMove(teaminfo.idx)"
                >
                  <i v-if="!profileURL" class="fas fa-user"></i>
                  <img v-else :src="profileURL" />
                </v-avatar>
                <div class="d-flex flex-column ml-3">
                  <span
                    style="cursor:pointer;"
                    @click="profileMove(teaminfo.idx)"
                  >{{ teaminfo.name }}</span>
                  <div>
                    <span>
                      {{
                      $moment(teaminfo.createdate).format("YYYY-MM-DD")
                      }}
                    </span>
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
                  <v-img v-else src="../../assets/images/competition.jpg" height="194"></v-img>
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
                  >{{ teaminfo.category }}</span>
                </span>

                <div
                  style="position:absolute; cursor:pointer; right:15px; bottom:-32px; z-index:2;"
                >
                  <i class="far fa-bookmark" v-if="!favorite" @click="addFavorite"></i>
                  <i class="fas fa-bookmark" v-else @click="delFavorite"></i>
                </div>
                <div style="position:absolute; cursor:pointer; left:15px; bottom:-32px; z-index:2;">
                  <i @click="applyform" class="fas fa-paper-plane">
                    <span class="ml-1 mb-0" style="font-family: 'Do Hyeon', sans-serif;">영입하기</span>
                  </i>
                </div>
              </div>

              <div class="shrink mt-10 mx-4 mb-4">
                <div class="d-flex justify-space-between align-center">
                  <span class="font-weight-black mb-1">{{ teaminfo.title }}</span>
                  <small @click="expand = !expand" style="cursor:pointer; color:primary">더보기</small>
                </div>
                <v-expand-transition>
                  <v-card flat v-show="expand" class="mx-auto">
                    {{ teaminfo.content }}
                    <v-icon
                      right
                      @click="memberDelete"
                      v-show="this.status"
                      style="position:absolute; bottom:8px; right:0;"
                    >mdi-delete</v-icon>
                  </v-card>
                </v-expand-transition>
                <div class="d-flex">
                  <v-chip
                    small
                    class="mr-2 mt-1"
                    color="#0277BD"
                    text-color="white"
                    v-for="stack in JSON.parse(stacks)"
                    :key="stack"
                  ># {{ stack }}</v-chip>
                </div>
              </div>
            </v-expansion-panel>
          </v-expansion-panels>

          <v-dialog v-model="dialog" max-width="600px">
            <v-card style="border: 3px solid #eeeeee;">
              <v-img class="header" height="200px" src="../../assets/images/member2.jpg"></v-img>
              <v-card-title
                v-if="this.teamlist.length !== 0"
                class="header-text text-center justify-center body-1"
              >❝ {{ teaminfo.name }} 님을 영입합니다 ❠</v-card-title>
              <v-card-title
                v-else
                class="header-text text-center justify-center body-1"
              >❝ 아쉽지만 등록된 팀이 없어 영업이 불가합니다. ❠</v-card-title>

              <v-card-text v-if="this.teamlist.length !== 0" class="pb-0">
                <div class="mt-4">
                  <p class="mb-3 pl-1" style="font-size:1rem;">팀원에게 보내는 어필 한마디 🙈🙉</p>
                  <v-textarea filled v-model="content" name="content" placeholder="내용을 작성해주세요."></v-textarea>
                  <v-row class="mb-0">
                    <v-col class="py-0" cols="12">
                      <v-overflow-btn
                        filled
                        v-model="teamno"
                        :items="teamlist"
                        label="영입할 팀을 선택하세요"
                        item-text="teamName"
                        item-value="teamboardNo"
                        class="mb-0"
                      ></v-overflow-btn>
                    </v-col>
                  </v-row>
                </div>
              </v-card-text>
              <v-card-actions>
                <v-btn color="blue darken-1" text @click="dialog = false">취소</v-btn>
                <v-spacer></v-spacer>
                <v-btn
                  v-if="this.teamlist.length !== 0"
                  color="blue darken-1"
                  text
                  @click="submit"
                >영입하기</v-btn>
              </v-card-actions>
            </v-card>
          </v-dialog>
        </v-card>
      </v-col>
    </v-row>
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
      status: false,

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
    if (this.teaminfo.idx == this.$store.state.myToken.idx) {
      this.status = true;
    } else {
      this.status = false;
    }

    // profileURL을 초기화
    this.profileURL = this.teaminfo.profile;
  },
  methods: {
    profileMove(no) {
      this.$store.state.profileidx = no;
      this.$store.commit("pchange");
      if (this.$route.path == "/userProfile") {
        this.$router.go().catch(() => {});
      } else {
        this.$router.push("/userProfile").catch(() => {});
      }
    },
    addFavorite() {
      let token = window.$cookies.get("nnd");

      axios
        .put(
          `${process.env.VUE_APP_API_URL}/likemember/save/` +
            this.$store.state.myToken.idx +
            "/" +
            this.teaminfo.boardno,
          {},
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
        .delete(
          `${process.env.VUE_APP_API_URL}/likemember/delete/${this.mlikeno}`,
          {
            headers: {
              Authorization: "Bearer " + token.data, // the token is a variable which holds the token
            },
          }
        )
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
          alert("신청되었습니다.");
        })
        .catch((error) => {
          console.log(error.response);
          alert("실패했습니다.");
        });
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
    memberDelete() {
      let token = window.$cookies.get("nnd");

      confirm("삭제하시겠습니까?") &&
        axios
          .delete(
            `${process.env.VUE_APP_API_URL}/memberboard/delete/` +
              this.teaminfo.boardno,
            {
              headers: {
                Authorization: "Bearer " + token.data, // the token is a variable which holds the token
              },
            }
          )
          .then(() => {
            this.$router.go();
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
  font-size: 0.5rem;
  top: 80px;
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
  border-top: 10px solid #0277bd;
}
</style>
