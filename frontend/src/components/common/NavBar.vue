<template>
  <div id="inspire">
    <v-navigation-drawer v-model="drawer" fixed-tabs app right>
      <v-list dense>
        <v-list-item link>
          <v-avatar color="grey" size="80" class="mb-2">
            <span v-if="!profileURL" class="white--text headline"></span>
            <img v-else :src="profileURL" />
          </v-avatar>

          <v-list-item-content class="float-right">
            <v-list-item-title class="user-interface">{{
              name
            }}</v-list-item-title>
            <v-list-item-title class="user-interface"
              >로그아웃</v-list-item-title
            >
          </v-list-item-content>
        </v-list-item>
        <v-list-item link>
          <v-list-item-content
            @click="$router.push('/profile').catch(() => {})"
          >
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
      </v-list>
    </v-navigation-drawer>

    <!-- 자몽 -->
    <!-- <v-app-bar color="#eb4d4b" app :flat="true" :fixed="true"> -->
    <!-- 남색 -->
    <!-- <v-app-bar color="#30336b" app :flat="true" :fixed="true"> -->
    <v-app-bar color="#FFF" app :flat="true" :fixed="true">
      <img
        src="../../assets/images/sm_logo.png"
        width="30px"
        @click="$router.push('/').catch(() => {})"
      />
      <v-spacer></v-spacer>

      <Search />

      <v-app-bar-nav-icon
        @click.stop="drawer = !drawer"
        color="#999"
      ></v-app-bar-nav-icon>
    </v-app-bar>
  </div>
</template>

<script>
import Search from "./Search.vue";
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
    name: "",
    profileURL: "",
    drawer: null,
    items: [
      { icon: "apps", title: "Home", to: "/" },
      { icon: "bubble_chart", title: "About", to: "/about" },
    ],
  }),
  created() {
    let token = window.$cookies.get("nnd");
    if (token) {
      console.log(token.object.idx);
      this.name = token.object.name;
      // this.profileURL = token.object.profile;
    }
  },
  methods: {},
};
</script>

<style scoped>
.user-interface {
  display: flex;
  justify-content: center;
  margin-top: 10px;
}
.mb-2 {
  margin-top: 10px;
}
</style>
