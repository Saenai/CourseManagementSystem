<template>
  <el-container>
    <el-header></el-header>
    <el-main direction="vertical">
      <el-avatar :size="256" :src="circleUrl"></el-avatar>
      <h2>{{ state.name }}</h2>
      <h4>用户类型：管理员</h4>
    </el-main>
  </el-container>
</template>

<script type="text/javascript">
import GLOBAL from "@/config/global_variable";
import { mapGetters, mapMutations, mapActions } from "vuex";
export default {
  name: "AdminHome",
  components: {},
  data() {
    return {
      state: this.$store.state,
      api: GLOBAL.apiBaseUrl,
      circleUrl:
        "https://fuss10.elemecdn.com/e/5d/4a731a90594a4af544c0c25941171jpeg.jpeg",
    };
  },
  watch: {},
  methods: {
    ...mapGetters([]),
    ...mapMutations([]),
    ...mapActions([]),
  },
};
</script>

<style lang="scss" scoped></style>
