<template>
  <div class="header">
    <div class="title">
      <nuxt-link to="/">GL's Blog</nuxt-link>
    </div>
  </div>
</template>

<style lang="less" scoped>
.header {
  border-bottom: 1px solid #eeeeee;
  background-color: #ffffff;
  box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1);
  .title {
    height: 60px;
    width: 1126px;
    margin: auto;
    @media screen and (max-width: 800px) {
      width: 100%;
    }
    a {
      font-size: 35px;
      color: #00b894;
      text-decoration: none;
      line-height: 60px;
      @media screen and (max-width: 800px) {
        padding-left: 10px;
      }
    }
  }
}
</style>

