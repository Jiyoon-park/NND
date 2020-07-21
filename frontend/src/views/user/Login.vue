<template>
  <div class="login-body">
    <img
      class="logo"
      src="../../assets/images/logo_without_title.png"
      alt="logo"
      width="20%"
      @click="goToIndex"
    />
    <form class="login-form">
      <div class="input-with-label">
        <input
          @keyup.enter="onLogin"
          v-model="email"
          class="login-input"
          type="text"
          placeholder="이메일을 입력해주세요."
          v-bind:class="{
            error: error.email,
            complete: !error.email && email.length !== 0,
          }"
        />
        <div class="error-text" v-if="error.email">{{ error.email }}</div>
      </div>

      <div class="input-with-label">
        <input
          class="login-input"
          v-model="password"
          type="password"
          placeholder="비밀번호를 입력해주세요."
          v-bind:class="{
            error: error.password,
            complete: !error.password && password.length !== 0,
          }"
          @keyup.enter="onLogin"
        />
        <div class="error-text" v-if="error.password">{{ error.password }}</div>
      </div>

      <button class="btn btn-info" @click="onLogin">로그인</button>

      <div class="login-checkbox">
        <input type="checkbox" class="checkbox" />로그인정보 유지
      </div>
    </form>

    <div class="sns-login">
      <p>SNS 계정으로 간편하게 로그인할 수 있어요!</p>
      <button class="btn btn-warning sns-bnt">kakao</button>
      <button class="btn btn-success sns-bnt">Naver</button>
      <button class="btn btn-primary sns-bnt">Google</button>
    </div>

    <div class="add-option">
      <router-link to="/signup" class="routers">가입하기</router-link>|
      <router-link to="/findemail" class="routers">이메일 찾기</router-link>|
      <router-link to="/findpw" class="routers">비밀번호 찾기</router-link>
    </div>
  </div>
</template>

<script>
// @ is an alias to /src
import "../../components/css/bootstrap.css";
// import UserApi from "../../api/UserApi";
// import axios from "axios";

export default {
  name: "Login",
  components: {},
  created() {
    this.component = this;

    this.passwordSchema
      .is()
      .min(8)
      .is()
      .max(100)
      .has()
      .digits()
      .has()
      .letters();
  },

  methods: {
    goToIndex() {
      this.$router.push("/");
    },
  },
  data: () => {
    return {
      email: "",
      password: "",
      // passwordSchema: new PV(),
      error: {
        email: false,
        passowrd: false,
      },
      isSubmit: false,
      component: this,
    };
  },
};
</script>
<style scoped>
* {
  box-sizing: border-box;
}

.login-body {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  /* min-height: 100vh; */
}

.login-form {
  width: 300px;
}

.login-form .login-input {
  width: 100%;
  padding: 5px 10px;
  display: block;
  border: 1.5px solid #f0f0f0;
  border-radius: 5px;
  margin: 10px 0;
}

.login-form button {
  width: 100%;
  padding: 10px 0;
}

.login-checkbox {
  display: flex;
  margin: 5px 0;
}

.sns-login {
  margin: 20px 0;
}

.sns-login .sns-bnt {
  margin: 0 4px;
  padding: 5px 15px;
}

.add-option .routers {
  margin: 0 5px;
}
</style>
