<template>
  <div class="loginpage">
    <el-row>
      <el-col :span="24">
        <el-container>
          <el-main>
            <el-tabs type="border-card">
              <el-tab-pane label="用户管理">学生</el-tab-pane>
              <el-tab-pane label="配置管理">教师</el-tab-pane>
              <el-tab-pane label="角色管理">管理员</el-tab-pane>
            </el-tabs>
          </el-main>
        </el-container>
      </el-col>
    </el-row>
  </div>
</template>

<script type="text/javascript">
export default { name: "LoginPage", components: {} };
</script>

<style lang="stylus" scoped></style>
