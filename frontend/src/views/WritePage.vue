<template>
  <v-row justify="center">
    <NavBar />
    <v-col cols="10" md="8" lg="6" class="mt-15">
      <v-container fluid>
        <v-combobox
          v-model="model"
          :items="items"
          :search-input.sync="search"
          hide-selected
          hint="사용할 수 있는 기술 스택"
          label="태그"
          multiple
          persistent-hint
          small-chips
        >
          <template v-slot:no-data>
            <v-list-item>
              <v-list-item-content>
                <v-list-item-title>
                  No results matching "<strong>{{ search }}</strong
                  >". Press <kbd>enter</kbd> to create a new one
                </v-list-item-title>
              </v-list-item-content>
            </v-list-item>
          </template>
        </v-combobox>
      </v-container>
    </v-col>
  </v-row>
</template>
<script>
import NavBar from "../components/common/NavBar.vue";

export default {
  name: "write",
  components: {
    NavBar,
  },

  data: () => ({
    items: ["Java", "C", "Vue", "C++", "Spring"],
    model: [],
    search: null,
  }),

  watch: {
    model(val) {
      if (val.length > 5) {
        this.$nextTick(() => this.model.pop());
      }
    },
  },
};
</script>
