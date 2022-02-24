<template>
  <el-config-provider :size="'small'" :zIndex="3000">
    <router-view />
  </el-config-provider>
</template>

<script lang="ts" setup>


</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  width: 100%;
  height: 100%;
  display: flex;
}
</style>
