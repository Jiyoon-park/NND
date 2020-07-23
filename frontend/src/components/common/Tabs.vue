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
        <v-tab-item v-for="item in items" :key="item" :value="'tab-' + item">
          <SearchBar />
          <NewsFeed />
          <br />
          <NewsFeed />
          <br />
          <NewsFeed />
        </v-tab-item>
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
    NewsFeed,
  },
  data: () => ({
    currentItem: "tab-Web",
    items: ["팀원", "팀"],
  }),

  methods: {},
};
</script>
