<template>
  <div>
    <NavBar />
    <v-container>
      <v-row class="mt-13">
        <v-col cols="12" sm="12" lg="12" class="px-0 pt-0">
          <v-sheet height="64">
            <v-toolbar flat color="white" style="position:relative;">
              <div class="d-flex justify-center align-center">
                <div
                  class="d-flex align-center"
                  style="position:absolute; left:0;"
                >
                  <v-btn fab text small color="grey darken-2" @click="prev">
                    <v-icon small>mdi-chevron-left</v-icon>
                  </v-btn>
                  <v-toolbar-title v-if="$refs.calendar">
                    {{ $refs.calendar.title }}
                  </v-toolbar-title>
                  <v-btn fab text small color="grey darken-2" @click="next">
                    <v-icon small>mdi-chevron-right</v-icon>
                  </v-btn>
                </div>
                <div style="position:absolute; right:10px;">
                  <v-btn
                    outlined
                    small
                    dark
                    color="grey darken-2"
                    @click="setToday"
                    >오늘</v-btn
                  >
                  <v-btn
                    v-if="type == 'day'"
                    outlined
                    small
                    dark
                    class="ml-2"
                    color="grey darken-3"
                    @click="type = 'month'"
                    >월</v-btn
                  >
                </div>
              </div>
            </v-toolbar>
          </v-sheet>

          <v-sheet height="600">
            <v-calendar
              ref="calendar"
              v-model="focus"
              :now="today"
              :value="today"
              color="primary"
              :events="events"
              :event-color="getEventColor"
              :type="type"
              @click:event="showEvent"
              @click:more="viewDay"
              @click:date="viewDay"
            ></v-calendar>
            <v-menu
              v-model="selectedOpen"
              :close-on-content-click="false"
              :activator="selectedElement"
            >
              <v-card color="grey lighten-4" flat style="max-width:500px;">
                <v-toolbar :color="selectedEvent.color" flat dark>
                  <v-toolbar-title
                    v-html="selectedEvent.name"
                  ></v-toolbar-title>
                </v-toolbar>
                <v-container class="py-0">
                  <v-row>
                    <v-col cols="12" sm="12" class="px-4 pt-4">
                      <img
                        v-bind:src="getPoster()"
                        style="max-height:200px; max-width:500px;"
                      />
                    </v-col>
                  </v-row>
                  <v-row>
                    <v-col cols="12" class="pb-0">
                      <v-card-text class="px-4 py-0">
                        <h3>{{ selectedEvent.name }}</h3>
                        <div
                          v-for="(board, index) in boards"
                          :key="index"
                          class="mt-5"
                        >
                          <div v-if="index === selectedEvent.id">
                            <div v-if="board.host !== null" class="mt-2">
                              <p class="mb-0 font-weight-bold">주최 🏢</p>
                              <span>{{ board.host }}</span>
                            </div>
                            <div class="mt-2">
                              <p class="mb-0 font-weight-bold">접수기간 🕖</p>
                              <span>{{ board.start }} ~ {{ board.end }}</span>
                            </div>
                            <!-- <div v-if="board.qua !== null" class="mt-2">
                              <p class="mb-0 font-weight-bold">참가자격</p>
                              <span>{{ board.qua }}</span>
                            </div>-->
                            <div v-if="board.price !== null" class="mt-2">
                              <p class="mb-0 font-weight-bold">시상금액 💰</p>
                              <span>1등 {{ board.price }}</span>
                              <br />
                            </div>
                            <div class="mt-5">
                              <small class="mb-0">
                                ❋ 더 자세한 정보는
                                <a
                                  :href="board.link"
                                  target="_blank"
                                  style="text-decoration:none;"
                                  >여기를 클릭</a
                                >하세요
                              </small>
                            </div>
                          </div>
                        </div>
                      </v-card-text>
                    </v-col>
                  </v-row>
                </v-container>

                <v-divider></v-divider>
                <v-card-actions>
                  <v-btn
                    text
                    color="secondary"
                    class="font-weight-bold"
                    @click="selectedOpen = false"
                    >닫기</v-btn
                  >
                  <v-spacer />
                  <v-btn
                    text
                    color="secondary"
                    class="font-weight-bold"
                    @click="$router.push('/')"
                    >팀찾기</v-btn
                  >
                </v-card-actions>
              </v-card>
            </v-menu>
          </v-sheet>
        </v-col>
      </v-row>
    </v-container>
  </div>
</template>

<script>
import NavBar from "../common/NavBar.vue";
//import axios from "axios";

export default {
  name: "Gongmo",
  components: {
    NavBar,
  },
  created() {
    // if (this.$store.state.day == "" || this.$store.state.day - t != 0) {
    //   console.log(this.$store.state.day - t);
    //   this.$store.commit("setDate", t);

    console.log("exist");
    this.boards = this.$store.state.contest;
    for (let index = 0; index < this.boards.length; index++) {
      var ncolor = "#" + Math.round(Math.random() * 0xffffff).toString(16);
      const allDay = this.rnd(0, 3) === 0;
      this.events.push({
        id: index,
        name: this.boards[index].title,
        start: this.boards[index].start,
        end: this.boards[index].start,
        color: ncolor,
        timed: !allDay,
        poster: this.boards[index].poster,
      });

      this.events.push({
        id: index,
        name: this.boards[index].title,
        start: this.boards[index].end,
        end: this.boards[index].end,
        color: ncolor,
        timed: !allDay,
        poster: this.boards[index].poster,
      });
    }
  },
  data: () => ({
    boards: [],
    events: [],
    today: "2020-08-11", //new Date() 타입수정
    type: "month",
    focus: "",
    selectedEvent: {},
    selectedElement: null,
    selectedOpen: false,
  }),
  mounted() {
    this.$refs.calendar.checkChange();
  },
  methods: {
    viewDay({ date }) {
      this.focus = date;
      this.type = "day";
    },
    setToday() {
      this.focus = "";
    },
    prev() {
      this.$refs.calendar.prev();
    },
    next() {
      this.$refs.calendar.next();
    },
    rnd(a, b) {
      return Math.floor((b - a + 1) * Math.random()) + a;
    },
    getEventColor(event) {
      return event.color;
    },
    showEvent({ nativeEvent, event }) {
      const open = () => {
        this.selectedEvent = event;
        this.selectedElement = nativeEvent.target;
        setTimeout(() => (this.selectedOpen = true), 10);
      };

      if (this.selectedOpen) {
        this.selectedOpen = false;
        setTimeout(open, 10);
      } else {
        open();
      }
      nativeEvent.stopPropagation();
    },
    getPoster() {
      return this.selectedEvent.poster;
    },
    getstart() {
      console.log(this.selectedEvent.id);
      return this.boards[this.selectedEvent.id].end;
    },
  },
};
</script>
