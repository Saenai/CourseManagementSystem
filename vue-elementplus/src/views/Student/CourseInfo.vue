<template>
  <el-container id="pageContainer">
    <el-main id="pageMain" direction="vertical">
      <el-descriptions :column="3" border class="margin-top" title="课程信息">
        <!-- <template #extra>
      <el-button type="primary" size="small">操作</el-button>
    </template> -->
        <el-descriptions-item>
          <template #label>
            <i class="el-icon-s-management"></i>
            课程名</template
          >
          <el-tag size="small">软件工程</el-tag>
        </el-descriptions-item>
        <el-descriptions-item>
          <template #label>
            <i class="el-icon-time"></i>
            上课时间
          </template>
          周三 3-4
        </el-descriptions-item>

        <el-descriptions-item>
          <template #label>
            <i class="el-icon-s-flag"></i>
            学分
          </template>
          4.0
        </el-descriptions-item>
        <el-descriptions-item>
          <template #label>
            <i class="el-icon-location-outline"></i>
            上课地点
          </template>
          403
        </el-descriptions-item>
        <el-descriptions-item>
          <template #label>
            <i class="el-icon-user"></i>
            任课教师
          </template>
          <el-tag size="small">张三</el-tag>
        </el-descriptions-item>
      </el-descriptions>
      <el-row id="cc">
        <el-col :span="8">
          <h4>出勤情况</h4>
          <el-progress
            type="circle"
            :percentage="onshowper"
            :status="progress_status"
          ></el-progress
        ></el-col>
        <el-col :span="8">
          <h4>平时成绩</h4>
          <el-progress
            type="circle"
            :percentage="onshowper1"
            :status="progress_status1"
          ></el-progress
        ></el-col>
        <el-col :span="8"
          ><h4>期末成绩</h4>
          <el-progress
            type="circle"
            :percentage="onshowper2"
            :status="progress_status2"
          ></el-progress
        ></el-col>
      </el-row>
    </el-main>
  </el-container>
</template>

<script type="text/javascript">
import GLOBAL from "@/config/global_variable";
import { mapGetters, mapMutations, mapActions } from "vuex";
export default {
  name: "StudentCourseInfo",
  components: {},
  data() {
    return {
      state: this.$store.state,
      api: GLOBAL.apiBaseUrl,
      circleUrl:
        "https://fuss10.elemecdn.com/e/5d/4a731a90594a4af544c0c25941171jpeg.jpeg",
      onshowper: 50,
      onshowper1: 50,
      onshowper2: 50,
      progress_status: "",
      progress_status1: "",
      progress_status2: "",
    };
  },
  mounted() {
    var date = new Date();
    this.today =
      date.getFullYear() + "-" + (date.getMonth() + 1) + "-" + date.getDate();

    this.init();
  },
  watch: {
    onshowper(nu) {
      if (nu == 100) this.progress_status = "success";
    },
    onshowper1(nu) {
      if (nu == 100) this.progress_status1 = "success";
    },
    onshowper2(nu) {
      if (nu == 100) this.progress_status2 = "success";
    },
  },
  computed: {},
  methods: {
    ...mapGetters(["getStore"]),
    ...mapMutations([]),
    ...mapActions([]),
    async init() {
      const Response = await fetch(this.api + "/student/courseinfo", {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
          Authorization: this.getStore().Authorization,
        },
        body: this.getStore().id,
      }).then((res) => res.json());
      console.log("*********Response:", Response);
      var sum = 0;
      for (let i = 1; i <= 10; ++i) {
        sum += Response["w" + i];
      }
      this.onshowper = sum * 10;
      this.onshowper1 = Response.score;
      this.onshowper2 = Response.final_score;
    },
  },
};
</script>

<style lang="scss" scoped>
:deep(#pageFooter) {
  background-color: #ffffff;
  text-align: right;
  line-height: 60px;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.12), 0 0 6px rgba(0, 0, 0, 0.04);
}
:deep(#pageMain) {
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.12), 0 0 6px rgba(0, 0, 0, 0.04);
  background-color: #ffffff;
  display: flex;
  flex-direction: column;
}
:deep(#aa) {
  flex: 1;
  align-content: center;
}
:deep(#cc) {
  flex: 1;
  align-content: center;
}
</style>
