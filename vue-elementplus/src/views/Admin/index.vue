<template>
  <el-row>
    <el-col :span="24">
      <el-container>
        <el-header class="indexHeader">
          <h3 id="headerLabel">
            <i class="el-icon-s-platform"></i>课程管理系统 @管理员
          </h3>
          <div id="userInfo">
            <i class="el-icon-user-solid"></i>
            <span>{{ state.name }}</span
            ><el-divider direction="vertical"></el-divider>
            <el-button type="text" @click="logOut()">注销</el-button>
          </div>
        </el-header>

        <el-container>
          <el-aside width="200px"
            ><h5>Content</h5>
            <el-menu
              default-active="1"
              class="el-menu-vertical-demo"
              @open="handleOpen"
              @close="handleClose"
              router
            >
              <el-menu-item :route="menuRoutes[0]" index="1">
                <i class="el-icon-menu"></i>
                <template #title>Home</template>
              </el-menu-item>
              <el-menu-item :route="menuRoutes[1]" index="2">
                <i class="el-icon-document"></i>
                <template #title>管理员管理</template>
              </el-menu-item>
              <el-menu-item :route="menuRoutes[2]" index="3">
                <i class="el-icon-setting"></i>
                <template #title>教师管理</template>
              </el-menu-item>
              <el-menu-item :route="menuRoutes[3]" index="4">
                <i class="el-icon-setting"></i>
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
        1: "/admin/adminsmanagement",
        2: "/admin/studentsmanagement",
        3: "/admin/teachersmanagement",
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
    handleOpen(key, keyPath) {},
    handleClose(key, keyPath) {},
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
:deep(.indexHeader) {
  background-color: #ffffff;
  line-height: 60px;
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

:deep(.el-aside) {
  background: #ffffff;
}
:deep(.el-menu) {
  text-align: left;
}
</style>
