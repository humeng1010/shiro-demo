<script setup>
import { reactive } from 'vue'
import {login} from "@/api";
import {useRouter} from "vue-router"
const router = useRouter()


const formLabelAlign = reactive({
  username: '',
  password: '',
})

const handleLogin = async ()=>{
  console.log(JSON.stringify(formLabelAlign))
  const res = await login(JSON.stringify(formLabelAlign))
  console.log(res.data)
  if (res.data.success){
    router.push("/home")
  }

}

</script>
<template>
  <div class="container">
    <el-form
        label-width="100px"
        :model="formLabelAlign"
        style="max-width: 460px"
    >
      <el-form-item label="用户名">
        <el-input v-model="formLabelAlign.username" />
      </el-form-item>
      <el-form-item label="密码">
        <el-input type="password" v-model="formLabelAlign.password" />
      </el-form-item>
    </el-form>
    <el-button type="primary" plain @click="handleLogin">登陆</el-button>
  </div>

</template>

<style>
.container .el-form{
  margin: 100px auto 0;
}

</style>