<template>
  <v-row justify="center">
    <NavBar />
    <v-col cols="10" md="8" lg="6" class="mt-15">
      <div class="user-info">
        <v-avatar color="grey" size="90" class="mb-2">
          <span v-if="!profileURL" class="white--text headline"></span>
          <img v-else :src="profileURL" />
        </v-avatar>
        <h3>팀 형태 : {{ teaminfo.category }}</h3>
        <p># 팀 이름 : {{ teaminfo.teamName }}</p>
        <!-- <v-btn small @click="$router.push('/profile-update')">
          <v-icon>mdi-pencil</v-icon>
        </v-btn>-->
      </div>

      <v-tabs class="tabs">
        <v-spacer></v-spacer>
        <v-tab @click="$vuetify.goTo('#team-post', options)">게시판</v-tab>
        <v-tab @click="$vuetify.goTo('#team-member', options)">팀원목록</v-tab>
        <v-tab @click="$vuetify.goTo('#team-member-graph', options)"
          >그래프</v-tab
        ><v-tab @click="$vuetify.goTo('#team-member-diary', options)"
          >다이어리</v-tab
        >
        <v-spacer></v-spacer>
      </v-tabs>

      <div id="team-post" class="target">
        <h3># 게시판</h3>
        <TeamPost :teaminfo="teaminfo" :userinfo="user" />
      </div>

      <hr />
      <div id="team-member" class="target">
        <h3># 팀원목록</h3>
        <!-- <div class="skills">
          <v-combobox v-model="select" chips multiple readonly></v-combobox>
        </div>-->
        <team-table></team-table>
      </div>
      <hr />
      <div id="team-member-graph" class="target">
        <h3># 그래프</h3>
        <MemberChart />
      </div>
      <hr />
      <div id="team-member-diary" class="target">
        <h3># 다이어리</h3>
        <TeamDiary :teaminfo="teaminfo" :userinfo="user" />
      </div>
    </v-col>
  </v-row>
</template>

<script>
import * as easings from "vuetify/es5/services/goto/easing-patterns";

import NavBar from "../common/NavBar.vue";
import TeamPost from "../team/teamPost.vue";
import TeamTable from "../team/teamTable.vue";
import MemberChart from "../team/memberChart.vue";
import TeamDiary from "../team/teamDiary";

import axios from "axios";

export default {
  components: {
    NavBar,
    TeamPost,
    TeamTable,
    MemberChart,
    TeamDiary,
  },
  data() {
    return {
      duration: 500,
      offset: 0,
      easing: "easeInOutCubic",
      easings: Object.keys(easings),
      user: {},
      profileURL: "",
      teaminfo: {},
      teamboardno: "",
    };
  },

  created() {
    this.teamboardno = this.$store.state.teamNo;
    let token = window.$cookies.get("nnd");
    let id = token.object.idx; //넘겨 받아야함
    this.$http
      .get(`${process.env.VUE_APP_API_URL}/member/info/${id}`, {
        headers: {
          Authorization: "Bearer " + token.data, // the token is a variable which holds the token
        },
      })
      .then((resp) => {
        this.user = resp.data;
        this.profileURL = this.user.profile;
      });

    axios
      .get(`${process.env.VUE_APP_API_URL}/teamboard/list/${this.teamboardno}`, {
        headers: {
          Authorization: "Bearer " + token.data, // the token is a variable which holds the token
        },
      })
      .then((res) => {
        this.teaminfo = res.data;
        // console.log("팀장idx찍어보자");
        // console.log(this.teaminfo.idx);
        this.$store.state.teamMaster = this.teaminfo.idx;
      })
      .catch((err) => {
        console.log(err);
      });
    axios
      .get(`${process.env.VUE_APP_API_URL}/teammenu/member/` + this.teamboardno)
      .then((data) => {
        console.log("데이터찍어보자");
        console.log(data.data);
        this.$store.state.teammembers = data.data;
        this.$store.commit("saveMember");
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
