const resolve = (dir) => require("path").join(__dirname, dir);
module.exports = {
  lintOnSave: false,

  devServer: {
    overlay: {
      warning: false,
      errors: true,
    },
  },

  pluginOptions: {
    "style-resources-loader": {
      preProcessor: "sass",
      patterns: [],
    },
  },
  configureWebpack: (config) => {
    if (process.env.NODE_ENV === "production") {
      return {
        resolve: {
          alias: {
            "@": resolve("src"), //配置src目录的别名
            v: resolve("src/views"),
          }, //配置公共组件目录的别名
        },
      };
      // 为生产环境修改配置...
    } else {
      return {
        resolve: {
          alias: {
            "@": resolve("src"), //配置src目录的别名
            v: resolve("src/views"),
          }, //配置公共组件目录的别名
        },
      };
    }
  },
};
