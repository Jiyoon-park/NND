<template>
  <div id="inspire">
    <v-navigation-drawer v-model="drawer" fixed-tabs app right>
      <div class="point-top"></div>
      <div class="point-bottom">
        <p>ⓒ 2020. 이나앨 All Rights Reserved.</p>
      </div>
      <v-list rounded>
        <v-list-item link class="d-flex flex-column pt-10">
          <v-avatar color="grey" size="80" class="mb-2">
            <span v-if="!profileURL" class="white--text headline"></span>
            <img v-else :src="profileURL" />
          </v-avatar>
          <div class="mt-2">
            <h3 class="text-center">{{username}}</h3>
            <v-btn x-small rounded color="#999" style="opacity:0.7;" @click="onLogout" dark>로그아웃</v-btn>
          </div>
        </v-list-item>
        <v-list-item-group color="#3949AB" class="text-center">
          <v-list-item link>
            <v-list-item-content @click="$router.push('/profile').catch(() => {})">
              <v-list-item-title>내 정보</v-list-item-title>
            </v-list-item-content>
          </v-list-item>

          <v-list-item link>
            <v-list-item-content @click="$router.push('/').catch(() => {})">
              <v-list-item-title>팀/팀원 구하기</v-list-item-title>
            </v-list-item-content>
          </v-list-item>

          <v-list-item link>
            <v-list-item-content>
              <v-list-item-title>외부 공모전</v-list-item-title>
            </v-list-item-content>
          </v-list-item>
        </v-list-item-group>
      </v-list>
    </v-navigation-drawer>

    <v-app-bar color="#FAFAFA" app :flat="true" :fixed="true">
      <div class="nav-icon" @click.stop="drawer = !drawer">
        <div></div>
      </div>

      <v-spacer></v-spacer>
      <!-- <img src="../../assets/images/logo_black_title.png" width="60px" alt /> -->
      <p class="title">neonaedong</p>
      <v-spacer></v-spacer>
      <Search />
      <v-badge :content="messages" :value="messages" color="green" overlap>
        <v-icon size="30" class="ml-3" @click="$router.push('/letter').catch(() => {})">mdi-email</v-icon>
      </v-badge>
    </v-app-bar>
  </div>
</template>

<script>
import Search from "./Search.vue";
import axios from "axios";
import { EventBus } from "../../main.js";

export default {
  name: "LayoutsDemosBaselineFlipped",
  components: {
    Search,
  },
  props: {
    source: String,
  },
  data: () => ({
    id: 0,
    username: "",
    profileURL: "",
    drawer: null,
    items: [
      { icon: "apps", title: "Home", to: "/" },
      { icon: "bubble_chart", title: "About", to: "/about" },
    ],
    messages: 0,
    letters: [],
  }),
  created() {
    let token = window.$cookies.get("nnd");
    if (token) {
      console.log(token.object.idx);
      this.user = token.object;
      this.username = token.object.name;
      this.profileURL = token.object.profile;
    }
    this.getLetters();

    EventBus.$on("letterRead", () => {
      this.messages--;
    });
  },
  methods: {
    onLogout: function () {
      this.$store.commit("logout");
      window.$cookies.remove("nnd");
      this.$router.push("/login");
    },
    getLetters() {
      axios
        .get(`http://localhost:8080/letter/list/receive/${this.user.idx}`)
        .then((res) => {
          console.log("#############");
          console.log(res);
          this.letters = res;
          this.messages = this.checkRead(this.letters.data);
          console.log(`message개수 :${this.messages}`);
        })
        .catch((err) => {
          console.log(err);
        });
    },
    checkRead: function (arr) {
      console.log("count 함수 실행!!");
      console.log("arr: " + arr);
      console.log("length: " + arr.length);
      var count = 0;
      for (let index = 0; index < arr.length; index++) {
        console.log(`arr :${arr[index]}`);
        if (arr[index].read == 0) {
          count++;
        }
      }
      return count;
    },
  },
};
</script>

<style scoped>
.title {
  margin-bottom: 0;
  font-family: "Rowdies", cursive;
  font-weight: 700;
  font-style: italic;
  color: #1565c0;
  margin-left: 20px;
}
.point-top {
  height: 90px;
  width: 100%;
  background-color: #3949ab;
  position: absolute;
  opacity: 0.6;
}
.point-bottom {
  height: 40px;
  width: 100%;
  background-color: #283593;
  position: absolute;
  bottom: 0;
  opacity: 0.7;
  font-size: 0.8rem;
  display: flex;
  justify-content: center;
  align-items: center;
}

.point-bottom p {
  margin-bottom: 0;
  color: #fff;
  opacity: 0.8;
}

.nav-icon {
  margin: 0;
  width: 25px;
}

.nav-icon:after,
.nav-icon:before,
.nav-icon div {
  background-color: #777;
  border-radius: 5px;
  content: "";
  display: block;
  height: 4px;
  margin: 4px 0;
  transition: all 0.2s ease-in-out;
}

.nav-icon:hover:before {
  transform: translateY(8px) rotate(135deg);
}

.nav-icon:hover:after {
  transform: translateY(-8px) rotate(-135deg);
}

.nav-icon:hover div {
  transform: scale(0);
}
</style>
