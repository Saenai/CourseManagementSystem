<template>
  <el-row>
    <el-col :span="24">
      <el-container>
        <el-header id="indexHeader">
          <h3 id="headerLabel">
            <i class="el-icon-s-platform"></i>课程管理系统
          </h3>
          <div id="userInfo">
            <i class="el-icon-user-solid"></i>
            <span>{{ state.name }}</span
            ><el-divider direction="vertical"></el-divider>
            <el-button type="text" @click="logOut()">注销</el-button>
          </div>
        </el-header>

        <el-container>
          <el-aside id="indexAside" width="200px">
            <!-- <h5>Content</h5> -->
            <el-menu default-active="1" class="el-menu-vertical-demo" router>
              <el-menu-item :route="menuRoutes[0]" index="1">
                <i class="el-icon-s-home"></i>
                <template #title>Home</template>
              </el-menu-item>
              <el-menu-item :route="menuRoutes[1]" index="2">
                <i class="el-icon-info"></i>
                <template #title>课程信息</template>
              </el-menu-item>
              <el-menu-item :route="menuRoutes[2]" index="3">
                <i class="el-icon-s-order"></i>
                <template #title>班级管理</template>
              </el-menu-item>
              <el-menu-item :route="menuRoutes[3]" index="4">
                <i class="el-icon-s-data"></i>
                <template #title>查看数据</template>
              </el-menu-item>
              <el-menu-item :route="menuRoutes[4]" index="5">
                <i class="el-icon-upload"></i>
                <template #title>资料发布</template>
              </el-menu-item>
            </el-menu></el-aside
          >
          <el-main> <router-view /></el-main>
        </el-container>
      </el-container>
    </el-col>
  </el-row>
</template>

<script type="text/javascript">
import { mapGetters, mapMutations, mapActions } from "vuex";
export default {
  name: "TeacherIndex",
  components: {},
  mounted() {
    this.$router.push("/teacher/home");
  },
  data() {
    return {
      state: this.$store.state,
      menuRoutes: {
        0: "/teacher/home",
        1: "/teacher/courseinfo",
        2: "/teacher/classmanage",
        3: "/teacher/dataanlyse",
        4: "/teacher/fileupload",
      },
    };
  },
  watch: {},
  methods: {
    ...mapGetters([]),
    ...mapMutations(["clearLogin"]),
    ...mapActions([]),
    logOut() {
      this.clearLogin();
      this.$router.push("/");
    },
  },
};
</script>

<style lang="scss" scoped>
:deep(#headerLabel) {
  text-align: left;
  display: inline;
  vertical-align: center;
  float: left;
  margin: 0;
}

:deep(#userInfo) {
  display: inline;
  text-align: right;
  vertical-align: center;
  float: right;
}

:deep(.el-container) {
  height: 100%;
}

:deep(#indexAside) {
  background: #ffffff;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.12), 0 0 6px rgba(0, 0, 0, 0.04);
}
:deep(.el-menu) {
  text-align: left;
}
:deep(#indexHeader) {
  color: #ffffff;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.12), 0 0 6px rgba(0, 0, 0, 0.04);
  background-color: #409eff;
  line-height: 60px;
}
:deep(#headerLabel) {
  color: #ffffff;
}
:deep(#userInfo) {
  color: #ffffff;
}
:deep(.el-header .el-button) {
  color: #e0e0e0;
}
:deep(.el-header .el-button:hover) {
  color: #ffffff;
}
</style>
