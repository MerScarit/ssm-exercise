/*
 * @Author: xinxin 949022601@qq.com
 * @Date: 2022-08-25 16:10:17
 * @LastEditors: xinxin 949022601@qq.com
 * @LastEditTime: 2022-08-28 20:42:38
 * @FilePath: \scarit-ui\src\api\index.js
 * @Description: 这是默认设置,请设置`customMade`, 打开koroFileHeader查看配置 进行设置: https://github.com/OBKoro1/koro1FileHeader/wiki/%E9%85%8D%E7%BD%AE
 */
/**
 * axios的基本api
 * // 发送 POST 请求
 * axios({
 *  method: 'post',
 *  url: '/user/12345',
 *  data: {
 *    firstName: 'Fred',
 *    lastName: 'Flintstone'
 *  }
 *});
 * 
 */

 import axios from 'axios'
 import store from '@/store'

 // 创建axios实例
 const request = axios.create({
     // axios中请求配置有baseURL选项，表示请求URL公共部分
     baseURL: 'http://localhost:80/admin/',
     // 超时
     timeout: 10000,
     // 设置Content-Type，规定了前后端的交互使用json
     headers: {'Content-Type': 'application/json;charset=utf-8'}
 })
 // 添加请求拦截器
request.interceptors.request.use(function (config) {
    // 在发送请求之前做些什么
    if (store.state.user.token) {
      //'Bearer '
      config.headers['Authorization'] = store.state.user.token // 让每个请求携带自定义token 请根据实际情况自行修改
      config.headers['username'] = store.state.user.username // 让每个请求携带自定义token 请根据实际情况自行修改

    }
    return config;
  }, function (error) {
    // 对请求错误做些什么
    return Promise.reject(error);
  });

 export default request