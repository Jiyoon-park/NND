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
              <br />
              <span>같이 javascript 및 jQuery 관련 프로젝트 팀원 구합니다</span>
            </div>
          </v-card-title>
          <v-card-actions>
            <v-btn text color="gray" @click.stop="dialog = true">상세정보</v-btn>
            <v-dialog v-model="dialog" max-width="500">
              <v-card>
                <v-card-title class="headline">공모전 팀원 구합니다</v-card-title>

                <v-card-text>
                  같이 javascript 및 jQuery 관련 프로젝트 팀원 구합니다.<br/>
                  프론트쪽 사람은 마무리되었습니다. <br/>
                  문의사항있으시면 아래 링크로 질문해주시면 고맙겠습니다.<br/>
                </v-card-text>

                <v-card-text>
                  오픈카톡방 :
                  <!-- <div v-bind: href="https://www.google/com">KAKAO!!</div> -->
                </v-card-text>

                <v-card-actions>
                  <v-spacer></v-spacer>

                  <v-btn color="green darken-1" text @click="dialog = false">닫기</v-btn>

                  <v-btn color="green darken-1" text @click="dialog = false">신청하기</v-btn>
                </v-card-actions>
              </v-card>
            </v-dialog>

            <v-btn text color="orange" v-if="!favorite" @click="addFavorite">즐겨찾기 추가</v-btn>
            <v-btn text color="red" v-if="favorite" @click="delFavorite">즐겨찾기 제거</v-btn>
          </v-card-actions>
        </v-card>
      </v-flex>
    </v-layout>
  </div>
</template>

<script>
export default {
  data () {
    return {
      favorite: false,
      dialog: false,
    };
  },
  methods: {
    addFavorite() {
      this.favorite = true
    },
    delFavorite() {
      this.favorite = false
    },
  }
};
</script>