<template>
  <div class="center">
    <h1>이것저것 실험해보는 용도입니당</h1>
    <div class="card">
      <div class="additional">
        <div class="user-card">
          <div class="level center">Level 13</div>
          <div class="points center">5,312 Points</div>

          <title id="title">Teacher</title>
        </div>
        <div class="more-info">
          <h1>Jane Doe</h1>
          <div class="coords">
            <span>Group Name</span>
            <span>Joined January 2019</span>
          </div>
          <div class="coords">
            <span>Position/Role</span>
            <span>City, Country</span>
          </div>
          <div class="stats">
            <div>
              <div class="title">Awards</div>
              <i class="fa fa-trophy"></i>
              <div class="value">2</div>
            </div>
            <div>
              <div class="title">Matches</div>
              <i class="fa fa-gamepad"></i>
              <div class="value">27</div>
            </div>
            <div>
              <div class="title">Pals</div>
              <i class="fa fa-group"></i>
              <div class="value">123</div>
            </div>
            <div>
              <div class="title">Coffee</div>
              <i class="fa fa-coffee"></i>
              <div class="value infinity">∞</div>
            </div>
          </div>
        </div>
      </div>
      <div class="general">
        <h1>너내동</h1>
        <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Fusce a volutpat mauris, at molestie lacus. Nam vestibulum sodales odio ut pulvinar.</p>
        <span class="more">Mouse over the card for more info</span>
      </div>
    </div>
  </div>

  <!-- 1 -->
  <!-- <div class="back">
    <v-container class="d-flex justify-center align-center">
      <div class="container">
        <img src="../assets/images/logo_white_title.png" width="70%" alt />
        <p style="color:white; font-size:27px;" class="my-0">너 내 동료가 되라</p>
        <div class="button">
          <div class="innerButton" @click="$router.push('/login')" color="yellow">로그인</div>
        </div>
      </div>
    </v-container>
  </div>-->

  <!-- 2 -->
  <!-- <div class="back">
    <div class="infocardContainer">
      <div id="main">
        <img src="../assets/images/logo_with_background.jpg" />
      </div>
      <div id="textbois">
        <div class="content">
          <h2>너내동</h2>
          <h4>너 내 동료가 되라</h4>
        </div>
      </div>
    </div>
  </div>-->
</template>

<script>
export default {
  name: "Welcome",
};
</script>

<style scoped>
html,
body {
  background: #fceeb5;
}

.center {
  position: absolute;
  top: 50%;
  left: 50%;
  -webkit-transform: translate(-50%, -50%);
}

