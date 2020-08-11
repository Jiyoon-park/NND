<template>
  <v-card>
    <v-list class="transparent">
      <v-list-item v-for="(post, index) in teamposts" :key="index + '_teamposts'">
        <v-list-item-title>{{ post.wirter }}</v-list-item-title>

        <v-list-item-icon>
          <v-icon>{{ post.title }}</v-icon>
        </v-list-item-icon>

        <v-list-item-subtitle class="text-right">{{ post.notice }}</v-list-item-subtitle>
      </v-list-item>
    </v-list>
  </v-card>
</template>

<script>
export default {};
</script>

<style>
</style>