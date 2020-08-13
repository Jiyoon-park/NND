<template>
  <div>
    <h3>Radar Chart Example in Vue</h3>
    <RadarChart v-if="loaded" :chartData="chartData" :options="options" />
  </div>
</template>

<script>
import RadarChart from "../team/RadarChart.js";
import axios from "axios";

export default {
  components: {
    RadarChart,
  },
  data() {
    return {
      loaded: false,
      chartData: null,
      data: [],
    };
  },
  async mounted() {
    this.loaded = false;
    try {
      let token = window.$cookies.get("nnd");

      axios
        .get(
          `http://localhost:8080/teammenu/rating/list/` +
            this.$store.state.teamNo +
            `/` +
            token.object.idx,
          {
            headers: {
              Authorization: "Bearer " + token.data, // the token is a variable which holds the token
            },
          }
        )
        .then((res) => {
          console.log("제발제발제발제발제발제발제발");
          console.log(res.data.issueCnt);
          this.chartData = {
            labels: ["지라", "팀워크", "출석률", "깃 커밋", "만족도"],
            datasets: [
              {
                label: "오기석",
                data: [
                  res.data.issueCnt,
                  res.data.teamworkship,
                  res.data.attendRate,
                  res.data.commitCnt,
                  res.data.satisfaction,
                ],
              },
            ],
          };
          this.loaded = true;
        })
        .catch((err) => {
          console.log(err);
        });
    } catch (e) {
      console.error(e);
    }
  },
  methods: {},
};
</script>

<style>
.small {
  max-width: 600px;
  margin: 150px auto;
}
</style>
