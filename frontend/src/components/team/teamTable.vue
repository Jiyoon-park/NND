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
          <v-btn
            v-if="props.row.memberIdx == myidx || props.row.rated == '1'"
            color="primary"
            disabled
            >평가하기</v-btn
          >
          <member-btn v-else :props="props"></member-btn>
        </span>
      </template>
    </vue-good-table>
  </v-card>
</template>

<script>
import "vue-good-table/dist/vue-good-table.css";
import { VueGoodTable } from "vue-good-table";
import MemberBtn from "./memberBtn.vue";
//import axios from "axios";

export default {
  props: ["teamdata"],

  data() {
    return {
      teamboardno: "",
      members: [],
      dialog: false,
      teamworkship: 0,
      satisfaction: 0,
      nowNum: 0,
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
    MemberBtn,
  },
  created() {
    this.teamboardno = this.$store.state.teamNo;
    this.members = this.$store.state.teammembers;
    this.checkMaster();

    let token = window.$cookies.get("nnd");
    if (token) {
      this.myidx = token.object.idx;
    }
  },
  watch: {
    teamdata: function() {
      this.teamboardno = this.$store.state.teamNo;
      this.members = this.$store.state.teammembers;
      this.checkMaster();
    },
  },
  methods: {
    checkMaster() {
      var list = this.members;
      for (var i = 0; i < list.length; i++) {
        if (this.$store.state.teamMaster == list[i].memberIdx) {
          list[i].master = "팀장";
        } else {
          list[i].master = "팀원";
        }
      }
      this.members = list;
    },
  },
};
</script>

<style></style>
