/*
 * @Author: xinxin 949022601@qq.com
 * @Date: 2022-08-28 09:11:49
 * @LastEditors: xinxin 949022601@qq.com
 * @LastEditTime: 2022-08-28 09:23:19
 * @FilePath: \scarit-ui\src\utils\storage.js
 * @Description: 这是默认设置,请设置`customMade`, 打开koroFileHeader查看配置 进行设置: https://github.com/OBKoro1/koro1FileHeader/wiki/%E9%85%8D%E7%BD%AE
 */
export default {
    saveSessionString(key,value){
        window.sessionStorage.setItem(key,value)
    },
    getSessionString(key){
        return window.sessionStorage.getItem(key);
    },
    saveSessionObject(key,value){
        window.sessionStorage.setItem(key,JSON.stringify(value));
    },
    getSessionObject(key){
        return JSON.parse(window.sessionStorage.getItem(key));
    },
    remove(key){
        return window.sessionStorage.removeItem(key);
    }
}