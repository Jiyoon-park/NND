<template>
  <div>
    <v-card
      class="mx-auto my-3 py-3"
      max-width="344"
      shaped
      v-for="project in projects"
      :key="project.historyNo"
    >
      <v-list-item three-line>
        <v-list-item-content>
          <v-list-item-title class="headline mb-1">{{ project.projectName }}</v-list-item-title>
          <v-list-item-subtitle>{{ project.summary }}</v-list-item-subtitle>
          <div class="mt-3">링크 : {{ project.gitLink }}</div>
        </v-list-item-content>
      </v-list-item>
    </v-card>
  </div>
</template>

<script>
import axios from "axios";
export default {
  data() {
    return {
      projects: [],
      user: "",
    };
  },
  created() {
    let id = this.$route.params.id //넘겨 받아야함
    axios.get(`http://localhost:8080/member/info/${id}`).then((res) => {
      this.user = res.data;
      console.log(res.data)
      this.profileURL = this.user.profile;
      console.log(this.user.idx)
      axios
        .get(`http://localhost:8080/member/projecthistory/list/${this.user.idx}`)
        .then(({ data }) => {
          this.projects = data;
          console.log(this.projects);
        })
        .catch((err) => {
          console.log(err);
        });
    });
  },
};
</script>

<style>
</style>