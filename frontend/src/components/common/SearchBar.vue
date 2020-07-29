<template>
  <div class="container">
    <v-row>
      <v-col cols="5" class="pr-0">
        <v-select
          menu-props="auto"
          v-model="select"
          :items="items"
          label="전체"
          hide-details
          single-line
          outlined
          dense
        ></v-select>
      </v-col>
      <v-col cols="7">
        <v-text-field dense single-line outlined></v-text-field>
      </v-col>
    </v-row>
    <!-- <v-form @submit.prevent="onSubmit">
      <div class="d-flex">
        <input class="search-input" type="text" v-model="inputData" placeholder="검색어를 입력하세요" />
        <v-btn depressed color="grey" dark class="ml-2">
          <v-icon>fas fa-search</v-icon>
        </v-btn>
      </div>
      <small v-show="warning" class="red--text">{{ errorMsg }}</small>
    </v-form>
    <div>
      <ul class="search-keyword-list d-flex">
        <li class="search-keyword" v-for="(keyword, index) in searchKeywords" :key="index">
          <span>{{ keyword }}</span>
          <button class="keyword-delbtn" @click="onDelbtn(index)">
            <i class="fas fa-times-circle"></i>
          </button>
        </li>
    </ul>-->
    <!-- </div> -->
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
      warning: false,
      errorMsg: "",
      select: null,
      items: ["전체", "아이디", "제목+내용", "태그검색"],
      // items: ["Java", "Vue", "Django", "Vuetify", "C", "C++", "JS", "Spring "],
      // model: ["Vuetify"],
      // search: null,
    };
  },
  watch: {
    model(val) {
      if (val.length > 5) {
        this.$nextTick(() => this.model.pop());
      }
    },
  },
  methods: {
    onSubmit() {
      this.warning = false;
      var counter = this.searchKeywords.length;
      if (!this.inputData) {
        this.errorMsg = "검색어를 입력해주십시오.";
        this.warning = true;
      } else {
        for (var i = 0; i < counter; i++) {
          if (this.searchKeywords[i] == this.inputData) {
            this.errorMsg = "이미 추가된 검색어입니다.";
            this.warning = true;
          }
        }
      }
      if (this.warning == false) {
        this.searchKeywords.push(this.inputData);
        this.inputData = "";
      }
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
