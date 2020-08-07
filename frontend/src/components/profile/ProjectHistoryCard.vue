<template>
  <v-card>
    <v-card-title
      class="headline font-weight-regular light-green lighten-1 white--text"
    >project history</v-card-title>
    <v-form>
      <v-container>
        <v-text-field
          v-model="projectName"
          filled
          label="프로젝트 이름"
          placeholder="내용을 입력해주세요."
          required
        ></v-text-field>
        <v-text-field
          v-model="summary"
          filled
          label="프로젝트 한줄 설명"
          placeholder="내용을 입력해주세요."
          required
        ></v-text-field>
        <v-text-field
          v-model="usedStack"
          filled
          label="기술 스택"
          placeholder="기타 기술스택 직접 입력하기"
          required
        ></v-text-field>
        <v-textarea v-model="content" filled label="상세 업무 및 성과" placeholder="내용을 입력해주세요." required></v-textarea>
        <v-text-field
          v-model="gitLink"
          filled
          label="저장소 링크"
          placeholder="https://github.com/example"
          required
        ></v-text-field>
      </v-container>
      <v-card-actions>
        <v-spacer></v-spacer>
        <v-btn color="blue darken-1" text @click="changeDialog">Close</v-btn>
        <v-btn color="blue darken-1" text @click="submit">Save</v-btn>
      </v-card-actions>
    </v-form>
  </v-card>
</template>

<script>
import axios from "axios";
export default {
  props: {},
  data() {
    return {
      projectName: null,
      summary: null,
      usedStack: null,
      content: null,
      gitLink: null,
      user: null,
    };
  },
  created() {
    let token = window.$cookies.get("nnd");
    let id = token.object.idx;
    if (token) {
      this.$http
        .get(`http://localhost:8080/member/info/${id}`, {
          headers: {
            Authorization: "Bearer " + token, // the token is a variable which holds the token
          },
        })
        .then((res) => {
          this.user = res.data;
          this.profileURL = this.user.profile;
        });
    }
  },
  methods: {
    submit() {
      axios
        .put(`http://localhost:8080/projecthistory/save/${this.user.idx}`, {
          idx: this.user.idx,
          projectName: this.projectName,
          summary: this.summary,
          content: this.content,
          usedStack: this.usedStack,
          gitLink: this.gitLink,
          techStack: JSON.stringify(this.techStack),
        })
        .then((response) => {
          this.$emit("onSubmit");
          console.log(response);
          alert("등록에 성공했습니다");
          this.changeDialog();
          this.projectName = null;
          this.summary = null;
          this.usedStack = null;
          this.content = null;
          this.gitLink = null;
        })
        .catch((error) => {
          console.log(error.response);
          alert("등록에 실패했습니다");
        });
    },
    changeDialog() {
      this.$emit("changeDialog");
    },
  },
};
</script>

<style>
</style>