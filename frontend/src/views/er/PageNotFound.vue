<template>
  <!-- 여기에 네브바를 넣겠습니다 -->
  <div class="wrapC d-flex flex-column justify-content-center align-items-center">
    <img src="../../assets/images/page_not_found.jpg" alt="error_page" width="30%">
    <h1>404 PAGE NOT FOUND</h1>
    <p>찾으시는 페이지는 존재하지 않습니다.</p>
    <button class="home-btn" @click="goToIndex">Back Home</button>
  </div>
</template>

<script>
export default {
  methods: {
    goToIndex() {
      this.$router.push("/feed/main");
    },
  }

}
</script>

<style scoped>
  .wrapC {
    min-height: 100vh;
  }

  .wrapC h1 {
    margin-top: 10px;
  }

  .home-btn {
    margin-top: 10px;
    cursor: pointer;
    background-color: #0CA791;
    border: 2px solid #0CA791;
    border-radius: 4px;
    color: #fff;
    display: block;
    font-size: 16px;
    padding: 10px;
    margin-top: 20px;
    width: 40%;
  }
</style>