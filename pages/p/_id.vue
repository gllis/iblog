<template>
  <div class="container">
    <Hearder/>
    <div class="ui-content">
      <div class="ui-title">
        <div>{{ data.title }}</div>
        <div
          class="ui-time"
        >发布时间：{{ new Date(data.created).format('yyyy年MM月dd日')}} 分类：{{ data.category.name }}</div>
      </div>

      <div class="md markdown-body" v-html="data.content_html"></div>
    </div>
    <Footer/>
  </div>
</template>

<script>
import Hearder from "~/components/Hearder.vue";

export default {
  components: {
    Hearder
  },
  head: {
    title: "GL的个人博客",
    meta: [
      {
        hid: "description",
        name: "description",
        content:
          "GL个人博客，是一个记录博主学习和成长的自媒体博客。关注于全栈技术的学习研究。"
      },
      {
        hid: "keywords",
        name: "keywords",
        content:
          "GL,互联网,自媒体,GL博客,新鲜科技,科技博客,独立博客,个人博客,原创博客,前端,前端开发,全栈,全栈开发"
      },
      { hid: "author", content: "GL" }
    ],
    link: [
      {
        rel: "stylesheet",
        href:
          "https://cdn.bootcss.com/github-markdown-css/2.10.0/github-markdown.min.css"
      }
    ]
  },
  data() {
    return {};
  },
  async asyncData({ $axios, params }) {
    const res = await $axios.$get("/article/get", {
      params: { id: params.id }
    });
    const data = res.data;
    return { data };
  }
};
</script>

<style lang="scss" scoped>
.container {
  margin: 0 auto;
}
.ui-content {
  width: 1024px;
  margin: 0 auto;
  .ui-title {
    margin: 2rem 0 3rem;
    font-size: 31px;
    font-weight: 700;
    padding-bottom: 1rem;
    color: #3d5064;
    border-bottom: 1px dashed #3d5064;
    text-align: center;
    .ui-time {
      margin: 1.5rem 0 0;
      font-size: 12px;
      font-weight: 400;
    }
  }
}
</style>
