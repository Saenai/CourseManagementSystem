<template>
  <el-row>
    <el-col :span="24">
      <el-container>
        <el-header class="indexHeader">
          <span><h3 class="headerLabel">课程管理系统 - 管理员</h3></span>
          <div class="userInfo">
            <span>{{ state.name }}</span
            ><el-divider direction="vertical"></el-divider>
            <el-button type="text" @click="logOut()">注销</el-button>
          </div>
        </el-header>

        <el-divider></el-divider>
        <el-container>
          <el-aside width="200px">Aside</el-aside>
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
:deep(.titleLabel) {
  display: inline;
  // text-align: left;
}
:deep(.indexHeader) {
  background-color: #ffffff;
}

:deep(.userInfo) {
  display: inline;
  //   text-align: right;
}
</style>
