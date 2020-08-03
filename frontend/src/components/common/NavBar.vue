<template>
  <div id="inspire">
    <v-navigation-drawer v-model="drawer" fixed-tabs app right>
      <v-list dense>
        <v-list-item link>
          <div v-if="id != null">
            <v-icon class="mr-5">mdi-account-circle</v-icon>
            <v-list-item-content class="float-right">
              <v-list-item-title>홍길동</v-list-item-title>
            </v-list-item-content>
          </div>
          <v-list-item-content
            v-else
            @click="$router.push('/login').catch(() => {})"
          >
            <v-list-item-title>로그인</v-list-item-title>
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
            <v-list-item-title>즐겨찾기(팀/팀원)</v-list-item-title>
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
    drawer: null,
    id: null,
    items: [
      { icon: "apps", title: "Home", to: "/" },
      { icon: "bubble_chart", title: "About", to: "/about" },
    ],
  }),

  methods: {},
};
</script>

<style></style>
