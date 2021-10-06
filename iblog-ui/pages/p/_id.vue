<template>
  <div class="container">
    <Hearder/>
    <div class="ui-content">
      <div class="ui-title">
        <div>{{ title }}</div>
        <div class="ui-time">发布时间：{{ new Date(data.created).format('yyyy年MM月dd日')}} <span v-if="data.tag">标签：{{ data.tag.name }}</span></div>
      </div>
      <div class="md markdown-body" v-html="data.content_html"></div>
      <div class="ui-comment">
        <el-form :model="entity" :rules="rules" ref="submitForm">
          <el-form-item label prop="nick_name">
            <el-input v-model="entity.nick_name" placeholder="昵称"></el-input>
          </el-form-item>
          <el-form-item label prop="content">
            <el-input type="textarea" :autosize="{ minRows: 6, maxRows: 8}" v-model="entity.content" placeholder="What do you think about"></el-input>
          </el-form-item>
          <el-form-item>
            <el-button type="success" @click="onSubmit">发表评论</el-button>
          </el-form-item> 
        </el-form> 
      </div>
      <div class="ui-last-comment" v-for="item in comments" :key="item.id">
        <div class="title">{{item.nick_name}} <span class="time">{{new Date(data.created).format('yyyy.MM.dd hh:mm')}}</span></div>
        <div class="content">{{item.content}}</div>
      </div>
    </div>
    <Footer/>
    <el-backtop></el-backtop>
  </div>
</template>

<script>
import Hearder from "~/components/Hearder.vue";
export default {
  components: {
    Hearder
  },
  data() {
    return {
      entity: {
        nick_name: '',
        content: ''
      },
      rules: {
        nick_name: [{ required: true, message: "请输入昵称", trigger: "blur" }],
        content: [{ required: true, message: "请输入评论内容", trigger: "blur" }],
      },
      comments: []
    }
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
        { hid: 'description', name: 'description', content: this.desc }
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
      ],
      script: [
        {
          src: 'https://cdn.bootcss.com/highlight.js/9.11.0/highlight.min.js'
        },
        {
          src: 'https://cdn.bootcss.com/highlightjs-line-numbers.js/1.1.0/highlightjs-line-numbers.min.js'
        }
      ],
    }
  },
  created() {
    this.getComments();
  },
  mounted() {
    if (hljs) {
      try {
        hljs.initHighlightingOnLoad();
        hljs.initLineNumbersOnLoad();
      } catch (error) {
      }
    }
  },
  methods: {
    async getComments() {
      let res = await this.$axios.$get('/comment/list', {
        params: { articleId: this.data.id }
      });
      this.comments = res.data;
    },
    async onSubmit() {
      let isValid = false;
      this.$refs['submitForm'].validate((valid) => {
          isValid = valid;
      });
      if (!isValid) {
        return;
      }
      this.entity['article_id'] = this.data.id;
      const res = await this.$axios.$post("/comment/save", this.entity);
      if (res && res.errcode == 0) {
        this.getComments();
        this.$notify({
            title: '评论成功',
            message: '发表成功',
            type: 'success'
        });
    
        this.entity = {
          nick_name: '',
          content: ''
        }
      }
    }
  }
  
};
</script>
<style lang="scss" scoped>
.container {
  margin: 0 auto;
}
.ui-content {
  width: 1126px;
  margin: 0 auto;
  overflow: auto;
  height: 100%;
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
  .ui-comment {
    margin-top: 40px;
    @media screen and (max-width: 800px) {
      padding: 10px;
    }
  }
  .ui-last-comment {
    padding: 10px 0px;
    @media screen and (max-width: 800px) {
      padding: 10px;
    }
    .title {
      color: #00b894;
      font-size: 20px;
      font-weight: bold;
    }
    .time {
      font-size: 12px;
      color: #666666;
      padding-left: 10px;
    }
    .content {
      font-size: 14px;
      padding: 5px 0px;
    }
  }
}


</style>
