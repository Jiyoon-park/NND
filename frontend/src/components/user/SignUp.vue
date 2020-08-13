<template>
  <v-container>
    <div class="d-flex align-center" @click="$router.push('/login')">
      <v-btn icon>
        <v-icon>mdi-arrow-left</v-icon>
      </v-btn>
      <p class="mb-0">로그인 화면으로</p>
    </div>
    <v-row justify="center">
      <v-col cols="10" md="8" lg="4">
        <h2 class="my-5">너내동 회원가입</h2>
        <v-form class="form" ref="form" v-model="valid" lazy-validation>
          <v-text-field v-model="name" :rules="nameRule" label="이름" outlined dense required></v-text-field>
          <v-text-field v-model="email" :rules="emailRules" label="이메일" outlined dense required></v-text-field>
          <v-text-field
            v-model="password"
            :rules="passwordRules"
            @click:append="show1 = !show1"
            :append-icon="show1 ? 'mdi-eye' : 'mdi-eye-off'"
            :type="show1 ? 'text' : 'password'"
            label="비밀번호"
            required
            outlined
            dense
          ></v-text-field>
          <v-text-field
            v-model="passwordchk"
            :rules="[passwordchkRules, passwordconfirmRules]"
            @click:append="show2 = !show2"
            :append-icon="show2 ? 'mdi-eye' : 'mdi-eye-off'"
            :type="show2 ? 'text' : 'password'"
            label="비밀번호 확인"
            required
            outlined
            dense
          ></v-text-field>
          <p class="mb-0">
            ✋ 잠깐!
            <br />회원 가입 버튼을 클릭하면, 너내동의
            <a>회원약관</a>에 동의하며 쿠키 사용을 포함한
            <a>개인정보처리방침</a>을 읽었음을 인정하게 됩니다.
          </p>
          <v-checkbox v-model="checkbox" label="동의합니다." required color="success"></v-checkbox>
          <v-btn large class="button" :disabled="!valid" color="#0277BD" @click="signup">회원가입</v-btn>
        </v-form>
      </v-col>
    </v-row>
  </v-container>
</template>

<script>
import axios from "axios";
import * as firebase from "firebase/app";
import "firebase/auth";
import "firebase/database";

export default {
  name: "SignUp",
  components: {},
  data: () => ({
    signupData: {
      name: null,
      email: null,
      password: null,
    },

    valid: true,
    name: "",
    nameRule: [(v) => !!v || "이름은 필수 입력항목입니다."],
    email: "",
    emailRules: [
      (v) => !!v || "E-Mail은 필수 입력항목입니다",
      (v) => /.+@.+\..+/.test(v) || "E-mail 양식이 올바르지 않습니다.",
    ],
    password: "",
    passwordRules: [
      (v) => !!v || "비밀번호는 필수 입력항목입니다",
      (v) => (v && v.length >= 8) || "8글자 이상 입력해야 합니다.",
    ],
    passwordchk: "",
    passwordchkRules: [(v) => !!v || "비밀번호 확인은 필수 입력항목입니다."],
    checkbox: false,
    show1: false,
    show2: false,
  }),

  computed: {
    passwordconfirmRules() {
      return () => this.password === this.passwordchk || "비밀번호가 다릅니다";
    },
  },

  methods: {
    signup() {
      if (this.$refs.form.validate()) {
        firebase
          .auth()
          .createUserWithEmailAndPassword(this.email, this.password)
          .then(() => {
            alert("회원가입 완료");
            var user = firebase.auth().currentUser;
            user.updateProfile({
              displayName: this.name,
            });
            this.$router.push("/login");
          })
          .catch(function (error) {
            const errorCode = error.code;
            const errorMessage = error.message;
            alert(errorMessage + errorCode);
          });

        axios
          .post(`${process.env.VUE_APP_API_URL}/member/signup`, {
            name: this.name,
            email: this.email,
            profile: null,
            password: this.password,
          })
          .then((response) => {
            console.log(response);
            // alert("회원가입 완료");
            this.$router.push("/login");
          })
          .catch((error) => {
            console.log(error.response);
            alert("이미 존재하는 아이디입니다");
            this.$router.push("/signup");
          });
      }
    },
    reset() {
      this.$refs.form.reset();
    },
  },
};
</script>

<style scoped>
.form .button {
  cursor: pointer;
  color: #fff;
  display: block;
  font-size: 16px;
  width: 100%;
  padding: px;
}
</style>
