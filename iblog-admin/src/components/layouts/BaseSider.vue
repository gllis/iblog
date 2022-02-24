<template>
  <el-row class="ui-sider">
    <el-col :span="24">
      <h5 class="ui-title">iBlog-Admin</h5>
      <el-menu
        active-text-color="#ffd04b"
        background-color="#545c64"
        class="ui-el-menu"
        :default-active="$route.path"
        text-color="#fff"
        @select="selectMenu"
      >
        <el-menu-item index="/setting">
          <template #title>
            <el-icon>
              <location />
            </el-icon>
            <span>网站设置</span>
          </template>
        </el-menu-item>
        <el-menu-item index="/article/publish">
          <el-icon>
            <icon-menu />
          </el-icon>
          <span>发布文章</span>
        </el-menu-item>
        <el-menu-item index="/article/list">
          <el-icon>
            <document />
          </el-icon>
          <span>文章列表</span>
        </el-menu-item>
        <el-menu-item index="/tag">
          <el-icon>
            <setting />
          </el-icon>
          <span>标签</span>
        </el-menu-item>
      </el-menu>
    </el-col>
  </el-row>
</template>

<script lang="ts" setup>
import {
  Location,
  Document,
  Menu as IconMenu,
  Setting,
} from '@element-plus/icons-vue'
import router from '~/router'
// 选择菜单 
const selectMenu = (index: string) => {
  router.push(index);
}
</script>

<style lang="scss" scoped>
.ui-el-menu {
  border: 1px solid #545c64;
}
.ui-sider {
  .ui-title {
    color: white;
    font-size: 16px;
  }
}
</style>
