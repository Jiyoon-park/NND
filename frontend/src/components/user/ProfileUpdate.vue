<template>
  <v-row justify="center">
    <NavBar />
    <v-col cols="10" md="8" lg="6" class="mt-15">
      <div class="user-info" style="position:relative;">
        <v-avatar color="grey" size="90" class="mb-2">
          <span v-if="!profileURL" class="white--text headline"></span>
          <img v-else :src="profileURL" />
        </v-avatar>
        <h3>{{ user.name }}</h3>
        <v-tooltip bottom>
          <template v-slot:activator="{ on, attrs }">
            <v-btn
              v-bind="attrs"
              v-on="on"
              small
              @click="modify"
              style="position:absolute; bottom:0; right:0;"
              color="grey lighten-1"
              dark
            >
              <span>저장</span>
            </v-btn>
          </template>
          <span>변경된 정보를 저장 합니다.</span>
        </v-tooltip>
      </div>
      <v-tabs fixed-tabs color="indigo lighten-1" class="tabs">
        <v-spacer></v-spacer>
        <v-tab @click="$vuetify.goTo('#my-info', options)">내정보</v-tab>
        <v-tab @click="$vuetify.goTo('#skills', options)">기술스택</v-tab>
        <v-tab @click="$vuetify.goTo('#experience', options)">참여이력</v-tab>
        <v-spacer></v-spacer>
      </v-tabs>

      <div id="my-info" class="target">
        <h3 class="mb-1">🌞 내정보 🌞</h3>
        <v-row class="py-2" style="background-color: #fafafa; border-radius:10px;">
          <v-card-text>
            <v-text-field filled dense disabled :value="user.email" label="이메일" color="white"></v-text-field>
            <v-text-field filled dense v-model="user.name" label="이름"></v-text-field>
            <v-text-field dense v-model="user.gitaddr" label="GIT 주소" filled></v-text-field>
            <v-text-field dense v-model="user.profile" label="프로필사진 링크" filled></v-text-field>
            <v-btn type="button" @click="PreviewImg">이미지 미리보기</v-btn>
          </v-card-text>
        </v-row>
      </div>

      <div id="skills" class="target">
        <h3 class="mb-1">✨ 기술스택 ✨</h3>
        <v-row class="py-2" style="background-color: #fafafa; border-radius:10px;">
          <v-col cols="12" sm="12" class="pb-0">
            <v-combobox v-model="select" :items="items" label="기술스택" multiple chips filled></v-combobox>
          </v-col>
        </v-row>
      </div>

      <div id="experience" class="target">
        <div class="d-flex justify-space-between">
          <h3 class="mb-1">🏅 참여이력 🏅</h3>
          <div>
            <AddProjectHistory />
          </div>
        </div>
        <v-row class="py-2" style="background-color: #fafafa; border-radius:10px;">
          <v-col cols="12" sm="12" lg="6">
            <ProjectHistoryList />
          </v-col>
        </v-row>
      </div>
    </v-col>
  </v-row>
</template>

<script>
import * as easings from "vuetify/es5/services/goto/easing-patterns";
import axios from "axios";

import NavBar from "../common/NavBar.vue";
import AddProjectHistory from "../profile/AddProjectHistory.vue";
import ProjectHistoryList from "../profile/ProjectHistoryList.vue";

export default {
  components: {
    NavBar,
    AddProjectHistory,
    ProjectHistoryList,
  },
  data() {
    return {
      duration: 500,
      offset: 0,
      easing: "easeInOutCubic",
      easings: Object.keys(easings),
      hasSaved: false,
      isEditing: null,
      model: null,
      user: "",
      profileURL: "",
      items: ["C", "C++", "JAVA", "Spring", "Django", "C#", "Go"],
      select: [],
    };
  },
  created() {
    let token = window.$cookies.get("nnd"); //nnd가 key인 쿠키 가져옴
    if (token) {
      //토큰 존재하면
      this.user = token.object;
      this.profileURL = this.user.profile;
      this.select = JSON.parse(this.user.memberstack);
    }
  },
  methods: {
    PreviewImg() {
      this.profileURL = this.user.profile;
    },
    modify() {
      let token = window.$cookies.get("nnd"); //nnd가 key인 쿠키 가져옴
      console.log(token);
      axios
        .post(
          `${process.env.VUE_APP_API_URL}/member/update`,
          {
            email: this.user.email,
            name: this.user.name,
            profile: this.user.profile,
            gitaddr: this.user.gitaddr,
            memberstack: JSON.stringify(this.select),
          },
          {
            headers: {
              Authorization: "Bearer " + token.data, // the token is a variable which holds the token
            },
          }
        )
        .then((res) => {
          console.log(res);
          window.$cookies.remove("nnd"); //쿠키삭제
          window.$cookies.set("nnd", res.data, "2d"); //쿠키다시저장
          this.$router.push({ name: "Profile" }); //home으로 보냄
        });
    },
  },
  computed: {
    target() {
      const value = this[this.type];
      if (!isNaN(value)) return Number(value);
      else return value;
    },
    options() {
      return {
        duration: this.duration,
        offset: this.offset,
        easing: this.easing,
      };
    },
  },
};
</script>

<style scoped>
.user-info {
  margin: 5px 0;
  padding: 30px 0;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
}
.tabs {
  margin-bottom: 30px;
}

.target {
  margin: 20px 0;
}

#my-info p {
  margin-bottom: 5px;
}

.skills {
  margin: 5px 0;
}

.skill {
  margin: 0 3px;
}
</style>
