<template>
  <div>
    <NavBar />
    <TeammemberFeed />
    <ActionButton />
  </div>
</template>

<script>
import NavBar from "../components/common/NavBar.vue";
import TeammemberFeed from "../components/common/TeammemberFeed.vue";
import ActionButton from "../components/common/ActionButton.vue";
import axios from "axios";

// @ is an alias to /src

export default {
  name: "Home",
  components: {
    NavBar,
    TeammemberFeed,
    ActionButton,
  },
  created() {
    let token = window.$cookies.get("kakao"); //nnd가 key인 쿠키 가져옴
    console.log(token);
    if (token) {
      // kakao 토큰이
      axios
        .get(`${process.env.VUE_APP_API_URL}/userinfo`, {
          headers: {
            Authorization: "Bearer " + token.data, // the token is a variable which holds the token
          },
        })
        .then(
          (response) => {
            console.log(response);
            window.$cookies.remove("kakao"); //kakao 쿠키 없애고 새로생성
            window.$cookies.set("nnd", response.data, "2d"); //로그인시 쿠키 저장
            //console.log(response.data.object.idx)
            location.reload();
          },
          () => {
            console.log("failed");
          }
        );
    }
    let ntoken = window.$cookies.get("nnd"); //nnd가 key인 쿠키 가져옴
    if (!ntoken) {
      alert("로그인해주세요!");
      this.$store.commit("logout");
      window.$cookies.remove("nnd");
      this.$router.push("/login");
    }
  },
};
</script>
