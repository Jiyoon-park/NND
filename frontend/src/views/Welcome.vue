<template>
  <div class="back">
    <v-container class="d-flex justify-center align-center">
      <div class="container">
        <img src="../assets/images/logo_white_title.png" width="70%" alt />
        <p style="color:white; font-size:27px;" class="my-0">너 내 동료가 되라</p>
        <div class="button">
          <div class="innerButton" @click="$router.push('/login')" color="yellow">로그인</div>
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
  margin-top: 50px;
  padding: 0;
  font-family: sans-serif;
}

.button .innerButton {
  position: absolute;
  transform: translate(-50%, -50%);
  padding: 10px 40px;
  text-transform: uppercase;
  text-decoration: none;
  letter-spacing: 2px;
  font-size: 1rem;
  color: #fff;
}

.button .innerButton:before {
  content: "";
  position: absolute;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background-color: #4a69bd;
  border-radius: 30px;
  z-index: -1;
  transition: transform 0.5s;
  transform-origin: right;
  transform: scale(0);
}

.button .innerButton:hover:before {
  transition: transform 0.5s;
  transform-origin: left;
  transform: scale(1);
}

.button .innerButton:after {
  content: "";
  position: absolute;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background-color: transparent;
  border: 2px solid #fff;
  opacity: 0.2;
  border-radius: 30px;
  box-sizing: border-box;
  z-index: -1;
  transition: transform 0.5s;
  transform-origin: left;
  transform: scale(1);
}

.button .innerButton:hover:after {
  transition: transform 0.5s;
  transform-origin: right;
  transform: scale(0);
}
</style>
