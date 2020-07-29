<template>
  <v-row justify="center">
    <NavBar />

    <v-col cols="10" md="8" lg="6" class="mt-15">
      <div class="user-info">
        <v-avatar color="grey" size="90" class="mb-2">
          <span class="white--text headline">GD</span>
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
            <p>https://github.com/project</p>
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
          </v-card-actions>
        </v-card>
        <v-card class="mx-auto my-3" max-width="344" shaped>
          <v-list-item three-line>
            <v-list-item-content>
              <div class="overline mb-4">프로젝트</div>
              <v-list-item-title class="headline mb-1">Headline 5</v-list-item-title>
              <v-list-item-subtitle>
                Greyhound divisely hello coldly
                fonwderfully
              </v-list-item-subtitle>
            </v-list-item-content>
          </v-list-item>
          <v-card-actions>
            <v-btn text>Button</v-btn>
          </v-card-actions>
        </v-card>
        <v-card class="mx-auto my-3" max-width="344" shaped>
          <v-list-item three-line>
            <v-list-item-content>
              <div class="overline mb-4">공모전</div>
              <v-list-item-title class="headline mb-1">Headline 5</v-list-item-title>
              <v-list-item-subtitle>
                Greyhound divisely hello coldly
                fonwderfully
              </v-list-item-subtitle>
            </v-list-item-content>
          </v-list-item>
          <v-card-actions>
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
import axios from "axios";

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
      user: "",
    };
  },
  created() {
    axios.get("http://localhost:8080/userinfo").then((res) => {
      this.user = res.data;
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
