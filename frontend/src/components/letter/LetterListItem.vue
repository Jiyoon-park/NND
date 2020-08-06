<template>
  <div>
    <v-list-item
      class="letter py-3"
      @click="onLetterDetail(`${letterinfo.letterNo}`)"
      @click.stop="dialog = true"
    >
      <div class="mr-4">
        <v-avatar color="grey" size="60">
          <span v-if="!letterinfo.profile" class="white--text headline"></span>
          <img v-else :src="letterinfo.profile" />
        </v-avatar>
      </div>
      <v-list-item-content>
        <div>
          <v-list-item-title v-text="letterinfo.name" class="font-weight-black"></v-list-item-title>
          <i
            class="fas fa-envelope float-right"
            style="font-size:20px; color: #7E57C2;"
            v-if="!letterinfo.read"
          ></i>
          <i
            class="fas fa-envelope-open-text float-right"
            style="font-size:20px; color: #BDBDBD;"
            v-else
          ></i>
          <br />
          <v-list-item-title v-text="letterinfo.content" class="text--secondary"></v-list-item-title>
        </div>
      </v-list-item-content>
    </v-list-item>
    <v-dialog v-model="dialog" max-width="300">
      <LetterDetail
        :item="item"
        :letterinfo="letterinfo"
        :dialog="dialog"
        @changeDialog="dialog = false"
      />
    </v-dialog>
  </div>
</template>

<script>
import LetterDetail from "./LetterDetail.vue";
import axios from "axios";

export default {
  components: {
    LetterDetail,
  },
  data() {
    return {
      dialog: false,
    };
  },
  props: {
    item: {
      type: Object,
    },
    letterinfo: {
      type: Array,
    },
  },
  methods: {
    // 클릭한 편지의 편지 pk를 받아옴.
    onLetterDetail(letterNo) {
      // 클릭한 편지는 읽음 처리
      axios
        .post(`http://localhost:8080/letter/update/${letterNo}`)
        .then((res) => console.log(res))
        .catch((err) => console.log(err));
    },
  },
};
</script>

<style>
</style>