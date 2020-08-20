import Vue from "vue";
import Vuex from "vuex";
import createPersistedState from "vuex-persistedstate";

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
    teamNo: "",
    teamMaster: "",
    teammembers: [],
    profileidx: "",
  },
  plugins: [createPersistedState()],

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
    pchange: (state) => state.profileidx,
    nchange: (state) => state.teamNo,
    saveMember: (state) => state.teammembers,
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
  
});
