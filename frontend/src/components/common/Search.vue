<template>
  <v-dialog v-model="dialog" width="50%" :fullscreen="$vuetify.breakpoint.mobile" hide-overlay>
    <template v-slot:activator="{ on, attrs }">
      <i
        class="fas fa-search fa-lg"
        style="color:#eeeeee;"
        @click="dialog = true"
        dark
        v-bind="attrs"
        v-on="on"
      ></i>
    </template>
    <v-card>
      <v-toolbar dark flat color="#0277BD">
        <v-toolbar-title>검색창</v-toolbar-title>
        <v-spacer></v-spacer>
      </v-toolbar>
      <v-container style="padding:3%;">
        <v-list three-line>
          <v-list-item>
            <v-combobox
              v-model="search"
              :search="search"
              hide-selected
              label="검색"
              placeholder="검색어를 입력하세요"
              multiple
              persistent-hint
              small-chips
              outlined
              class="mt-5"
              color="#0277BD"
            ></v-combobox>
          </v-list-item>
        </v-list>
        <i class="fas fa-filter ml-3">Filter</i>
        <v-divider></v-divider>
        <v-list three-line>
          <span class="ml-3 subheader">✔ 원하는 타입</span>
          <v-list-item>
            <v-list-item-content class="py-0">
              <div class="d-flex justify-center">
                <v-chip-group
                  v-model="typeSelection"
                  active-class="blue darken-3 white--text text--accent-4"
                  mandatory
                >
                  <v-chip large v-for="type in types" :key="type" :value="type">{{ type }}</v-chip>
                </v-chip-group>
              </div>
            </v-list-item-content>
          </v-list-item>
        </v-list>

        <v-list three-line>
          <span class="ml-3 subheader">✔ 원하는 카테고리</span>
          <v-list-item>
            <v-list-item-content class="py-0">
              <div class="d-flex justify-center">
                <v-checkbox
                  v-model="categorySelection"
                  label="스터디"
                  color="red darken-3"
                  value="스터디"
                ></v-checkbox>
                <v-checkbox
                  v-model="categorySelection"
                  label="프로젝트"
                  color="indigo darken-3"
                  value="프로젝트"
                  class="mx-2"
                ></v-checkbox>
                <v-checkbox
                  v-model="categorySelection"
                  label="공모전"
                  color="orange darken-3"
                  value="공모전"
                ></v-checkbox>
              </div>
            </v-list-item-content>
          </v-list-item>
        </v-list>

        <v-list three-line>
          <span class="ml-3 subheader">✔ 원하는 기술스택</span>
          <v-list-item>
            <v-list-item-content class="py-0">
              <div class="d-flex flex-column justify-center">
                <v-combobox
                  v-model="newSkill"
                  :items="items"
                  label="기술스택 추가"
                  filled
                  multiple
                  dense
                  chips
                  color="#0277BD"
                ></v-combobox>
              </div>
            </v-list-item-content>
          </v-list-item>
        </v-list>
      </v-container>
      <v-divider></v-divider>
      <v-card-actions>
        <v-btn color="#0277BD" text class="font-weight-bold" @click="dialog = false">닫기</v-btn>
        <v-spacer></v-spacer>
        <v-btn color="#0277BD" text class="font-weight-bold" @click="submit">검색</v-btn>
      </v-card-actions>
    </v-card>
  </v-dialog>
</template>

<script>
import { EventBus } from "../../main.js";

export default {
  data: () => ({
    dialog: false,
    search: [],
    typeSelection: [],
    categorySelection: [],
    skillSelection: [],
    newSkill: [],
    types: ["team", "member"],
    items: [
      "C",
      "C++",
      "JAVA",
      "Spring",
      "Django",
      "C#",
      "Go",
      "R",
      "Javascript",
      "vue.js",
      "Linux",
      "MYSQL",
      "Mariadb",
      "RestApi",
      "Python",
      "Pytorch",
      "Tensorflow",
      "Spirngboot",
      "Ruby",
      "React.js",
    ],
    info: {},
    isMobile: false,
  }),
  beforeDestroy() {
    if (typeof window !== "undefined") {
      window.removeEventListener("resize", this.onResize, { passive: true });
    }
  },

  mounted() {
    this.onResize();
    window.addEventListener("resize", this.onResize, { passive: true });
  },
  methods: {
    submit() {
      //console.log(this.search);
      EventBus.$emit("search", {
        typeSelection: this.typeSelection, //팀,팀원
        search: this.search, //검색
        categorySelection: this.categorySelection, //공모전,스터디
        newSkill: this.newSkill,
      });
      this.dialog = false;
    },
    onResize() {
      this.isMobile = window.innerWidth < 600;
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
