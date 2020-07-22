<template>
  <div>
    <v-card>
      <v-tabs background-color="teal" v-model="currentItem" dark fixed-tabs slider-color="white">
        <v-tab v-for="item in items" :key="item" :href="'#tab-' + item">
          {{
          item
          }}
        </v-tab>
      </v-tabs>

      <v-tabs-items v-model="currentItem">
        <SearchBar />
        <NewsFeed />
        <NewsFeed />
        <NewsFeed />
      </v-tabs-items>
    </v-card>
  </div>
</template>
<script>
import SearchBar from "./SearchBar.vue";
import NewsFeed from "./NewsFeed.vue";

export default {
  components: {
    SearchBar,
    NewsFeed
  },
  data: () => ({
    currentItem: "tab-Web",
    items: ["팀", "팀원"]
  }),

  methods: {}
};
</script>
