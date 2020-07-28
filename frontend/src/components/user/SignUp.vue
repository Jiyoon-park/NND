<template>
  <v-parallax src="../../assets/images/logo_without_title.png">
    <v-row justify="center">
      <v-col cols="10" md="8" lg="6" class="mt-15">
        <h2>회원가입</h2>
        <v-form ref="form" v-model="valid" lazy-validation>
          <v-text-field
            v-model="email"
            :rules="emailRules"
            label="E-mail ID"
            required
          ></v-text-field>

          <v-text-field
            v-model="password"
            :rules="passwordRules"
            :type="'password'"
            label="비밀번호"
            required
          ></v-text-field>

          <v-checkbox
            v-model="checkbox"
            :rules="[(v) => !!v || '약관동의를 해야합니다']"
            label="약관에 동의하십니까?"
            required
          ></v-checkbox>

          <v-btn
            :disabled="!valid"
            color="success"
            class="mr-4"
            @click="validate"
          >
            제출
          </v-btn>

          <v-btn color="error" class="mr-4" @click="reset">
            초기화
          </v-btn>
          <v-btn color="yellow" class="mr-4" @click="$router.push('/login')">
            로그인화면
          </v-btn>
          <v-btn color="blue" class="mr-4" @click="$router.push('/findpw')">
            비밀번호찾기
          </v-btn>
        </v-form>
      </v-col>
    </v-row>
  </v-parallax>
</template>

<script>
export default {
  data: () => ({
    valid: true,
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
    validate() {
      this.$refs.form.validate();
    },
    reset() {
      this.$refs.form.reset();
    },
  },
};
</script>
