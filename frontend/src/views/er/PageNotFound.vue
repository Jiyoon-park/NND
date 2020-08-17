<template>
  <!-- 여기에 네브바를 넣겠습니다 -->
  <div>
    <NavBar />
    <v-container class="mt-16">
      <v-row justify="center">
        <v-col cols="10" sm="8" md="6" lg="4">
          <div class="d-flex flex-column align-center justify-content-center">
            <img src="../../assets/images/page_not_found.jpg" alt="error_page" width="200px" />
            <h1>Sorry!</h1>
            <p>찾으시는 페이지는 존재하지 않습니다.</p>
            <button class="home-btn" @click="goToIndex">메인으로</button>
          </div>
        </v-col>
      </v-row>
    </v-container>
  </div>
</template>

<script>
import NavBar from "../../components/common/NavBar.vue";

export default {
  components: {
    NavBar,
  },
  methods: {
    goToIndex() {
      this.$router.push("/");
    },
  },
};
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
  background-color: #0277bd;
  border: 2px solid #0277bd;
  border-radius: 4px;
  color: #fff;
  display: block;
  font-size: 16px;
  padding: 10px;
  margin-top: 20px;
  width: 50%;
}
</style>