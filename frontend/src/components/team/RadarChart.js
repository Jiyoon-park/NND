import { Radar } from "vue-chartjs";

export default {
  extends: Radar,

  props: {
    chartData: {
      type: Object,
    },
    options: {
      type: Array,
    },
  },
  mounted() {
    this.renderChart(this.chartData, this.options);
  },
};
