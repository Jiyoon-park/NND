<template>
  <v-card>
    <v-toolbar dark color="purple lighten-2">
      <v-btn icon dark @click="changeDialog">
        <v-icon>mdi-close</v-icon>
      </v-btn>
      <v-toolbar-title>{{ item.tab }}</v-toolbar-title>
      <v-spacer></v-spacer>
    </v-toolbar>
    <v-card-text class="mt-5">
      <div class="d-flex align-center">
        <div>
          <v-avatar color="grey" size="48">
            <span class="white--text headline"></span>
          </v-avatar>
        </div>
        <div class="ml-3">
          <p class="mb-1">보낸 사람</p>
          <p class="mb-1">보낸 날짜</p>
        </div>
        <div class="ml-3">
          <p class="mb-1">{{ letterinfo.name }}</p>
          <p class="mb-1">{{ letterinfo.createDate }}</p>
        </div>
      </div>
      <div class="rounded grey lighten-2 pa-5 mt-3">
        <p class="p-3">{{ letterinfo.content }}</p>
      </div>
    </v-card-text>
    <v-divider></v-divider>
    <v-card-actions>
      <v-spacer></v-spacer>
      <v-btn color="green darken-1" text @click="changeDialog">닫기</v-btn>
      <v-btn color="green darken-1" text @click="changeDialog">수락하기</v-btn>
    </v-card-actions>
  </v-card>
</template>

<script>
export default {
  components: {},
  props: {
    letterinfo: {
      type: Array,
    },
    item: {
      type: Object,
    },
    dialog: {
      type: Boolean,
    },
  },
  data() {
    return {};
  },
  methods: {
    changeDialog() {
      this.$emit("changeDialog");
    },
  },
};
</script>

<style>
</style>