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
      // 为生产环境修改配置...
    } else {
      // 为开发环境修改配置...
    }
  },
};
