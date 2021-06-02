<template>
  <el-container>
    <el-main id="pageMain" direction="vertical">
      <el-row id="aa">
        <el-col>
          <el-result icon="error" title="错误" :subTitle="subtitle">
            <template #extra>
              <el-button type="primary" size="medium" @click="close()"
                >回到登录页面</el-button
              >
            </template>
          </el-result>
        </el-col>
      </el-row>
      <!-- Use for test fetch with Authorization Headers -->
      <!-- <el-button class="loginButton" type="primary" @click="testFetch()"
      >Test Fetch</el-button
    > -->
    </el-main>
  </el-container>
</template>

<script type="text/javascript">
import GLOBAL from "@/config/global_variable.js";
import { mapGetters, mapMutations } from "vuex";
export default {
  name: "ErrorPage",
  data() {
    return {
      api: GLOBAL.apiBaseUrl,
      subtitle: "Error_code : " + sessionStorage.getItem("error_code"),
    };
  },
  components: {},
  methods: {
    ...mapGetters(["getStore"]),
    ...mapMutations(["clearLogin"]),
    close() {
      sessionStorage.removeItem("error_code");
      this.clearLogin();
      this.$router.push("/");
    },
    // Use for test fetch with Authorization Headers
    // async testFetch() {
    //   console.log(this.getStore().Authorization);
    //   let Response = await fetch(this.api + "/login/test", {
    //     method: "GET",
    //     headers: {
    //       "Content-Type": "application/json",
    //       Authorization: this.getStore().Authorization,
    //     },
    //   }).then((res) => res.json());
    //   console.log(Response);
    //   return Response;
    // },
  },
};
</script>

<style lang="scss" scoped>
:deep(#pageMain) {
  display: flex;
  flex-direction: column;
}
:deep(#aa) {
  flex: 1;
  align-content: center;
}
</style>
