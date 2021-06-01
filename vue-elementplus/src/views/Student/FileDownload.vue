<template>
  <el-container>
    <el-footer></el-footer>
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
export default {
  name: "StudentFileDownload",
  components: {},
  data() {
    return { state: this.$store.state, api: GLOBAL.apiBaseUrl };
  },
  watch: {},
  methods: { ...mapGetters([]), ...mapMutations([]), ...mapActions([]) },
};
</script>

<style lang="scss" scoped></style>
