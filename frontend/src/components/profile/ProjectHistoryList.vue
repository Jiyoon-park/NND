<template>
  <v-col cols="12" sm="12" class="px-4 py-4">
    <v-card class="mx-auto mb-3 py-3" v-for="(project, i) in projects" :key="i">
      <ProjectHistoryListItem :historyinfo="projects[i]" :isEdit="isEdit" />
    </v-card>
  </v-col>
</template>

<script>
import ProjectHistoryListItem from "./ProjectHistoryListItem.vue";
import axios from "axios";
import { EventBus } from "../../main";

export default {
  components: {
    ProjectHistoryListItem
  },
  props: {
    isEdit: {
      type: Boolean
    }
  },
  data() {
    return {
      projects: [],
      user: "",
      dialog: false
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
        .get(
          `${process.env.VUE_APP_API_URL}/projecthistory/list/${this.$store.state.profileidx}`,
          {
            headers: {
              Authorization: "Bearer " + token.data // the token is a variable which holds the token
            }
          }
        )
        .then(({ data }) => {
          this.projects = data;
          this.projects.forEach(element => {
            element.content = element.content.replace(/\n/g, "<br>");
          });
        })
        .catch(err => {
          console.log(err);
        });
    }

    // EventBus.$on('delete-card',() =>{
    //    axios
    //     .get(`${process.env.VUE_APP_API_URL}/projecthistory/list/${this.user.idx}`)
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
        .get(
          `${process.env.VUE_APP_API_URL}/projecthistory/list/${this.$store.state.profileidx}`,
          {
            headers: {
              Authorization: "Bearer " + token.data // the token is a variable which holds the token
            }
          }
        )
        .then(({ data }) => {
          this.projects = data;
          console.log(this.projects);
        })
        .catch(err => {
          console.log(err);
        });
    });
  },
  methods: {
    onDeleteBtn(projecthistoryNo) {
      let token = window.$cookies.get("nnd"); //nnd가 key인 쿠키 가져옴
      axios
        .delete(
          `${process.env.VUE_APP_API_URL}/projecthistory/delete/${projecthistoryNo}`,
          {
            headers: {
              Authorization: "Bearer " + token.data // the token is a variable which holds the token
            }
          }
        )
        .then(res => console.log(res));
    },
    onEditBtn(projecthistoryNo, i) {
      let token = window.$cookies.get("nnd"); //nnd가 key인 쿠키 가져옴
      axios
        .post(
          `${process.env.VUE_APP_API_URL}/projecthistory/update/${projecthistoryNo}`,

          {
            idx: this.projects[i].idx,
            projectName: this.projects[i].projectName,
            summary: this.projects[i].summary,
            content: this.projects[i].content,
            usedStack: this.projects[i].usedStack,
            gitLink: this.projects[i].gitLink,
            techStack: JSON.stringify(this.projects[i].techStack)
          },
          {
            headers: {
              Authorization: "Bearer " + token.data // the token is a variable which holds the token
            }
          }
        )
        .then(response => {
          console.log(response);
          this.dialog = false;
        })
        .catch(error => {
          console.log(error.response);
          this.dialog = false;
        });
    }
  }
};
</script>

<style></style>