<template>
  <el-container>
    <el-main id="pageMain">
      <el-table
        border
        stripe
        :data="tableData"
        style="width: 100%"
        height="100%"
      >
        <el-table-column align="center" label="No." width="48px">
          <template #default="scope">
            <span>{{ scope.$index }}</span>
          </template>
        </el-table-column>
        <el-table-column align="center" label="ID">
          <template #default="scope">
            <span>{{ scope.row.id }}</span>
          </template>
        </el-table-column>
        <el-table-column align="center" label="第1周"
          ><template #default="scope">
            <el-checkbox
              v-model="scope.row.w1"
              :true-label="1"
              :false-label="0"
              disabled
            ></el-checkbox> </template
        ></el-table-column>
        <el-table-column align="center" label="第2周"
          ><template #default="scope">
            <el-checkbox
              v-model="scope.row.w2"
              :true-label="1"
              :false-label="0"
              disabled
            ></el-checkbox> </template
        ></el-table-column>
        <el-table-column align="center" label="第3周"
          ><template #default="scope">
            <el-checkbox
              v-model="scope.row.w3"
              :true-label="1"
              :false-label="0"
              disabled
            ></el-checkbox> </template
        ></el-table-column>
        <el-table-column align="center" label="第4周"
          ><template #default="scope">
            <el-checkbox
              v-model="scope.row.w4"
              :true-label="1"
              :false-label="0"
              disabled
            ></el-checkbox> </template
        ></el-table-column>
        <el-table-column align="center" label="第5周"
          ><template #default="scope">
            <el-checkbox
              v-model="scope.row.w5"
              :true-label="1"
              :false-label="0"
              disabled
            ></el-checkbox> </template
        ></el-table-column>
        <el-table-column align="center" label="第6周"
          ><template #default="scope">
            <el-checkbox
              v-model="scope.row.w6"
              :true-label="1"
              :false-label="0"
              disabled
            ></el-checkbox> </template
        ></el-table-column>
        <el-table-column align="center" label="第7周"
          ><template #default="scope">
            <el-checkbox
              v-model="scope.row.w7"
              :true-label="1"
              :false-label="0"
              disabled
            ></el-checkbox> </template
        ></el-table-column>
        <el-table-column align="center" label="第8周"
          ><template #default="scope">
            <el-checkbox
              v-model="scope.row.w8"
              :true-label="1"
              :false-label="0"
              disabled
            ></el-checkbox> </template
        ></el-table-column>
        <el-table-column align="center" label="第9周"
          ><template #default="scope">
            <el-checkbox
              v-model="scope.row.w9"
              :true-label="1"
              :false-label="0"
              disabled
            ></el-checkbox> </template
        ></el-table-column>
        <el-table-column align="center" label="第10周"
          ><template #default="scope">
            <el-checkbox
              v-model="scope.row.w10"
              :true-label="1"
              :false-label="0"
              disabled
            ></el-checkbox> </template
        ></el-table-column>
        <el-table-column align="center" label="平时成绩"
          ><template #default="scope"
            ><span>{{ scope.row.score }}</span></template
          ></el-table-column
        >
        <el-table-column align="center" label="期末成绩"
          ><template #default="scope"
            ><span>{{ scope.row.final_score }}</span></template
          ></el-table-column
        >
        <el-table-column width="85px" align="center" label="操作">
          <template #default="scope">
            <el-button size="mini" @click="handleEdit(scope.$index, scope.row)"
              >编辑</el-button
            >
          </template>
        </el-table-column>
      </el-table>
    </el-main>
  </el-container>

  <!-- Edit Dialog -->
  <el-dialog
    title="编辑记录"
    v-model="dialogEditVisible"
    width="572px"
    :before-close="handleEditClose"
  >
    <span
      ><el-form
        :model="editFormModel"
        status-icon
        :rules="rules1"
        ref="editFormRef"
        label-width="72px"
      >
        <el-form-item label="ID" prop="id">
          <el-input v-model="editFormModel.id" :disabled="true">></el-input>
        </el-form-item>
        <el-form-item label="出勤" prop="wa">
          <el-checkbox
            v-model="editFormModel.w1"
            :true-label="1"
            :false-label="0"
            >第1周</el-checkbox
          >
          <el-checkbox
            v-model="editFormModel.w2"
            :true-label="1"
            :false-label="0"
            >第2周</el-checkbox
          >
          <el-checkbox
            v-model="editFormModel.w3"
            :true-label="1"
            :false-label="0"
            >第3周</el-checkbox
          >
          <el-checkbox
            v-model="editFormModel.w4"
            :true-label="1"
            :false-label="0"
            >第4周</el-checkbox
          >
          <el-checkbox
            v-model="editFormModel.w5"
            :true-label="1"
            :false-label="0"
            >第5周</el-checkbox
          >
          <el-checkbox
            v-model="editFormModel.w6"
            :true-label="1"
            :false-label="0"
            >第6周</el-checkbox
          >
          <el-checkbox
            v-model="editFormModel.w7"
            :true-label="1"
            :false-label="0"
            >第7周</el-checkbox
          >
          <el-checkbox
            v-model="editFormModel.w8"
            :true-label="1"
            :false-label="0"
            >第8周</el-checkbox
          >
          <el-checkbox
            v-model="editFormModel.w9"
            :true-label="1"
            :false-label="0"
            >第9周</el-checkbox
          >
          <el-checkbox
            v-model="editFormModel.w10"
            :true-label="1"
            :false-label="0"
            >第10周</el-checkbox
          >
        </el-form-item>
        <el-form-item label="平时成绩" prop="score">
          <el-input
            v-model.number="editFormModel.score"
            autocomplete="off"
          ></el-input>
        </el-form-item>
        <el-form-item label="期末成绩" prop="final_score">
          <el-input
            v-model.number="editFormModel.final_score"
            autocomplete="off"
          ></el-input>
        </el-form-item> </el-form
    ></span>
    <template #footer>
      <span class="dialog-footer">
        <el-button @click="handleEditClose()">取 消</el-button>
        <el-button
          type="primary"
          native-type="submit"
          @click="submitEditForm('editFormRef')"
          >编 辑</el-button
        >
      </span>
    </template>
  </el-dialog>
