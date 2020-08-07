<template>
  <v-container fluid>
    <v-flex xs12 md6 offset-sm3>
      <v-card outlined class="elevation-3">
        <v-list-item class="mt-3">
          <v-avatar color="white" size="60" class="user-img mb-2">
            <img v-if="!profileURL" src="https://picsum.photos/200" />
            <img v-else :src="profileURL" />
          </v-avatar>
          <!-- <span class="grey--text">
              {{ teaminfo.teamName }}
              <div class="text-right">
                <v-chip
                  class="ma-2"
                  color="indigo"
                  text-color="white"
                  v-for="stack in JSON.parse(stacks)"
                  :key="stack"
                >{{ stack }}</v-chip>
              </div>
          </span>-->
          <v-col cols="4" md="4">{{ teaminfo.teamName }}</v-col>
          <v-col cols="6" md="6">
            <div class="text-right">
              <v-chip
                class="ma-2"
                color="indigo"
                text-color="white"
                v-for="stack in JSON.parse(stacks)"
                :key="stack"
              >{{ stack }}</v-chip>
            </div>
          </v-col>
        </v-list-item>
        <v-card-title>
          <span>{{ teaminfo.title }}</span>
        </v-card-title>
        <v-expansion-panels class="elevation-0 mt-5">
          <v-expansion-panel>
            <v-expansion-panel-header></v-expansion-panel-header>
            <v-expansion-panel-content>{{ teaminfo.content }}</v-expansion-panel-content>
            <v-expansion-panel-content>{{ teaminfo.kakaoLink }}</v-expansion-panel-content>
            <v-card-actions>
              <v-btn icon color="pink" v-if="!favorite" @click="addFavorite">
                <v-icon>mdi-star-outline</v-icon>
              </v-btn>
              <v-btn icon color="pink" v-if="favorite" @click="delFavorite">
                <v-icon>mdi-star</v-icon>
              </v-btn>
              <v-spacer></v-spacer>
              <v-btn color="green darken-1" text @click="applyform" right>신청하기</v-btn>
            </v-card-actions>
          </v-expansion-panel>
        </v-expansion-panels>

        <v-expand-transition>
          <div v-show="show">
            <v-divider />
            <v-dialog v-model="dialog" max-width="600px">
              <v-card style="align='center';
            justify='center';">
                <v-card-title>
                  <span class="headline">신청 Form</span>
                </v-card-title>
                <v-card-text>
                  <v-container>
                    <v-row>
                      <v-col cols="12" align="center" justify="center">
                        <v-avatar color="grey" size="90" class="mb-2">
                          <span v-if="!profileURL" class="white--text headline">GD</span>
                          <img v-else :src="profileURL" />
                        </v-avatar>
                        <h3>팀 장 : {{ teaminfo.name }}</h3>
                        <p>팀 이름 : {{ teaminfo.teamName }}</p>
                      </v-col>
                      <v-col cols="12">
                        <v-textarea v-model="content" name="content" label="신청 메세지를 적어주세요."></v-textarea>
                      </v-col>
                    </v-row>
                  </v-container>
                </v-card-text>
                <v-card-actions>
                  <v-spacer></v-spacer>
                  <v-btn color="blue darken-1" text @click="dialog = false">취소</v-btn>
                  <v-btn color="blue darken-1" text @click="submit">신청하기</v-btn>
                </v-card-actions>
              </v-card>
            </v-dialog>
          </div>
        </v-expand-transition>
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
      stacks: this.teaminfo.techStack,
      username: "",
      profileURL: "",
      ///쪽찌보낼내용
      sendIdx: "",
      receiveIdx: "",
      content: "",
      lettertype: "tboard",
      letterNo: "",
      createDate: "",
    };
  },
  // mounted(){
  //   this.teamboardno = this.teaminfo.teamboardNo;
  // },
  created() {},
  methods: {
    addFavorite() {
      this.favorite = true;
      alert("즐겨찾기에 등록되었습니다.");
    },
    delFavorite() {
      this.favorite = false;
    },
    submit() {
      this.dialog = false;
      console.log(this.sendIdx + " send");
      console.log(this.receiveIdx + " receive");
      console.log(this.lettertype + " type");
      axios
        .put("http://localhost:8080/letter/create/" + this.lettertype, {
          sendIdx: this.sendIdx,
          receiveIdx: this.teaminfo.idx,
          content: this.content,
          letterNo: this.letterNo,
          createDate: this.createDate,
        })
        .then((response) => {
          console.log(response);
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
        console.log(token.object.idx);
        this.username = token.object.name;
        this.profileURL = token.object.profile;
        this.sendIdx = token.object.idx;
      }
    },
  },
};
</script>

<style></style>
