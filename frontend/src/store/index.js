import Vue from "vue";
import Vuex from "vuex";
Vue.use(Vuex);

//토큰 저장
export default new Vuex.Store({
  state: {
    count: 0,
    token: "",
    user: {},
    log: [],
    myToken: "", //cy
    day: "",
    contest: [],
  },
  getters: {
    getCountValue: (state) => {
      return state.count;
    },
    getToken: (state) => {
      return state.token;
    },
    getUser: (state) => {
      return state.user;
    },
    getLog: (state) => {
      return state.log;
    },
    isloggedIn: (state) => {
      return !!state.user;
    },
  },
  mutations: {
    increment(state) {
      state.count++;
    },
    setToken(state, token) {
      state.token = token;
    },
    setUser(state, user) {
      state.user = user;
    },
    setLog(state, log) {
      state.log = log;
    },
    logout(state) {
      state.user = "";
      state.token = "";
    },
    setDate(state, day) {
      state.day = day;
    },
    setContest(state, contest) {
      state.contest = contest;
    },
  },
  // actions: {
  //   async getUserFromServer(context) {
  //     if (context.state.token) {
  //       axios
  //         .get("http://localhost:8080/member", {
  //           //재시작시 실행
  //           headers: {
  //             Authorization: "Bearer " + context.state.token, // the token is a variable which holds the token
  //           },
  //         })
  //         .then(
  //           (resp) => {
  //             console.log(resp);
  //             console.log("getUserFromServer.action.afterAxios");
  //             context.commit("setUser", resp.data);
  //           },
  //           (resp) => {
  //             console.log(resp);
  //             console.log("Error while loading user");
  //             window.$cookies.remove("nnd");
  //             context.commit("setUser", "");
  //           }
  //         );
  //     }
  //   },
  //   async getLogById({ commit, state }) {
  //     console.log("getUserLogById.action");
  //     if (state.user.id) {
  //       axios
  //         .get("http://localhost:8080/member/info/?id=${state.user.id}", {
  //           headers: {
  //             Authorization: "Bearer " + state.token, // the token is a variable which holds the token
  //           },
  //         })
  //         .then(
  //           (resp) => {
  //             console.log(resp);
  //             commit("setLog", resp.data);
  //           },
  //           (resp) => {
  //             console.log(resp);
  //             console.log("Error while loading log ");
  //           }
  //         );
  //     }
  //   },
  // },
});
