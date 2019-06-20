<template>
  <div class="container">
    <Hearder/>
    <div class="ui-content">
      <el-row>
        <el-col :span="16">
          <div v-for="item in list" :key="item.id" class="ui-article" @click="goArticle(item.id)">
            <div class="ui-title">{{item.title}}</div>
            <div class="ui-time"><i class="el-icon-date"></i>{{new Date(item.created).format('yyyy年MM月dd日')}}</div>
            <div class="ui-summary">{{item.summary}}</div>
          </div>

          <el-pagination
            class="ui-pagination"
            background
            layout="prev, pager, next"
            :page-size="limit"
            :total="total"
            @current-change="fetchData"></el-pagination>
        </el-col>
        <el-col :span="8">
          <div class="ui-tags">
            <el-tag v-for="item in categorys.data" :key="item.id" :type="comsys.type()">{{ item.name }}</el-tag>
          </div>
          <div class="ui-tags">
            <el-tag v-for="item in tags.data" :key="item.id" :type="comsys.type()">{{ item.name }}</el-tag>
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
          "GL,互联网,自媒体,GL博客,新鲜科技,科技博客,独立博客,个人博客,原创博客,前端,前端开发,全栈,全栈开发"
      },
      { hid: "author", content: "GL" }
    ],
  },
  data() {
    return {
      total: 0,
      limit: 6,
      list: []
    };
  },
  async asyncData({ $axios }) {
    const categorys = await $axios.$get("/category/list");
    const tags = await $axios.$get("/tag/list");
    return { categorys, tags };
  },
  created() {
    this.fetchData();
  },
  methods: {
    async fetchData(page) {
      page = (page - 1) | 0;
      const res = await this.$axios.$post("/article/list", {
        page: page,
        size: this.limit
      });
      this.list = res.data;
      this.total = res.total;
    },
    goArticle(id) {
      this.$router.push(`/p/${id}`);
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
}
.ui-article {
  margin: 10px;
  padding: 10px;
  border: 1px solid #eeeeee;
  line-height: 30px;
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
  border-radius: 4px;
  line-height: 44px;
}
.el-tag {
  margin-right: 10px;
  cursor: pointer;
}
.ui-pagination {
    text-align: center;
    margin: 10px;
}
</style>
