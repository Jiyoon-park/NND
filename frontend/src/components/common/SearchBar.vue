<template>
  <v-app>
    <v-form>
      <v-container class="d-flex">
        <v-text-field
          placeholder="검색어를 입력해주세요."
          outlined
          dense
          clearable
          color="teal"
          v-model="inputData"
        ></v-text-field>
        <v-btn depressed color="teal" dark class="ml-2">
          <v-icon>fas fa-search</v-icon>
        </v-btn>
      </v-container>
    </v-form>
    <SearchKeywords />
  </v-app>
</template>

<script>
import SearchKeywords from "./SearchKeywords.vue";

export default {
  components: {
    SearchKeywords,
  },
  data() {
    return {
      inputData: "",
      searchKeywords: [],
    };
  },
};
</script>

<style scoped></style>
