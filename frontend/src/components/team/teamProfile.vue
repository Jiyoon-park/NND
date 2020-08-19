<template>
  <v-container>
    <NavBar />
    <v-row justify="center" class="mt-11" style="background-color:#eceff1;">
      <v-col
        cols="10"
        md="8"
        lg="6"
        class="my-6"
        style="background-color:#fafafa; border-radius:10px;"
      >
        <div class="user-info">
          <!-- <v-avatar color="#eeeeee" size="90" class="mb-3">
          <i v-if="!teaminfo.imageurl">{{ teaminfo.category }}</i>
          <img v-else :src="teaminfo.imgaeurl" />
          </v-avatar>-->

          <v-btn rounded small dark color="#0277BD" class="mb-2">{{ teaminfo.category }}</v-btn>

          <h2>{{ teaminfo.teamName }}</h2>
        </div>

        <v-tabs show-arrows fixed-tabs background-color="#fafafa" color="#0277BD" class="tabs">
          <v-tab class="px-0" @click="$vuetify.goTo('#team-post', options)">게시판</v-tab>
          <v-tab class="px-0" @click="$vuetify.goTo('#team-member-diary', options)">일정관리</v-tab>
          <v-tab class="px-0" @click="$vuetify.goTo('#team-member', options)">팀원목록</v-tab>
          <v-tab class="px-0" @click="$vuetify.goTo('#team-member-graph', options)">그래프</v-tab>
        </v-tabs>

        <div id="team-post" class="target">
          <h3 class="mb-3">
            <v-tooltip right>
              <template v-slot:activator="{ on, attrs }">
                <span v-bind="attrs" v-on="on">📢 게시판 📢</span>
              </template>
              <span>프로젝트 관련 정보들을 공유해주세요.</span>
            </v-tooltip>
          </h3>
          <v-row style="background-color: #fafafa; border-radius:10px;">
            <v-col cols="12" sm="12" class="px-4 py-4">
              <team-board :teaminfo="teaminfo" :userinfo="user"></team-board>
            </v-col>
          </v-row>
        </div>

        <div id="team-member-diary" class="target">
          <h3 class="mb-3">
            <v-tooltip right>
              <template v-slot:activator="{ on, attrs }">
                <span v-bind="attrs" v-on="on">✍ 일정관리 ✍</span>
              </template>
              <span>팀원과 공유할 일정이 있나요? 해당 일을 클릭해 드래그로 일정을 기록해보세요.</span>
            </v-tooltip>
          </h3>

          <v-row style="background-color: #fafafa; border-radius:10px;">
            <v-col cols="12" sm="12" class="px-4 py-4">
              <TeamDiary :teaminfo="teaminfo" :userinfo="user" />
            </v-col>
          </v-row>
        </div>

        <v-row class="py-0 px-0">
          <v-col cols="12" sm="12" md="6">
            <div id="team-member" class="target">
              <h3 class="mb-3">
                <v-tooltip right>
                  <template v-slot:activator="{ on, attrs }">
                    <span v-bind="attrs" v-on="on">🏃 팀원목록 🏃</span>
                  </template>
                  <span>프로젝트가 마무리되었나요? 팀원에 대한 점수를 남겨주세요.</span>
                </v-tooltip>
              </h3>
              <v-row>
                <v-col cols="12" sm="12">
                  <TeamTable :teamdata="this.$store.state.teammembers" />
                </v-col>
              </v-row>
            </div>
          </v-col>
          <v-col cols="12" sm="12" md="6">
            <div id="team-member-graph" class="target">
              <h3 class="mb-3">
                <v-tooltip right>
                  <template v-slot:activator="{ on, attrs }">
                    <span v-bind="attrs" v-on="on">📈 그래프 📉</span>
                  </template>
                  <span>전체 팀원의 평가 결과를 확인할 수 있습니다.</span>
                </v-tooltip>
              </h3>
              <v-row class="py-0 px-0">
                <v-col cols="12" sm="12">
                  <MemberChart :axiostype="axiostype" />
                </v-col>
              </v-row>
            </div>
          </v-col>
        </v-row>
      </v-col>
    </v-row>
  </v-container>
</template>

<script>
import * as easings from "vuetify/es5/services/goto/easing-patterns";

import NavBar from "../common/NavBar.vue";
//import TeamPost from "../team/teamPost.vue";
import TeamTable from "../team/teamTable.vue";
import MemberChart from "../team/memberChart.vue";
import TeamDiary from "../team/teamDiary";
import TeamBoard from "../team/teamBoard";

import axios from "axios";

export default {
  components: {
    NavBar,
    //TeamPost,
    TeamTable,
    MemberChart,
    TeamDiary,
    TeamBoard,
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
      axiostype: "list",
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
      .get(
        `${process.env.VUE_APP_API_URL}/teammenu/member/${this.teamboardno}/${id}`,
        {
          headers: {
            Authorization: "Bearer " + token.data, // the token is a variable which holds the token
          },
        }
      )
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
  margin-top: 30px;
  margin-bottom: 30px;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
}

.tabs {
  margin-top: 20px;
  margin-bottom: 40px;
}

.target {
  margin: 30px 0;
}

#my-info p {
  margin-bottom: 5px;
}
</style>