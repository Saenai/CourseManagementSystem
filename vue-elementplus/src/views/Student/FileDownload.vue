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
            <el-table-column width="85px" align="center" label="操作">
              <template #default="scope">
                <el-button
                  size="mini"
                  type="primary"
                  @click="handleDownload(scope.$index, scope.row)"
                  >下载</el-button
                >
              </template>
            </el-table-column>
          </el-table>
        </el-col>
      </el-row>
    </el-main>
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
