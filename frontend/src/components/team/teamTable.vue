<template>
  <v-card class="ml-10 mt-10">
    <vue-good-table
      :columns="columns"
      :rows="rows"
      :sort-options="{
        enabled: false,
      }"
    >
      <template slot="table-row" slot-scope="props">
        <span v-if="props.column.field === 'survey'">
          <v-btn color="primary" @click="goSurvey">평가하기</v-btn>
        </span>
      </template>
    </vue-good-table>
  </v-card>
</template>

<script>
import "vue-good-table/dist/vue-good-table.css";
import { VueGoodTable } from "vue-good-table";

export default {
  data() {
    return {
      columns: [
        {
          label: "역할",
          field: "position",
        },
        {
          label: "이름",
          field: "name",
        },
        {
          label: "평가",
          field: "survey",
        },
      ],
      rows: [
        { position: "팀장", name: "John", survey: "평가" },
        { position: "팀원", name: "Mike", survey: "평가" },
        { position: "팀원", name: "Mike", survey: "평가" },
        { position: "팀원", name: "Mike", survey: "평가" },
        { position: "팀원", name: "Mike", survey: "평가" },
      ],
    };
  },
  components: {
    VueGoodTable,
  },
  created() {
    let teamboardno = this.$route.params.teamboardno;
    console.log(teamboardno);
  },
  methods: {
    goSurvey() {
      //axios.get(`${process.env.VUE_APP_API_URL}/teammenu/member/` + 1);
    },
  },
};
</script>

<style></style>
