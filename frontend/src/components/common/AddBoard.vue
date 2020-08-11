<template>
  <v-card>
    <v-toolbar dark color="indigo lighten-2">
      <v-btn icon dark @click="changeDialog">
        <v-icon>mdi-close</v-icon>
      </v-btn>
      <v-toolbar-title>새 글 작성</v-toolbar-title>
      <v-spacer></v-spacer>
    </v-toolbar>
    <v-container style="padding:3%;">
      <div class="d-flex justify-center">
        <v-chip-group
          v-model="teamcheck"
          active-class="indigo lighten-1 white--text text--accent-4"
          mandatory
          class="my-3"
        >
          <v-chip
            large
            v-for="type in types"
            :key="type"
            :value="type"
            class="mx-2"
            >{{ type }} 등록</v-chip
          >
        </v-chip-group>
      </div>
      <v-col cols="12" sm="12">
        <span class="subheader">✔ 모집 유형</span>
        <v-radio-group v-model="category" row>
          <v-radio
            label="스터디"
            color="orange darken-3"
            value="스터디"
          ></v-radio>
          <v-radio
            label="프로젝트"
            color="red darken-3"
            value="프로젝트"
          ></v-radio>
          <v-radio
            label="공모전"
            color="indigo darken-3"
            value="공모전"
          ></v-radio>
        </v-radio-group>
      </v-col>
      <span class="ml-3 subheader" v-if="teamcheck == '팀'">✔ 팀 소개</span>
      <span class="ml-3 subheader" v-else>✔ 자기 소개</span>
      <v-form ref="form">
        <v-card-text>
          <v-text-field
            filled
            dense
            v-model="teamName"
            label="팀명"
            required
            v-if="teamcheck == '팀'"
          ></v-text-field>
          <v-text-field
            filled
            dense
            v-model="title"
            label="제목"
            required
          ></v-text-field>
          <v-textarea filled dense v-model="content" label="내용"></v-textarea>
          <v-combobox
            filled
            dense
            v-model="techStack"
            :items="items"
            hide-selected
            label="필요 기술 스택"
            multiple
            persistent-hint
            small-chips
            v-if="teamcheck == '팀'"
          ></v-combobox>

          <v-combobox
            v-model="memberstack"
            :items="items"
            hide-selected
            label="보유 기술 스택"
            multiple
            persistent-hint
            small-chips
            filled
            v-if="teamcheck == '팀원'"
          ></v-combobox>
          <v-combobox
            filled
            dense
            v-model="memberEmails"
            :items="memberEmails"
            hide-selected
            label="참여 확정 팀원"
            multiple
            persistent-hint
            small-chips
            v-if="teamcheck == '팀'"
          ></v-combobox>
        </v-card-text>
        <v-card-text class="py-0">
          <v-row>
            <v-col cols="12" lg="6" v-if="teamcheck == '팀'" class="py-0">
              <span class="subheader">✔ 모집 팀원 수</span>
              <v-overflow-btn
                outlined
                v-model="groupSize"
                :items="dropdown_font"
                label="선택하세요"
                class="mt-2 pl-0"
              ></v-overflow-btn>
            </v-col>
            <v-col sm="12" lg="6" v-if="teamcheck == '팀'" class="py-0">
              <span class="subheader">✔ 모집 마감일</span>
              <v-menu
                ref="menu"
                v-model="menu"
                :close-on-content-click="false"
                :return-value.sync="date"
                transition="scale-transition"
                offset-y
                min-width="290px"
              >
                <template v-slot:activator="{ on, attrs }">
                  <v-text-field
                    outlined
                    v-model="date"
                    readonly
                    v-bind="attrs"
                    v-on="on"
                    class="mt-2"
                  ></v-text-field>
                </template>
                <v-date-picker v-model="date" no-title scrollable>
                  <v-spacer></v-spacer>
                  <v-btn text color="primary" @click="menu = false"
                    >Cancel</v-btn
                  >
                  <v-btn text color="primary" @click="$refs.menu.save(date)"
                    >OK</v-btn
                  >
                </v-date-picker>
              </v-menu>
            </v-col>
          </v-row>
        </v-card-text>
      </v-form>
    </v-container>
    <v-divider></v-divider>
    <v-card-actions>
      <v-btn text @click="changeDialog">취소</v-btn>
      <v-spacer></v-spacer>
      <v-btn
        color="indigo darken-1"
        text
        class="font-weight-bold"
        v-if="teamcheck == '팀'"
        @click="submit"
        >팀 등록</v-btn
      >
      <v-btn
        color="indigo darken-1"
        text
        class="font-weight-bold"
        v-if="teamcheck == '팀원'"
        @click="submit"
        >팀원 등록</v-btn
      >
    </v-card-actions>
  </v-card>
</template>

<script>
import axios from "axios";

export default {
  data: () => ({
    date: new Date().toISOString().substr(0, 10),
    menu: false,
    model: false,
    menu2: false,
    dropdown_font: [3, 4, 5, 6],
    categoryList: ["스터디", "프로젝트", "공모전"],
    items: [],
    teamName: null,
    title: null,
    content: null,
    groupSize: null,
    category: "",
    techStack: [],
    memberstack: [],
    email: null,
    teamcheck: null,
    idx: null,
    memberEmails: [],
    name: "",
    types: ["팀", "팀원"],
  }),
  components: {},
  created() {
    let token = window.$cookies.get("nnd");
    if (token) {
      console.log("유저의 전체정보: " + token.object);
      this.email = token.object.email;
      this.idx = token.object.idx;
      this.name = token.object.name;
      this.memberstack = JSON.parse(token.object.memberstack);
      console.log("유저의 email: " + this.email);
      console.log("유저의 idx: " + this.idx);
      console.log("유저의 name: " + this.name);
      console.log("유저의 memberstack: " + this.memberstack);
    }
  },
  methods: {
    submit() {
      var obj, url;
      let token = window.$cookies.get("nnd");
      if (this.teamcheck == "팀") {
        obj = {
          email: this.email,
          teamName: this.teamName,
          memberEmails: JSON.stringify(this.memberEmails),
          groupSize: this.groupSize,
          deadLine: this.date,
          title: this.title,
          content: this.content,
          techStack: JSON.stringify(this.techStack),
          category: this.category,
          name: this.name,
        };
        url = "http://localhost:8080/teamboard/save/" + this.idx;
      } else {
        // 팀원의 경우
        obj = {
          email: this.email,
          title: this.title,
          content: this.content,
          category: this.category,
          techStack: JSON.stringify(this.memberstack),
          name: this.name,
        };
        url = "http://localhost:8080/memberboard/save/" + this.idx;
      }
      axios
        .put(url, obj, {
          headers: {
            Authorization: "Bearer " + token.data, // the token is a variable which holds the token
          },
        })
        .then((response) => {
          console.log(response);
          alert("등록성공");
          this.changeDialog();

          this.teamName = null;
          this.title = null;
          this.content = null;
          this.groupSize = null;
          this.memberEmails = [];
          this.category = "";
          this.techStack = [];
          this.memberstack = [];
          this.date = new Date().toISOString().substr(0, 10);
        })
        .catch((error) => {
          console.log(error.response);
          alert("실패");
        });
    },
    goMain() {
      this.$router.push({
        name: "Home",
      });
    },
    changeDialog() {
      this.$emit("changeDialog");
    },
  },
};
</script>

<style scoped>
.tc {
  text-align: center;
}

.subheader {
  padding: 1px 0;
  background-color: #eeeeee;
  font-style: italic;
}
</style>
