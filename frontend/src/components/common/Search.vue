<template>
  <v-dialog v-model="dialog" fullscreen hide-overlay>
    <template v-slot:activator="{ on, attrs }">
      <i
        class="fas fa-search"
        @click="dialog = true"
        dark
        v-bind="attrs"
        v-on="on"
      ></i>
    </template>
    <v-card>
      <v-toolbar dark color="purple lighten-2">
        <v-btn icon dark @click="dialog = false">
          <v-icon>mdi-close</v-icon>
        </v-btn>
        <v-toolbar-title>Search</v-toolbar-title>
        <v-spacer></v-spacer>
      </v-toolbar>
      <v-container>
        <v-list three-line subheader>
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
              color="purple darken-2"
            ></v-combobox>
          </v-list-item>
        </v-list>
        <i class="fas fa-filter ml-3">Filter</i>
        <v-divider></v-divider>
        <v-list three-line subheader>
          <v-subheader>By type:</v-subheader>
          <v-list-item>
            <v-list-item-content class="py-0">
              <div class="d-flex justify-center">
                <v-chip-group
                  v-model="typeSelection"
                  active-class="deep-purple--text text--accent-4"
                  mandatory
                >
                  <v-chip
                    large
                    v-for="type in types"
                    :key="type"
                    :value="type"
                    >{{ type }}</v-chip
                  >
                </v-chip-group>
              </div>
            </v-list-item-content>
          </v-list-item>
        </v-list>
        <v-divider></v-divider>
        <v-list three-line subheader>
          <v-subheader>By Category:</v-subheader>
          <v-list-item>
            <v-list-item-content>
              <div class="d-flex justify-center">
                <v-switch
                  v-model="categorySelection"
                  label="스터디"
                  color="red darken-3"
                  value="스터디"
                ></v-switch>
                <v-switch
                  v-model="categorySelection"
                  label="프로젝트"
                  color="indigo darken-3"
                  value="프로젝트"
                  class="mx-2"
                ></v-switch>
                <v-switch
                  v-model="categorySelection"
                  label="공모전"
                  color="orange darken-3"
                  value="공모전"
                ></v-switch>
                <!-- {{ categorySelection }} -->
              </div>
            </v-list-item-content>
          </v-list-item>
        </v-list>
        <v-divider></v-divider>
        <v-list three-line subheader>
          <v-subheader>By Skills:</v-subheader>
          <v-list-item>
            <v-list-item-content class="py-0">
              <div class="d-flex flex-column justify-center">
                <v-chip-group v-model="skillSelection" multiple column>
                  <v-chip
                    filter
                    outlined
                    v-for="skill in skills"
                    :key="skill"
                    :value="skill"
                    >{{ skill }}</v-chip
                  >
                </v-chip-group>
                <!-- 기술스택 추가하기 구현 못함-->
                <v-combobox
                  v-model="newSkill"
                  label="기술스택 추가"
                  chips
                ></v-combobox>
              </div>
            </v-list-item-content>
          </v-list-item>
        </v-list>
      </v-container>
      <v-card-actions>
        <v-spacer></v-spacer>
        <v-btn color="blue darken-1" text @click="dialog = false">Close</v-btn>
        <v-btn color="blue darken-1" text @click="submit">Search</v-btn>
      </v-card-actions>
    </v-card>
  </v-dialog>
</template>

<script>
// import axios from "axios";
import EventBus from "../../main";

export default {
  data: () => ({
    dialog: false,
    search: [],
    typeSelection: [],
    categorySelection: [],
    skillSelection: [],
    newSkill: "",
    types: ["team", "member"],
    skills: ["Java", "Python", "Spring", "C", "C++", "JavaScript"],
  }),
  methods: {
    submit() {
      EventBus.$emit("search", {
        type: this.typeSelection,
        query: this.search,
        category: this.categorySelection,
        skills: this.skillSelection,
      });
      this.dialog = false;
    },
  },
};
</script>

<style></style>
