<template>
  <el-container id="pageContainer">
    <el-main id="pageMain" direction="vertical">
      <el-row>
        <el-col :span="16">
          <div id="Chart1" :style="{ width: '100%', height: '100%' }"></div>
        </el-col>

        <!-- <el-col :span="8"> </el-col> -->
        <el-col :span="8">
          <div id="Chart3" :style="{ width: '100%', height: '100%' }"></div>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="8">
          <div id="Chart4" :style="{ width: '100%', height: '100%' }"></div
        ></el-col>

        <el-col :span="8">
          <div id="Chart5" :style="{ width: '100%', height: '100%' }"></div>
        </el-col>
        <el-col :span="8">
          <div id="Chart10" :style="{ width: '100%', height: '100%' }"></div
        ></el-col>
      </el-row>
    </el-main>
  </el-container>
</template>

<script type="text/javascript">
import GLOBAL from "@/config/global_variable";
import { mapGetters, mapMutations, mapActions } from "vuex";
import * as echarts from "echarts";
import ecStat from "echarts-stat";
import { sum } from "lodash";
var _ = require("lodash");
const api = GLOBAL.apiBaseUrl;
const apiRole = api + "/teacher";
// const apiLocal = apiRole + "/dataanalyse";
export default {
  name: "TeacherDataAnalyse",
  components: {},
  data() {
    return {
      state: this.$store.state,
      DATA: [{}],
      chartsData: {
        chart1: {
          A: 0,
          B: 0,
          C: 0,
          D: 0,
          E: 0,
          F: 0,
          G: 0,
          H: 0,
          I: 0,
          J: 0,
        },
        chart4: {
          A: 0,
          B: 0,
          C: 0,
          D: 0,
          E: 0,
        },
        chart5: {
          A: 0,
          B: 0,
          C: 0,
          D: 0,
          E: 0,
        },
        chart3: {
          all: {
            showup: 0,
            pass: 0,
            great: 0,
            ave: 0,
            mid: 0,
          },
          ninety: {
            showup: 0,
            pass: 0,
            great: 0,
            ave: 0,
            mid: 0,
          },
        },
        chart10: [[]],
      },
    };
  },
  mounted() {
    this.initAsync();
  },
  watch: {},
  computed: {},
  methods: {
    ...mapGetters(["getStore"]),
    ...mapMutations([]),
    ...mapActions([]),

    async initAsync() {
      // Get Data
      const Response = await fetch(apiRole + "/classmanage", {
        method: "GET",
        headers: {
          "Content-Type": "application/json",
          Authorization: this.getStore().Authorization,
        },
      }).then((res) => res.json());
      this.DATA = Response;

      let size = Response.length;
      // Data Process for Charts

      //chart1
      var res1 = {
        A: 0,
        B: 0,
        C: 0,
        D: 0,
        E: 0,
        F: 0,
        G: 0,
        H: 0,
        I: 0,
        J: 0,
      };
      Response.forEach((key) => {
        if (key.w1 == 1) {
          res1.A += 1;
        }
        if (key.w2 == 1) {
          res1.B += 1;
        }
        if (key.w3 == 1) {
          res1.C += 1;
        }
        if (key.w4 == 1) {
          res1.D += 1;
        }
        if (key.w5 == 1) {
          res1.E += 1;
        }
        if (key.w6 == 1) {
          res1.F += 1;
        }
        if (key.w7 == 1) {
          res1.G += 1;
        }
        if (key.w8 == 1) {
          res1.H += 1;
        }
        if (key.w9 == 1) {
          res1.I += 1;
        }
        if (key.w10 == 1) {
          res1.J += 1;
        }
      });
      this.chartsData.chart1 = res1;

      //chart4
      let res2 = _.countBy(Response, (key) => {
        if (key.final_score >= 90) return "A";
        if (key.final_score >= 80 && key.final_score < 90) return "B";
        if (key.final_score >= 70 && key.final_score < 80) return "C";
        if (key.final_score >= 60 && key.final_score < 70) return "D";
        if (key.final_score < 60) return "E";
      });
      this.chartsData.chart4 = res2;

      //chart5
      let res6 = _.countBy(Response, (key) => {
        if (key.score >= 90) return "A";
        if (key.score >= 80 && key.score < 90) return "B";
        if (key.score >= 70 && key.score < 80) return "C";
        if (key.score >= 60 && key.score < 70) return "D";
        if (key.score < 60) return "E";
      });
      this.chartsData.chart5 = res6;

      //chart3
      let res3A = { showup: 0, pass: 0, great: 0, ave: 0, mid: 0 };
      //all
      //showup
      res3A.showup += sum(Object.values(res1));
      res3A.showup = (res3A.showup / size) * 10;
      //pass
      res3A.pass = ((size - this.chartsData.chart5.E) / size) * 100;
      //great
      res3A.great = (this.chartsData.chart5.A / size) * 100;
      //ave
      res3A.ave =
        _.sumBy(Response, (key) => {
          return key.final_score;
        }) / size;
      //mid
      let order = [];
      Response.forEach((key) => {
        order.push(key.final_score);
      });
      order.sort();

      res3A.mid = order[_.ceil(size / 2)];
      this.chartsData.chart3.all = res3A;

      //ninety
      let res3N = { showup: 0, pass: 0, great: 0, ave: 0, mid: 0 };
      //showup
      let size60 = size - this.chartsData.chart5.E;
      let showup_over60 = 0;
      Response.forEach((key) => {
        if (key.final_score >= 60) {
          if (key.w1 == 1) {
            showup_over60 += 1;
          }
          if (key.w2 == 1) {
            showup_over60 += 1;
          }
          if (key.w3 == 1) {
            showup_over60 += 1;
          }
          if (key.w4 == 1) {
            showup_over60 += 1;
          }
          if (key.w5 == 1) {
            showup_over60 += 1;
          }
          if (key.w6 == 1) {
            showup_over60 += 1;
          }
          if (key.w7 == 1) {
            showup_over60 += 1;
          }
          if (key.w8 == 1) {
            showup_over60 += 1;
          }
          if (key.w9 == 1) {
            showup_over60 += 1;
          }
          if (key.w10 == 1) {
            showup_over60 += 1;
          }
        }
      });
      res3N.showup = (showup_over60 / size60) * 10;
      //pass
      res3N.pass = 100;
      //great
      res3N.great = (this.chartsData.chart5.A / size60) * 100;
      //ave
      res3N.ave =
        _.sumBy(Response, (key) => {
          if (key.final_score >= 60) return key.final_score;
          else return 0;
        }) / size60;
      //mid
      let order1 = [];
      Response.forEach((key) => {
        if (key.final_score >= 60) order1.push(key.final_score);
      });
      order1.sort();

      res3N.mid = order[_.ceil(size60 / 2)];
      this.chartsData.chart3.ninety = res3N;

      //chart10
      Response.forEach((key) => {
        let A,
          B = 0;
        A = key.final_score;
        if (key.w1 == 1) {
          B += 1;
        }
        if (key.w2 == 1) {
          B += 1;
        }
        if (key.w3 == 1) {
          B += 1;
        }
        if (key.w4 == 1) {
          B += 1;
        }
        if (key.w5 == 1) {
          B += 1;
        }
        if (key.w6 == 1) {
          B += 1;
        }
        if (key.w7 == 1) {
          B += 1;
        }
        if (key.w8 == 1) {
          B += 1;
        }
        if (key.w9 == 1) {
          B += 1;
        }
        if (key.w10 == 1) {
          B += 1;
        }
        this.chartsData.chart10.push([A, B]);
      });

      // Chart Init
      var Chart1 = echarts.init(document.getElementById("Chart1"));
      Chart1.setOption({
        title: { text: "出勤情况分布" },
        xAxis: {
          type: "category",
          data: [
            "周1",
            "周2",
            "周3",
            "周4",
            "周5",
            "周6",
            "周7",
            "周8",
            "周9",
            "周10",
          ],
        },
        yAxis: {
          type: "value",
        },
        series: [
          {
            data: [
              this.chartsData.chart1.A,
              this.chartsData.chart1.B,
              this.chartsData.chart1.C,
              this.chartsData.chart1.D,
              this.chartsData.chart1.E,
              this.chartsData.chart1.F,
              this.chartsData.chart1.G,
              this.chartsData.chart1.H,
              this.chartsData.chart1.I,
              this.chartsData.chart1.J,
            ],
            type: "bar",
            showBackground: true,
            backgroundStyle: {
              color: "rgba(180, 180, 180, 0.2)",
            },
          },
        ],
      });

      var Chart4 = echarts.init(document.getElementById("Chart4"));
      Chart4.setOption({
        title: {
          text: "平时成绩分布",
          subtext: "",
          left: "center",
        },
        tooltip: {
          trigger: "item",
        },
        series: [
          {
            name: "平时成绩",
            type: "pie",
            radius: "50%",
            data: [
              { value: this.chartsData.chart4.A, name: "≥90" },
              { value: this.chartsData.chart4.B, name: "[80, 90)" },
              { value: this.chartsData.chart4.C, name: "[70, 80)" },
              { value: this.chartsData.chart4.D, name: "[60, 70)" },
              { value: this.chartsData.chart4.E, name: "未及格<60" },
            ],
            emphasis: {
              itemStyle: {
                shadowBlur: 10,
                shadowOffsetX: 0,
                shadowColor: "rgba(0, 0, 0, 0.5)",
              },
            },
          },
        ],
      });
      var Chart5 = echarts.init(document.getElementById("Chart5"));
      Chart5.setOption({
        title: {
          text: "期末成绩分布",
          subtext: "",
          left: "center",
        },
        tooltip: {
          trigger: "item",
        },
        // legend: {
        //   orient: "vertical",
        //   left: "left",
        // },
        series: [
          {
            name: "期末成绩",
            type: "pie",
            radius: "50%",
            data: [
              { value: this.chartsData.chart5.A, name: "≥90" },
              { value: this.chartsData.chart5.B, name: "[80, 90)" },
              { value: this.chartsData.chart5.C, name: "[70, 80)" },
              { value: this.chartsData.chart5.D, name: "[60, 70)" },
              { value: this.chartsData.chart5.E, name: "未及格<60" },
            ],
            emphasis: {
              itemStyle: {
                shadowBlur: 10,
                shadowOffsetX: 0,
                shadowColor: "rgba(0, 0, 0, 0.5)",
              },
            },
          },
        ],
      });
      var Chart3 = echarts.init(document.getElementById("Chart3"));
      Chart3.setOption({
        title: {
          text: "数据雷达图",
        },
        legend: {
          data: ["全部学生", "及格学生"],
        },
        radar: {
          // shape: 'circle',
          indicator: [
            { name: "出勤率", max: 100 },
            { name: "及格率", max: 100 },
            { name: "期末成绩90分以上", max: 100 },
            { name: "平均期末成绩", max: 100 },
            { name: "中位期末成绩", max: 100 },
          ],
        },
        series: [
          {
            name: "全部学生/及格学生",
            type: "radar",
            data: [
              {
                value: [
                  this.chartsData.chart3.all.showup,
                  this.chartsData.chart3.all.pass,
                  this.chartsData.chart3.all.great,
                  this.chartsData.chart3.all.ave,
                  this.chartsData.chart3.all.mid,
                ],
                name: "全部学生",
              },
              {
                value: [
                  this.chartsData.chart3.ninety.showup,
                  this.chartsData.chart3.ninety.pass,
                  this.chartsData.chart3.ninety.great,
                  this.chartsData.chart3.ninety.ave,
                  this.chartsData.chart3.ninety.mid,
                ],
                name: "及格学生",
              },
            ],
          },
        ],
      });
      echarts.registerTransform(ecStat.transform.regression);
      var Chart10 = echarts.init(document.getElementById("Chart10"));
      console.log("*********this.chartsData.chart10:", this.chartsData.chart10);
      Chart10.setOption({
        dataset: [
          {
            source: this.chartsData.chart10,
          },
          {
            transform: {
              type: "ecStat:regression",
            },
          },
        ],
        title: {
          text: "期末成绩与出勤率",
          subtext: "散点图",
          left: "center",
        },
        legend: {
          bottom: 5,
        },
        tooltip: {
          trigger: "axis",
          axisPointer: {
            type: "cross",
          },
        },
        xAxis: {
          splitLine: {
            lineStyle: {
              type: "dashed",
            },
          },
        },
        yAxis: {
          splitLine: {
            lineStyle: {
              type: "dashed",
            },
          },
        },
        series: [
          {
            name: "样本",
            type: "scatter",
          },
        ],
      });
    },
  },
};
</script>

<style lang="scss" scoped>
:deep(#pageMain) {
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.12), 0 0 6px rgba(0, 0, 0, 0.04);
  background-color: #ffffff;
  display: flex;
  flex-direction: column;
}
:deep(#tabletitle) {
  text-align: left;
}
:deep(.el-row) {
  flex: 1;
}
</style>
