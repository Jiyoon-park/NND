<template>
  <div class="back">
    <v-container class="d-flex justify-center align-center" width="350px;">
      <div class="container" style="position:relative;">
        <img src="../assets/images/logo.png" width="90px" style="position:absolute; top:30px;" />
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
      <video class="embed-container" id="videoBG" poster="poster.JPG" autoplay muted loop>
        <source src="../assets/video/videoplayback2.mp4" type="video/mp4">
      </video>
  </div>
</template>

<script>
export default {
  name: "Welcome",
};
</script>

<style scoped>
.back {
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

#videoBG {
  max-width: 1903px;
  max-height: 100%;
  position: fixed;
  z-index: -1;
}
</style>
