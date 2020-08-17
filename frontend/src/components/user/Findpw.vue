<template>
  <v-container>
    <v-row justify="center">
      <v-col cols="10" sm="8" md="6" lg="4" class="px-0">
        <div class="d-flex align-center" @click="$router.push('/login')">
          <v-btn icon>
            <v-icon>mdi-arrow-left</v-icon>
          </v-btn>
          <p class="mb-0">로그인 화면으로</p>
        </div>
      </v-col>
    </v-row>
    <v-row justify="center">
      <v-col cols="10" sm="8" md="6" lg="4">
        <h2 class="my-5">비밀번호 찾기</h2>
        <div>
          <v-text-field
            label="E-mail을 입력해주세요"
            v-model="user.email"
            :rules="emailRules"
            outlined
            dense
          ></v-text-field>
          <v-btn block large class="button" color="#0277BD" @click="authEmail">이메일로 인증받기</v-btn>
          <v-row align="center">
            <v-col cols="8">
              <v-text-field
                class="mt-7"
                v-model="email_code"
                label="인증번호 입력"
                persistent-hint="persistentHint"
                solo
              ></v-text-field>
            </v-col>
            <v-col cols="4">
              <v-btn large block :disabled="!valid" color="success">인증</v-btn>
            </v-col>
          </v-row>
          <div class="text-center">
            <p class="mb-2" style="opacity: 0.8;">🙄 혹시 이메일을 받지 못하셨나요?</p>
            <a href="#" style="text-decoration:none;">이메일 재발송</a>
          </div>
        </div>
      </v-col>
    </v-row>
  </v-container>
</template>

<script>
import firebase from "firebase";

export default {
  data() {
    return {
      user: {
        email: "",
      },
      valid: true,
      title: "Preliminary report",
      emailRules: [
        (v) => !!v || "E-Mail은 필수 입력항목입니다",
        (v) => /.+@.+\..+/.test(v) || "E-mail 양식이 올바르지 않습니다.",
      ],
      email_code: "",
    };
  },
  methods: {
    authEmail() {
      firebase
        .auth()
        .sendPasswordResetEmail(this.user.email)
        .then(() => {
          alert("Check your registered email to reset the password!");
          this.user = {
            email: "",
          };
        })
        .catch((error) => {
          alert(error);
        });
    },
  },
};
</script>

<style scoped>
.button {
  cursor: pointer;
  color: #fff;
  display: block;
  font-size: 16px;
  width: 100%;
  padding: px;
}
</style>
