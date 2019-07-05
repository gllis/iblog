<template>
  <div class="container">
    <Hearder/>
    <div class="ui-content">
      <el-row>
        <el-col class="ui-article-view" :span="15" v-loading="loading" :class="{ pt: loading }">
          <div v-for="item in list" :key="item.id" class="ui-article">
            <nuxt-link :to="'/p/' + item.id">
              <div class="ui-title">{{item.title}}</div>
              <div class="ui-time"><i class="el-icon-date"></i>{{new Date(item.created).format('yyyy年MM月dd日')}}</div>
              <div class="ui-summary">{{item.summary}}</div>
            </nuxt-link>
          </div>
          <el-pagination
            v-if="!loading && total > 0"
            class="ui-pagination"
            background
            layout="prev, pager, next"
            :page-size="limit"
            :total="total"
            @current-change="fetchData"></el-pagination>
        </el-col>
        <el-col :span="8">
          <div class="ui-tags ui-about">
            <div class="title">About Me</div>
            <div class="content">
              <p>网名：GL</p> 
              <p>职业：全栈工程师</p> 
              <p>邮箱：admin@gllis.com</p>
            </div>
          </div>
          <div class="ui-tags">
            <el-tag v-for="item in tags.data" :key="item.id" :type="comsys.type()">
              <nuxt-link :to="item.name">{{ item.name }}</nuxt-link>
            </el-tag>
          </div>
        </el-col>
      </el-row>
    </div>
    <Footer/>
  </div>
</template>

<script>
import Hearder from "~/components/Hearder.vue";
import Footer from "~/components/Footer.vue";

export default {
  components: {
    Hearder,
    Footer
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
          "GL,互联网,自媒体,GL博客,新鲜科技,科技博客,独立博客,个人博客,原创博客,全栈,全栈开发"
      },
      { hid: "author", content: "GL" }
    ],
  },
  data() {
    return {
      loading: false,
      limit: 6
    };
  },
  async asyncData({ $axios, params }) {
    const tag = params.tag;
    const tags = await $axios.$get("/tag/list");
    let para = { page: 0, size: 6}
    if (tag) {
      tags.data.forEach(e => {
        if (e.name == tag) {
          para['obj'] = e.id;
          return;
        }
      });
    }
    const res = await $axios.$post("/article/list", para);
    const list = res.data;
    const total = res.total;
    return { tag, tags, list, total };
  },
  methods: {
    async fetchData(page) {
      page = page - 1 | 0
      let params = {
        page: page,
        size: this.limit
      }
      if (this.tag) {
        this.tags.data.forEach(e => {
          if (e.name == this.tag) {
            params['obj'] = e.id;
            return;
          }
        });
      }
      const res = await this.$axios.$post("/article/list", params);
      this.list = res.data;
      this.total = res.total;
    }
  }
};
</script>

<style lang="scss" scoped>
.container {
  margin: 0 auto;
  background-color: #f5f6f7;
}

.el-col-15 {
    @media screen and (max-width: 800px) {
      width: 100%;
    }
}

.ui-content {
  width: 1024px;
  margin: 0 auto;
  transform: .3;
  @media screen and (max-width: 800px) {
    width: 100%;
  }
  .ui-article-view {
    min-height: 80vh;
  }
}
.pt {
  margin: 10px;
}
.ui-article {
  margin: 10px;
  padding: 10px;
  border: 1px solid #eeeeee;
  line-height: 30px;
  background-color: #ffffff;
  box-shadow: 0 2px 12px 0 rgba(0,0,0,.1);
  a {
    text-decoration: none;
    color: inherit;
  }
  cursor: pointer;
  .ui-title {
    font-weight: bold;
    font-size: 1.5em;
    color: #3d5064;
    padding: 8px auto;
  }
  .ui-time i {
    padding-right: 6px;
  }
  .ui-time {
    color: #999999;
  }
  .ui-summary {
    overflow: hidden;
    text-overflow: ellipsis;
    white-space: nowrap;
  }
}
.ui-tags {
  margin: 10px;
  padding: 10px;
  border: 1px solid #eeeeee;
  background-color: #ffffff;
  border-radius: 4px;
  line-height: 44px;
  box-shadow: 0 2px 12px 0 rgba(0,0,0,.1);
  a {
    text-decoration: none;
    color: inherit;
  }
  @media screen and (max-width: 800px) {
    display: none;
  }
}
.el-tag {
  margin-right: 10px;
  cursor: pointer;
}
.ui-about {
  .title {
    text-align: center;
    position: relative;
    font-size: 20px;
    font-weight: 700;
    box-sizing: border-box;
  }
  .title::before, .title::after {
    content: "";
    width: 30%;
    height: 1px;
    background: #999;
    position: absolute;
    top: 21px;
  }
  .title::before {
    left: 0;
  }
  .title::after{
    right: 0;
  }
  .content p {
    color: #555;
    padding: 2px;
    text-indent: 10px;
    background: #f6f6f6;
    border-radius: 6px;
    line-height: 26px;
  }

}
.ui-pagination {
    text-align: center;
    margin: 10px;
}
</style>
