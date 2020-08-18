<template>
  <div style="position:relative;">
    <div
      v-if="this.teampost.length == 0"
      class="py-5"
      style="border-radius:5px; background-color:#fff;"
    >
      <p class="mb-0 text-center">작성된 글이 없습니다.</p>
    </div>

    <v-data-table
      v-else
      :headers="headers"
      :items="teampost"
      :search="search"
      :sort-by="['num']"
      :sort-desc="[true]"
      :items-per-page="5"
      class="elevation-1"
      mobile-breakpoint="0"
    >
      <template v-slot:top>
        <v-toolbar flat color="white">
          <v-spacer></v-spacer>
          <v-text-field
            v-model="search"
            append-icon="mdi-magnify"
            label="검색"
            single-line
            hide-details
          ></v-text-field>
          <v-spacer></v-spacer>
        </v-toolbar>
      </template>
      <template v-slot:item.actions="{ item }">
        <v-icon small class="mr-2" @click="editItem(item)">mdi-pencil</v-icon>
        <v-icon small @click="deleteItem(item)">mdi-delete</v-icon>
      </template>
    </v-data-table>
    <v-dialog v-model="dialog" max-width="500px">
      <template v-slot:activator="{ on, attrs }">
        <v-btn
          color="#0277BD"
          dark
          class="mb-2"
          v-bind="attrs"
          v-on="on"
          style="position:absolute; top:-55px; right:0px;"
        >글작성</v-btn>
      </template>
      <v-card>
        <v-toolbar dark flat color="#0277BD">
          <v-toolbar-title>{{ formTitle }}</v-toolbar-title>
          <v-spacer></v-spacer>
          <!-- <v-icon small @click="deleteItem(item)">mdi-delete</v-icon> -->
        </v-toolbar>

        <v-container>
          <v-card-text>
            <v-row>
              <v-col cols="12" class="py-0">
                <span class="subheader">✔ 제목</span>
                <v-text-field class="mt-2" filled label="제목" required v-model="editedItem.title"></v-text-field>
              </v-col>
              <v-col cols="12" class="py-0">
                <span class="subheader">✔ 내용</span>
                <v-textarea class="mt-2" filled label="내용" required v-model="editedItem.content"></v-textarea>
              </v-col>
            </v-row>
          </v-card-text>
        </v-container>
        <v-card-actions>
          <v-btn color="#0277BD" text @click="close">닫기</v-btn>
          <v-spacer></v-spacer>
          <v-btn color="#0277BD" text @click="save" :disabled="status">저장</v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>
  </div>
</template>

<script>
import axios from "axios";

export default {
  data: () => ({
    dialog: false,
    search: "",
    checkNum: 1,
    postSize: 0,
    status: false,
    myIdx: "",
    headers: [
      {
        text: "번호",
        align: "start",
        value: "num",
        sortable: false,
      },
      {
        text: "제목",
        // align: "start",
        value: "title",
      },
      { text: "이름", value: "writer" },
      { text: "상세보기/삭제", value: "actions", sortable: false },
    ],
    teampost: [],

    editedIndex: -1,
    editedItem: {
      content: "",
      writer: "",
      title: "",
    },
    defaultItem: {
      title: "",
      writer: "",
      memberIdx: "",
      notice: 0,
    },
  }),

  props: {
    userinfo: {
      type: Object,
    },
    teaminfo: {
      type: Object,
    },
  },
  computed: {
    formTitle() {
      return this.editedIndex === -1 ? "게시글 작성" : "상세보기";
    },
  },

  watch: {
    dialog(val) {
      val || this.close();
    },
  },
  created() {
    this.showPost();
  },

  methods: {
    showPost() {
      let token = window.$cookies.get("nnd");

      axios
        .get(
          `${process.env.VUE_APP_API_URL}/teammenu/post/` +
            this.$store.state.teamNo,
          {
            headers: {
              Authorization: "Bearer " + token.data, // the token is a variable which holds the token
            },
          }
        )
        .then((res) => {
          this.teampost = res.data;
          this.checkNum = this.teampost.length - 1;
          this.postSize = this.teampost.length;

          for (var i = this.teampost.length - 1; i >= 0; i--) {
            this.teampost[this.teampost.length - i - 1].num = this.checkNum--;
          }
        })
        .catch((err) => {
          console.log(err);
        });
    },

    editItem(item) {
      if (this.$store.state.myToken.idx == item.memberIdx) {
        this.status = false;
      } else {
        this.status = true;
      }
      this.editedIndex = this.teampost.indexOf(item);
      this.editedItem = Object.assign({}, item); //병합
      this.dialog = true;
    },

    deleteItem(item) {
      let token = window.$cookies.get("nnd");

      const index = this.teampost.indexOf(item);
      confirm("삭제하시겠습니까?") &&
        this.teampost.splice(index, 1) &&
        axios.delete(
          `${process.env.VUE_APP_API_URL}/teammenu/post/delete/` +
            item.teamPostNo,
          {
            headers: {
              Authorization: "Bearer " + token.data, // the token is a variable which holds the token
            },
          }
        );
    },

    close() {
      this.dialog = false;
      this.$nextTick(() => {
        this.editedItem = Object.assign({}, this.defaultItem);
        this.editedIndex = -1;
      });
    },

    save() {
      let token = window.$cookies.get("nnd");

      if (this.editedIndex > -1) {
        Object.assign(this.teampost[this.editedIndex], this.editedItem);
        axios.post(
          `${process.env.VUE_APP_API_URL}/teammenu/post/update`,
          {
            teamboardNo: this.teaminfo.teamboardNo,
            teamPostNo: this.teampost[this.editedIndex].teamPostNo,
            title: this.editedItem.title,
            content: this.editedItem.content,
          },
          {
            headers: {
              Authorization: "Bearer " + token.data, // the token is a variable which holds the token
            },
          }
        );
      } else {
        this.editedItem.writer = this.userinfo.name;
        this.teampost.push(this.editedItem);
        axios.put(
          `${process.env.VUE_APP_API_URL}/teammenu/post/save`,
          {
            teamboardNo: this.teaminfo.teamboardNo,
            memberIdx: this.userinfo.idx,
            writer: this.userinfo.name,
            title: this.editedItem.title,
            content: this.editedItem.content,
            notice: 0,
            createDate: "",
          },
          {
            headers: {
              Authorization: "Bearer " + token.data, // the token is a variable which holds the token
            },
          }
        );
        this.teampost[this.postSize].num = this.postSize++;
      }
      this.close();
    },
  },
};
</script>

<style scoped>
.subheader {
  padding: 1px 0;
  background-color: #eeeeee;
  font-style: italic;
}
</style>
