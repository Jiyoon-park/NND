<template>
  <v-card style="position:relative;">
    <v-list class="transparent">
      <v-row justify="center">
        <v-dialog v-model="dialog" persistent max-width="600px">
          <template v-slot:activator="{ on, attrs }">
            <v-btn
              color="#0277BD"
              dark
              small
              v-bind="attrs"
              v-on="on"
              style="position:absolute; top:-55px; right:-10px;"
              >글쓰기</v-btn
            >
          </template>
          <v-card>
            <v-toolbar dark flat color="#0277BD">
              <v-toolbar-title>게시글 작성</v-toolbar-title>
              <v-spacer></v-spacer>
            </v-toolbar>
            <v-container>
              <div id="map"></div>
              <v-card-text>
                <v-row>
                  <v-col cols="12" class="py-0">
                    <span class="subheader">✔ 제목</span>
                    <v-text-field
                      class="mt-2"
                      filled
                      label="제목"
                      required
                      v-model="title"
                    ></v-text-field>
                  </v-col>
                  <v-col cols="12" class="py-0">
                    <span class="subheader">✔ 내용</span>
                    <v-textarea
                      class="mt-2"
                      filled
                      label="내용"
                      required
                      v-model="content"
                    ></v-textarea>
                  </v-col>
                  <v-col cols="12" class="py-0">
                    <span class="subheader">지도보기</span>
                  </v-col>
                  <v-col cols="12" sm="6" class="py-0">
                    <span class="subheader">게시글 유형</span>
                    <v-select
                      class="mt-2"
                      filled
                      :items="['공지']"
                      label="공지"
                      v-model="notice"
                    ></v-select>
                  </v-col>
                  <v-col cols="12" class="py-0">
                    <small>✔ 필수 작성 항목</small>
                  </v-col>
                </v-row>
              </v-card-text>
            </v-container>

            <v-card-actions>
              <v-btn color="#0277BD" text @click="dialog = false">닫기</v-btn>
              <v-spacer></v-spacer>
              <v-btn color="#0277BD" text @click="addPost()">저장</v-btn>
            </v-card-actions>
          </v-card>
        </v-dialog>
      </v-row>
      <p v-if="this.teamposts.length == 0" class="mb-0 text-center">
        작성된 글이 없습니다.
      </p>
      <v-list-item-group v-else>
        <TeamPostItem
          v-for="(post, i) in teamposts"
          :key="i"
          :postinfo="teamposts[i]"
          :userinfo="userinfo"
        />
      </v-list-item-group>
    </v-list>
  </v-card>
</template>

<script>
import TeamPostItem from "./teamPostItem.vue";
import axios from "axios";

export default {
  name: "TeamPost",
  components: {
    TeamPostItem,
  },
  data() {
    return {
      dialog: false,
      teamposts: [],
      title: "",
      content: "",
      notice: "",
      teamboardno: "",
    };
  },
  props: {
    userinfo: {
      type: Object,
    },
    teaminfo: {
      type: Object,
    },
  },

  created() {
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
        this.teamposts = res.data;
      })
      .catch((err) => {
        console.log(err);
      });
  },
  mounted() {
    window.kakao && window.kakao.maps ? this.initMap() : this.addScript();
  },

  methods: {
    initMap() {
      var container = document.getElementById("map");
      var options = {
        center: new kakao.maps.LatLng(33.450701, 126.570667),
        level: 3,
      };
      var map = new kakao.maps.Map(container, options); //마커추가하려면 객체를 아래와 같이 하나 만든다.
      var marker = new kakao.maps.Marker({
        position: map.getCenter(),
      });
      marker.setMap(map);
    },

    addScript() {
      const script = document.createElement("script");
      /* global kakao */
      script.onload = () => kakao.maps.load(this.initMap);
      script.src =
        "http://dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=511186fb3a541c9983edcb36d9f67265";
      document.head.appendChild(script);
    },

    addPost() {
      var obj, url;
      let token = window.$cookies.get("nnd");
      this.dialog = false;
      if (this.notice == "공지") {
        this.notice = 1;
      } else {
        this.noice = 0;
      }
      obj = {
        teamPostNo: "",
        teamboardNo: this.teaminfo.teamboardNo,
        memberIdx: this.userinfo.idx,
        writer: this.userinfo.name,
        title: this.title,
        content: this.content,
        notice: this.notice,
        createDate: "",
      };
      url = `${process.env.VUE_APP_API_URL}/teammenu/post/save`;

      axios
        .put(url, obj, {
          headers: {
            Authorization: "Bearer " + token.data, // the token is a variable which holds the token
          },
        })
        .then((response) => {
          console.log(response);
          alert("등록성공");

          this.title = null;
          this.content = null;
          this.notice = null;
          this.$emit("changeDialog");
        })
        .catch((error) => {
          console.log(error.response);
          alert("실패");
        });
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

<style scoped lang="scss">
@import "@/assets/stylesheets/index.scss";
#map {
  @include css-size(300px, 300px);
}
</style>
