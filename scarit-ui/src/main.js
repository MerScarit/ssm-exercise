/*
 * @Author: xinxin 949022601@qq.com
 * @Date: 2022-08-25 15:59:39
 * @LastEditors: xinxin 949022601@qq.com
 * @LastEditTime: 2022-08-30 15:50:26
 * @FilePath: \scarit-ui\src\main.js
 * @Description: 这是默认设置,请设置`customMade`, 打开koroFileHeader查看配置 进行设置: https://github.com/OBKoro1/koro1FileHeader/wiki/%E9%85%8D%E7%BD%AE
 */
import { createApp } from 'vue'
import App from './App.vue'
import router from '@/router'
import store from '@/store'
import ElementPlus from 'element-plus'
import 'element-plus/dist/index.css'
import '@/assets/style/common.css'
import directives from '@/directive';

let app = createApp(App)
//全局安装组件
app.use(router).use(store).use(ElementPlus);

//安装所有的自定义指令
for(let key in directives){
    console.log(key);
    app.directive(key,directives[key]);
}

app.mount('#app')
