<template>
  <el-container style="height: 100%">
    <el-main id="pageMain1">
      <el-scrollbar max-height="100%">
        <el-descriptions :column="2" border title="课程信息">
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
        </el-descriptions>
        <el-divider></el-divider>
        <h4 id="tabletitle">学生列表</h4>
        <el-table
          :height="config.tableHeight"
          :data="tableData"
          style="width: 100%"
        >
          <el-table-column type="index" width="50"> </el-table-column>
          <el-table-column property="id" label="ID">
            <template #default="scope">
              <span>{{ scope.row.id }}</span>
            </template>
          </el-table-column>
          <el-table-column property="name" label="姓名">
            <template #default="scope">
              <span>{{ scope.row.name }}</span>
            </template>
          </el-table-column>
        </el-table>
      </el-scrollbar>
    </el-main>
  </el-container>
</template>

<script type="text/javascript">
import GLOBAL from "@/config/global_variable";
import { mapGetters, mapMutations, mapActions } from "vuex";
const apiRole = GLOBAL.apiBaseUrl + "/teacher";
const apiLocal = apiRole + "/courseinfo";
export default {
  name: "TeacherCourseInfo",
  components: {},
  data() {
    return {
      state: this.$store.state,
      tableData: [{ id: "", name: "" }],
      config: {
        tableHeight: window.innerHeight - 367,
      },
    };
  },
  mounted() {
    this.init();
    window.addEventListener("resize", this.setTable);
  },
  watch: {},
  computed: {},
  methods: {
    ...mapGetters(["getStore"]),
    ...mapMutations([]),
    ...mapActions([]),
    async init() {
      const Response = await fetch(apiLocal, {
        method: "GET",
        headers: {
          "Content-Type": "application/json",
          Authorization: this.getStore().Authorization,
        },
      }).then((res) => res.json());
      this.tableData = Response;
    },
    setTabel() {
      this.config.tableHeight = window.innerHeight - 367;
    },
  },
};
</script>

<style lang="scss" scoped>
:deep(#pageMain) {
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.12), 0 0 6px rgba(0, 0, 0, 0.04);
  background-color: #ffffff;
  // display: flex;
  // flex-direction: column;
}
:deep(#pageMain1) {
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.12), 0 0 6px rgba(0, 0, 0, 0.04);
  background-color: #ffffff;
}
:deep(#tabletitle) {
  text-align: left;
}
:deep(#pageFooter) {
  background-color: #ffffff;
  text-align: right;
  line-height: 60px;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.12), 0 0 6px rgba(0, 0, 0, 0.04);
}
</style>