</template>

<script type="text/javascript">
import GLOBAL from "@/config/global_variable";
import { mapGetters, mapMutations, mapActions } from "vuex";
export default {
  name: "TeacherClassManage",
  components: {},
  data() {
    let api = GLOBAL.apiBaseUrl;
    let apiLocal = api + "/teacher/classmanage";
    return {
      state: this.$store.state,
      apiLocal: apiLocal,
      apiAdd: apiLocal,
      apiDelete: apiLocal + "/delete",
      tableData: [{}],
      dialogEditVisible: false,
      rules1: {
        score: [{ type: "number", message: "分数必须为数字值" }],
        final_score: [{ type: "number", message: "分数必须为数字值" }],
      },
      editFormModel: {
        id: "",
        w1: 0,
        w2: 0,
        w3: 0,
        w4: 0,
        w5: 0,
        w6: 0,
        w7: 0,
        w8: 0,
        w9: 0,
        w10: 0,
        score: 0,
        final_score: 0,
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
      let Response = await fetch(this.apiAdd, {
        method: "GET",
        headers: {
          "Content-Type": "application/json",
          Authorization: this.getStore().Authorization,
        },
      }).then((res) => res.json());
      this.tableData = Response;
      console.log("*********Response:", Response);
    },
    handleEdit(index, row) {
      this.editFormModel = row;
      this.dialogEditVisible = true;
    },

    handleClose(done) {
      this.$confirm("确认关闭？")
        .then(() => {
          this.dialogEditVisible = false;
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
          this.editFormModel = {
            id: "",
            w1: "0",
            w2: "0",
            w3: "0",
            w4: "0",
            w5: "0",
            w6: "0",
            w7: "0",
            w8: "0",
            w9: "0",
            w10: "0",
            score: "0",
            final_score: "0",
          };
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
:deep(#pageMain) {
  padding: 0;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.12), 0 0 6px rgba(0, 0, 0, 0.04);
}
</style>
