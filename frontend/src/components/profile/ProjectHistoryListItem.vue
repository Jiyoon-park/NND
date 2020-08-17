<template>
  <v-list-item three-line style="position:relative;">
    <v-list-item-content>
      <v-list-item-title class="headline mb-1">
        <span>{{ historyinfo.projectName }}</span>
      </v-list-item-title>
      <v-list-item-subtitle>{{ historyinfo.summary }}</v-list-item-subtitle>
      <div class="mt-3">{{ historyinfo.content }}</div>
      <small class="mt-3">
        관련링크
        <br />
        <a :href="historyinfo.gitLink" style="text-decoration:none;">
          {{
          historyinfo.gitLink
          }}
        </a>
      </small>
      <!-- 히스토리 삭제/수정 버튼 -->
      <div v-show="isEditPage && isEdit" style="position:absolute; top:0;right:5px;">
        <v-btn
          x-small
          color="red darken-3"
          dark
          class="mr-1"
          @click="onDeleteBtn(`${historyinfo.historyNo}`)"
        >삭제</v-btn>
        <v-dialog v-model="dialog" persistent max-width="600px">
          <template v-slot:activator="{ on, attrs }">
            <v-btn color="green" dark v-bind="attrs" v-on="on" x-small class="mr-1">수정</v-btn>
          </template>
          <v-card>
            <v-card-title
              class="headline font-weight-regular blue darken-3 white--text"
            >project history</v-card-title>
            <v-form>
              <v-container>
                <v-text-field
                  v-model="historyinfo.projectName"
                  filled
                  label="프로젝트 이름"
                  placeholder="내용을 입력해주세요."
                  required
                ></v-text-field>
                <v-text-field
                  v-model="historyinfo.summary"
                  filled
                  label="프로젝트 한줄 설명"
                  placeholder="내용을 입력해주세요."
                  required
                ></v-text-field>
                <v-combobox
                  v-model="select"
                  :items="items"
                  label="기술스택"
                  multiple
                  small-chips
                  filled
                  required
                ></v-combobox>
                <v-textarea
                  v-model="historyinfo.content"
                  filled
                  label="상세 업무 및 성과"
                  placeholder="내용을 입력해주세요."
                  required
                ></v-textarea>
                <v-text-field
                  v-model="historyinfo.gitLink"
                  filled
                  label="저장소 링크"
                  placeholder="https://github.com/example"
                  required
                ></v-text-field>
              </v-container>
              <v-card-actions>
                <v-btn
                  color="blue darken-3"
                  class="font-weight-bold"
                  text
                  @click="dialog = false"
                >닫기</v-btn>
                <v-spacer></v-spacer>
                <v-btn
                  color="blue darken-3"
                  class="font-weight-bold"
                  text
                  @click="onEditBtn(`${historyinfo.historyNo}`)"
                >수정</v-btn>
              </v-card-actions>
            </v-form>
          </v-card>
        </v-dialog>
      </div>
    </v-list-item-content>
  </v-list-item>
</template>

<script>
import axios from "axios";
//import EventBus from "../../main";
export default {
  props: {
    historyinfo: {
      type: Object,
    },
    isEdit: {
      type: Boolean,
    },
  },
  data() {
    return {
      dialog: false,
      isEditPage: true,
      select: [],
    };
  },
  created() {
    console.log(this.historyinfo.idx);
    if (this.$route.path == "/profile-update") {
      this.isEditPage = true;
    } else {
      this.isEditPage = false;
    }
    this.select = JSON.parse(this.historyinfo.usedStack);
  },
  methods: {
    onDeleteBtn(projecthistoryNo) {
      let token = window.$cookies.get("nnd");

      axios
        .delete(
          `${process.env.VUE_APP_API_URL}/projecthistory/delete/${projecthistoryNo}`,
          {
            headers: {
              Authorization: "Bearer " + token.data, // the token is a variable which holds the token
            },
          }
        )
        .then((res) => {
          console.log(res);
          location.reload();
          //EventBus.$emit('delete-card');          //location.reload()
        });
    },
    onEditBtn(projecthistoryNo) {
      let token = window.$cookies.get("nnd");
      axios
        .post(
          `${process.env.VUE_APP_API_URL}/projecthistory/update/${projecthistoryNo}`,
          {
            idx: this.historyinfo.idx,
            projectName: this.historyinfo.projectName,
            summary: this.historyinfo.summary,
            content: this.historyinfo.content,
            usedStack: JSON.stringify(this.select),
            gitLink: this.historyinfo.gitLink,
          },
          {
            headers: {
              Authorization: "Bearer " + token.data, // the token is a variable which holds the token
            },
          }
        )
        .then((response) => {
          console.log(response);
          this.dialog = false;
        })
        .catch((error) => {
          console.log(error.response);
          this.dialog = false;
        });
    },
  },
};
</script>

<style></style>