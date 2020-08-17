<template>
  <v-card>
    <vue-good-table
      :columns="columns"
      :rows="members"
      :sort-options="{
        enabled: false,
      }"
    >
      <template slot="table-row" slot-scope="props">
        <span v-if="props.column.field === 'survey'">
          <v-btn v-if="props.row.memberIdx == myidx" color="#0277BD" disabled>평가하기</v-btn>
          <v-btn
            v-else
            dark
            color="#0277BD"
            @click="goSurvey(props)"
            :disabled="btnStatus[props.index]"
          >평가하기</v-btn>
        </span>
      </template>
    </vue-good-table>
    <v-dialog v-model="dialog" persistent max-width="400">
      <v-card>
        <div class="d-flex flex-column align-center py-3">
          <v-card-title class="h5 pb-2 font-weight-bold">팀워크</v-card-title>
          <div>
            <v-rating color="indigo" medium v-model="teamworkship"></v-rating>
          </div>
          <v-card-title class="h5 pb-2 mt-2 font-weight-bold">만족도</v-card-title>
          <div>
            <v-rating color="indigo" medium v-model="satisfaction"></v-rating>
          </div>
        </div>
        <v-card-actions>
          <v-btn color="#3949AB" class="font-weight-bold" text @click="dialog = false">취소</v-btn>
          <v-spacer></v-spacer>
          <v-btn color="#3949AB" class="font-weight-bold" text @click="sendRating">확인</v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>
  </v-card>
</template>

<script>
import "vue-good-table/dist/vue-good-table.css";
import { VueGoodTable } from "vue-good-table";
import axios from "axios";

export default {
  data() {
    return {
      teamboardno: "",
      members: [],
      dialog: false,
      teamworkship: 0,
      satisfaction: 0,
      nowNum: 0,
      btnStatus: [false, false, false, false, false, false],
      columns: [
        {
          label: "역할",
          field: "master",
        },
        {
          label: "이름",
          field: "memberName",
        },
        {
          label: "평가",
          field: "survey",
        },
      ],
    };
  },
  components: {
    VueGoodTable,
  },
  created() {
    this.teamboardno = this.$store.state.teamNo;
    console.log("스토어 오는지 확인");
    console.log(this.teamboardno);
    console.log(this.$store.state.teammembers);
    this.members = this.$store.state.teammembers;
    console.log("마스터idx");
    console.log(this.$store.state.teamMaster);
    this.checkMaster();
    console.log("함수끝");
    console.log(this.members);

    let token = window.$cookies.get("nnd");
    if (token) {
      this.myidx = token.object.idx;
    }
  },

  methods: {
    goSurvey(props) {
      this.dialog = true;
      console.log("props찍어보자");
      console.log(props);
      this.nowNum = props.index;
    },
    sendRating() {
      this.dialog = false;
      console.log(this.satisfaction);
      console.log(this.teamworkship);
      axios
        .put(`${process.env.VUE_APP_API_URL}/teammenu/rating`, {
          ratingN0: "",
          idx: this.nowNum,
          commitCnt: 4,
          issueCnt: 3,
          attendRate: 4,
          satisfaction: this.satisfaction,
          teamworkship: this.teamworkship,
        })
        .then(() => {
          this.btnStatus[this.nowNum] = true;
          console.log("btnStatus찍어보자");
          console.log(this.btnStatus);
          this.isDisabled;
        });
    },
    checkMaster() {
      var list = this.members;
      console.log("list를 찍어보자");
      for (var i = 0; i < list.length; i++) {
        if (this.$store.state.teamMaster == list[i].memberIdx) {
          list[i].master = "팀장";
        } else {
          list[i].master = "팀원";
        }
      }
      console.log(list);
      this.members = list;
    },
  },
  // computed: {
  //   isDisabled() {
  //     if (this.btnStatus[this.nowNum]) {
  //       console.log("이거 언제찏힘1");
  //       console.log(this.btnStatus[this.nowNum]);
  //       return true;
  //     } else {
  //       console.log("이거 언제찏힘2");
  //       console.log(this.btnStatus[this.nowNum]);

  //       return false;
  //     }
  //   },
  // },
};
</script>

<style></style>
