<template>
  <v-row justify="center">
    <NavBar />
    <v-col cols="10" md="8" lg="6" class="mt-15">
      <h2>회원가입</h2>
      <v-form ref="form" v-model="valid" lazy-validation>
        <v-text-field v-model="name" :rules="nameRule" label="이름*" required></v-text-field>

        <v-text-field v-model="email" :rules="emailRules" label="이메일*" required></v-text-field>

        <v-text-field
          v-model="password"
          :rules="passwordRules"
          :type="'password'"
          label="비밀번호*"
          required
        ></v-text-field>

        <v-file-input v-model="profile" label="프로필 사진" prepend-icon></v-file-input>

        <v-text-field v-model="git" label="깃 주소"></v-text-field>

        <v-checkbox
          v-model="checkbox"
          :rules="[(v) => !!v || '약관동의를 해야합니다']"
          label="약관에 동의하십니까?"
          required
        ></v-checkbox>

        <v-btn :disabled="!valid" color="success" class="mr-4" @click="signup">제출</v-btn>

        <v-btn color="error" class="mr-4" @click="reset">초기화</v-btn>
        <v-btn color="yellow" class="mr-4" @click="$router.push('/login')">로그인화면</v-btn>
        <v-btn color="blue" class="mr-4" @click="$router.push('/findpw')">비밀번호찾기</v-btn>
      </v-form>
    </v-col>
  </v-row>
</template>

<script>
import NavBar from "../common/NavBar.vue";
import axios from "axios";

export default {
  name: "SignUp",
  components: {
    NavBar,
  },
  data: () => ({
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
    checkbox: false,
  }),

  methods: {
    signup() {
      if (this.$refs.form.validate()) {
        axios
          .post("http://localhost:8080/signup", {
            params: {
              name: this.name,
              email: this.email,
              password: this.password,
              profile: this.profile,
              git: this.git,
            },
          })
          .then((response) => {
            console.log(response);
            alert("회원가입 완료");
            this.$router.push("/");
          })
          .catch((error) => {
            console.log(error.response);
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
