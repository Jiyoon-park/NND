<template>
  <div class="class1">
    <v-sheet height="64">
      <v-toolbar flat color="white" style="position:relative;">
        <v-btn fab text small color="grey darken-2" @click="prev">
          <v-icon small>mdi-chevron-left</v-icon>
        </v-btn>
        <v-toolbar-title v-if="$refs.calendar">{{ $refs.calendar.title }}</v-toolbar-title>
        <v-btn fab text small color="grey darken-2" @click="next">
          <v-icon small>mdi-chevron-right</v-icon>
        </v-btn>
        <div style="position:absolute; right:10px;">
          <v-btn outlined class="mr-4" color="grey darken-2" @click="setToday">오늘</v-btn>
          <v-btn
            v-if="type == 'week'"
            outlined
            class="mr-4"
            color="grey darken-3"
            @click="type = 'month'"
          >이전</v-btn>
        </div>
      </v-toolbar>
    </v-sheet>

    <v-sheet height="100%">
      <v-calendar
        ref="calendar"
        v-model="focus"
        color="primary"
        :type="type"
        :events="events"
        :event-color="getEventColor"
        :event-ripple="false"
        @click:more="viewDay"
        @click:date="viewDay"
        @mousedown:event="startDrag"
        @mousedown:time="startTime"
        @mousemove:time="mouseMove"
        @mouseup:time="endDrag"
        @mouseleave.native="cancelDrag"
      >
        <template #event="{ event, timed, eventSummary }">
          <div class="v-event-draggable" v-html="eventSummary()"></div>
          <div v-if="timed" class="v-event-drag-bottom" @mousedown.stop="extendBottom(event)"></div>
        </template>
      </v-calendar>
    </v-sheet>

    <v-dialog v-model="ok" max-width="600px">
      <v-card>
        <v-toolbar color="blue darken-3" flat dark>
          <v-toolbar-title>스케줄 내용 수정</v-toolbar-title>
        </v-toolbar>

        <v-card-text class="mt-5 pb-0">
          <div class="mt-3">
            <v-textarea filled v-model="msg" placeholder="내용을 작성해주세요."></v-textarea>
          </div>
        </v-card-text>
        <v-divider></v-divider>
        <v-card-actions>
          <v-btn color="blue darken-1" text @click="ok = false">취소</v-btn>
          <v-spacer></v-spacer>
          <v-btn color="blue darken-1" text @click="submit">확인</v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>
  </div>
</template>

<script>
import axios from "axios";

