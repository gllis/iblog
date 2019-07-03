<template>
  <div class="container">
    <Hearder/>
    <div class="ui-content">
      <div class="ui-title">
        <div>{{ title }}</div>
        <div
          class="ui-time"
        >发布时间：{{ new Date(data.created).format('yyyy年MM月dd日')}} <span v-if="data.tag">标签：{{ data.tag.name }}</span></div>
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
  async asyncData({ $axios, params }) {
    let res = await $axios.$get("/article/get", {
      params: { id: params.id }
    });
    let data = res.data;
    let title = data.title;
    let desc = data.summary;
    return { title, desc, data };
  },
  head() {
    return {
      title: this.title,
      meta: [
        { hid: 'description', name: 'description', content:this.desc }
      ],
      link: [
      {
        rel: "stylesheet",
        href:
          "https://cdn.bootcss.com/github-markdown-css/2.10.0/github-markdown.min.css"
      },
      {
        rel: "stylesheet",
        href:
          "https://cdnjs.cloudflare.com/ajax/libs/highlight.js/9.12.0/styles/github.min.css"
      }
    ]
    }
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
  @media screen and (max-width: 800px) {
    width: 100%;
  }
  .ui-title {
    margin: 2rem 0 3rem;
    font-size: 31px;
    font-weight: 700;
    padding-bottom: 1rem;
    color: #3d5064;
    border-bottom: 1px dashed #3d5064;
    text-align: center;
    @media screen and (max-width: 800px) {
      margin: 8px 12px;
    }
    .ui-time {
      margin: 1.5rem 0 0;
      font-size: 12px;
      font-weight: 400;
    }
  }
  .md {
    @media screen and (max-width: 800px) {
      padding: 8px;
    }
  }
}
</style>
