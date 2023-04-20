<template>

  <router-link to="/login">去登陆</router-link>
  <br>
  <router-link to="/home">首页</router-link>

  <Suspense>
    <RouterView></RouterView>
  </Suspense>

</template>

<script setup>


</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}
</style>
