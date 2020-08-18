import { Radar } from "vue-chartjs";

export default {
  extends: Radar,
  // data() {
  //   return {
  //     chartData: {
  //       labels: [
  //         "Babol",
  //         "Cabanatuan",
  //         "Daegu",
  //         "Jerusalem",
  //         "Fairfield",
  //         "New York",
  //         "Gangtok",
  //         "Buenos Aires",
  //         "Hafar Al-Batin",
  //         "Idlib",
  //       ],
  //       datasets: [
  //         {
  //           label: "Radar Chart",
  //           borderWidth: 1,
  //           backgroundColor: "rgba(54, 162, 235, 0.2)",

  //           data: [
  //             32127289,
  //             24724027,
  //             25820412,
  //             23685667,
  //             25644258,
  //             22433220,
  //             22966210,
  //             22743184,
  //             21880515,
  //             21543111,
  //           ],
  //         },
  //       ],
  //     },
  //     options: {
  //       responsive: true,
  //       maintainAspectRatio: false,
  //     },
  //   };
  // },
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
