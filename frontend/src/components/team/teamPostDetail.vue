<template>
  <v-card>
    <v-toolbar dark color="purple lighten-2">
      <v-btn icon dark @click="changeDialog">
        <v-icon>mdi-close</v-icon>
      </v-btn>
    </v-toolbar>
    <v-card-text class="mt-5">
      <div class="d-flex align-center">
        <div>
          <v-avatar color="grey" size="48">
            <span v-if="!userinfo.profile" class="white--text headline"></span>
            <img v-else :src="userinfo.profile" />
          </v-avatar>
        </div>
        <div class="ml-3">
          <p class="mb-1">{{ postinfo.writer }}</p>
          <!-- <p class="mb-1">{{ letterinfo.createDate }}</p> -->
          <p class="mb-1">
            {{ $moment(postinfo.createdate).format("YYYY-MM-DD") }}
          </p>
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
      <v-dialog v-model="updateDialog" persistent max-width="600px">
        <template v-slot:activator="{ on, attrs }">
          <v-btn
            v-if="postinfo.memberIdx == userinfo.idx"
            color="green darken-1"
            v-bind="attrs"
            v-on="on"
            text
            >수정하기</v-btn
          >
        </template>
        <v-card>
          <v-card-title>
            <span class="headline">게시판 글쓰기 수정</span>
          </v-card-title>
          <v-card-text>
            <v-container>
              <v-row>
                <v-col cols="12">
                  <v-text-field
                    label="제목*"
                    required
                    v-model="title"
                  ></v-text-field>
                </v-col>
                <v-col cols="12">
                  <v-textarea
                    label="내용*"
                    required
                    v-model="content"
                  ></v-textarea>
                </v-col>
                <v-col cols="12" sm="6">
                  <v-select
                    :items="['공지']"
                    label="공지"
                    v-model="notice"
                  ></v-select>
                </v-col>
              </v-row>
            </v-container>
            <small>*꼭 필요한 항목 표시</small>
          </v-card-text>
          <v-card-actions>
            <v-spacer></v-spacer>
            <v-btn color="blue darken-1" text @click="updateDialog = false"
              >닫기</v-btn
            >
            <v-btn color="blue darken-1" text @click="postUpdate()">수정</v-btn>
          </v-card-actions>
        </v-card>
      </v-dialog>
      <v-btn
        v-if="postinfo.memberIdx == userinfo.idx"
        color="green darken-1"
        text
        @click="postDelete()"
        >삭제하기</v-btn
      >
    </v-card-actions>
  </v-card>
</template>

<script>
import axios from "axios";

export default {
  components: {},
  data() {
    return {
      updateDialog: false,
      title: this.postinfo.title,
      content: this.postinfo.content,
      notice: this.postinfo.notice,
    };
  },
  props: {
    postinfo: {
      type: Object,
    },
    dialog: {
      type: Boolean,
    },
    userinfo: {
      type: Object,
    },
  },
  // 수정 삭제 메소드
  methods: {
    postUpdate() {
      this.updateDialog = false;
      axios
        .post(`http://localhost:8080/teammenu/post/update`, {
          teamPostNo: this.postinfo.teamPostNo,
          teamboardNo: this.postinfo.teamboardNo,
          memberIdx: this.userinfo.idx,
          writer: this.userinfo.name,
          title: this.title,
          content: this.content,
          notice: this.notice,
          createDate: "",
        })
        .then((res) => {
          console.log(res.data);
          this.$emit("changeDialog");
          alert("성공");
        })
        .catch((err) => {
          console.log(err.respose);
          alert("수정실패");
        });
    },
    postDelete() {
      axios
        .delete(
          `http://localhost:8080/teammenu/post/delete/${this.postinfo.teamPostNo}`
        )
        .then((response) => {
          console.log(response);
          this.$emit("changeDialog");
          alert("성공");
        })
        .catch((err) => {
          console.log(err.response);
          alert("실패");
        });
    },
    changeDialog() {
      this.$emit("changeDialog");
    },
  },
};
</script>

<style></style>
