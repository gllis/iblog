<template>
  <el-container>
    <el-aside class="ui-sider"><BaseSider /></el-aside>
    <el-container>
      <el-header><BaseHeader /></el-header>
      <el-main>
        <router-view />
      </el-main>
    </el-container>
  </el-container>
</template>

<style scoped>
.ui-sider {
  background-color: #545c64;
}
</style>