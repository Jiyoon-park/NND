<template>
  <v-row class="mt-16">
    <NavBar />
    <v-col>
      <v-sheet height="64">
        <v-toolbar flat color="white">
          <v-btn outlined class="mr-4" color="grey darken-2" @click="setToday"
            >오늘</v-btn
          >
          <v-btn fab text small color="grey darken-2" @click="prev">
            <v-icon small>mdi-chevron-left</v-icon>
          </v-btn>
          <v-btn fab text small color="grey darken-2" @click="next">
            <v-icon small>mdi-chevron-right</v-icon>
          </v-btn>
          <v-toolbar-title v-if="$refs.calendar">{{
            $refs.calendar.title
          }}</v-toolbar-title>
          <v-spacer />
          <v-btn
            v-if="type == 'day'"
            outlined
            class="mr-4"
            color="grey darken-3"
            @click="type = 'month'"
            >월</v-btn
          >
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
          offset-x
        >
          <v-card color="grey lighten-4" min-width="350px" flat>
            <v-toolbar :color="selectedEvent.color" dark>
              <v-toolbar-title v-html="selectedEvent.name"></v-toolbar-title>
            </v-toolbar>
            <v-container>
              <v-row>
                <v-col cols="auto">
                  <img height="200" width="200" v-bind:src="getPoster()" />
                </v-col>
                <v-col cols="auto">
                  <v-card-text>
                    <div v-for="(board, index) in boards" :key="index">
                      <div v-if="index === selectedEvent.id">
                        시작일 : {{ board.start }} <br />
                        종료일 : {{ board.end }}<br />
                        <div v-if="board.host !== null">
                          주최 : {{ board.host }}<br />
                        </div>
                        <div v-if="board.qua !== null">
                          참가자격 : {{ board.qua }}<br />
                        </div>
                        <div v-if="board.price !== null">
                          1등 시상금 : {{ board.price }}<br />
                        </div>
                        상세정보
                        <a :href="board.link">{{ board.link }}</a>
                      </div>
                    </div>
                  </v-card-text>
                </v-col>
                <!-- <v-col cols="auto">
                  <img height="200" width="200" v-bind:src="getPoster()" />
                </v-col> -->
              </v-row>
            </v-container>
            <v-card-actions>
              <v-btn text color="secondary" @click="selectedOpen = false"
                >닫기</v-btn
              >
            </v-card-actions>
          </v-card>
        </v-menu>
      </v-sheet>
    </v-col>
  </v-row>
</template>

<script>
import NavBar from "../common/NavBar.vue";
import axios from "axios";

export default {
  name: "Gongmo",
  components: {
    NavBar,
  },
  created() {
    let token = window.$cookies.get("nnd");
    var t = new Date().getDay();
    console.log(t);
    console.log(this.$store.state.day);

    if (this.$store.state.day == "" || this.$store.state.day - t != 0) {
      console.log(this.$store.state.day - t);
      this.$store.commit("setDate", t);
      axios
        .get(`http://localhost:8080/contest`, {
          headers: {
            Authorization: "Bearer " + token.data, // the token is a variable which holds the token
          },
        })
        .then(({ data }) => {
          this.boards = data;

          this.$store.commit("setContest", this.boards);
          console.log(this.boards);
          for (let index = 0; index < this.boards.length; index++) {
            var ncolor =
              "#" + Math.round(Math.random() * 0xffffff).toString(16);
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
        })
        .catch((err) => {
          console.log(err);
        }); //endaxios
    } //end if
    else {
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
