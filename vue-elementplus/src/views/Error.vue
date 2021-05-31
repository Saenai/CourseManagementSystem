<template>
  <div class="errorpage">
    <h1>Error Page</h1>
    <h2>{{ error_code }}</h2>
    <el-button @click="close()">关闭</el-button>
    <!-- Use for test fetch with Authorization Headers -->
    <!-- <el-button class="loginButton" type="primary" @click="testFetch()"
      >Test Fetch</el-button
    > -->
  </div>
</template>

<script type="text/javascript">
import GLOBAL from "@/config/global_variable.js";
import { mapGetters } from "vuex";
export default {
  name: "ErrorPage",
  data() {
    return {
      api: GLOBAL.apiBaseUrl,
      error_code: sessionStorage.getItem("error_code"),
    };
  },
  components: {},
  methods: {
    ...mapGetters(["getStore"]),
    close() {
      sessionStorage.removeItem("error_code");
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

<style lang="stylus" scoped></style>
