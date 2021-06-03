<template>
  <el-container id="pageContainer">
    <el-main id="pageMain" direction="vertical">
      <el-row>
        <el-col :span="24"
          ><el-table stripe :data="DATA" style="width: 100%" height="100%">
            <el-table-column type="index" align="center" width="48px">
            </el-table-column>
            <el-table-column label="文件名">
              <template #default="scope">
                <span>{{ scope.row.originname }}</span>
              </template>
            </el-table-column>
            <el-table-column width="150px" label="文件大小">
              <template #default="scope">
                <span>{{ scope.row.size }}</span>
              </template>
            </el-table-column>
            <el-table-column width="170px" align="center" label="操作">
              <template #default="scope">
                <el-button
                  size="mini"
                  type="primary"
                  @click="handleDownload(scope.$index, scope.row)"
                  >下载</el-button
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
        </el-col>
      </el-row>
    </el-main>
    <el-footer id="pageFooter">
      <el-upload
        class="upload"
        :action="apiUpload"
        :before-upload="beforeUpload"
        :show-file-list="false"
        :on-success="onSuccess"
        :on-error="onError"
      >
        <el-button
          :disabled="!enabledUploadBtn"
          :icon="uploadBtnIcon"
          type="success"
          >{{ uploadStatus }}</el-button
        >
      </el-upload></el-footer
    >
  </el-container>
</template>

<script type="text/javascript">
import GLOBAL from "@/config/global_variable";
import { mapGetters, mapMutations, mapActions } from "vuex";
// var _ = require("lodash");
const api = GLOBAL.apiBaseUrl;
const apiRole = api + "/teacher";
const apiLocal = apiRole + "/fileupload";
export default {
  name: "TeacherFileUpload",
  components: {},
  data() {
    return {
      state: this.$store.state,
      DATA: [{}],
      apiUpload: apiLocal + "/up",
      tableData: [],
      uploadStatus: "上传文件",
      uploadBtnIcon: "el-icon-upload2",
      enabledUploadBtn: true,
    };
  },
  mounted() {
    this.init();
  },
  watch: {},
  computed: {},
  methods: {
    ...mapGetters(["getStore"]),
    ...mapMutations([]),
    ...mapActions([]),

    async init() {
      // Get Data
      const Response = await fetch(apiLocal, {
        method: "GET",
        headers: {
          "Content-Type": "application/json",
          Authorization: this.getStore().Authorization,
        },
      }).then((res) => res.json());

      function getFileSize(fileByte) {
        var fileSizeByte = fileByte;
        var fileSizeMsg = "";
        if (fileSizeByte < 1048576)
          fileSizeMsg = (fileSizeByte / 1024).toFixed(2) + "KB";
        else if (fileSizeByte == 1048576) fileSizeMsg = "1MB";
        else if (fileSizeByte > 1048576 && fileSizeByte < 1073741824)
          fileSizeMsg = (fileSizeByte / (1024 * 1024)).toFixed(2) + "MB";
        else if (fileSizeByte > 1048576 && fileSizeByte == 1073741824)
          fileSizeMsg = "1GB";
        else if (fileSizeByte > 1073741824 && fileSizeByte < 1099511627776)
          fileSizeMsg = (fileSizeByte / (1024 * 1024 * 1024)).toFixed(2) + "GB";
        else fileSizeMsg = "文件超过1TB";
        return fileSizeMsg;
      }

      Response.forEach((key) => {
        key.size = getFileSize(key.size);
      });

      this.DATA = Response;
    },
    async handleDelete(index, row) {
      const Response = await fetch(apiLocal + "/del", {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
          Authorization: this.getStore().Authorization,
        },
        body: row.originname,
      }).then((res) => res.json());
      if (Response == 1) {
        this.$message({
          message: "删除成功",
          type: "warning",
        });
      } else {
        this.$message({
          message: "删除失败",
          type: "warning",
        });
      }
      this.init();
    },
    async handleDownload(index, row) {
      const Response = await fetch(apiLocal + "/down", {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
          responseType: "blob",
          Authorization: this.getStore().Authorization,
        },
        body: row.originname,
      })
        .then((res) => res.blob())
        .then((blob) => {
          let bl = new Blob([blob], { type: "octet/stream" });
          let filename = row.originname;
          var link = document.createElement("a");
          link.href = window.URL.createObjectURL(blob);
          link.download = filename;
          link.click();
          window.URL.revokeObjectURL(link.href);
        });
      this.$message({
        message: "开始下载…",
        type: "warning",
      });
    },
    // Upload
    onSuccess(response, file, fileList) {
      this.enabledUploadBtn = true;
      this.uploadBtnIcon = "el-icon-upload2";
      this.uploadStatus = "上传文件";
      this.$message({
        message: "上传成功",
        type: "success",
      });
      this.init();
    },
    onError(err, file, fileList) {
      this.enabledUploadBtn = true;
      this.uploadBtnIcon = "el-icon-upload2";
      this.uploadStatus = "上传文件";
      this.$message({
        message: "上传失败",
        type: "error",
      });
      this.init();
    },
    beforeUpload(file) {
      this.enabledUploadBtn = false;
      this.uploadBtnIcon = "el-icon-loading";
      this.uploadStatus = "正在上传…";
      this.$message({
        message: "正在上传…",
        type: "warning",
      });
    },
  },
};
</script>

<style lang="scss" scoped>
:deep(#pageMain) {
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.12), 0 0 6px rgba(0, 0, 0, 0.04);
  background-color: #ffffff;
  display: flex;
  flex-direction: column;
}
:deep(#tabletitle) {
  text-align: left;
}
:deep(.el-row) {
  flex: 1;
}

:deep(#pageFooter) {
  background-color: #ffffff;
  text-align: right;
  line-height: 60px;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.12), 0 0 6px rgba(0, 0, 0, 0.04);
}
</style>
