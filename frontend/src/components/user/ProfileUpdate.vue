<template>
  <v-row justify="center">
    <NavBar />
    <v-col cols="10" md="8" lg="6" class="mt-15">
      <div class="user-info">
        <v-avatar color="grey" size="90" class="mb-2">
          <span class="white--text headline">GD</span>
        </v-avatar>
        <h3>SSAFY3기 홍길동</h3>
        <p># 참여중인 팀 : 앨리스</p>
        <v-btn small @click="$router.push('/profile')">save</v-btn>
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
          <v-text-field filled dense disabled value="abcd1123@naver.com" label="이메일" color="white"></v-text-field>
          <v-text-field filled dense v-model="name" value="홍길동" label="이름"></v-text-field>
          <v-text-field dense v-model="password" label="비밀번호" filled></v-text-field>
          <v-text-field dense v-model="password2" label="비밀번호 확인" filled></v-text-field>
          <v-text-field dense v-model="gitaddress" label="GIT 주소" filled></v-text-field>
        </v-card-text>
      </div>
      <v-divider></v-divider>
      <div id="skills" class="target">
        <h3># 기술스택</h3>
        <div class="skills">
          <v-chip-group column multiple>
            <v-chip filter outlined>Java</v-chip>
            <v-chip filter outlined>C</v-chip>
            <v-chip filter outlined>C++</v-chip>
            <v-chip filter outlined>Python</v-chip>
            <v-chip filter outlined>Spring</v-chip>
            <v-chip filter outlined>Django</v-chip>
          </v-chip-group>
        </div>
      </div>

      <v-divider></v-divider>
      <div id="experience" class="target">
        <div class="d-flex justify-space-between">
          <h3># 참여이력</h3>
          <div>
            <v-btn color="green" fab small class="mr-1">
              <v-icon>mdi-plus</v-icon>
            </v-btn>
            <v-btn color="red" fab small>
              <v-icon>mdi-minus</v-icon>
            </v-btn>
          </div>
        </div>
        <v-card class="mx-auto my-3" max-width="344" shaped>
          <v-list-item three-line>
            <v-list-item-content>
              <div class="overline mb-4">프로젝트</div>
              <v-list-item-title class="headline mb-1">너내동</v-list-item-title>
              <v-list-item-subtitle>SSAFY인들을 위한 팀빌딩 SNS</v-list-item-subtitle>
            </v-list-item-content>
          </v-list-item>
          <v-card-actions>
            <v-btn text>Button</v-btn>
            <v-btn text>Button</v-btn>
          </v-card-actions>
        </v-card>
      </div>
    </v-col>
  </v-row>
</template>

<script>
import NavBar from "../common/NavBar.vue";
import * as easings from "vuetify/es5/services/goto/easing-patterns";
// import axios from "axios";

export default {
  components: {
    NavBar,
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
      name: "",
      password: "",
      password2: "",
      gitaddress: "",
    };
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
