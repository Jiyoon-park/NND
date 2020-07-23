<template>
  <div class="container">
    <v-form @submit.prevent="onSubmit">
      <div class="d-flex">
        <input class="search-input" type="text" v-model="inputData" placeholder="검색어를 입력하세요" />
        <v-btn depressed color="teal" dark class="ml-2">
          <v-icon>fas fa-search</v-icon>
        </v-btn>
      </div>
    </v-form>
    <div>
      <ul class="search-keyword-list d-flex">
        <li class="search-keyword" v-for="(keyword, index) in searchKeywords" :key="index">
          <span>{{ keyword }}</span>
          <button class="keyword-delbtn" @click="onDelbtn(index)">
            <i class="fas fa-times-circle"></i>
          </button>
        </li>
      </ul>
    </div>
    <!-- <SearchKeywords /> -->
  </div>
</template>

<script>
// import SearchKeywords from "./SearchKeywords.vue";

export default {
  components: {
    // SearchKeywords,
  },
  data() {
    return {
      inputData: "",
      searchKeywords: [],
    };
  },
  methods: {
    onSubmit() {
      if (this.inputData) {
        this.searchKeywords.push(this.inputData);
      }
      this.inputData = "";
    },
    onDelbtn(index) {
      this.searchKeywords.splice(index, 1);
    },
  },
};
</script>

<style scoped>
.search-input {
  border: 2px solid rgb(192, 192, 192);
  border-radius: 5px;
  padding: 0 10px;
  width: 80%;
}

.search-keyword-list {
  padding: 0;
  margin-top: 5px;
}

.search-keyword {
  list-style: none;
  background-color: #999;
  margin: 3px 5px;
  padding: 2px 3px;
  border-radius: 10px;
  color: #fff;
}

.keyword-delbtn {
  margin: 0 2px;
}
</style>
