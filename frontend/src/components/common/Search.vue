<template>
  <v-dialog v-model="dialog" fullscreen hide-overlay>
    <template v-slot:activator="{ on, attrs }">
      <i class="fas fa-search" @click="dialog = true" dark v-bind="attrs" v-on="on"></i>
    </template>
    <v-card>
      <v-toolbar dark color="primary">
        <v-btn icon dark @click="dialog = false">
          <v-icon>mdi-close</v-icon>
        </v-btn>
        <v-toolbar-title></v-toolbar-title>
        <v-spacer></v-spacer>
        <v-toolbar-items>
          <v-btn dark text @click="dialog = false">search</v-btn>
        </v-toolbar-items>
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
                <v-chip large color="primary" outlined pill>
                  member
                  <i class="fas fa-user ml-2"></i>
                </v-chip>
                <v-chip large color="primary" outlined pill class="ml-2">
                  team
                  <i class="fas fa-users ml-2"></i>
                </v-chip>
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
                <v-radio-group row>
                  <v-radio label="스터디" color="red" value="스터디"></v-radio>
                  <v-radio label="프로젝트" color="orange" value="프로젝트"></v-radio>
                  <v-radio label="공모전" color="success" value="공모전"></v-radio>
                </v-radio-group>
              </div>
            </v-list-item-content>
          </v-list-item>
        </v-list>
        <v-divider></v-divider>
        <v-list three-line subheader>
          <v-subheader>By Skills:</v-subheader>
          <v-list-item></v-list-item>
        </v-list>
      </v-container>
    </v-card>
  </v-dialog>
</template>

<script>
export default {
  data: () => ({
    dialog: false,
    search: [],
  }),
};
</script>

<style>
</style>