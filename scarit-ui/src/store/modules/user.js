/*
 * @Author: xinxin 949022601@qq.com
 * @Date: 2022-08-25 16:07:15
 * @LastEditors: xinxin 949022601@qq.com
 * @LastEditTime: 2022-08-30 15:18:11
 * @FilePath: \scarit-ui\src\store\modules\user.js
 * @Description: 这是默认设置,请设置`customMade`, 打开koroFileHeader查看配置 进行设置: https://github.com/OBKoro1/koro1FileHeader/wiki/%E9%85%8D%E7%BD%AE
 */
import {
    login,
    logout,
    getInfo
} from '@/api/user.js'
import storage from '@/utils/storage.js'
const user = {
    state: {
        username: '',
        nickname: '',
        token: '',
        roles: [],
        permissions: [],
    },
    getters: {
        isLogin(state) {
            return state.username !== '' && state.token !== '';
        },
        permissions(state) {
            return state.permissions;
        },
        roles(state) {
            return state.roles;
        }
    },
    mutations: {
        SAVE_USERNAME(state, username) {
            state.username = username;
        },
        SAVE_NICKNAME(state, nickname) {
            state.nickname = nickname;
        },
        SAVE_TOKEN(state, token) {
            state.token = token;
        },
        SAVE_ROLES(state, roles) {
            state.roles = roles;
        },
        SAVE_PERMISSIONS(state, permissions) {
            state.permissions = permissions;
        },
    },
    actions: {
        LOGIN({
            commit
        }, user) {
            return new Promise(function (resolve) {
                login(user).then(res => {
                    console.log(res.data)
                    //需要将获取 的数据保存起来
                    commit("SAVE_USERNAME", res.data.exerciseUser.userName);
                    commit("SAVE_NICKNAME", res.data.exerciseUser.nickName);
                    commit("SAVE_TOKEN", res.data.token);
                    storage.saveSessionObject("loginUser", res.data);
                    resolve(res);
                })
            })
        },
        LOGOUT({
            commit
        }) {
            return new Promise(function (resolve) {
                logout().then(res => {
                    commit("SAVE_USERNAME", '');
                    commit("SAVE_NICKNAME", '');
                    commit("SAVE_TOKEN", '');
                    storage.remove("loginUser");
                    resolve(res);
                })
            })
        },
        GET_INFO({
            commit
        }) {
            return new Promise((resolve) => {
                getInfo().then(res => {
                    console.log(res);
                    console.log(res.data.roles);
                    console.log(res.data.prems);
                    commit("SAVE_ROLES", res.data.roles);
                    commit("SAVE_PERMISSIONS", res.data.prems);
                    resolve();

                })
            })
        },
        RECOVERY_USER({
            commit
        }) {
            //从storage中获取数据
            let loginUser = storage.getSessionObject("loginUser");
            if (loginUser) {
                commit("SAVE_USERNAME", loginUser.exerciseUser.userName);
                commit("SAVE_NICKNAME", loginUser.exerciseUser.nickName);
                commit("SAVE_TOKEN", loginUser.token);
            }
        }
    }
}

export default user