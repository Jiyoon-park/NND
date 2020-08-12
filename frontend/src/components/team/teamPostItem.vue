<template>
  <div>
    <v-list-item @click.stop="dialog = true">
      <div class="mr-3">
        <v-avatar color="grey" size="60">
          <span v-if="!userinfo.profile" class="white--text headline"></span>
          <img v-else :src="userinfo.profile" />
        </v-avatar>
      </div>
      <v-list-item-content>
        <div>
          <v-list-item-title v-text="postinfo.writer" class="font-weight-black"></v-list-item-title>
          <div class="d-flex">
            <v-list-item-title
              v-text="postinfo.title"
              class="text--secondary d-inline-block text-truncate"
              style="max-width: 160px;"
            ></v-list-item-title>
            <p class="mb-0">{{ postinfo.createDate }}</p>
          </div>
        </div>
      </v-list-item-content>
    </v-list-item>
    <v-dialog v-model="dialog" max-width="300">
      <TeamPostDetail
        :postinfo="postinfo"
        :userinfo="userinfo"
        :dialog="dialog"
        @changeDialog="dialog = false"
      />
    </v-dialog>
  </div>
</template>
<script>
import TeamPostDetail from "./teamPostDetail.vue";
// import axios from "axios";

export default {
  components: {
    TeamPostDetail,
  },
  data() {
    return {
      dialog: false,
    };
  },
  props: {
    postinfo: {
      type: Object,
    },
    userinfo: {
      type: Object,
    },
  },
  methods: {
    // onPostDetail(teamboardno) {},
  },
};
</script>

<style></style>
