<template>
  <div class="container">
    <Hearder/>
    <div class="ui-content">
      <el-row>
        <el-col class="ui-article-view" :span="15" v-loading="loading" :class="{ pt: loading }">
          <div v-for="item in list" :key="item.id" class="ui-article">
            <a :href="'/p/' + item.id" target="_blank">
              <div class="ui-title">{{item.title}}</div>
              <div class="ui-time">
                <i class="fa fa-clock" aria-hidden="true"></i>{{new Date(item.created).format('yyyy-MM-dd')}}
                <i class="fa fa-tag" aria-hidden="true"></i>{{item.tag.name}}
              </div>
              <div class="ui-summary">{{item.summary}}</div>
            </a>
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
          <div class="info-wrapper ui-about">
            <div class="title">博主简介</div>
            <div class="content">
              <p>网名：{{website.author}}</p> 
              <p>职业：{{website.job}}</p> 
              <p>邮箱：{{website.mail}}</p>
            </div>
            <div>
              <hr />
              <i class="fa fa-tags" aria-hidden="true"></i>标签
            </div>
            <div class="ui-tag">
              <el-tag v-for="item in tags.data" :key="item.id" :color="item.color">
                <nuxt-link :to="item.name">{{ item.name }}</nuxt-link>
              </el-tag>
            </div>
          </div>
        </el-col>
      </el-row>
    </div>
    <Footer/>
    <el-backtop></el-backtop>
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
  head() {
    return {
      title: this.website.name,
      meta: [
        {
          hid: "description",
          name: "description",
          content: this.website.description
        },
        {
          hid: "keywords",
          name: "keywords",
          content: this.website.keywords
        },
        { hid: "author", content: this.website.author }
      ],
      link: [
        {
          rel: "stylesheet",
          href: "https://cdn.bootcdn.net/ajax/libs/font-awesome/5.14.0/css/all.min.css"
        }
      ]
    }
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
    const info = await $axios.$get("/sys/get/info");
    const res = await $axios.$post("/article/list", para);
    const list = res.data;
    const total = res.total;
    if (info.data == undefined) {
      info.data = {
        name: "",
        description: "",
        keywords: "",
        author: "",
        job: "",
        mail: ""
      }
    }
    const website = info.data;
    return { website, tag, tags, list, total};
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
  width: 1126px;
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
    font-size: 1.5em;
    color: #00b894;
    padding: 8px auto;
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
.info-wrapper {
  margin: 10px;
  padding: 10px;
  border-top: 1px solid #eeeeee;
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
  color: #ffffff;
  border: 0px;
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
    margin: 30px 10px;
}
</style>
