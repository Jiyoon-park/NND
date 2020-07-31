<template>
  <v-row justify="center">
    <NavBar />
    <v-col cols="10" md="8" lg="6" class="mt-15">
      <h2>로그인</h2>
      <v-form class="form" ref="form" v-model="valid" lazy-validation>
        <input type="hidden" th:name="${_csrf.parameterName}" th:value="${_csrf.token}" />

        <v-text-field
          v-model="email"
          :rules="emailRules"
          label="이메일"
          required
          outlined
          dense
        ></v-text-field>
        <v-text-field
          v-model="password"
          :rules="[rules.required, rules.min]"
          label="비밀번호"
          @click:append="show = !show"
          :append-icon="show ? 'mdi-eye' : 'mdi-eye-off'"
          :type="show ? 'text' : 'password'"
          outlined
          dense
        ></v-text-field>
        <v-btn class="button" :disabled="!valid" color="teal" @click="login"
          >로그인</v-btn
        >
        <v-checkbox
          v-model="checkbox"
          color="success"
          label="로그인 정보 기억"
        ></v-checkbox>
      </v-form>
      <div class="login-body text-center">
        <div class="sns-login">
          <p>SNS로 간편하게 로그인할 수 있어요!</p>
          <v-btn color="yellow darken-1" class="sns-btn" dark>
            <a
              href="https://kauth.kakao.com/oauth/authorize?client_id=136ae30351513efbd13773e917430828&redirect_uri=http://localhost:8080/login&response_type=code"
              >Kakao</a
            >
          </v-btn>
          <v-btn color="green accent-4" class="sns-btn" dark>Naver</v-btn>
          <v-btn color="light-blue darken-2" class="sns-btn" dark>Google</v-btn>
        </div>
        <div class="add-option">
          <router-link to="/signup" class="routers">회원가입</router-link>|
          <router-link to="/findemail" class="routers">이메일 찾기</router-link
          >|
          <router-link to="/findpw" class="routers">비밀번호 찾기</router-link>
        </div>
      </div>
    </v-col>
  </v-row>
</template>
<script>
import NavBar from "../common/NavBar.vue";
import axios from "axios";

export default {
  name: "Login",
  components: {
    NavBar,
  },
  data: () => ({
    loginData: {
      email: null,
      password: null,
    },
    valid: true,
    emailRules: [
      (v) => !!v || "이메일을 입력해주세요",
      (v) => /.+@.+\..+/.test(v) || "올바른 양식의 이메일을 입력해주세요",
    ],
    password: "",
    rules: {
      required: (value) => !!value || "비밀번호를 입력해주세요.",
      min: (v) => (v && v.length >= 8) || "비밀번호는 8글자 이상 입력해주세요",
    },
    checkbox: false,
    show: false,
  }),
    created () {
    let token = window.$cookies.get('nnd') //nnd가 key인 쿠키 가져옴
    if (token) { //토큰 존재하면
      this.$router.push("/") //home으로 보냄
    }
  },
  methods: {
    login() {
      if (this.$refs.form.validate()) {
        axios
          .post("http://localhost:8080/member/login", {
            params: {
              email: this.email,
              password: this.password,
            },
          })
          .then((response) => {
          console.log(response)
          window.$cookies.set('nnd', response.data, '2d') //로그인시 쿠키 저장
          //location.reload()
          this.$router.push("/") //로그인 성공하면 home으로
          })
          .catch((error) => {
            console.log(error.response);
          });
      }
    },
  },
};
</script>
<style scoped>
.login-body {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  /* min-height: 100vh; */
}

.container {
  width: 300px;
}
h2 {
  text-align: center;
  margin: 0 0 20px;
}

.form button {
  cursor: pointer;
  color: #fff;
  display: block;
  font-size: 16px;
  width: 100%;
}

.sns-login {
  margin: 20px 0;
}

.sns-login .sns-btn {
  margin: 0 4px;
  padding: 5px 15px;
}

.add-option .routers {
  margin: 0 5px;
  text-decoration: none;
  color: #222;
}
</style>
