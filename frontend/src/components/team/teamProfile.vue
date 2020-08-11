<template>
  <v-row justify="center">
    <NavBar />
    <v-col cols="10" md="8" lg="6" class="mt-15">
      <div class="user-info">
        <v-avatar color="grey" size="90" class="mb-2">
          <span v-if="!profileURL" class="white--text headline"></span>
          <img v-else :src="profileURL" />
        </v-avatar>
        <h3>팀 형태 : 프로젝트</h3>
        <p># 팀 이름 : 앨리스</p>
        <v-btn small @click="$router.push('/profile-update')">
          <v-icon>mdi-pencil</v-icon>
        </v-btn>
      </div>

      <v-tabs class="tabs">
        <v-spacer></v-spacer>
        <v-tab @click="$vuetify.goTo('#team-post', options)">게시판</v-tab>
        <v-tab @click="$vuetify.goTo('#team-member', options)">팀원목록</v-tab>
        <v-tab @click="$vuetify.goTo('#team-member-graph', options)">그래프</v-tab>
        <v-spacer></v-spacer>
      </v-tabs>

      <div id="team-post" class="target">
        <h3># 게시판</h3>
        <team-post></team-post>
      </div>

      <hr />
      <div id="skills" class="target">
        <h3># 기술스택</h3>
        <div class="skills">
          <v-combobox v-model="select" chips multiple readonly></v-combobox>
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
import teamPost from "../team/teamPost.vue";

export default {
  components: {
    NavBar,
    ProjectHistoryList,
    teamPost,
  },
  data() {
    return {
      duration: 500,
      offset: 0,
      easing: "easeInOutCubic",
      easings: Object.keys(easings),
      user: "",
      profileURL: "",
      select: [],
      // labels: ['SU', 'MO', 'TU', 'WED', 'TH', 'FR', 'SA'],
      //   time: 0,
      //   forecast: [
      //     { day: 'Tuesday', icon: 'mdi-white-balance-sunny', temp: '24\xB0/12\xB0' },
      //     { day: 'Wednesday', icon: 'mdi-white-balance-sunny', temp: '22\xB0/14\xB0' },
      //     { day: 'Thursday', icon: 'mdi-cloud', temp: '25\xB0/15\xB0' },
      //   ],
      teamposts: [],
    };
  },
  created() {
    let token = window.$cookies.get("nnd");
    console.log(token);
    let id = token.object.idx; //넘겨 받아야함
    this.$http
      .get(`http://localhost:8080/member/info/${id}`, {
        headers: {
          Authorization: "Bearer " + token.data, // the token is a variable which holds the token
        },
      })
      .then((resp) => {
        console.log(resp);
        this.user = resp.data;
        this.profileURL = this.user.profile;
        this.select = JSON.parse(this.user.memberstack);
      });
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











<style></style>
