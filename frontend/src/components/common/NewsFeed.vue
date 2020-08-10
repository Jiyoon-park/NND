<template>
  <v-container fluid>
    <v-flex xs12 md6 offset-sm3>
      <v-card outlined class="elevation-3">
        <v-list-item class="mt-3">
          <v-avatar color="indigo" class="mr-5">
            <v-icon
              @click="
                $router
                  .push({ name: 'userProfile', params: { idx: teaminfo.idx } })
                  .catch(() => {})
              "
              dark
              >mdi-account-circle</v-icon
            >
          </v-avatar>
          <v-col cols="4" md="4">{{ teaminfo.name }}</v-col>
          <v-col cols="6" md="6">
            <div class="text-right">
              <v-chip
                class="ma-2"
                color="indigo"
                text-color="white"
                v-for="stack in JSON.parse(stacks)"
                :key="stack"
                >{{ stack }}</v-chip
              >
            </div>
          </v-col>
        </v-list-item>
        <v-card-title>
          <span>{{ teaminfo.title }}</span>
        </v-card-title>
        <v-expansion-panels class="elevation-0 mt-5">
          <v-expansion-panel>
            <v-expansion-panel-header></v-expansion-panel-header>
            <v-expansion-panel-content>{{
              teaminfo.content
            }}</v-expansion-panel-content>
            <v-card-actions>
              <v-btn icon color="pink" v-if="!favorite" @click="addFavorite">
                <v-icon>mdi-star-outline</v-icon>
              </v-btn>
              <v-btn icon color="pink" v-if="favorite" @click="delFavorite">
                <v-icon>mdi-star</v-icon>
              </v-btn>
              <v-spacer></v-spacer>
              <v-btn color="green darken-1" text @click="applyform" right
                >꼬시기</v-btn
              >
            </v-card-actions>
          </v-expansion-panel>
        </v-expansion-panels>

        <v-dialog v-model="dialog" max-width="600px">
          <v-card>
            <v-img
              class="header"
              height="200px"
              src="../../assets/images/member2.jpg"
            ></v-img>
            <v-card-title class="header-text justify-center font-italic">
              ❝ {{ teaminfo.teamName }}팀으로
              <br />
              {{ teaminfo.name }}님을 영입합니다 ❠
            </v-card-title>

            <v-card-text class="mt-5 pb-0">
              <div class="mt-3">
                <v-row>
                  <p class="mb-0 pl-1">
                    {{ teaminfo.name }}에게 보내는 어필 한마디 🙈🙉
                  </p>
                  <select name="teamno" id="teamno"> </select>
                </v-row>
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
              <v-btn color="blue darken-1" text @click="submit">영입하기</v-btn>
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
      teamno: ["a", "b", "c", "d"],
    };
  },
  // mounted(){
  //   this.teamboardno = this.teaminfo.teamboardNo;
  // },
  created() {
    if (this.teaminfo.mno != null) {
      console.log("즐겨찾기 상태");
      this.favorite = true;
    } else {
      console.log("즐겨찾기 아닌상태");
      this.favorite = false;
    }
  },
  methods: {
    addFavorite() {
      axios
        .put(
          "http://localhost:8080/likemember/save/" +
            this.$store.state.myToken.idx +
            "/" +
            this.teaminfo.boardno
        )
        .then(() => {
          this.favorite = true;
          alert("즐겨찾기에 등록되었습니다.");
        });
    },
    delFavorite() {
      axios
        .delete(
          "http://localhost:8080/likemember/delete/" + this.teaminfo.likeno
        )
        .then(() => {
          this.favorite = false;
          alert("즐겨찾기에서 삭제되었습니다.");
        });
    },
    submit() {
      let token = window.$cookies.get("nnd");
      this.dialog = false;
      console.log(this.sendIdx + " send");
      console.log(this.teaminfo.idx + " receive");
      console.log(this.letterType + " type");

      axios
        .put(
          "http://localhost:8080/letter/create/" + this.lettertype,
          {
            sendIdx: this.sendIdx,
            receiveIdx: this.teaminfo.idx,
            content: this.content,
            letterNo: this.letterNo,
            createDate: this.createDate,
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
    },
  },
};
</script>
