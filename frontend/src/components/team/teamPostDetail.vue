<template>
  <v-card>
    <v-card-text class="mt-5">
      <div class="d-flex align-center">
        <!-- <div>
          <v-avatar color="grey" size="48">
            <span v-if="!letterinfo.profile" class="white--text headline"></span>
            <img v-else :src="letterinfo.profile" />
          </v-avatar>
        </div>-->
        <div class="ml-3">
          <p class="mb-1">{{ postinfo.writer }}</p>
          <!-- <p class="mb-1">{{ letterinfo.createDate }}</p> -->
          <p class="mb-1">{{ postinfo.createDate }}</p>
        </div>
      </div>
      <div class="rounded grey lighten-2 pa-5 mt-3">
        <p class="p-3">{{ postinfo.content }}</p>
      </div>
    </v-card-text>
    <v-divider></v-divider>
    <v-card-actions>
      <v-spacer></v-spacer>
      <v-btn color="green darken-1" text @click="changeDialog">닫기</v-btn>
      <!-- 나를 영입하는 편지인지/다른 사람이 우리 팀에 지원하는 편지인지 분기 해서 둘 중 하나만 보여줘야 함 -->
      <v-btn color="green darken-1" text @click="postUpdate()">수정하기</v-btn>
      <v-btn color="green darken-1" text @click="postDelete()">삭제하기</v-btn>
    </v-card-actions>
  </v-card>
</template>

<script>
// import axios from "axios";
export default {
  components: {},
  props: {
    postinfo: {
      type: Object,
    },
    dialog: {
      type: Boolean,
    },
  },
  // 수정 삭제 메소드
  methods: {
    postUpdate() {},
    postDelete() {},
  },
};
</script>

<style>
</style>