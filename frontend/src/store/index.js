import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    newsfeeds: {
      text1: {
        id: 'user1',
        title: '공모전 팀원 구합니다',
        description: '같이 외부 공모전 참여할 사람 구합니다',
        favorite: false,
      },
      text2: {
        id: 'user2',
        title: '공모전 팀원 구합니다',
        description: '같이 외부 공모전 참여할 사람 구합니다. 입문자 환영',
        favorite: false,
      },
      text3: {
        id: 'user3',
        title: '스터디 같이 할사람 구합니다',
        description: 'jQuery 공부할 사람 구합니다',
        favorite: false,
      },
    }
  },
  mutations: {
  },
  actions: {
  },
  modules: {
  }
})
