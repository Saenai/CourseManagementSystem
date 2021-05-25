<template>
  <div custom-class="loginpage">
    <el-row type="flex" class="row-bg" justify="center">
      <el-col :span="10">
        <el-container>
          <el-main>
            <el-space warp direction="vertical" :size="32">
              <img alt="Vue logo" src="../assets/logo.png" />
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
                    <el-input v-model="loginFormModel.id"></el-input>
                  </el-form-item>
                  <el-form-item label="密码" prop="pw">
                    <el-input
                      type="password"
                      v-model="loginFormModel.pw"
                    ></el-input>
                  </el-form-item>
                  <el-form-item>
                    <el-button
                      id="loginButton"
                      type="primary"
                      @click="loginFormSubmit('loginFormRef')"
                      :round="true"
                      width="64"
                      >登录</el-button
                    >
                  </el-form-item>
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
    };
  },
  methods: {
    loginFormSubmit(loginFormRef) {
      this.$refs[loginFormRef].validate((valid) => {
        if (valid) {
          // alert("submit!");
        } else {
          // console.log("error submit!!");
          return false;
        }
      });
    },
  },
};
</script>

<style>
</style>
