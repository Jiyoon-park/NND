<template>
  <div id="app">
    <!-- <NavBar /> -->
    <v-app id="inspire">
      <v-content class="back">
        <v-container fluid fill-height>
          <v-layout align-center justify-center>
            <v-img src="../assets/images/logo_with_title.png" max-width="500px" position="top" />
            <v-btn class="mr-4" @click="$router.push('/login')" color="yellow">로그인하러가기</v-btn>
          </v-layout>
        </v-container>
        <v-container fluid fill-height class="back2">
          <v-layout align-start justify-center>
            <v-img src="../assets/images/logo_without_title.png" max-width="600px" position="top" />
            <div>
              <a>너, 내 동료가 되라(너내동)은</a>
            </div>
          </v-layout>
        </v-container>
      </v-content>
    </v-app>
  </div>
</template>

<script>
// import NavBar from "../components/common/NavBar.vue";

export default {
  // name: "Welcome",
  // components: {
  //   NavBar,
  // },
};
</script>

<style scoped>
.back {
  background: url(../assets/images/Sea.png);
  background-size: cover;
  background-repeat: no-repeat;
}
#inspire {
  background: none;
}
.back2 {
  background: url(../assets/images/Sea.png);
  background-size: cover;
  background-repeat: no-repeat;
  background-position: center;
}
</style>