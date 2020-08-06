<template>
  <v-row justify="center">
    <NavBar />
    <v-col cols="10" md="8" lg="6" class="mt-15">
      <h2>회원가입</h2>
      <v-form ref="form" v-model="valid" lazy-validation>
        <v-text-field
          v-model="name"
          :rules="nameRule"
          label="이름*"
          required
        ></v-text-field>

        <v-text-field
          v-model="email"
          :rules="emailRules"
          label="이메일*"
          required
        ></v-text-field>

        <v-text-field
          v-model="password"
          :rules="passwordRules"
          :type="'password'"
          label="비밀번호*"
          required
        ></v-text-field>

        <v-text-field
          v-model="passwordchk"
          :rules="[passwordchkRules, passwordconfirmRules]"
          :type="'password'"
          label="비밀번호 확인"
          required
        ></v-text-field>

        <v-checkbox
          v-model="checkbox"
          :rules="[(v) => !!v || '약관동의를 해야합니다']"
          label="약관에 동의하십니까?"
          required
        ></v-checkbox>

        <v-btn :disabled="!valid" color="success" class="mr-4" @click="signup"
          >제출</v-btn
        >
        <v-btn color="error" class="mr-4" @click="reset">초기화</v-btn>
        <v-btn color="yellow" class="mr-4" @click="$router.push('/login')"
          >로그인화면</v-btn
        >
        <v-btn color="blue" class="mr-4" @click="$router.push('/findpw')"
          >비밀번호찾기</v-btn
        >
      </v-form>
    </v-col>
  </v-row>
</template>

<script>
import NavBar from "../common/NavBar.vue";
import axios from "axios";
import firebase from "firebase";

export default {
  name: "SignUp",
  components: {
    NavBar,
  },
  data: () => ({
    signupData: {
      name: null,
      email: null,
      profile: null,
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
  }),

  computed: {
    passwordconfirmRules() {
      return () => this.password === this.passwordchk || "비밀번호가 다릅니다";
    },
  },

  methods: {
    signup() {
      if (this.$refs.form.validate()) {
        axios
          .post("http://localhost:8080/member/signup", {
            name: this.name,
            email: this.email,
            profile: null,
            password: this.password,
          })
          .then((response) => {
            console.log(response);
            // alert("회원가입 완료");
            this.$router.push({
              name: "Home",
              params: { id: response.data.object.idx },
            });
          })
          .catch((error) => {
            console.log(error.response);
            // alert("이미 있는 아이디입니다");
            this.$router.push("/signup");
          });

        firebase
          .auth()
          .createUserWithEmailAndPassword(this.email, this.password)
          .then(() => {
            // alert("회원가입 완료");
            var user = firebase.auth().currentUser;
            user.updateProfile({
              displayName: this.name,
            });
            this.$router.push("/");
          });
        // .catch(function(error) {
        //   var errorCode = error.code;
        //   var errorMessage = error.message;
        //   alert(errorMessage + errorCode);
        // });
      }
    },
    reset() {
      this.$refs.form.reset();
    },
  },
};
</script>
