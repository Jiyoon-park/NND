
<!--
    가입하기는 기본적인 폼만 제공됩니다
    기능명세에 따라 개발을 진행하세요.
    Sub PJT I에서는 UX, 디자인 등을 포함하여 백엔드를 제외하여 개발합니다.
 -->
<template>
  <div class="user join wrapC">
    <div class="wrapC middle">
      <img
        class="logo"
        src="../../assets/images/logo_without_title.png"
        alt="logo"
        width="30%"
        @click="goToIndex"
      />
      <h1>동료 만들기 첫단계</h1>
      <div class="form-wrap">
        <div class="input-with-label">
          <input v-model="email" id="email" placeholder="이메일을 입력하세요." type="text" />
          <label for="email">이메일</label>
        </div>

        <div class="input-with-label">
          <input v-model="password" id="password" type="password" placeholder="비밀번호를 입력하세요." />
          <label for="password">비밀번호</label>
        </div>

        <div class="input-with-label">
          <input
            v-model="passwordConfirm"
            type="password"
            id="password-confirm"
            placeholder="비밀번호를 다시한번 입력하세요."
          />
          <label for="password-confirm">비밀번호 확인</label>
        </div>

        <div class="input-with-label">
          <input v-model="phone" id="phone" placeholder="전화번호를 입력하세요." type="text" />
          <label for="phone">전화번호</label>
        </div>
      </div>

      <label>
        <input v-model="isTerm" type="checkbox" id="term" />
        <span>약관을 동의합니다.</span>
      </label>

      <span @click="termPopup=true">약관보기</span>
      <div>
        <button @click="back">취소</button>
      </div>
      <button class="btn-bottom" @click="register">가입하기</button>
    </div>
  </div>
</template>

<script>
export default {
    data: () => {
      return {
        email: "",
        password: "",
        passwordConfirm: "",
        phone: "",

        isTerm: false,
        isLoading: false,
        error: {
          email: false,
          password: false,
          passwordConfirm: false,
          phone: false,
          term: false
        },
        isSubmit: false,
        passwordType: "password",
        passwordConfirmType: "password",
        termPopup: false
      };
    },
    methods: {
      back() {
        return this.$router.push("/");
      },
      register() {
        this.isSubmit = true;
        this.$router.push("/");
      },
      termPopup() {
        
      }
    }
  }
</script>


