<template>
  <v-container>
    <v-row justify="center">
      <v-col cols="10" sm="8" md="6" lg="4" style="max-height:800px; ">
        <div class="logo-container">
          <img
            class="logo"
            src="../../assets/images/logo_black_title.png"
            width="60%"
          />
        </div>
        <v-form class="form" ref="form" v-model="valid" lazy-validation>
          <input
            type="hidden"
            th:name="${_csrf.parameterName}"
            th:value="${_csrf.token}"
          />
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
          <v-btn
            large
            class="button"
            :disabled="!valid"
            color="#0277BD"
            @click="login"
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
            <p>✨ SNS로 간편하게 로그인할 수 있어요!</p>

            <a :href="this.redirectURL">
              <img src="../../assets/images/kakao_login.png" alt />
            </a>
          </div>
          <div class="add-option">
            <router-link to="/signup" class="routers">회원가입</router-link>|
            <router-link to="/findpw" class="routers"
              >비밀번호 찾기</router-link
            >
          </div>
        </div>
      </v-col>
    </v-row>
  </v-container>
</template>
<script>
import axios from "axios";

export default {
  name: "Login",
  components: {},
  data: () => ({
    redirectURL: `https://kauth.kakao.com/oauth/authorize?client_id=136ae30351513efbd13773e917430828&redirect_uri=${process.env.VUE_APP_API_URL}/login&response_type=code`,
    id: 0,
    token: "",
    valid: true,
    email: "",
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
  created() {
    let token = window.$cookies.get("nnd"); //nnd가 key인 쿠키 가져옴
    axios
      .get(`${process.env.VUE_APP_API_URL}/contest`, {
        // headers: {
        //   Authorization: "Bearer " + token.data, // the token is a variable which holds the token
        // },
      })
      .then(({ data }) => {
        this.boards = data;

        this.$store.commit("setContest", this.boards);
        console.log(this.boards);
        // for (let index = 0; index < this.boards.length; index++) {
        //   var ncolor =
        //     "#" + Math.round(Math.random() * 0xffffff).toString(16);
        //   const allDay = this.rnd(0, 3) === 0;
        //   this.events.push({
        //     id: index,
        //     name: this.boards[index].title,
        //     start: this.boards[index].start,
        //     end: this.boards[index].start,
        //     color: ncolor,
        //     timed: !allDay,
        //     poster: this.boards[index].poster,
        //   });

        //   this.events.push({
        //     id: index,
        //     name: this.boards[index].title,
        //     start: this.boards[index].end,
        //     end: this.boards[index].end,
        //     color: ncolor,
        //     timed: !allDay,
        //     poster: this.boards[index].poster,
        //   });
        // }
      })
      .catch((err) => {
        console.log(err);
      }); //endaxios
    if (token) {
      //토큰 존재하면
      console.log("already-login");
      this.$router.push("/"); //home으로 보냄
    }
  },
  methods: {
    makeURL() {
      location.href = `https://kauth.kakao.com/oauth/authorize?client_id=136ae30351513efbd13773e917430828&redirect_uri=${process.env.VUE_APP_API_URL}/login&response_type=code`;
    },
    login() {
      if (this.$refs.form.validate()) {
        axios
          .post(`${process.env.VUE_APP_API_URL}/member/login`, {
            email: this.email,
            password: this.password,
          })
          .then(
            (response) => {
              console.log(response);
              window.$cookies.set("nnd", response.data, "2d"); //로그인시 쿠키 저장
              console.log(response.data.object);
              //location.reload()
              this.$router.push({
                name: "Home",
                params: { id: response.data.object.idx },
              });
            },
            () => {
              alert("비밀번호가 틀렸습니다.");
              console.log("failed");
            }
          );
      }
    },
  },
};
</script>
<style scoped>
.logo-container {
  text-align: center;
}

.login-body {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  /* min-height: 100vh; */
}

h2 {
  text-align: center;
  margin: 0 0 20px;
}

.form .button {
  cursor: pointer;
  color: #fff;
  display: block;
  font-size: 16px;
  width: 100%;
  padding: px;
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
