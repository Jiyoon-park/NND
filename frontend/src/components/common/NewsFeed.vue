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
            <span>같이 javascript 및 jQuery 관련 프로젝트할 팀원 구합니다</span>
          </v-card-subtitle>
          <v-card-actions>
            <div>
              <v-btn v-if="!favorite" @click="addFavorite" outlined fab x-small max-width="50px" >
                <v-img src="../../assets/images/star(blank).png" max-width="40px"/>
              </v-btn>
              <v-btn v-if="favorite" @click="delFavorite" outlined fab x-small max-width="50px">
                <v-img src="../../assets/images/star(full).png" max-width="40px"/>
              </v-btn>
            </div>
            <div>
              <v-btn depressed @click.stop="dialog = true" right>상세정보</v-btn>
            </div>
            <v-dialog v-model="dialog" max-width="500">
              <v-card>
                <v-card-title class="headline">공모전 팀원 구합니다</v-card-title>
                <v-card-text>
                  같이 javascript 및 jQuery 관련 프로젝트 팀원 구합니다.
                  <br />프론트쪽 사람은 마무리되었습니다.
                  <br />문의사항있으시면 아래 링크로 질문해주시면 고맙겠습니다.
                  <br />
                </v-card-text>
                <v-card-text>오픈카톡방 : KAKAO!!</v-card-text>
                <v-card-actions>
                  <v-spacer></v-spacer>

                  <v-btn color="green darken-1" text @click="dialog = false">닫기</v-btn>

                  <v-btn color="green darken-1" text @click="dialog2 = !dialog2" right>신청하기</v-btn>
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
                  <v-btn color="blue darken-1" text @click="dialog2 = false">취소</v-btn>
                  <v-btn color="blue darken-1" text @click="dialog2 = false">신청하기</v-btn>
                </v-card-actions>
              </v-card>
            </v-dialog>
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
      selected1: false,
      selected2: false,
      selected3: false,
      selected4: false,
      selected5: false,
      selected6: false,
      selected7: false
    };
  },
  methods: {
    addFavorite() {
      this.favorite = true;
    },
    delFavorite() {
      this.favorite = false;
    }
  }
};
</script>
