<template>
  <div>
    <v-layout>
      <v-flex xs12 sm6 offset-sm3>
        <v-card>
          <v-card-title>
            <div>
              <v-icon class="mr-5">mdi-account-circle</v-icon>
              <span class="grey--text">User Name</span>
              <br />
              <span>공모전 팀원 구합니다</span>
            </div>
          </v-card-title>
          <v-card-subtitle>
            <span>같이 javascript 및 jQuery 관련 프로젝트 팀원 구합니다</span>
          </v-card-subtitle>
          <v-card-actions>
            <v-btn text color="gray" @click.stop="dialog = true"
              >상세정보</v-btn
            >
            <Detail />

            <v-btn text color="orange" v-if="!favorite" @click="addFavorite"
              >즐겨찾기 추가</v-btn
            >
            <v-btn text color="red" v-if="favorite" @click="delFavorite"
              >즐겨찾기 제거</v-btn
            >
          </v-card-actions>
        </v-card>
      </v-flex>
    </v-layout>
  </div>
</template>

<script>
import Detail from "./Detail.vue";

export default {
  data() {
    return {
      favorite: false,
      dialog: false,
    };
  },
  methods: {
    addFavorite() {
      this.favorite = true;
    },
    delFavorite() {
      this.favorite = false;
    },
  },
};
</script>
