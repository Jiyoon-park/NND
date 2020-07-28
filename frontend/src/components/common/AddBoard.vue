<template>
  <v-app>
    <NavBar />
    <v-main>
      <v-row justify="center">
        <v-col cols="8">
          <v-card class="mt-5">
            <h2 class="tc">팀 등록</h2>
            <v-card-text>
              <v-text-field label="팀이름" required></v-text-field>
              <v-text-field label="제목" required></v-text-field>
              <v-textarea label="내용"></v-textarea>
            </v-card-text>
            <v-col cols="12" sm="12">
              <p>팀원수</p>

              <v-overflow-btn class="my-2" :items="dropdown_font" label="선택하세요"></v-overflow-btn>
            </v-col>
            <v-col sm="12" md="12">
              <p>마감일</p>
              <v-menu
                ref="menu"
                v-model="menu"
                :close-on-content-click="false"
                :return-value.sync="date"
                transition="scale-transition"
                offset-y
                min-width="290px"
              >
                <template v-slot:activator="{ on, attrs }">
                  <v-text-field v-model="date" readonly v-bind="attrs" v-on="on"></v-text-field>
                </template>
                <v-date-picker v-model="date" no-title scrollable>
                  <v-spacer></v-spacer>
                  <v-btn text color="primary" @click="menu = false">Cancel</v-btn>
                  <v-btn text color="primary" @click="$refs.menu.save(date)">OK</v-btn>
                </v-date-picker>
              </v-menu>
            </v-col>
            <v-col sm="12" md="12">
              <v-combobox
                v-model="model"
                :items="items"
                :search-input.sync="search"
                hide-selected
                label="기술 스택"
                multiple
                persistent-hint
                small-chips
              >
                <!--
                <template v-slot:no-data>
                  <v-list-item>
                    <v-list-item-content>
                      <v-list-item-title>
                        No results matching "
                        <strong>{{ search }}</strong>". Press
                        <kbd>enter</kbd> to create a new one
                      </v-list-item-title>
                    </v-list-item-content>
                  </v-list-item>
                </template>
                -->
              </v-combobox>
              <v-card-actions>
                <v-spacer></v-spacer>
                <v-btn text>취소</v-btn>
                <v-btn text color="primary">등록</v-btn>
              </v-card-actions>
            </v-col>
          </v-card>
        </v-col>
      </v-row>
    </v-main>
  </v-app>
</template>

<script>
import NavBar from "../../components/common/NavBar.vue";

export default {
  data: () => ({
    date: new Date().toISOString().substr(0, 10),
    menu: false,
    model: false,
    menu2: false,
    dropdown_font: ["3", "4", "5", "6"],
    items: ["Gaming", "Programming", "Vue", "Vuetify"],
  }),
  components: {
    NavBar,
  },
};
</script>

<style>
.tc {
  text-align: center;
}
</style>