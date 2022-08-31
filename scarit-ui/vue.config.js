/*
 * @Author: xinxin 949022601@qq.com
 * @Date: 2022-08-25 15:59:38
 * @LastEditors: xinxin 949022601@qq.com
 * @LastEditTime: 2022-08-28 21:14:58
 * @FilePath: \scarit-ui\vue.config.js
 * @Description: 这是默认设置,请设置`customMade`, 打开koroFileHeader查看配置 进行设置: https://github.com/OBKoro1/koro1FileHeader/wiki/%E9%85%8D%E7%BD%AE
 */
const { defineConfig } = require('@vue/cli-service')
module.exports = defineConfig({
  transpileDependencies: true,
  lintOnSave:false
})

module.exports = {
  devServer: {
      port: 80,
      proxy:'http://localhost:8088' ,
  }
}