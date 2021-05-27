<template>
  <div custom-class="loginpage">
    <el-row type="flex" class="row-bg" justify="center">
      <el-col :span="10">
        <el-container>
          <el-main>
            <el-space warp direction="vertical" :size="32">
              <!-- <img alt="Vue logo" src="../assets/logo.png" /> -->
              <h1>课程管理系统</h1>
              <el-form
                :model="loginFormModel"
                status-icon
                :rules="rules"
                ref="loginFormRef"
                label-width="auto"
                custom-class="loginForm"
              >
                <el-tabs
                  type="border-card"
                  v-model="loginFormModel.role"
                  :stretch="true"
                >
                  <el-tab-pane label="学生" name="student"></el-tab-pane>
                  <el-tab-pane label="教师" name="teacher"></el-tab-pane>
                  <el-tab-pane label="管理员" name="admin"></el-tab-pane>
                  <el-form-item label="用户名" prop="id">
                    <el-input
                      v-model="loginFormModel.id"
                      prefix-icon="el-icon-user"
                    ></el-input>
                  </el-form-item>
                  <el-form-item label="密码" prop="pw">
                    <el-input
                      type="password"
                      v-model="loginFormModel.pw"
                      prefix-icon="el-icon-lock"
                    ></el-input>
                  </el-form-item>
                  <el-button
                    class="loginButton"
                    type="primary"
                    native-type="submit"
                    @click="loginFormSubmit('loginFormRef')"
                    :round="true"
                    >登录</el-button
                  >
                </el-tabs>
              </el-form>
            </el-space>
          </el-main>
        </el-container>
      </el-col>
    </el-row>
  </div>
</template>

<script type="text/javascript">
import GLOBLE from "@/config/global_variable";
export default {
  name: "LoginPage",
  components: {},
  data() {
    return {
      loginFormModel: {
        id: "",
        pw: "",
        role: "student",
      },
      rules: {
        id: [{ required: true, message: "请输入用户名", trigger: "blur" }],
        pw: [{ required: true, message: "请输入密码", trigger: "blur" }],
      },
      api: GLOBLE.apiBaseUrl,
    };
  },
  methods: {
    ...mapMutations(["changeLogin"]),
    loginFormSubmit(loginFormRef) {
      this.$refs[loginFormRef].validate(async (valid) => {
        if (valid) {
          // 検証する
          const validateResponse = await fetch(this.api + "/login/validate", {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify(this.loginFormModel),
          }).then((res) => res.json());
          // 検証した
          if (validateResponse.flag == true) {
            this.changeLogin({ Authorization: validateResponse.token });
            //セッションにログインデータを
            //   const getUserResponse=await fetch(this.api + "/login/getuser", {
            //   method: "POST",
            //   headers: { "Content-Type": "application/json" },
            //   body: JSON.stringify(this.loginFormModel),
            // }).then((res) => res.json());
            // 対応を示す
            this.$message({
              message: "欢迎，  ！",
              type: "success",
            });

            // redirect to selected role's index page
            this.$router.push("/" + this.loginFormModel.role + "/index");
          } else {
            this.$message({
              message: "用户名或密码错误。",
              type: "error",
            });
            this.loginFormModel.pw = null;
            return false;
          }
        }
      });
    },
  },
};
</script>

<style lang="scss" scoped>
:deep(.loginButton) {
  width: 200px;
}
</style>
