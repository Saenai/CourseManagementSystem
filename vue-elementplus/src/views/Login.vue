<template>
  <div class="loginpage">
    <el-row type="flex" class="row-bg" justify="center">
      <el-col :span="8">
        <el-container>
          <el-main>
            <el-form
              :model="loginForm"
              status-icon
              :rules="rules"
              ref="lgoinForm"
              label-width="100px"
              class=";oginForm"
            >
              <el-tabs v-model="activeName" @tab-click="handleClick">
                <el-tab-pane label="学生" name="student"></el-tab-pane>
                <el-tab-pane label="教师" name="teacher"></el-tab-pane>
                <el-tab-pane label="管理员" name="admin"></el-tab-pane>
                <el-form-item label="用户名" prop="id">
                  <el-input v-model="loginForm.id"></el-input>
                </el-form-item>
                <el-form-item label="密码" prop="pw">
                  <el-input type="password" v-model="loginForm.pw"></el-input>
                </el-form-item>
                <el-form-item>
                  <el-button type="primary" @click="submitForm('ruleForm')"
                    >登录</el-button
                  >
                </el-form-item>
              </el-tabs>
            </el-form>
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
    var validateId = (rule, value, callback) => {
      if (value === "") {
        callback(new Error("请输入用户名"));
      } else {
        if (this.ruleForm.pw !== "") {
          this.$refs.loginForm.validateField("checkPass");
        }
        callback();
      }
    };
    var validatePw = (rule, value, callback) => {
      if (value === "") {
        callback(new Error("请输入密码"));
      } else {
        callback();
      }
    };
    return {
      activeName: "student",
      loginForm: {
        id: "",
        pw: "",
        role: "0",
      },
      rules: {
        id: [{ validator: validateId, trigger: "blur" }],
        pw: [{ validator: validatePw, trigger: "blur" }],
      },
    };
  },
  method: {
    handleClick(tab, event) {
      console.log(tab, event);
    },
  },
};
</script>

<style lang="stylus" scoped></style>
