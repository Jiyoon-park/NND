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
        <v-btn small @click="$router.push('/profile-update')">
          <v-icon>mdi-pencil</v-icon>
        </v-btn>
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
        <v-row>
          <v-col cols="4" md="2">
            <p>이메일</p>
            <p>GIT 주소</p>
          </v-col>
          <v-col cols="8" md="10">
            <p>{{ user.email }}</p>
            <p>{{ user.gitaddr }}</p>
          </v-col>
        </v-row>
      </div>

      <hr />
      <div id="skills" class="target">
        <h3># 기술스택</h3>
        <div class="skills">
          <v-btn small color="primary" class="skill">java</v-btn>
          <v-btn small color="primary" class="skill">C</v-btn>
          <v-btn small color="primary" class="skill">C++</v-btn>
          <v-btn small color="primary" class="skill">Python</v-btn>
        </div>
      </div>

      <hr />
      <div id="experience" class="target">
        <h3># 참여이력</h3>
        <ProjectHistoryList />
      </div>
    </v-col>
  </v-row>
</template>

<script>
import * as easings from "vuetify/es5/services/goto/easing-patterns";

import NavBar from "../common/NavBar.vue";
import ProjectHistoryList from "../profile/ProjectHistoryList.vue";

export default {
  components: {
    NavBar,
    ProjectHistoryList,
  },
  data() {
    return {
      duration: 500,
      offset: 0,
      easing: "easeInOutCubic",
      easings: Object.keys(easings),
      user: "",
      profileURL: "",
    };
  },
  created() {
    let token = window.$cookies.get("nnd");
    console.log(token);
    console.log(token.object.idx);
    let id = token.object.idx;
    if (token) {
      this.$http
        .get(`http://localhost:8080/member/info/${id}`, {
          headers: {
            Authorization: "Bearer " + token, // the token is a variable which holds the token
          },
        })
        .then((resp) => {
          console.log(resp);
          this.user = resp.data;
          console.log(this.user.profile);
          this.profileURL = this.user.profile;
        });
    }
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

hr {
  margin: 30px 0;
}
</style>
