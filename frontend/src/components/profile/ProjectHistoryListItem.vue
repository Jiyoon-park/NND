<template>
  <v-list-item three-line>
    <v-list-item-content>
      <v-list-item-title class="headline mb-1">{{ historyinfo.projectName }}</v-list-item-title>
      <v-list-item-subtitle>{{ historyinfo.summary }}</v-list-item-subtitle>
      <div class="mt-3">링크 : {{ historyinfo.gitLink }}</div>
      <div>
        <v-btn @click="onDeleteBtn(`${historyinfo.historyNo}`)">삭제</v-btn>
        <v-dialog v-model="dialog" persistent max-width="600px">
          <template v-slot:activator="{ on, attrs }">
            <v-btn color="green" dark v-bind="attrs" v-on="on" fab small class="mr-1">수정</v-btn>
          </template>
          <v-card>
            <v-card-title
              class="headline font-weight-regular light-green lighten-1 white--text"
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
                <v-text-field
                  v-model="historyinfo.usedStack"
                  filled
                  label="기술 스택"
                  placeholder="기타 기술스택 직접 입력하기"
                  required
                ></v-text-field>
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
                <v-spacer></v-spacer>
                <v-btn color="blue darken-1" text @click="dialog=false">Close</v-btn>
                <v-btn
                  color="blue darken-1"
                  text
                  @click="onEditBtn(`${historyinfo.historyNo}`)"
                >Save</v-btn>
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
  },
  data() {
    return {
      dialog: false,
    };
  },
  created() {
    console.log(this.historyinfo.idx);
  },
  methods: {
    onDeleteBtn(projecthistoryNo) {
          let token = window.$cookies.get('nnd')

      axios
        .delete(
          `${process.env.VUE_APP_API_URL}/projecthistory/delete/${projecthistoryNo}`,
          {
            headers: { 
                      Authorization: "Bearer " + token.data, // the token is a variable which holds the token
             },
          }
        )
        .then((res) =>{ 
          console.log(res)
          location.reload();
          //EventBus.$emit('delete-card');          //location.reload()
          });
    },
    onEditBtn(projecthistoryNo) {
          let token = window.$cookies.get('nnd')
      axios
        .post(
          `${process.env.VUE_APP_API_URL}/projecthistory/update/${projecthistoryNo}`,
         {
            idx: this.historyinfo.idx,
            projectName: this.historyinfo.projectName,
            summary: this.historyinfo.summary,
            content: this.historyinfo.content,
            usedStack: this.historyinfo.usedStack,
            gitLink: this.historyinfo.gitLink,
        },
        {
            headers: { 
          Authorization: "Bearer " + token.data, // the token is a variable which holds the token
         },
        })
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

<style>
</style>