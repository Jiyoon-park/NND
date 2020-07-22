<template>
  <v-card>
    <v-tabs
      background-color="teal"
      v-model="currentItem"
      dark
      flat
      fixed-tabs
      slider-color="white"
    >
      <v-tab v-for="item in items" :key="item" :href="'#tab-' + item">{{
        item
      }}</v-tab>
    </v-tabs>

    <v-tabs-items v-model="currentItem">
      <v-tab-item v-for="item in items" :key="item" :value="'tab-' + item">
        <v-card flat>
          <SearchBar />
        </v-card>
      </v-tab-item>
    </v-tabs-items>
  </v-card>
</template>
<script>
import SearchBar from "./SearchBar.vue";
export default {
  components: {
    SearchBar,
  },
  data: () => ({
    currentItem: "tab-Web",
    items: ["팀", "팀원"],
  }),

  methods: {
    addItem(item) {
      const removed = this.items.splice(0, 1);
      this.items.push(...this.more.splice(this.more.indexOf(item), 1));
      this.more.push(...removed);
      this.$nextTick(() => {
        this.currentItem = "tab-" + item;
      });
    },
  },
};
</script>
