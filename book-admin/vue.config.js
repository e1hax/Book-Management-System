const { defineConfig } = require('@vue/cli-service')
module.exports = defineConfig({
  transpileDependencies: true,
  lintOnSave:false,
  devServer:{
    proxy:{
      "/api":{
        target:"http://43.143.63.236:8081/",
        // target:"http://127.0.0.1:8081/",
        pathRewrite: { "^/api": "" },
      }
    }
  },
})
 