.card {
  width: 500px;
  height: 250px;
  background-color: #fff;
  background: linear-gradient(#f8f8f8, #fff);
  box-shadow: 0 8px 16px -8px rgba(0, 0, 0, 0.4);
  border-radius: 6px;
  overflow: hidden;
  position: relative;
  margin: 1.5rem;
}

.card h1 {
  text-align: center;
}

.card .additional {
  position: absolute;
  width: 150px;
  height: 100%;
  background: linear-gradient(#de685e, #ee786e);
  transition: width 0.4s;
  overflow: hidden;
  z-index: 2;
}

.card.green .additional {
  background: linear-gradient(#92bca6, #a2ccb6);
}

.card:hover .additional {
  width: 100%;
  border-radius: 0 5px 5px 0;
}

.card .additional .user-card {
  width: 150px;
  height: 100%;
  position: relative;
  float: left;
}

.card .additional .user-card::after {
  content: "";
  display: block;
  position: absolute;
  top: 10%;
  right: -2px;
  height: 80%;
  border-left: 2px solid rgba(0, 0, 0, 0.025);
}

.card .additional .user-card .level,
.card .additional .user-card .points {
  top: 15%;
  color: #fff;
  text-transform: uppercase;
  font-size: 0.75em;
  font-weight: bold;
  background: rgba(0, 0, 0, 0.15);
  padding: 0.125rem 0.75rem;
  border-radius: 100px;
  white-space: nowrap;
}

.card .additional .user-card .points {
  top: 85%;
}

.card .additional .user-card svg {
  top: 50%;
}

.card .additional .more-info {
  width: 300px;
  float: left;
  position: absolute;
  left: 150px;
  height: 100%;
}

.card .additional .more-info h1 {
  color: #fff;
  margin-bottom: 0;
}

.card.green .additional .more-info h1 {
  color: #224c36;
}

.card .additional .coords {
  margin: 0 1rem;
  color: #fff;
  font-size: 1rem;
}

.card.green .additional .coords {
  color: #325c46;
}

.card .additional .coords span + span {
  float: right;
}

.card .additional .stats {
  font-size: 2rem;
  display: flex;
  position: absolute;
  bottom: 1rem;
  left: 1rem;
  right: 1rem;
  top: auto;
  color: #fff;
}

.card.green .additional .stats {
  color: #325c46;
}

.card .additional .stats > div {
  flex: 1;
  text-align: center;
}

.card .additional .stats i {
  display: block;
}

.card .additional .stats div.title {
  font-size: 0.75rem;
  font-weight: bold;
  text-transform: uppercase;
}

.card .additional .stats div.value {
  font-size: 1.5rem;
  font-weight: bold;
  line-height: 1.5rem;
}

.card .additional .stats div.value.infinity {
  font-size: 2.5rem;
}

.card .general {
  width: 300px;
  height: 100%;
  position: absolute;
  top: 0;
  right: 0;
  z-index: 1;
  box-sizing: border-box;
  padding: 1rem;
  padding-top: 0;
}

.card .general .more {
  position: absolute;
  bottom: 1rem;
  right: 1rem;
  font-size: 0.9em;
}
/* 2 */
/* h2 {
  font-weight: 600;
  font-style: italic;
  font-family: "Fira Sans Condensed", sans-serif;
}

.infocardContainer {
  display: flex;
  height: 200px;
  width: 200px;
  border-radius: 100px;
  background: rgb(9, 95, 148);
  background: linear-gradient(
    121deg,
    rgba(69, 50, 138, 0.774) 13%,
    rgba(9, 95, 148, 1) 100%
  );
  transition: all 500ms ease-in;
  transition-delay: 1s;
  margin: auto;
  margin-top: 100px;
  --margin-top: 100px;
}
.infocardContainer:hover {
  width: 400px;
  border-radius: 100px 10px 100px 100px;
  transition: all 1s ease-out;
}

.infocardContainer div {
  color: white;
  flex-shrink: 1;
  width: 100%;
  --background-color: green;
}
.infocardContainer div * {
  display: flex;
  --flex: inherit;
  overflow: hidden;
  text-overflow: hidden;
  --background-color: yellow;
  color: white;
  white-space: nowrap;
  width: 0;
  height: auto;
  transition: all 450ms ease-in;
  transition-delay: 1s;
}
.infocardContainer:hover div * {
  --background-color: purple;
  display: flex;
  visibility: visible;
  transition: all 1s ease-out;
  transition-delay: 500ms;
  width: 100%;
  height: auto;
}

.infocardContainer #main,
.infocardContainer #main img {
  --background-color: red;
  height: 200px;
  width: 200px;
  padding-right: 10px;
  border-radius: 100%;
  flex-shrink: 0;
  object-fit: cover;
}
.infocardContainer #main img {
  height: 190px;
  width: 190px;
  transition: none;
  display: float;
  position: relative;
  margin: 0 0 0 0;
  padding: 0 0 0 0;
}
.infocardContainer #textbois {
  position: relative;
  display: flex;
  justify-content: center;
  align-items: center;
}

.infocardContainer #textbois .content {
  display: flex;
  flex-direction: column;
}
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
} */

/* 1 */
/* .container {
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
} */
</style>
