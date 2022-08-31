/*
 * @Author: xinxin 949022601@qq.com
 * @Date: 2022-08-25 16:11:15
 * @LastEditors: xinxin 949022601@qq.com
 * @LastEditTime: 2022-08-30 09:56:28
 * @FilePath: \scarit-ui\src\api\user.js
 * @Description: 这是默认设置,请设置`customMade`, 打开koroFileHeader查看配置 进行设置: https://github.com/OBKoro1/koro1FileHeader/wiki/%E9%85%8D%E7%BD%AE
 */
import request from "@/api";

// 新增用户
// 查询用户列表
/* export function listUser(query) {
    return request({
        url: '/user',
        method: 'get',
        params: query
    })
}

// 新增用户
export function addUser(data) {
    return request({
        url: '/user',
        method: 'post',
        data: data
    })
}
 */
// 用户登陆
export function login(data) {
    return request({
        url: '/login',
        method: 'post',
        data: data
    })
}
//用户登出
export function logout() {
    return request({
        url: '/logout',
        method: 'get',
    })
}
export function listUser(data) { 
    return request({
        url: '/exerciseUser',
        method: 'get',
        params: data
    })
}
export function getInfo(){
    return request({
        url: 'exerciseUser/getInfo',
        method: 'get',
    })
}
export function getById(id) {
    return request({
        url: 'exerciseUser/'+id,
        method: 'get',
    })
}
