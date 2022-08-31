<!-- eslint-disable vue/multi-word-component-names -->
<!--
 * @Author: xinxin 949022601@qq.com
 * @Date: 2022-08-26 09:01:19
 * @LastEditors: xinxin 949022601@qq.com
 * @LastEditTime: 2022-08-28 21:04:07
 * @FilePath: \scarit-ui\src\components\main\index.vue
 * @Description: 这是默认设置,请设置`customMade`, 打开koroFileHeader查看配置 进行设置: https://github.com/OBKoro1/koro1FileHeader/wiki/%E9%85%8D%E7%BD%AE
-->
<template>
  <el-container>
    <el-aside width="200px">
      <el-row>
        <el-col :span="24">
          <el-image
            style="width: 120px; height: 60px; display: block; margin: 40px auto"
            fit="fill"
            :src="require('@/assets/image/main_logo.png')"
          ></el-image>
          <el-menu
            active-text-color="#ffd04b"
            background-color="#191A22"
            class="el-menu-vertical-demo"
            default-active="2"
            text-color="#fff"
          >
            <el-sub-menu index="1">
              <template #title>
                <el-icon></el-icon>
                <span>系统管理</span>
              </template>

              <el-menu-item index="1-1" @click="open('user')"> 用户管理</el-menu-item>
              <el-menu-item index="1-2" @click="open('role')"> 角色管理</el-menu-item>
            </el-sub-menu>
            <el-menu-item index="2">
              <el-icon></el-icon>
              <span>客户管理</span>
            </el-menu-item>
          </el-menu>
        </el-col>
      </el-row>
    </el-aside>
    <el-container>
      <el-header
        >昵称: {{ $store.state.user.nickname }}
        <el-button color="#626aef" @click="doLogout" plain>登出</el-button></el-header
      >
      <!--进行路由跳转-->
      <el-main>
        <router-view />
      </el-main>
    </el-container>
  </el-container>
</template>
<script setup>
//组合式api
import { useStore } from "vuex";
import { useRouter } from "vue-router";
let store = useStore();
const router = useRouter();

let doLogout = function () {
  alert(1);
  store.dispatch("LOGOUT").then((res) => {
    console.log(res);
    router.push({ name: "userLogin" });
  });
};

let open = function (name) {
  router.push({ name });
};
</script>

<style scoped>
.el-container {
  height: 100%;
}

.el-header {
  margin-bottom: 20px;
  background: #e9eef3;
}

.el-footer {
  line-height: 60px;
}

.el-aside {
  background-color: #191a22;
  color: var(--el-text-color-primary);
  text-align: center;
  line-height: 200px;
}

.el-main {
  background-color: #fcfcfc;
  color: var(--el-text-color-primary);
  text-align: center;
}
</style>
