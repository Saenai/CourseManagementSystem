<template>
  <div class="errorpage">
    <h1>Error Page</h1>
    <!-- Use for test fetch with Authorization Headers -->
    <!-- <el-button class="loginButton" type="primary" @click="testFetch()"
      >Test Fetch</el-button
    > -->
  </div>
</template>

<script type="text/javascript">
import GLOBAL from "@/config/global_variable.js";
import { mapMutations, mapGetters } from "vuex";
export default {
  name: "ErrorPage",
  data() {
    return {
      api: GLOBAL.apiBaseUrl,
    };
  },
  components: {},
  methods: {
    ...mapGetters(["getStore"]),
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
