<template>
  <v-app>
    <NavBar />
    <v-main>
      <v-row justify="center">
        <v-col cols="8">
          <v-card class="mt-5">
            <v-radio-group v-model="teamcheck" row :mandatory="true">
              <v-radio label="팀" value="팀"></v-radio>
              <v-radio label="팀원" value="팀원"></v-radio>
            </v-radio-group>
            <v-form ref="form">
              <h2 class="tc">{{ teamcheck }} 등록</h2>
              <v-card-text>
                <v-text-field
                  v-model="teamName"
                  label="팀이름"
                  required
                  v-if="teamcheck == '팀'"
                ></v-text-field>
                <v-text-field
                  v-model="title"
                  label="제목"
                  required
                ></v-text-field>
                <v-textarea v-model="content" label="내용"></v-textarea>
              </v-card-text>
              <v-col sm="12" md="12" v-if="teamcheck == '팀'">
                <v-combobox
                  v-model="memberEmails"
                  :items="memberEmails"
                  hide-selected
                  label="팀원 이메일"
                  multiple
                  persistent-hint
                  small-chips
                >
                </v-combobox>
              </v-col>
              <v-col cols="12" sm="12" v-if="teamcheck == '팀'">
                <p>팀원수</p>

                <v-overflow-btn
                  v-model="groupSize"
                  :items="dropdown_font"
                  label="선택하세요"
                  class="my-2"
                ></v-overflow-btn>
              </v-col>
              <v-col sm="12" md="12" v-if="teamcheck == '팀'">
                <p>마감일</p>
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
                      v-model="date"
                      readonly
                      v-bind="attrs"
                      v-on="on"
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

              <v-col sm="12" md="12" v-if="teamcheck == '팀'">
                <v-combobox
                  v-model="techStack"
                  :items="items"
                  hide-selected
                  label="필요 기술 스택"
                  multiple
                  persistent-hint
                  small-chips
                >
                </v-combobox>
              </v-col>

              <v-col sm="12" md="12" v-if="teamcheck == '팀원'">
                <v-combobox
                  v-model="memberstack"
                  :items="items"
                  hide-selected
                  label="기술 스택"
                  multiple
                  persistent-hint
                  small-chips
                >
                </v-combobox>
              </v-col>

              <v-col cols="12" sm="12">
                <p>팀 유형</p>
                <v-overflow-btn
                  v-model="category"
                  :items="categoryList"
                  label="선택하세요"
                  class="my-2"
                ></v-overflow-btn>
              </v-col>
              <v-card-actions>
                <v-spacer></v-spacer>
                <v-btn text @click="$router.push('/')">취소</v-btn>
                <v-btn
                  text
                  color="primary"
                  v-if="teamcheck == '팀'"
                  @click="submit"
                  >팀 등록</v-btn
                >
                <v-btn
                  text
                  color="primary"
                  v-if="teamcheck == '팀원'"
                  @click="submit"
                  >팀원 등록</v-btn
                >
              </v-card-actions>
            </v-form>
          </v-card>
        </v-col>
      </v-row>
    </v-main>
  </v-app>
</template>

<script>
import NavBar from "../../components/common/NavBar.vue";
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
  }),
  components: {
    NavBar,
  },
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
        .put(url,obj,
        {
          {
            headers: {
              Authorization: "Bearer " + token.data, // the token is a variable which holds the token
            },
          }
        )
        .then((response) => {
          console.log(response);
          alert("등록성공");
          this.goMain();
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
  },
};
</script>

<style>
.tc {
  text-align: center;
}
</style>
