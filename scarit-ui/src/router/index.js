/*
 * @Author: xinxin 949022601@qq.com
 * @Date: 2022-08-25 16:04:23
 * @LastEditors: xinxin 949022601@qq.com
 * @LastEditTime: 2022-08-30 09:26:38
 * @FilePath: \scarit-ui\src\router\index.js
 * @Description: 这是默认设置,请设置`customMade`, 打开koroFileHeader查看配置 进行设置: https://github.com/OBKoro1/koro1FileHeader/wiki/%E9%85%8D%E7%BD%AE
 */
// 导入用来创建路由和确定路由模式的两个方法
import store from '@/store'
import storage from '@/utils/storage'
import {
    createRouter,
    createWebHistory
} from 'vue-router'

/**
 * 定义路由信息
 * 
 */
const routes = [
    {
        name: 'userLogin',
        path: '/login',
        component: () => import('@/components/login'),
    },
    {
        name: 'main',
        path: '/main',
        component: () => import('@/components/main'),
        children:[
            {
                name: 'user',
                path: '/user',
                component: () => import('@/components/system/userPart'),
            },
        ]
    },
]

// 创建路由实例并传递 `routes` 配置
// 我们在这里使用 html5 的路由模式，url中不带有#，部署项目的时候需要注意。
const router = createRouter({
    history: createWebHistory(),
    routes,
})


// 全局的路由守卫
router.beforeEach((to) => {
 //每次进行路由切换都判断一下有没有登录，如果没有登录则路由到登录页面，否则放行
    console.log(to)
    //1.如果去的是登录页面就放行
    if(to.name === 'userLogin'){
        return true;
    }
    //2.检查是否登录，如果已经登录则放行
    if(!store.getters.isLogin){
        //去storage查看,如果也没有就去登录页面
        if(!storage.getSessionObject("loginUser")){
        router.push({name:'userLogin'})
        }
    else{
        store.dispatch("RECOVERY_USER");
        store.dispatch("GET_INFO");
        }
    }
    //3.没有登录则跳转至登陆页面
})

// 讲路由实例导出
export default router