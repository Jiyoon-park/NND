<template>
  <v-row class="mt-16">
    <NavBar />
    <v-col>
      <v-sheet height="64">
        <v-toolbar flat color="white">
          <v-btn outlined class="mr-4" color="grey darken-2" @click="setToday">오늘</v-btn>
          <v-btn fab text small color="grey darken-2" @click="prev">
            <v-icon small>mdi-chevron-left</v-icon>
          </v-btn>
          <v-btn fab text small color="grey darken-2" @click="next">
            <v-icon small>mdi-chevron-right</v-icon>
          </v-btn>
          <v-toolbar-title v-if="$refs.calendar">{{ $refs.calendar.title }}</v-toolbar-title>
          <v-spacer />
          <v-btn
            v-if="type =='day'"
            outlined
            class="mr-4"
            color="grey darken-3"
            @click="type='month'"
          >월</v-btn>
        </v-toolbar>
      </v-sheet>
      <v-sheet height="1200">
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
              <v-btn icon>
                <v-icon>mdi-pencil</v-icon>
              </v-btn>
              <v-toolbar-title v-html="selectedEvent.name"></v-toolbar-title>
              <v-spacer></v-spacer>
              <v-btn icon>
                <v-icon>mdi-heart</v-icon>
              </v-btn>
              <v-btn icon>
                <v-icon>mdi-dots-vertical</v-icon>
              </v-btn>
            </v-toolbar>
            <v-card-text>
              <span v-html="selectedEvent.details"></span>
            </v-card-text>
            <v-card-actions>
              <v-btn text color="secondary" @click="selectedOpen = false">취소</v-btn>
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
    axios
      .get(`http://localhost:8080/contest`, 
        {
          headers: { 
          Authorization: "Bearer " + token.data, // the token is a variable which holds the token
          }
        }
      )
      .then(({ data }) => {
        this.boards = data;
        console.log(this.boards);
        for (let index = 0; index < this.boards.length; index++) {
          const allDay = this.rnd(0, 3) === 0
          this.events.push({
          name: this.boards[index].title,
          start: this.boards[index].start,
          end: this.boards[index].end,
          color: this.colors[this.rnd(0, this.colors.length - 1)],
          timed: !allDay,
        })
        }
        console.log(this.events);
      })
      .catch((err) => {
        console.log(err);
      });
  },
  data: () => ({
    boards : null,
    events: [],
    today: "2020-08-11", //new Date() 타입수정
    type: "month",
    colors: ['blue', 'indigo', 'deep-purple', 'cyan', 'green', 'orange', 'grey darken-1'],
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
    rnd (a, b) {
      return Math.floor((b - a + 1) * Math.random()) + a
    },
    getEventColor (event) {
      return event.color
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
  },
};
</script>