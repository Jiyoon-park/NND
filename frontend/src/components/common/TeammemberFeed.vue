<template>
  <div>
    <p>팀멤버</p>
    <NewsFeed />
    <NewsFeed />
    <NewsFeed />
  </div>
</template>

<script>
import NewsFeed from "./NewsFeed.vue";
export default {
  components: {
    NewsFeed,
  },
};
</script>

<style>
</style>