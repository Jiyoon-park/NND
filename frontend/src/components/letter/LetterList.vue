<template>
  <div>
    <NavBar />
    <div class="mt-13">
      <v-tabs fixed-tabs v-model="tab" color="purple">
        <v-tab v-for="item in items" :key="item.tab">{{ item.tab }}</v-tab>
      </v-tabs>
      <v-tabs-slider color="yellow"></v-tabs-slider>
      <v-tabs-items v-model="tab">
        <v-tab-item v-for="item in items" :key="item.tab">
          <v-card flat>
            <v-list>
              <v-list-item-group>
                <v-dialog v-model="dialog" persistent max-width="290">
                  <template v-slot:activator="{ on, attrs }">
                    <!-- 리스트 하나하나 -->
                    <v-list-item
                      v-for="(item, i) in items"
                      :key="i"
                      class="letter py-3"
                      v-bind="attrs"
                      v-on="on"
                    >
                      <div class="mr-4">
                        <v-avatar color="grey" size="60">
                          <span class="white--text headline">user</span>
                        </v-avatar>
                      </div>
                      <v-list-item-content>
                        <div>
                          <v-list-item-text v-text="item.letter.username" class="font-weight-black"></v-list-item-text>
                          <i
                            class="fas fa-envelope float-right"
                            style="font-size:20px; color: #7E57C2;"
                            v-if="!item.letter.read"
                          ></i>
                          <i
                            class="fas fa-envelope-open-text float-right"
                            style="font-size:20px; color: #BDBDBD;"
                            v-else
                          ></i>
                          <br />
                          <v-list-item-text v-text="item.letter.createdate" class="text--secondary"></v-list-item-text>
                        </div>
                      </v-list-item-content>
                    </v-list-item>
                    <!-- 리스트 하나하나 끝 -->
                  </template>
                  <!-- props로 데이터를 넘겨줘야함 -->
                  <v-card>
                    <v-toolbar dark color="purple lighten-2">
                      <v-btn icon dark @click="dialog = false">
                        <v-icon>mdi-close</v-icon>
                      </v-btn>
                      <v-toolbar-title>{{ item.tab }}</v-toolbar-title>
                      <v-spacer></v-spacer>
                    </v-toolbar>
                    <v-card-text class="mt-5">
                      <p>보낸 사람</p>
                      <p>보낸 날짜</p>
                      <p>{{ item.content }}</p>
                    </v-card-text>
                    <v-divider></v-divider>
                    <v-card-actions>
                      <v-spacer></v-spacer>
                      <v-btn color="green darken-1" text @click="dialog = false">닫기</v-btn>
                      <v-btn color="green darken-1" text @click="dialog = false">수락하기</v-btn>
                    </v-card-actions>
                  </v-card>
                </v-dialog>
              </v-list-item-group>
            </v-list>
          </v-card>
        </v-tab-item>
      </v-tabs-items>
    </div>
  </div>
</template>

<script>
import NavBar from "../common/NavBar.vue";
//import axios from "axios";

export default {
  components: { NavBar },
  data() {
    return {
      tab: null,
      receiveLetter: [],
      sendLetter: [],
      items: [
        {
          tab: "받은 편지함",
          letter: {
            username: "대장",
            content: "백엔드 마스터임당.",
            createdate: "오늘",
            read: false,
          },
        },
        {
          tab: "보낸 편지함",
          letter: {
            username: "팀장",
            content: "프론트엔드 마스터임당.",
            createdate: "8월 1일",
            read: true,
          },
        },
      ],
      dialog: false,
    };
  },
  created() {
    let token = window.$cookies.get("nnd"); //nnd가 key인 쿠키 가져옴
    if (token) {
      console.log("@@@");
      console.log(token);
    }
  },
};
</script>

<style>
.v-list-item:not(.on-hover) {
  opacity: 0.6;
}
</style>