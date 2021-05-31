<template>
  <el-container>
    <el-main id="pageMain">
      <el-table stripe :data="tableData" style="width: 100%" height="100%">
        <el-table-column label="ID">
          <template #default="scope">
            <span>{{ scope.row.id }}</span>
          </template>
        </el-table-column>
        <el-table-column label="姓名">
          <template #default="scope">
            <span>{{ scope.row.name }}</span>
          </template>
        </el-table-column>
        <el-table-column label="密码">
          <template #default="scope">
            <span>{{ scope.row.pw }}</span>
          </template>
        </el-table-column>
        <el-table-column width="170px" align="center" label="操作">
          <template #default="scope">
            <el-button size="mini" @click="handleEdit(scope.$index, scope.row)"
              >编辑</el-button
            >
            <el-button
              size="mini"
              type="danger"
              @click="handleDelete(scope.$index, scope.row)"
              >删除</el-button
            >
          </template>
        </el-table-column>
      </el-table>
    </el-main>
    <el-footer id="pageFooter">
      <el-button type="primary" @click="dialogVisible = true">新 建</el-button>
    </el-footer>
  </el-container>
  <!-- Dialog -->
  <el-dialog
    title="新建用户"
    v-model="dialogVisible"
    width="512px"
    :before-close="handleClose"
  >
    <span
      ><el-form
        :model="addFormModel"
        status-icon
        :rules="rules"
        ref="addFormRef"
        label-width="50px"
      >
        <el-form-item label="ID" prop="id">
          <el-input v-model="addFormModel.id" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="姓名" prop="name">
          <el-input v-model="addFormModel.name" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="密码" prop="pw">
          <el-input
            v-model.number="addFormModel.pw"
            autocomplete="off"
          ></el-input>
        </el-form-item> </el-form
    ></span>
    <template #footer>
      <span class="dialog-footer">
        <el-button @click="dialogVisible = false">取 消</el-button>
        <el-button type="primary" @click="submitForm('addFormRef')"
          >确 定</el-button
        >
      </span>
    </template>
  </el-dialog>
</template>

<script type="text/javascript">
import GLOBAL from "@/config/global_variable";
import { mapGetters, mapMutations, mapActions } from "vuex";
export default {
  name: "AdminAdminsManage",
  components: {},
  data() {
    return {
      state: this.$store.state,
      api: GLOBAL.apiBaseUrl,
      tableData: [{}],
      dialogVisible: false,
      addFormModel: { id: "", name: "", pw: "" },
      rules: {
        id: [{ required: true, message: "ID是必填项", trigger: "blur" }],
        name: [{ trigger: "blur" }],
        pw: [{ trigger: "blur" }],
      },
    };
  },
  mounted() {
    this.getTableData();
  },
  watch: {},
  methods: {
    ...mapGetters(["getStore"]),
    ...mapMutations([]),
    ...mapActions([]),
    async getTableData() {
      let Response = await fetch(this.api + "/admin/admins", {
        method: "GET",
        headers: {
          "Content-Type": "application/json",
          Authorization: this.getStore().Authorization,
        },
      }).then((res) => res.json());
      this.tableData = Response;
    },
    handleEdit(index, row) {
      console.log(index, row);
    },
    handleDelete(index, row) {
      console.log(index, row);
    },
    handleClose(done) {
      this.$confirm("确认关闭？")
        .then((_) => {
          this.$refs["addFormRef"].resetFields();
          done();
        })
        .catch((_) => {});
    },
    submitForm(formRef) {
      this.$refs[formRef].validate(async (valid) => {
        if (valid) {
          const addResponse = await fetch(this.api + "/admin/admins", {
            method: "POST",
            headers: {
              "Content-Type": "application/json",
              Authorization: this.getStore().Authorization,
            },
            body: JSON.stringify(this.addFormModel),
          }).then((res) => res.json());
          this.dialogVisible = false;
          this.$refs["addFormRef"].resetFields();
          //Insertが成功すると、returnは
          if (addResponse > 0) {
            this.$message({
              message: "数据添加成功",
              type: "success",
            });
          } else {
            this.$message({
              message: "数据添加失败",
              type: "error",
            });
          }
          this.getTableData();
        } else {
          console.log("error submit!!");
          return false;
        }
      });
    },
  },
};
</script>

<style lang="scss" scoped>
:deep(.el-container) {
  background-color: #ffffff;
}
:deep(#pageFooter) {
  background-color: #ffffff;
  text-align: right;
  line-height: 60px;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.12), 0 0 6px rgba(0, 0, 0, 0.04);
}
:deep(#pageMain) {
  padding: 0;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.12), 0 0 6px rgba(0, 0, 0, 0.04);
}
</style>
