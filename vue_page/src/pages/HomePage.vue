<script setup>
import {getResource, logout} from "@/api";
import {ElMessage} from "element-plus";
import {useRouter} from "vue-router";

const res = await getResource()
const router = useRouter()
const handleLogout = ()=>{
  logout().then(res=>{
    if (res.data.success){
      ElMessage({
        message: '退出成功!',
        type: 'success',
      })
      router.push("/login")
    }
  }).catch(error=>{
    ElMessage({
      message: `error:${error}`,
      type: 'error',
    })
  })

}

</script>
<template>
  <div class="container">
    系统主页V1.0
    <el-button type="warning" @click="handleLogout">退出登陆</el-button>
    <ul>
      <li v-for="(resource,index) in res.data.data.resources" :key="index">
        <a href="">{{resource}}</a>
      </li>
    </ul>
  </div>

</template>

<style>

</style>