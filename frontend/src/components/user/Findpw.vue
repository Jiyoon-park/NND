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
          v-model="email"
          :rules="[rules.email]"
          outlined
          dense
        ></v-text-field>

        <v-btn block large color="primary" class="mb-4" @click="authEmail"
          >이메일로 인증번호 받기</v-btn
        >

        <v-text-field
          single-line
          type="text"
          label="인증번호 입력해주세요"
          Filled
          class="mb-6"
        />
        <v-text-field
          id="email-code"
          type="text"
          placeholder="인증번호 입력(5분 이내)"
        />
        <v-btn class="mr-4">인증</v-btn>
        <v-btn class="c-20" @click="$router.push('/login')">뒤로가기</v-btn>

        <div class="text-right">
          <a href="#">이메일 재발송</a>
        </div>
      </div>
    </v-col>
  </v-row>
</template>

<script>
export default {
  data() {
    return {
      title: "Preliminary report",
      email: "",
      rules: {
        // required: (value) => !!value || "이메일 형식이 아닙니다.",
        //counter: (value) => value.length <= 20 || "Max 20 characters",
        email: (value) => {
          const pattern = /^(([^<>()[\]\\.,;:\s@"]+(\.[^<>()[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
          return pattern.test(value) || "이메일 형식이 아닙니다";
        },
      },
    };
  },
  methods: {},
};
</script>

<style scoped></style>
