<!--
 * @Author: xinxin 949022601@qq.com
 * @Date: 2022-08-25 22:23:11
 * @LastEditors: xinxin 949022601@qq.com
 * @LastEditTime: 2022-08-28 15:52:05
 * @FilePath: \scarit-ui\src\components\login\index.vue
 * @Description: 这是默认设置,请设置`customMade`, 打开koroFileHeader查看配置 进行设置: https://github.com/OBKoro1/koro1FileHeader/wiki/%E9%85%8D%E7%BD%AE
-->
<template>
  <el-container>
    <el-aside width="790px"
      ><el-image fit="fill" :src="require('@/assets/image/login_left.png')"></el-image
    ></el-aside>
    <el-main>
      <el-card class="box-card login-card">
        <span class="login-title">元动力后台管理系统</span>
        <span class="login-tip">welcome 欢迎登陆</span>
        <el-form ref="user" :model="user" label-width="80px" :rules="rules">
          <el-form-item label="用户名" prop="userName">
            <el-input v-model="user.userName" placeholder="请输入用户"></el-input>
          </el-form-item>
          <el-form-item label="密码" prop="password">
            <el-input
              v-model="user.password"
              type="password"
              placeholder="请输入密码"
              show-password
            ></el-input>
          </el-form-item>
          <el-form-item>
            <el-button type="primary" @click="doLogin">登陆</el-button>
          </el-form-item>
        </el-form>
      </el-card>
    </el-main>
  </el-container>
</template>
/* eslint-disable */
<script>
import { ElMessage } from "element-plus";

export default {
  name: "userLogin",
  data() {
    return {
      // 用户信息
      url: "../../assets/image/login_left.png",
      user: {
        userName: "admin",
        password: "xxxxxx",
      },
      rules: {
        userName: [
          {
            required: true,
            message: "用户名不能为空",
            trigger: "blur",
          },
          {
            mix: 3,
            max: 10,
            message: "用户名长度应是3到10",
            trigger: "blur",
          },
        ],
        password: [
          {
            required: true,
            message: "密码不能为空",
            trigger: "blur",
          },
        ],
      },
    };
  },
  methods: {
    doLogin() {
      this.$refs.user.validate((valid) => {
        if (valid) {
          this.$store.dispatch("LOGIN", this.user).then((res) => {
            console.log(res);
            if (res.status === 200) {
              console.log(status);
              this.$store.dispatch("GET_INFO").then(() => {
                this.$router.push({ name: "main" });
              });

              //先获取一下权限信息

              /*  this.$router.push({ name: "main" }); */
              ElMessage("登陆成功 ");
            }
          });
        } else {
          ElMessage("您的数据不合法");
        }
      });
    },
  },
};
</script>

<style scope>
.el-image {
  height: 885px;
  width: 750px;
}
.el-main {
  position: relative;
}
.login-card {
  position: absolute;
  top: 0;
  left: 0;
  bottom: 0;
  right: 0;
  margin: auto;
  width: 480px;
  height: 400px;
  padding: 50px;
}
.login-title {
  width: 459px;
  height: 70px;
  font-size: 40px;
  font-family: AlibabaPuHuiTiB;
  color: #333333;
  line-height: 90px;
  letter-spacing: 1px;
  font-weight: 800;
  display: block;
  text-align: left;
}
.login-tip {
  width: 319px;
  height: 70px;
  font-size: 30px;
  font-family: PingFangSC-Regular, PingFang SC;
  font-weight: 400;
  color: #999999;
  line-height: 90px;
  letter-spacing: 1px;
  display: block;
  text-align: left;
  margin-bottom: 30px;
}
</style>
