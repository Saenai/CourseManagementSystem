<template>
  <el-container>
    <el-header></el-header>
    <el-main direction="vertical">
      <el-avatar :size="256" :src="circleUrl"></el-avatar>
      <h2>{{ state.name }}</h2>
      <h4>用户类型：学生</h4>
      <h4>授权开始日：{{ dateA }}</h4>
      <h4>授权终止日：{{ dateB }}</h4>
      <h4>今日：{{ today }}</h4>
    </el-main>
  </el-container>
</template>

<script type="text/javascript">
import GLOBAL from "@/config/global_variable";
import { mapGetters, mapMutations, mapActions } from "vuex";
var dayjs = require("dayjs");
export default {
  name: "StudentHome",
  components: {},
  data() {
    return {
      state: this.$store.state,
      api: GLOBAL.apiBaseUrl,
      circleUrl:
        "https://fuss10.elemecdn.com/e/5d/4a731a90594a4af544c0c25941171jpeg.jpeg",
      dateA: "",
      dateB: "",
      today: "",
    };
  },
  mounted() {
    var date = new Date();
    this.today = dayjs(
      date.getFullYear() + "-" + (date.getMonth() + 1) + "-" + date.getDate()
    ).format("YYYY-MM-DD");

    this.init();
    this.validateDate();
  },
  watch: {},
  computed: {},
  methods: {
    ...mapGetters(["getStore"]),
    ...mapMutations([]),
    ...mapActions([]),
    async init() {
      const Response = await fetch(this.api + "/student/home", {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
          Authorization: this.getStore().Authorization,
        },
        body: this.getStore().id,
      }).then((res) => res.json());
      this.dateA = Response.dateA;
      this.dateB = Response.dateB;
    },
    async validateDate() {
      const Response = await fetch(this.api + "/student/home", {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
          Authorization: this.getStore().Authorization,
        },
        body: this.getStore().id,
      }).then((res) => res.json());
      this.dateA = Response.dateA;
      this.dateB = Response.dateB;

      console.log("*********thsi.today:", this.today);
      console.log("*********this.dateA:", this.dateA);
      if (this.today < this.dateA || this.today > this.dateB) {
        this.$message({
          message: "权限逾期",
          type: "error",
        });
        sessionStorage.setItem("error_code", "E03");
        this.$router.push("/error");
      }
    },
  },
};
</script>

<style lang="scss" scoped></style>
