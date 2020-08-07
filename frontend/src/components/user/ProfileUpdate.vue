<template>
  <v-row justify="center">
    <NavBar />
    <v-col cols="10" md="8" lg="6" class="mt-15">
      <div class="user-info">
        <v-avatar color="grey" size="90" class="mb-2">
          <span v-if="!profileURL" class="white--text headline"></span>
          <img v-else :src="profileURL" />
        </v-avatar>
        <h3>{{ user.name }}</h3>
        <p># 참여중인 팀 : 앨리스</p>
        <v-btn small @click="modify">save</v-btn>
      </div>
      <v-tabs class="tabs">
        <v-spacer></v-spacer>
        <v-tab @click="$vuetify.goTo('#my-info', options)">내정보</v-tab>
        <v-tab @click="$vuetify.goTo('#skills', options)">기술스택</v-tab>
        <v-tab @click="$vuetify.goTo('#experience', options)">참여이력</v-tab>
        <v-spacer></v-spacer>
      </v-tabs>

      <div id="my-info" class="target">
        <h3># 내정보</h3>
        <v-card-text>
          <v-text-field filled dense disabled :value="user.email" label="이메일" color="white"></v-text-field>
          <v-text-field filled dense v-model="user.name" label="이름"></v-text-field>
          <v-text-field dense v-model="user.gitaddr" label="GIT 주소" filled></v-text-field>
          <v-text-field dense v-model="user.profile" label="프로필사진 링크" filled></v-text-field>
          <v-btn type="button" @click="PreviewImg">이미지 미리보기</v-btn>
        </v-card-text>
      </div>
      <v-divider></v-divider>
      <div id="skills" class="target">
        <h3># 기술스택</h3>
        <div class="skills">
          <v-combobox
            v-model="select"
            :items="items"
            label="기술스택"
            multiple
            chips
        ></v-combobox>
        </div>
      </div>

      <v-divider></v-divider>
      <div id="experience" class="target">
        <div class="d-flex justify-space-between">
          <h3># 참여이력</h3>
          <div>
            <AddProjectHistory />
          </div>
        </div>
        <ProjectHistoryList />
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
      items: [
          'C',
          'C++',
          'JAVA',
          'Spring',
          'Django',
          'C#',
          'Go'
        ],
        select:[],
    };
  },
  created() {
    let token = window.$cookies.get("nnd"); //nnd가 key인 쿠키 가져옴
    if (token) {
      //토큰 존재하면
      this.user = token.object;
      this.profileURL = this.user.profile;
      this.select = JSON.parse(this.user.memberstack)
    }
  },
  methods: {
    PreviewImg() {
      this.profileURL = this.user.profile;
    },
    modify() {
      axios
        .post("http://localhost:8080/member/update", {
          email: this.user.email,
          name: this.user.name,
          profile: this.user.profile,
          gitaddr: this.user.gitaddr,
          memberstack: JSON.stringify(this.select),
        })
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
  margin: 10px 0;
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
