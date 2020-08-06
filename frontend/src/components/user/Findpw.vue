<template>
  <v-row no-gutters>
    <v-col class="pa-5" tile>
      <!--sm="12" md="4" lg="4"-->
      <div class="mt-5 pt-5 pb-5" align="center">
        <h1>비밀번호 찾기</h1>
      </div>

      <div class="pa-10">
        <v-text-field
          label="E-mail을 입력해주세요"
          v-model="user.email"
          :rules="emailRules"
          outlined
          dense
        ></v-text-field>

        <v-btn
          block
          large
          color="green accent-4"
          class="mb-4"
          @click="authEmail"
          >이메일로 인증받기</v-btn
        >

        <v-text-field
          v-model="email_code"
          :rules="emailRules"
          placeholder="인증번호 입력(5분 이내)"
        />
        <v-btn :disabled="!valid" class="mr-4" color="yellow">인증</v-btn>
        <v-btn class="c-20" @click="$router.push('/login')">뒤로가기</v-btn>

        <div class="text-right">
          <a href="#">이메일 재발송</a>
        </div>
      </div>
    </v-col>
  </v-row>
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

<style scoped></style>
