<template>
  <v-card style="position:relative;">
    <v-list class="transparent">
      <v-row justify="center">
        <v-dialog v-model="dialog" persistent max-width="600px">
          <template v-slot:activator="{ on, attrs }">
            <v-btn
              color="primary"
              dark
              small
              v-bind="attrs"
              v-on="on"
              style="position:absolute; top:-40px; right:0;"
            >글쓰기</v-btn>
          </template>
          <v-card>
            <v-card-title>
              <span class="headline">게시판 글쓰기</span>
            </v-card-title>
            <v-card-text>
              <v-container>
                <v-row>
                  <v-col cols="12">
                    <v-text-field label="제목*" required v-model="title"></v-text-field>
                  </v-col>
                  <v-col cols="12">
                    <v-textarea label="내용*" required v-model="content"></v-textarea>
                  </v-col>
                  <v-col cols="12" sm="6">
                    <v-select :items="['공지']" label="공지" v-model="notice"></v-select>
                  </v-col>
                </v-row>
              </v-container>
              <small>*꼭 필요한 항목 표시</small>
            </v-card-text>
            <v-card-actions>
              <v-spacer></v-spacer>
              <v-btn color="blue darken-1" text @click="dialog = false">닫기</v-btn>
              <v-btn color="blue darken-1" text @click="addPost()">저장</v-btn>
            </v-card-actions>
          </v-card>
        </v-dialog>
      </v-row>
      <v-list-item-group>
        <p v-if="this.teamposts.length == 0">작성된 글이 없습니다</p>
        <TeamPostItem
          v-else
          v-for="(post, i) in teamposts"
          :key="i"
          :postinfo="teamposts[i]"
          :userinfo="userinfo"
        />
      </v-list-item-group>
    </v-list>
  </v-card>
</template>

<script>
import TeamPostItem from "./teamPostItem.vue";
import axios from "axios";

export default {
  name: "TeamPost",
  components: {
    TeamPostItem,
  },
  data() {
    return {
      dialog: false,
      teamposts: [],
      title: "",
      content: "",
      notice: "",
      teamboardno: "",
    };
  },
  props: {
    userinfo: {
      type: Object,
    },
    teaminfo: {
      type: Object,
    },
  },

  created() {
    let token = window.$cookies.get("nnd");

    axios
      .get(
        `${process.env.VUE_APP_API_URL}/teammenu/post/` +
          this.$store.state.teamNo,
        {
          headers: {
            Authorization: "Bearer " + token.data, // the token is a variable which holds the token
          },
        }
      )
      .then((res) => {
        this.teamposts = res.data;
      })
      .catch((err) => {
        console.log(err);
      });
  },
  methods: {
    addPost() {
      var obj, url;
      let token = window.$cookies.get("nnd");
      this.dialog = false;
      if (this.notice == "공지") {
        this.notice = 1;
      } else {
        this.noice = 0;
      }
      obj = {
        teamPostNo: "",
        teamboardNo: this.teaminfo.teamboardNo,
        memberIdx: this.userinfo.idx,
        writer: this.userinfo.name,
        title: this.title,
        content: this.content,
        notice: this.notice,
        createDate: "",
      };
      url = `${process.env.VUE_APP_API_URL}/teammenu/post/save/`;

      axios
        .put(url, obj, {
          headers: {
            Authorization: "Bearer " + token.data, // the token is a variable which holds the token
          },
        })
        .then((response) => {
          console.log(response);
          alert("등록성공");

          this.title = null;
          this.content = null;
          this.notice = null;
          this.$emit("changeDialog");
        })
        .catch((error) => {
          console.log(error.response);
          alert("실패");
        });
    },
  },
};
</script>

<style></style>
