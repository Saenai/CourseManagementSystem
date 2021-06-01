<template>
  <el-container>
    <el-main id="pageMain">
      <el-table stripe :data="tableData" style="width: 100%" height="100%">
        <el-table-column label="No." width="48px">
          <template #default="scope">
            <span>{{ scope.$index }}</span>
          </template>
        </el-table-column>
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
        <el-table-column label="授权开始日">
          <template #default="scope">
            <span>{{ scope.row.dateA }}</span>
          </template>
        </el-table-column>
        <el-table-column label="授权终止日">
          <template #default="scope">
            <span>{{ scope.row.dateB }}</span>
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
        label-width="auto"
      >
        <el-form-item label="ID" prop="id">
          <el-input v-model="addFormModel.id" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="姓名" prop="name">
          <el-input v-model="addFormModel.name" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="密码" prop="pw">
          <el-input v-model="addFormModel.pw" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="授权期间" prop="date">
          <el-date-picker
            v-model="value1"
            type="daterange"
            range-separator="至"
            start-placeholder="授权开始日"
            end-placeholder="授权终止日"
            format="YYYY-MM-DD"
          >
          </el-date-picker>
        </el-form-item> </el-form
    ></span>
    <template #footer>
      <span class="dialog-footer">
        <el-button @click="handleClose()">取 消</el-button>
        <el-button
          type="primary"
          native-type="submit"
          @click="submitForm('addFormRef')"
          >确 定</el-button
        >
      </span>
    </template>
  </el-dialog>
  <!-- Edit Dialog -->
  <el-dialog
    title="编辑用户"
    v-model="dialogEditVisible"
    width="512px"
    :before-close="handleEditClose"
  >
    <span
      ><el-form
        :model="addFormModel"
        status-icon
        :rules="rules1"
        ref="editFormRef"
        label-width="auto"
      >
        <el-form-item label="ID" prop="id">
          <el-input v-model="editFormModel.id" :disabled="true">></el-input>
        </el-form-item>
        <el-form-item label="姓名" prop="name">
          <el-input v-model="editFormModel.name" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="密码" prop="pw">
          <el-input v-model="editFormModel.pw" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="授权期间" prop="date">
          <el-date-picker
            v-model="value2"
            type="daterange"
            range-separator="至"
            start-placeholder="授权开始日"
            end-placeholder="授权终止日"
            format="YYYY-MM-DD"
          >
          </el-date-picker>
        </el-form-item>
      </el-form>
    </span>
    <template #footer>
      <span class="dialog-footer">
        <el-button @click="handleEditClose()">取 消</el-button>
        <el-button type="primary" @click="submitEditForm('editFormRef')"
          >编 辑</el-button
        >
      </span>
    </template>
  </el-dialog>
</template>

<script type="text/javascript">
import GLOBAL from "@/config/global_variable";
import { mapGetters, mapMutations, mapActions } from "vuex";
var dayjs = require("dayjs");
export default {
  name: "AdminStudentsManage",
  components: {},
  data() {
    let api = GLOBAL.apiBaseUrl;
    let apiLocal = api + "/admin";
    let day = dayjs;
    return {
      state: this.$store.state,
      apiAdd: apiLocal + "/students",
      apiDelete: apiLocal + "/students/delete",
      tableData: [{}],
      dialogVisible: false,
      dialogEditVisible: false,
      addFormModel: { id: "", name: "", pw: "", dateA: "", dateB: "" },
      rules: {
        id: [{ required: true, message: "ID是必填项", trigger: "blur" }],
        name: [{}],
        pw: [{}],
      },
      rules1: {
        name: [{}],
        pw: [{}],
      },
      editFormModel: { id: "", name: "", pw: "", dateA: "", dateB: "" },
      value1: "",
      value2: "",
    };
  },
  mounted() {
    this.getTableData();
  },
  watch: {
    value1(newValue1, oldValue1) {
      this.addFormModel.dateA = dayjs(newValue1[0]).format("YYYY-MM-DD");
      this.addFormModel.dateB = dayjs(newValue1[1]).format("YYYY-MM-DD");
    },
    value2(newValue2, oldValue2) {
      this.editFormModel.dateA = dayjs(newValue2[0]).format("YYYY-MM-DD");
      this.editFormModel.dateB = dayjs(newValue2[1]).format("YYYY-MM-DD");
    },
  },
  methods: {
    ...mapGetters(["getStore"]),
    ...mapMutations([]),
    ...mapActions([]),
    async getTableData() {
      let Response = await fetch(this.apiAdd, {
        method: "GET",
        headers: {
          "Content-Type": "application/json",
          Authorization: this.getStore().Authorization,
        },
      }).then((res) => res.json());
      this.tableData = Response;
    },
    handleEdit(index, row) {
      this.editFormModel.id = row.id;
      this.editFormModel.name = row.name;
      this.editFormModel.pw = row.pw;
      this.dialogEditVisible = true;
    },
    handleDelete(index, row) {
      this.$confirm("确认删除？")
        .then(async () => {
          const Response = await fetch(this.apiDelete, {
            method: "POST",
            headers: {
              "Content-Type": "application/json",
              Authorization: this.getStore().Authorization,
            },
            body: row.id,
          }).then((res) => res.json());
          console.log("Response:", Response);
          if (Response > 0) {
            this.$message({
              message: "数据删除成功",
              type: "success",
            });
          } else {
            this.$message({
              message: "数据删除失败",
              type: "error",
            });
          }
          this.getTableData();
        })
        .catch(() => {});
    },
    handleClose(done) {
      this.$confirm("确认关闭？")
        .then(() => {
          this.dialogVisible = false;
          this.dialogEditVisible = false;
          this.$refs["addFormRef"].resetFields();
          this.$refs["editFormRef"].resetFields();
          done();
        })
        .catch(() => {});
    },
    handleEditClose(done) {
      this.$confirm("确认关闭？")
        .then(() => {
          this.$refs["editFormRef"].resetFields();
          this.editFormModel = { id: "", name: "", pw: "" };
          this.dialogEditVisible = false;
          done();
        })
        .catch(() => {});
    },
    submitForm(formRef) {
      this.$refs[formRef].validate(async (valid) => {
        if (valid) {
          const addResponse = await fetch(this.apiAdd, {
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
    submitEditForm(formRef) {
      this.$refs[formRef].validate(async (valid) => {
        if (valid) {
          const Response = await fetch(this.apiAdd, {
            method: "PUT",
            headers: {
              "Content-Type": "application/json",
              Authorization: this.getStore().Authorization,
            },
            body: JSON.stringify(this.editFormModel),
          }).then((res) => res.json());
          this.dialogEditVisible = false;
          this.$refs["editFormRef"].resetFields();
          this.editFormModel = { id: "", name: "", pw: "" };
          //Insertが成功すると、returnは
          if (Response > 0) {
            this.$message({
              message: "数据修改成功",
              type: "success",
            });
          } else {
            this.$message({
              message: "数据修改失败",
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
