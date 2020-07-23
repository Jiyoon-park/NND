<template>
  <div>
    <v-layout>
      <v-flex xs12 sm6 offset-sm3>
        <v-card>
          <v-card-title>
            <div>
              <v-icon class="mr-5">mdi-account-circle</v-icon>
              <span class="grey--text">User Name</span>
              <br />
              <span>공모전 팀원 구합니다</span>
            </div>
          </v-card-title>
          <v-card-subtitle>
            <span>같이 javascript 및 jQuery 관련 프로젝트 팀원 구합니다</span>
          </v-card-subtitle>
          <v-card-actions>
            <v-btn text color="gray" @click.stop="dialog = true"
              >상세정보</v-btn
            >
            <v-dialog v-model="dialog" max-width="500">
              <v-card>
                <v-card-title class="headline"
                  >공모전 팀원 구합니다</v-card-title
                >
                <v-card-text>
                  같이 javascript 및 jQuery 관련 프로젝트 팀원 구합니다.<br />
                  프론트쪽 사람은 마무리되었습니다. <br />
                  문의사항있으시면 아래 링크로 질문해주시면 고맙겠습니다.<br />
                </v-card-text>
                <v-card-text>
                  오픈카톡방 :
                  <!-- <div v-bind: href="https://www.google/com">KAKAO!!</div> -->
                </v-card-text>
                <v-card-actions>
                  <v-spacer></v-spacer>

                  <v-btn color="green darken-1" text @click="dialog = false"
                    >닫기</v-btn
                  >

                  <v-btn color="green darken-1" text @click="dialog2 = !dialog2"
                    >신청하기</v-btn
                  >
                </v-card-actions>
              </v-card>
            </v-dialog>
            <v-dialog v-model="dialog2" max-width="600px">
              <v-card>
                <v-card-title>
                  <span class="headline">신청 Form</span>
                </v-card-title>
                <v-card-text>
                  <v-container>
                    <v-row>
                      <v-col cols="12" sm="6" md="4">
                        <v-text-field
                          label="Legal first name*"
                          required
                        ></v-text-field>
                      </v-col>
                      <v-col cols="12" sm="6" md="4">
                        <v-text-field
                          label="Legal middle name"
                          hint="example of helper text only on focus"
                        ></v-text-field>
                      </v-col>
                      <v-col cols="12" sm="6" md="4">
                        <v-text-field
                          label="Legal last name*"
                          hint="example of persistent helper text"
                          persistent-hint
                          required
                        ></v-text-field>
                      </v-col>
                      <v-col cols="12">
                        <v-text-field label="Email*" required></v-text-field>
                      </v-col>
                      <v-col cols="12">
                        <v-text-field
                          label="Password*"
                          type="password"
                          required
                        ></v-text-field>
                      </v-col>
                      <v-col cols="12" sm="6">
                        <v-select
                          :items="['0-17', '18-29', '30-54', '54+']"
                          label="Age*"
                          required
                        ></v-select>
                      </v-col>
                      <v-col cols="12" sm="6">
                        <v-autocomplete
                          :items="[
                            'Skiing',
                            'Ice hockey',
                            'Soccer',
                            'Basketball',
                            'Hockey',
                            'Reading',
                            'Writing',
                            'Coding',
                            'Basejump',
                          ]"
                          label="Interests"
                          multiple
                        ></v-autocomplete>
                      </v-col>
                    </v-row>
                  </v-container>
                  <small>*indicates required field</small>
                </v-card-text>
                <v-card-actions>
                  <v-spacer></v-spacer>
                  <v-btn color="blue darken-1" text @click="dialog = false"
                    >Close</v-btn
                  >
                  <v-btn color="blue darken-1" text @click="dialog = false"
                    >Save</v-btn
                  >
                </v-card-actions>
              </v-card>
            </v-dialog>

            <v-btn text color="orange" v-if="!favorite" @click="addFavorite"
              >즐겨찾기 추가</v-btn
            >
            <v-btn text color="red" v-if="favorite" @click="delFavorite"
              >즐겨찾기 제거</v-btn
            >
          </v-card-actions>
        </v-card>
      </v-flex>
    </v-layout>
  </div>
</template>

<script>
export default {
  data() {
    return {
      favorite: false,
      dialog: false,
      dialog2: false,
    };
  },
  methods: {
    addFavorite() {
      this.favorite = true;
    },
    delFavorite() {
      this.favorite = false;
    },
  },
};
</script>
