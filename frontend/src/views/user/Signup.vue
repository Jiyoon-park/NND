<template>
  <div class="signup">
    <!-- <img
        class="logo"
        src="../assets/images/logo_without_title.png"
        alt="logo"
        width="30%"
      /> -->
    <h1>회원가입</h1>
    <div class="join-form">
        <div class="input">
          <label for="email">이메일 : </label>
          <input v-model="email" id="email" placeholder="이메일을 입력하세요." type="text" />
        </div>
        <div class="input">
          <label for="password">비밀번호 : </label>
          <input v-model="email" id="email" placeholder="이메일을 입력하세요." type="text" />
        </div>
        <div class="input">
          <label for="email">이메일 : </label>
          <input v-model="email" id="email" placeholder="이메일을 입력하세요." type="text" />
        </div>
      </div>
  </div>
  
</template>

<script>
// @ is an alias to /src

export default {
  name: "Signup",
  components: {}
};
</script>
