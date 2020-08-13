<template>
  <div>
    <v-card
      class="mx-auto my-3 py-3"
      max-width="344"
      v-for="(project, i) in projects"
      :key="i"
    >
      <ProjectHistoryListItem :historyinfo="projects[i]" :isEdit="isEdit" />
    </v-card>
  </div>
</template>

<script>
import ProjectHistoryListItem from "./ProjectHistoryListItem.vue";
import axios from "axios";
import { EventBus } from "../../main";

export default {
  components: {
    ProjectHistoryListItem,
  },
  props: {
    isEdit: {
      type: Boolean,
    },
  },
  data() {
    return {
      projects: [],
      user: "",
      dialog: false,
    };
  },
  created() {
    let token = window.$cookies.get("nnd"); //nnd가 key인 쿠키 가져옴
    if (token) {
      //토큰 존재하면
      this.user = token.object;
      console.log("###########");
      console.log(this.user.idx);
      axios
        .get(`http://localhost:8080/projecthistory/list/${this.user.idx}`, {
          headers: {
            Authorization: "Bearer " + token.data, // the token is a variable which holds the token
          },
        })
        .then(({ data }) => {
          this.projects = data;
          console.log(this.projects);
        })
        .catch((err) => {
          console.log(err);
        });
    }

    // EventBus.$on('delete-card',() =>{
    //    axios
    //     .get(`http://localhost:8080/projecthistory/list/${this.user.idx}`)
    //     .then(({ data }) => {
    //       this.projects = data;
    //       console.log(this.projects);
    //     })
    //     .catch((err) => {
    //       console.log(err);
    //     });
    // });
    EventBus.$on("create-card", () => {
      axios
        .get(`http://localhost:8080/projecthistory/list/${this.user.idx}`, {
          headers: {
            Authorization: "Bearer " + token.data, // the token is a variable which holds the token
          },
        })
        .then(({ data }) => {
          this.projects = data;
          console.log(this.projects);
        })
        .catch((err) => {
          console.log(err);
        });
    });
  },
  methods: {
    onDeleteBtn(projecthistoryNo) {
      let token = window.$cookies.get("nnd"); //nnd가 key인 쿠키 가져옴
      axios
        .delete(
          `http://localhost:8080/projecthistory/delete/${projecthistoryNo}`,
          {
            headers: {
              Authorization: "Bearer " + token.data, // the token is a variable which holds the token
            },
          }
        )
        .then((res) => console.log(res));
    },
    onEditBtn(projecthistoryNo, i) {
      let token = window.$cookies.get("nnd"); //nnd가 key인 쿠키 가져옴
      axios
        .post(
          `http://localhost:8080/projecthistory/update/${projecthistoryNo}`,

          {
            idx: this.projects[i].idx,
            projectName: this.projects[i].projectName,
            summary: this.projects[i].summary,
            content: this.projects[i].content,
            usedStack: this.projects[i].usedStack,
            gitLink: this.projects[i].gitLink,
            techStack: JSON.stringify(this.projects[i].techStack),
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
