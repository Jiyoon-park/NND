<template>
  <v-row justify="center">
    <NavBar />
    <v-col cols="10" md="8" lg="6" class="mt-13">
      <div class="user-info">
        <v-avatar color="grey" size="90" class="mb-3">
          <span v-if="!profileURL" class="white--text headline"></span>
          <img v-else :src="profileURL" />
        </v-avatar>
        <small>{{ teaminfo.category }}</small>
        <h4>{{ teaminfo.teamName }}</h4>
        <!-- <v-btn small @click="$router.push('/profile-update')">
          <v-icon>mdi-pencil</v-icon>
        </v-btn>-->
      </div>

      <v-tabs show-arrows fixed-tabs color="indigo lighten-1" class="tabs">
        <v-tab class="px-0" @click="$vuetify.goTo('#team-post', options)">게시판</v-tab>
        <v-tab class="px-0" @click="$vuetify.goTo('#team-member', options)">팀원목록</v-tab>
        <v-tab class="px-0" @click="$vuetify.goTo('#team-member-graph', options)">그래프</v-tab>
        <v-tab class="px-0" @click="$vuetify.goTo('#team-member-diary', options)">다이어리</v-tab>
      </v-tabs>

      <div id="team-post" class="target">
        <h3 class="mb-3">게시판</h3>
        <v-row style="background-color: #fafafa; border-radius:10px;">
          <v-col cols="12" sm="12" class="px-4 py-4">
            <TeamPost :teaminfo="teaminfo" :userinfo="user" />
          </v-col>
        </v-row>
      </div>

      <v-row class="py-0 px-0">
        <v-col cols="12" sm="12" lg="6">
          <div id="team-member" class="target">
            <h3 class="mb-3">팀원목록</h3>
            <v-row>
              <v-col cols="12" sm="12">
                <TeamTable />
              </v-col>
            </v-row>
          </div>
        </v-col>
        <v-col cols="12" sm="12" lg="6">
          <div id="team-member-graph" class="target">
            <h3 class="mb-3">그래프</h3>
            <v-row class="py-0 px-0">
              <v-col cols="12" sm="12">
                <MemberChart />
              </v-col>
            </v-row>
          </div>
        </v-col>
      </v-row>

      <div id="team-member-diary" class="target">
        <h3 class="mb-3">다이어리</h3>
        <v-row style="background-color: #fafafa; border-radius:10px;">
          <v-col cols="12" sm="12" class="px-4 py-4">
            <TeamDiary :teaminfo="teaminfo" :userinfo="user" />
          </v-col>
        </v-row>
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
      .get(
        `${process.env.VUE_APP_API_URL}/teamboard/list/${this.teamboardno}`,
        {
          headers: {
            Authorization: "Bearer " + token.data, // the token is a variable which holds the token
          },
        }
      )
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
      .get(`http://localhost:8080/teammenu/member/` + this.teamboardno)
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
  margin: 30px 0;
}

#my-info p {
  margin-bottom: 5px;
}
</style>