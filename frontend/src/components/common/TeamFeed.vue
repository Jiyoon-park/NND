<template>
  <div :style="{ backgroundImage: 'url(../../assets/images/Sea.png)' }">
    <p>팀</p>
    <NewsFeed />
    <br />
    <NewsFeed />
    <br />
    <NewsFeed />
    <br />
    <NewsFeed />
    <br />
    <NewsFeed />
    <br />
    <NewsFeed />
    <br />
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

<style></style>
