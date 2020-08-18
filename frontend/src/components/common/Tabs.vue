<template>
  <div>
    <v-tabs
      class="rounded-0 mt-13"
      background-color="white"
      v-model="currentItem"
      color="black"
      fixed-tabs
      slider-color="#30336b"
    >
      <v-tab v-for="item in items" :key="item" :href="'#tab-' + item">
        {{
        item
        }}
      </v-tab>
    </v-tabs>

    <v-tabs-items v-model="currentItem">
      <v-tab-item v-for="item in items" :key="item" :value="'tab-' + item">
        <SearchBar />
        <TeammemberFeed v-if="item==='팀원'" />
        <TeamFeed v-if="item==='팀'" />
      </v-tab-item>
    </v-tabs-items>
  </div>
</template>
<script>
import SearchBar from "./SearchBar.vue";
import TeamFeed from "./TeamFeed.vue";
import TeammemberFeed from "./TeammemberFeed.vue";

export default {
  components: {
    SearchBar,
    TeammemberFeed,
    TeamFeed,
  },
  data: () => ({
    currentItem: "tab-Web",
    items: ["팀원", "팀"],
  }),

  methods: {},
};
</script>
<!--
<style scoped>
.mb-20 {
  margin-top: 64px;
}
</style>-->
