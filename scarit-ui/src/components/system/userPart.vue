<!--
 * @Author: xinxin 949022601@qq.com
 * @Date: 2022-08-28 21:32:42
 * @LastEditors: xinxin 949022601@qq.com
 * @LastEditTime: 2022-08-29 09:53:08
 * @FilePath: \scarit-ui\src\components\system\userPart.vue
 * @Description: 这是默认设置,请设置`customMade`, 打开koroFileHeader查看配置 进行设置: https://github.com/OBKoro1/koro1FileHeader/wiki/%E9%85%8D%E7%BD%AE
-->
<template>
  <el-form :inline="true" :model="queryParam" class="demo-form-inline">
    <el-form-item label="用户名:">
      <el-input v-model="queryParam.userName" placeholder="用户名" />
    </el-form-item>
    <el-form-item label="昵称:">
      <el-input v-model="queryParam.nickName" placeholder="昵称" />
    </el-form-item>
    <el-form-item>
      <el-button type="primary" @click="onSubmit">查询</el-button>
    </el-form-item>
  </el-form>
  <div class="flex">
    <el-button type="primary" :icon="Edit" v-hasRole="['admin']" />
    <el-button type="primary" :icon="Share" />
    <el-button type="primary" :icon="Delete" />
    <el-button type="primary" :icon="Search">Search</el-button>
    <el-button type="primary">
      Upload<el-icon class="el-icon--right"><Upload /></el-icon>
    </el-button>
  </div>

  <el-table ref="tableRef" row-key="userName" :data="tableData" style="width: 100%">
    <el-table-column prop="userName" label="用户名" width="180" />
    <el-table-column prop="nickName" label="昵称" width="180" />
    <el-table-column prop="email" label="邮箱" />
  </el-table>
  <el-pagination
    background
    layout="prev, pager, next"
    @current-change="changePage"
    :total="total"
    :page-sizes="[2, 5, 10, 15]"
    :page-size="queryParam.size"
  />
</template>

<script setup>
import { ref } from "vue";
import { ElTable } from "element-plus";
import { listUser } from "@/api/user";
import { onMounted } from "vue";
import { Delete, Edit, Search, Share, Upload } from "@element-plus/icons-vue";

const queryParam = ref({
  size: 2,
  page: 0,
  userName: "",
  nickName: "",
});

const tableData = ref([]);
const total = ref(0);

const getList = function () {
  listUser(queryParam.value).then((res) => {
    tableData.value = res.data.content;
    total.value = res.data.totalElements;
    queryParam.value.size = res.data.size;
  });
};

onMounted(() => {
  getList();
});
const changePage = async function (current) {
  queryParam.value.page = current - 1;
  getList();
};

const onSubmit = function () {
  getList();
};
</script>
