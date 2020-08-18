<template>
  <v-row justify="center" class="mt-13" style="background-color:#eceff1;">
    <NavBar />
    <v-col
      cols="10"
      md="8"
      lg="6"
      class="my-6"
      style="background-color:#fafafa; border-radius:10px;"
    >
      <div class="user-info" style="position:relative;">
        <v-avatar color="grey" size="90" class="mb-3">
          <i v-if="!profileURL" class="fas fa-user fa-lg"></i>
          <img v-else :src="profileURL" />
        </v-avatar>
        <h3>{{ user.name }}</h3>
      </div>

      <v-tabs fixed-tabs background-color="#fafafa" color="#0277BD" class="tabs">
        <v-tab @click="$vuetify.goTo('#my-info', options)">내정보</v-tab>
        <v-tab @click="$vuetify.goTo('#skills', options)">기술스택</v-tab>
        <v-tab @click="$vuetify.goTo('#experience', options)">참여이력</v-tab>
      </v-tabs>

      <div id="my-info" class="target">
        <h3 class="mb-3">🌞 내정보 🌞</h3>
        <v-row style="background-color: #fafafa; border-radius:10px;">
          <v-col cols="3" md="2" class="px-4 py-4">
            <p>이메일</p>
            <p class="mb-0">GIT</p>
          </v-col>
          <v-col cols="9" md="10" class="px-4 py-4">
            <p>{{ user.email }}</p>
            <p class="mb-0">{{ user.gitaddr }}</p>
          </v-col>
        </v-row>
      </div>

      <div id="skills" class="target">
        <h3 class="mb-3">✨ 기술스택 ✨</h3>
        <v-row style="background-color: #fafafa; border-radius:10px;">
          <v-col cols="12" sm="12" class="px-4 py-4">
            <v-chip-group column>
              <v-chip v-for="tag in select" :key="tag" color="indigo" dark>
                {{
                tag
                }}
              </v-chip>
            </v-chip-group>
          </v-col>
        </v-row>
      </div>

      <div id="experience" class="target">
        <h3 class="mb-3">🏅 참여이력 🏅</h3>
        <v-row style="background-color: #fafafa; border-radius:10px;">
          <ProjectHistoryList :isEditPage="isEditPage" />
        </v-row>
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
      select: [],
      isEditPage: false,
    };
  },
  created() {
    let id = this.$store.state.profileidx;
    let token = window.$cookies.get("nnd");
    this.$http
      .get(`${process.env.VUE_APP_API_URL}/member/info/${id}`, {
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
