<template>
  <div class="signup">
    <img class="logo" src="../../assets/images/logo_without_title.png" alt="logo" width="20%" />
    <h1>회원가입</h1>
    <div class="join-form">
        <div class="input">
          <label for="email">이메일 : </label>
          <input v-model="email" id="email" placeholder="이메일을 입력하세요." type="text" />
        </div>
        <div class="input">
          <label for="password">비밀번호 : </label>
          <input v-model="password" id="password" placeholder="비밀번호를 입력하세요." type="password" />
        </div>
        <div class="input">
          <label for="password-confirm">비밀번호 확인 : </label>
          <input v-model="passwordConfirm" id="password-confirm" placeholder="비밀번호를 다시 한번 입력하세요." type="password"/>
        </div>
        <div class="input">
          <label for="tel">전화번호 : </label>
          <input v-model="tel" id="tel" placeholder="전화번호를 입력하세요." type="text" />
        </div>
      </div>
      <label>
        <input v-model="isTerm" type="checkbox" id="term" />
        <span>약관을 동의합니다.</span>
      </label>
      <div class="button">
        <button class="register" @click="register">가입하기</button>
        <button class="cancle" @click="cancel">취소</button>
      </div>
  </div>
  
</template>

<script>
// @ is an alias to /src

export default {
  name: "Signup",
  components: {},
  data: () => {
      return {
        email: "",
        password: "",
        passwordConfirm: "",
        tel: "",

        isTerm: false,
        isLoading: false,
        error: {
          email: false,
          password: false,
          passwordConfirm: false,
          tel: false,
          term: false
        },
        isSubmit: false,
        passwordType: "password",
        passwordConfirmType: "password",
        termPopup: false
      };
  },
  methods: {
    cancel() {
      return this.$router.push("/");
    },
    register() {
      this.isSubmit = true;
      this.$router.push("/");
    }
  }
};
</script>
