<template>
  <div>
    <nuxt />
  </div>
</template>

<style>
html {
  font-family: 'Source Sans Pro', -apple-system, BlinkMacSystemFont, 'Segoe UI',
    Roboto, 'Helvetica Neue', Arial, sans-serif;
  font-size: 16px;
  word-spacing: 1px;
  -ms-text-size-adjust: 100%;
  -webkit-text-size-adjust: 100%;
  -moz-osx-font-smoothing: grayscale;
  -webkit-font-smoothing: antialiased;
  box-sizing: border-box;
}
body {
  margin: 0px;
}

.hljs-line-numbers {
  text-align: right;
  border-right: 1px solid #ccc !important;
  margin-right: 10px !important;
  padding-right: 5px !important;
  color: #999;
  -webkit-touch-callout: none;
  -webkit-user-select: none;
  -khtml-user-select: none;
  -moz-user-select: none;
  -ms-user-select: none;
  user-select: none;
}
.el-pager li,.el-pagination.is-background .btn-next, .el-pagination.is-background .btn-prev   {
  padding: 0px;
  -webkit-box-shadow: 0 2px 4px -1px rgba(0,0,0,.2), 0 4px 5px 0 rgba(0,0,0,.14), 0 1px 10px 0 rgba(0,0,0,.12);
  box-shadow: 0 2px 4px -1px rgba(0,0,0,.2), 0 4px 5px 0 rgba(0,0,0,.14), 0 1px 10px 0 rgba(0,0,0,.12);
}
.el-pagination.is-background .el-pager li, .el-pagination.is-background .btn-next, .el-pagination.is-background .btn-prev {
  border-radius: 50%;
}
.fa {
  font-size: 14px;
  padding-right: 6px;
}

.el-backtop {
  position: fixed;
  background-color: #fff;
  width: 40px;
  height: 40px;
  border-radius: 50%;
  color: #00b894;
  display: flex;
  align-items: center;
  justify-content: center;
  font-size: 20px;
  box-shadow: 0 0 6px rgba(0,0,0,.12);
  cursor: pointer;
  z-index: 5;
}

.el-pagination.is-background .el-pager li:not(.disabled).active {
    background-color: #00b894;
}
</style>
