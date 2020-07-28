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
          <v-list-item-content v-else @click="$router.push('/login')">
            <v-list-item-title>로그인</v-list-item-title>
          </v-list-item-content>
        </v-list-item>
        <v-list-item link>
          <v-list-item-content>
            <v-list-item-title>내 정보</v-list-item-title>
          </v-list-item-content>
        </v-list-item>

        <v-list-item link>
          <v-list-item-content @click="$router.push('/')">
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

    <v-app-bar app color="white" dark :flat="true" :fixed="true">
      <v-img
        src="../../assets/images/logo_without_title.png"
        max-width="110px"
      />
      <v-spacer></v-spacer>
      <v-spacer></v-spacer>
      <!-- <v-app-bar-nav-icon @click.stop="drawer = !drawer"></v-app-bar-nav-icon> -->
      <v-btn @click.stop="drawer = !drawer">
        <v-icon>fas fa-list</v-icon>
      </v-btn>
    </v-app-bar>
  </div>
</template>

<script>
export default {
  name: "LayoutsDemosBaselineFlipped",
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
  components: {},
  methods: {},
};
</script>

<style></style>
