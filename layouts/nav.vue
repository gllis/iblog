<template>
  <div>
    <el-container>
        <el-header>
            <div class="title">iBlog Admin</div>
        </el-header>
        <el-container class="ui-container">
            <el-aside width="200px">
                <el-menu @select="selectMenu">
                    <el-menu-item index="/admin/article/publish">
                        <i class="el-icon-menu"></i>
                        <span slot="title">发布文章</span>
                    </el-menu-item>
                    <el-menu-item index="/admin/article/list">
                        <i class="el-icon-menu"></i>
                        <span slot="title">文章列表</span>
                    </el-menu-item>
                    <el-menu-item index="/admin/category">
                        <i class="el-icon-document"></i>
                        <span slot="title">分类</span>
                    </el-menu-item>
                    <el-menu-item index="/admin/tag">
                        <i class="el-icon-setting"></i>
                        <span slot="title">标签</span>
                    </el-menu-item>
                </el-menu>
            </el-aside>
            <el-main>
                 <nuxt/>
            </el-main>
        </el-container>
        <el-footer>
            <span>Copyright © 2019. All rights reserved.</span>
        </el-footer>
    </el-container>
   
  </div>
</template>

<script>
export default {
    methods: {
        selectMenu(index) {
            this.$router.push(index);
        }
    }
}
</script>

<style lang="scss" scoped>
.el-header {
    border-bottom: 1px solid #eee;
    line-height: 60px;
    .title {
        margin: auto;
        width: 1306px;
        line-height: 60px;
        font-weight: bold;
    }
}

.ui-container {
    margin: auto;
    width: 1306px;
    min-height: 80vh;
    display: flex;
    justify-content: center;
}
.el-menu  {
    height: 100%;
}
.el-footer {
    border-top: 1px solid #eee;
    line-height: 60px;
}
</style>