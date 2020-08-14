<template>
  <v-card>
    <v-card-title
      class="headline font-weight-regular indigo lighten-1 white--text"
      >project history</v-card-title
    >
    <v-form>
      <v-container class="pb-0">
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
          persistent-hint
          required
        ></v-text-field>
        <v-combobox
          filled
          v-model="usedStack"
          :items="items"
          hide-selected
          label="기술 스택"
          placeholder="기술스택 직접 입력하기"
          persistent-hint
          multiple
          required
          small-chips
        ></v-combobox>

        <v-textarea
          v-model="content"
          filled
          label="상세 업무 및 성과"
          placeholder="내용을 입력해주세요."
          required
        ></v-textarea>
        <v-text-field
          v-model="gitLink"
          filled
          label="저장소 링크"
          placeholder="https://github.com/example"
          required
        ></v-text-field>
      </v-container>
      <v-divider></v-divider>
      <v-card-actions>
        <v-btn
          color="indigo darken-1"
          class="font-weight-bold"
          text
          @click="changeDialog"
          >닫기</v-btn
        >
        <v-spacer></v-spacer>
        <v-btn
          color="indigo darken-1"
          class="font-weight-bold"
          text
          @click="submit"
          >저장</v-btn
        >
      </v-card-actions>
    </v-form>
  </v-card>
</template>

<script>
import axios from "axios";
import { EventBus } from "../../main";

export default {
  props: {},
  data() {
    return {
      projectName: null,
      summary: null,
      usedStack: JSON.stringify(this.usedStack),
      content: null,
      gitLink: null,
      user: null,
      items: [
        "C",
        "C++",
        "JAVA",
        "Spring",
        "Django",
        "C#",
        "Go",
        "R",
        "Javascript",
        "vue.js",
        "Linux",
        "MYSQL",
        "Mariadb",
        "RestApi",
        "Python",
        "Pytorch",
        "Tensorflow",
        "Spirngboot",
        "Ruby",
      ],
    };
  },
  created() {
    let token = window.$cookies.get("nnd");
    this.user = token.object;
    this.profileURL = this.user.profile;
  },
  methods: {
    submit() {
      let token = window.$cookies.get("nnd");
      axios
        .put(
          `${process.env.VUE_APP_API_URL}/projecthistory/save/${this.user.idx}`,
          {
            idx: this.user.idx,
            projectName: this.projectName,
            summary: this.summary,
            content: this.content,
            usedStack: JSON.stringify(this.usedStack),
            gitLink: this.gitLink,
          },
          {
            headers: {
              Authorization: "Bearer " + token.data, // the token is a variable which holds the token
            },
          }
        )
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
          EventBus.$emit("create-card"); //location.reload()
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

<style></style>
