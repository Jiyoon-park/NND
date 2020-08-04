<template>
  <v-container fluid>
    <v-flex xs12 md6 offset-sm3>
      <v-card outlined class="elevation-3">
        <v-list-item class="mt-3">
          <v-avatar color="indigo" class="mr-5">
            <v-icon dark>mdi-account-circle</v-icon>
          </v-avatar>

          <v-list-item-content>
            <span class="grey--text">{{ teaminfo.email }}</span>
            <div class="text-right">
              <v-chip
                class="ma-2"
                color="indigo"
                text-color="white"
                v-for="stack in JSON.parse(stacks)"
                :key="stack"
              >{{ stack }}</v-chip>
            </div>
          </v-list-item-content>
        </v-list-item>
        <v-card-title>
          <span>{{ teaminfo.title }}</span>
        </v-card-title>
        <v-expansion-panels class="elevation-0 mt-5">
          <v-expansion-panel>
            <v-expansion-panel-header></v-expansion-panel-header>
            <v-expansion-panel-content>{{ teaminfo.content }}</v-expansion-panel-content>
            <v-expansion-panel-content>{{ teaminfo.kakaoLink }}</v-expansion-panel-content>
            <v-card-actions>
              <v-btn icon color="pink" v-if="!favorite" @click="addFavorite">
                <v-icon>mdi-star-outline</v-icon>
              </v-btn>
              <v-btn icon color="pink" v-if="favorite" @click="delFavorite">
                <v-icon>mdi-star</v-icon>
              </v-btn>
              <v-spacer></v-spacer>
              <v-btn color="green darken-1" text @click="dialog = !dialog" right>신청하기</v-btn>
            </v-card-actions>
          </v-expansion-panel>
        </v-expansion-panels>

        <v-expand-transition>
          <div v-show="show">
            <v-divider />
            <v-dialog v-model="dialog" max-width="600px">
              <v-card>
                <v-card-title>
                  <span class="headline">신청 Form</span>
                </v-card-title>
                <v-card-text>
                  <v-container>
                    <v-row>
                      <v-col cols="12">
                        <header>이름*</header>
                        <v-text-field label="이름" required></v-text-field>
                      </v-col>
                      <v-col cols="12">
                        <header>이메일*</header>
                        <v-text-field label="이메일" required></v-text-field>
                      </v-col>
                      <v-col cols="12">
                        <header>사용가능한 기술 스택*</header>
                        <v-checkbox v-model="selected1" label="JAVA" value="JAVA"></v-checkbox>
                        <v-checkbox v-model="selected2" label="C" value="C"></v-checkbox>
                        <v-checkbox v-model="selected3" label="C++" value="C++"></v-checkbox>
                        <v-checkbox v-model="selected4" label="Python" value="Python"></v-checkbox>
                        <v-checkbox v-model="selected5" label="Django" value="Django"></v-checkbox>
                        <v-checkbox v-model="selected6" label="SpringBoot" value="Springboot"></v-checkbox>
                        <v-checkbox v-model="selected7" label="Vue" value="Vue"></v-checkbox>
                      </v-col>
                      <v-col cols="12">
                        <v-textarea
                          name="description"
                          label="자기소개 100자
                        이내로 적어주세요."
                        ></v-textarea>
                      </v-col>
                    </v-row>
                  </v-container>
                  <small>*는 필수입력항목입니다</small>
                </v-card-text>
                <v-card-actions>
                  <v-spacer></v-spacer>
                  <v-btn color="blue darken-1" text @click="dialog = false">취소</v-btn>
                  <v-btn color="blue darken-1" text @click="submit">신청하기</v-btn>
                </v-card-actions>
              </v-card>
            </v-dialog>
          </div>
        </v-expand-transition>
      </v-card>
    </v-flex>
  </v-container>
</template>

<script>
export default {
  name: "NewsFeed2",
  props: ["teaminfo"],

  data() {
    return {
      show: false,
      favorite: false,
      dialog: false,
      selected1: false,
      selected2: false,
      selected3: false,
      selected4: false,
      selected5: false,
      selected6: false,
      selected7: false,
      stacks: this.teaminfo.techStack,
    };
  },
  methods: {
    addFavorite() {
      this.favorite = true;
      alert("즐겨찾기에 등록되었습니다.");
    },
    delFavorite() {
      this.favorite = false;
    },
    submit() {
      this.dialog = false;
      alert("신청되었습니다.");
    },
  },
};
</script>