export default {
  name: "TeamDiary",
  components: {},
  created() {
    //디비에서 가져와야함
    let token = window.$cookies.get("nnd");
    axios
      .get(
        `${process.env.VUE_APP_API_URL}/teammenu/diary/list/${this.$store.state.teamNo}`,
        {
          headers: {
            Authorization: "Bearer " + token.data, // the token is a variable which holds the token
          },
        }
      )
      .then(({ data }) => {
        this.boards = data;
        for (let index = 0; index < this.boards.length; index++) {
          this.events.push({
            no: this.boards[index].teamdiaryNo,
            name: this.boards[index].title,
            start: Number(this.boards[index].start),
            end: Number(this.boards[index].end),
            color: this.boards[index].color,
            timed: this.boards[index].timed,
          });
        }
      })
      .catch((err) => {
        console.log(err);
      });
  },
  data: () => ({
    boards: null,
    ok: false,
    msg: "",
    events: [],
    today: "2020-08-11", //new Date() 타입수정
    type: "month",
    focus: "",
    dragflag: false,
    dragmsg: null,
    dragEvent: null,
    dragStart: null,
    createEvent: null,
    createStart: null,
    extendOriginal: null,
  }),
  mounted() {
    this.$refs.calendar.checkChange();
  },
  methods: {
    viewDay({ date }) {
      this.focus = date;
      this.type = "week";
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
    startDrag({ event, timed }) {
      //눌렀는데 이벤트 있을때
      if (event && timed) {
        this.dragflag = true;
        this.dragEvent = event;
        this.dragTime = null;
        this.extendOriginal = null;
      }
    },

    submit() {
      const i = this.events.indexOf(this.dragmsg);
      this.events[i].name = this.msg;

      let token = this.$cookies.get("nnd");
      //update
      axios
        .post(
          `${process.env.VUE_APP_API_URL}/teammenu/diary/update/${this.dragmsg.no}`,
          {
            title: this.events[i].name,
            color: this.events[i].color,
            start: this.events[i].start,
            end: this.events[i].end,
            timed: true,
          },
          {
            headers: {
              Authorization: "Bearer " + token.data, // the token is a variable which holds the token
            },
          }
        )
        .then(() => {
        })
        .catch((error) => {
          console.log(error.response);
        });

      //초기화
      this.ok = false;
      this.msg = "";
      this.dragflag = false;
      this.dragTime = null;
      this.dragEvent = null;
      this.createEvent = null;
      this.createStart = null;
      this.extendOriginal = null;
    },

    startTime(tms) {
      const mouse = this.toTime(tms);
      let token = window.$cookies.get("nnd");

      if (this.dragEvent && this.dragTime === null) {
        //이벤트가 존재하면
        const start = this.dragEvent.start;
        this.dragTime = mouse - start;
      } else {
        //이벤트가 존재하지 않아 새로 만듬
        this.createStart = this.roundTime(mouse);
        this.createEvent = {
          name: `Event #`,
          color: "#" + Math.round(Math.random() * 0xffffff).toString(16),
          start: this.createStart,
          end: this.createStart,
          timed: true,
        };
        //추가하시겠습니까 물어보고 세이브
        this.events.push(this.createEvent);
        //axios 통해서 디비에 추가해야함
        axios
          .put(
            `${process.env.VUE_APP_API_URL}/teammenu/diary/create/${this.$store.state.teamNo}`,
            {
              title: this.createEvent.name,
              color: this.createEvent.color,
              start: this.createEvent.start,
              end: this.createEvent.end,
              timed: true,
            },
            {
              headers: {
                Authorization: "Bearer " + token.data, // the token is a variable which holds the token
              },
            }
          )
          .then(({ data }) => {
            this.createEvent.no = data.teamdiaryNo;
          })
          .catch((err) => {
            console.log(err);
          });
      }
    },
    extendBottom(event) {
      this.createEvent = event;
      this.createStart = event.start;
      this.extendOriginal = event.end;
    },
    mouseMove(tms) {
      const mouse = this.toTime(tms);
      this.dragflag = false;
      if (this.dragEvent && this.dragTime !== null) {
        //이벤트 클릭하면
        const start = this.dragEvent.start;
        const end = this.dragEvent.end;
        const duration = end - start;
        const newStartTime = mouse - this.dragTime;
        const newStart = this.roundTime(newStartTime);
        const newEnd = newStart + duration;

        this.dragEvent.start = newStart;
        this.dragEvent.end = newEnd;
      } else if (this.createEvent && this.createStart !== null) {
        const mouseRounded = this.roundTime(mouse, false);
        const min = Math.min(mouseRounded, this.createStart);
        const max = Math.max(mouseRounded, this.createStart);

        this.createEvent.start = min;
        this.createEvent.end = max;

        //업데이트 해줘야함
      }
    },
    endDrag() {
      let token = window.$cookies.get("nnd");
      if (this.dragflag) {
        this.dragmsg = this.dragEvent;
        this.ok = true;
      }
      if (this.dragEvent !== null) {
        axios
          .post(
            `${process.env.VUE_APP_API_URL}/teammenu/diary/update/${this.dragEvent.no}`,
            {
              title: this.dragEvent.name,
              color: this.dragEvent.color,
              start: this.dragEvent.start,
              end: this.dragEvent.end,
              timed: true,
            },
            {
              headers: {
                Authorization: "Bearer " + token.data, // the token is a variable which holds the token
              },
            }
          )        
          .catch((error) => {
            console.log(error.response);
          });
      } else if (this.createEvent !== null) {
        axios
          .post(
            `${process.env.VUE_APP_API_URL}/teammenu/diary/update/${this.createEvent.no}`,
            {
              title: this.createEvent.name,
              color: this.createEvent.color,
              start: this.createEvent.start,
              end: this.createEvent.end,
              timed: true,
            },
            {
              headers: {
                Authorization: "Bearer " + token.data, // the token is a variable which holds the token
              },
            }
          )
          .catch((error) => {
            console.log(error.response);
          });
      }

      this.dragTime = null;
      this.dragEvent = null;
      this.createEvent = null;
      this.createStart = null;
      this.extendOriginal = null;
    },
    cancelDrag() {

      if (this.ok) {
        //모달창으로 나간거면 삭제하지마라
      } else {
        const i = this.events.indexOf(this.dragEvent);
        let token = window.$cookies.get("nnd");
        //이벤트 삭제
        if (i !== -1) {
          axios
            .delete(
              `${process.env.VUE_APP_API_URL}/teammenu/diary/delete/${this.dragEvent.no}`,
              {
                headers: {
                  Authorization: "Bearer " + token.data, // the token is a variable which holds the token
                },
              }
            );
          this.events.splice(i, 1);
        }
      }

      this.createEvent = null;
      this.createStart = null;
      this.dragTime = null;
      this.dragEvent = null;
    },
    roundTime(time, down = true) {
      const roundTo = 15; // minutes
      const roundDownTime = roundTo * 60 * 1000;

      return down
        ? time - (time % roundDownTime)
        : time + (roundDownTime - (time % roundDownTime));
    },
    toTime(tms) {
      return new Date(
        tms.year,
        tms.month - 1,
        tms.day,
        tms.hour,
        tms.minute
      ).getTime();
    },
    getEventColor(event) {
      const rgb = parseInt(event.color.substring(1), 16);
      const r = (rgb >> 16) & 0xff;
      const g = (rgb >> 8) & 0xff;
      const b = (rgb >> 0) & 0xff;

      return event === this.dragEvent
        ? `rgba(${r}, ${g}, ${b}, 0.7)`
        : event === this.createEvent
        ? `rgba(${r}, ${g}, ${b}, 0.7)`
        : event.color;
    },
  },
};
</script>
<style>
.v-event-draggable {
  padding-left: 6px;
}

.v-event-timed {
  user-select: none;
  -webkit-user-select: none;
}

.v-event-drag-bottom {
  position: absolute;
  left: 0;
  right: 0;
  bottom: 4px;
  height: 4px;
  cursor: ns-resize;
}

.v-event-drag-bottom::after {
  display: none;
  position: absolute;
  left: 50%;
  height: 4px;
  border-top: 1px solid white;
  border-bottom: 1px solid white;
  width: 16px;
  margin-left: -8px;
  opacity: 0.8;
  content: "";
}
:hover::after {
  display: block;
}
</style>