<template>
  <div class="login">
    <img class="logo" src="../../assets/images/logo_without_title.png" alt="logo" width="20%" />
    <h5>로그인</h5>
    <div class="login-form">
      <input type="text" placeholder="이메일을 입력해주세요." />
      <br />
      <input type="password" placeholder="비밀번호를 입력해주세요." />
      <br />
      <button class="btn btn-info">로그인</button>
    </div>
    <div class="sns-login">
      <p>SNS 계정으로 간편하게 로그인할 수 있어요!</p>
      <button>kakao</button>
      <button>Naver</button>
      <button>Google</button>
    </div>
    <div class="add-option">
      <router-link to="/signup" class="routers">가입하기</router-link>|
      <router-link to="/findemail" class="routers">이메일 찾기</router-link>|
      <router-link to="/findpw" class="routers">비밀번호 찾기</router-link>
    </div>
  </div>
</template>

<script>
// @ is an alias to /src

export default {
  name: "Login",
  components: {}
};
</script>
<style scoped>
* {
  box-sizing: border-box;
}
</style>