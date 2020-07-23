<template>
  <v-card>
    <v-card-title class="headline">공모전 팀원 구합니다</v-card-title>
    <v-card-text>
      같이 javascript 및 jQuery 관련 프로젝트 팀원 구합니다.<br />
      프론트쪽 사람은 마무리되었습니다. <br />
      문의사항있으시면 아래 링크로 질문해주시면 고맙겠습니다.<br />
    </v-card-text>
    <v-card-text>
      오픈카톡방 :
      <!-- <div v-bind: href="https://www.google/com">KAKAO!!</div> -->
    </v-card-text>
    <v-card-actions>
      <v-spacer></v-spacer>

      <v-btn color="green darken-1" text @click="dialog = false">닫기</v-btn>

      <v-btn color="green darken-1" text @click="dialog = false"
        >신청하기</v-btn
      >
    </v-card-actions>
  </v-card>
</template>
<script>
export default {
  data() {
    return {
      dialog: false,
    };
  },
};
</script>
