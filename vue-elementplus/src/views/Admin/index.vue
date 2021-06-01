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
                <i class="el-icon-s-custom"></i>
                <template #title>管理员管理</template>
              </el-menu-item>
              <el-menu-item :route="menuRoutes[2]" index="3">
                <i class="el-icon-user-solid"></i>
                <template #title>教师管理</template>
              </el-menu-item>
              <el-menu-item :route="menuRoutes[3]" index="4">
                <i class="el-icon-user"></i>
                <template #title>学生管理</template>
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
import GLOBAL from "@/config/global_variable";
import { mapGetters, mapMutations, mapActions } from "vuex";
export default {
  name: "AdminIndex",
  components: {},
  mounted() {
    this.$router.push("/admin/home");
  },
  data() {
    return {
      state: this.$store.state,
      api: GLOBAL.apiBaseUrl,
      menuRoutes: {
        0: "/admin/home",
        1: "/admin/adminsmanage",
        2: "/admin/teachersmanage",
        3: "/admin/studentsmanage",
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
