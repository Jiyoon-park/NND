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
    };
  },
  async mounted() {
    try {
      let token = window.$cookies.get("nnd");

      axios
        .get(
          `${process.env.VUE_APP_API_URL}/teammenu/rating/list/` +
            this.$store.state.teamNo,
          {
            headers: {
              Authorization: "Bearer " + token.data, // the token is a variable which holds the token
            },
          }
        )
        .then((res) => {
          console.log("제발제발제발제발제발제발제발");
          console.log(res.data);

          this.chartData = {
            labels: ["지라", "팀워크", "출석률", "깃 커밋", "만족도"],
            datasets: [
              // {
              //   label: "오기석",
              //   data: [1, 2, 3, 4, 5],
              // },
            ],
          };
          for (let index = 0; index < res.data.length; index++) {
            this.chartData.datasets.push({
              label: res.data[index].name,
              backgroundColor: this.color(),

              data: [
                res.data[index].issueCnt,
                res.data[index].teamworkship,
                res.data[index].attendRate,
                res.data[index].commitCnt,
                res.data[index].satisfaction,
              ],
            });
          }
          console.log("chart dataaaaaaaaaaaaaaaaaaaaaaaaaaa");
          console.log(this.chartData);
          this.loaded = true;
        })
        .catch((err) => {
          console.log(err);
        });
    } catch (e) {
      console.error(e);
    }
  },
  methods: {
    color() {
      var a = Math.floor(Math.random() * 256);
      var b = Math.floor(Math.random() * 256);
      var c = Math.floor(Math.random() * 256);

      var str = `rgba(${a},${b},${c},0.2)`;
      return str;
    },
  },
};
</script>

<style>
.small {
  max-width: 600px;
  margin: 150px auto;
}
</style>
