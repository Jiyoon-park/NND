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
                    <span class="deadline">
                      ~ {{ teaminfo.deadline }}
                      마감
                    </span>
                  </div>
                </div>
              </div>
              <div style="position:relative;">
                <div v-if="!teaminfo.imageurl">
                  <v-img
                    :aspect-ratio="16 / 9"
                    v-if="teaminfo.category === '스터디'"
                    src="../../assets/images/study.jpg"
                  ></v-img>
                  <v-img
                    :aspect-ratio="16 / 9"
                    v-else-if="teaminfo.category === '프로젝트'"
                    src="../../assets/images/project.jpg"
                  ></v-img>
                  <v-img :aspect-ratio="16 / 9" v-else src="../../assets/images/competition.jpg"></v-img>
                </div>
                <div v-else>
                  <v-img :aspect-ratio="16 / 9" :src="teaminfo.imageurl"></v-img>
                </div>
                <span
                  class="mr-3 mt-1 d-flex flex-column align-end"
                  style="position:absolute; top:0; right:0; font-weight:bold; font-style:italic;"
                >
                  <span
                    style="text-shadow:1px 1px black; color:#eeeeee; font-size:18px;"
                  >{{ teaminfo.category }}</span>

                  <small style="background-color:#eeeeee; opacity:0.7;" class="px-1">
                    참여 인원 {{ teaminfo.memcnt }}/{{
                    teaminfo.groupsize
                    }}
                  </small>
                </span>

                <div
                  style="position:absolute; cursor:pointer; right:15px; bottom:-32px; z-index:2;"
                >
                  <i class="far fa-bookmark" v-if="!favorite" @click="addFavorite"></i>
                  <i class="fas fa-bookmark" v-else @click="delFavorite"></i>
                </div>
                <div style="position:absolute; cursor:pointer; left:15px; bottom:-32px; z-index:2;">
                  <i @click="applyform" class="fas fa-paper-plane">
                    <span class="ml-1 mb-0" style="font-family: 'Do Hyeon', sans-serif;">지원하기</span>
                  </i>
                </div>
              </div>

              <div class="shrink mt-10 mx-4 mb-4">
                <div class="d-flex justify-space-between align-center">
                  <span class="font-weight-black mb-1">{{ teaminfo.title }}</span>
                  <small @click="expand = !expand" style="cursor:pointer; color:primary">더보기</small>
                </div>
                <v-expand-transition>
                  <v-card flat v-show="expand" class="mx-auto" style="position:relative;">
                    {{ teaminfo.content }}
                    <div class="mt-4">
                      <small>참여팀원</small>
                      <div class="d-flex mr-3 my-2 align-center">
                        <v-avatar
                          v-for="profile in teaminfo.profiles"
                          v-bind:key="profile.memberProfile"
                          style="cursor:pointer; margin-right:10px"
                          color="#eeeeee"
                          size="30"
                          class="user-img mb-2"
                          @click="profileMove(profile.memberIdx)"
                        >
                          <i v-if="!profile.memberProfile" class="fas fa-user"></i>
                          <img v-else :src="profile.memberProfile" />
                        </v-avatar>
                      </div>
                    </div>
                    <v-icon
                      right
                      @click="teamDelete"
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
              <v-img class="header" height="200px" src="../../assets/images/team2.jpg"></v-img>
              <v-card-title
                class="header-text text-center justify-center body-1"
              >❝ {{ teaminfo.teamname }} 팀에 지원합니다 ❠</v-card-title>

              <v-card-text class="pb-0">
                <div class="mt-4">
                  <p class="mb-3 pl-1" style="font-size:1rem;">팀장에게 보내는 어필 한마디 🙈🙉</p>
                  <v-textarea filled v-model="content" name="content" placeholder="내용을 작성해주세요."></v-textarea>
                </div>
              </v-card-text>
              <v-card-actions class="pt-0">
                <v-btn color="blue darken-1" text @click="dialog = false">취소</v-btn>
                <v-spacer></v-spacer>
                <v-btn color="blue darken-1" class="font-weight-bold" text @click="submit">지원하기</v-btn>
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
  name: "NewsFeed2",
  props: ["teaminfo", "boardtype"],

  data() {
    return {
      show: false,
      favorite: false,
      status: false,
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
      expand: false,
    };
  },

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
          `${process.env.VUE_APP_API_URL}/liketeam/save/` +
            this.$store.state.myToken.idx +
            "/" +
            this.teaminfo.teamboardno,
          {},
          {
            headers: {
              Authorization: "Bearer " + token.data, // the token is a variable which holds the token
            },
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
        .delete(
          `${process.env.VUE_APP_API_URL}/liketeam/delete/${this.tlikeno}`,
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
      this.dialog = false;
      let token = window.$cookies.get("nnd");

      axios
        .put(
          `${process.env.VUE_APP_API_URL}/letter/create/` + this.lettertype,
          {
            read: "",
            sendIdx: this.sendIdx,
            receiveIdx: this.teaminfo.idx,
            content: this.content,
            letterNo: this.letterNo,
            createDate: this.createDate,
            lettertype: this.lettertype,
            teamboardNo: this.teamboardNo,
          },
          {
            headers: {
              Authorization: "Bearer " + token.data, // the token is a variable which holds the token
            },
          }
        )
        .then(() => {
          console.log(this.sendIdx);
          alert("신청되었습니다.");
        })
        .catch((error) => {
          console.log(error.response);
          alert("실패했습니다.");
        });
    },
    applyform() {
      let token = window.$cookies.get("nnd");
      // 지원을 받기전 마감시간이 지났는지 체크하도록 한다.
      // 지났다 = 현재시간 - 마감시간 > 0
      // 안지났다 = 반대
      var curTime = new Date();
      var endTime = new Date(this.teaminfo.deadline);

      // 9시로 계산되는 경향이 있어서 15시간을 추가한다.
      endTime.setHours(endTime.getHours() + 15);

      if (this.boardtype == "team") {
        this.boardtype = "tboard";
      } else if (this.boardtype == "member") {
        this.boardtype = "mboard";
      }
      axios
        .get(
          `${process.env.VUE_APP_API_URL}/letter/check/overlap/${token.object.idx}/${this.teaminfo.idx}/${this.boardtype}/${this.teaminfo.teamboardno}`,
          {
            headers: {
              Authorization: "Bearer " + token.data, // the token is a variable which holds the token
            },
          }
        )
        .then((res) => {
          if (res.data == "overlap letter") {
            alert("중복 지원입니다.");
          } else if (this.teaminfo.memcnt >= this.teaminfo.groupsize) {
            alert("모집인원을 초과했습니다.");
          } else if (curTime.getTime() - endTime.getTime() > 0) {
            alert("마감되었습니다.");
          } else {
            this.dialog = !this.dialog;
            if (token) {
              console.log("프로필주소 : " + token.object.profile);
              this.username = token.object.name;
              // this.profileURL = token.object.profile;
              this.sendIdx = token.object.idx;
            }
          }
        })
        .catch((err) => {
          console.log(err);
        });
    },
    teamDelete() {
      let token = window.$cookies.get("nnd");

      confirm("삭제하시겠습니까?") &&
        axios
          .delete(
            `${process.env.VUE_APP_API_URL}/teamboard/delete/` +
              this.teaminfo.teamboardno,
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
