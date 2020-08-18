<template>
  <div class="back">
    <v-container class="d-flex justify-center align-center" width="350px;">
      <img
        src="../assets/images/logo.png"
        width="45px"
        style="position:absolute; top: 5px; left:4px;"
      />
      <div class="container">
        <div class="d-flex flex-column" style="font-family: 'Do Hyeon', sans-serif; font-size:40px">
          <div style="color:#0277BD;">너</div>
          <div style="color:#0277BD;">내</div>
          <div>
            <span style="color:#0277BD;">동</span>
            <span style="color:#fff;">료가 되라.</span>
          </div>
        </div>
        <p style="color:white;" class="mb-0">당신이 간절히 찾던 바로 그 팀원.</p>
        <p style="color:white;" class="mb-0">바로 여기, 너내동에서</p>
        <div class="button">
          <v-btn large class="button" color="#0277BD" @click="$router.push('/login')">로그인</v-btn>
          <!-- <div class="innerButton" @click="$router.push('/login')" color="yellow">로그인</div> -->
        </div>
      </div>
    </v-container>
  </div>
</template>

<script>
export default {
  name: "Welcome",
};
</script>

<style scoped>
.back {
  background: url(../assets/images/team2.jpg);
  background-size: cover;
  background-repeat: no-repeat;
  background-position: center;
  opacity: 0.8;
  min-height: 100vh;
  display: flex;
  align-items: center;
  justify-content: center;
}

.container {
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
}

.button {
  margin-top: 10px;
  cursor: pointer;
  color: #fff;
  display: block;
  font-size: 16px;
  width: 230px;
  padding: px;
}
</style>
