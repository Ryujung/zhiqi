const { defineConfig } = require('@vue/cli-service')

const name = process.env.VUE_APP_TITLE || '知其后台管理系统'
const port = process.env.port || process.env.npm_confgi_port || 80

module.exports = defineConfig({
  publicPath: process.env.NODE_ENV === 'production' ? '/' : '/',
  transpileDependencies: true,
  outputDir: 'dist',
  assetsDir: 'static',
  lintOnSave: process.env.NODE_ENV === 'development',
  // TODO finish the config and first run the project.
})
