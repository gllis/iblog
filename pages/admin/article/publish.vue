<template>
  <div>
    <el-form>
      <el-form-item label>
        <el-input v-model="entity.title" placeholder="标题"></el-input>
      </el-form-item>
      <el-form-item label>
        <el-input type="textarea" v-model="entity.summary" placeholder="简介"></el-input>
      </el-form-item>
      <el-form-item label="分类">
        <el-select v-model="entity.category.id" placeholder="请选择">
          <el-option
            v-for="item in categorys.data"
            :key="item.id"
            :label="item.name"
            :value="item.id"
          ></el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="标签">
        <el-select v-model="tagIds" multiple placeholder="请选择">
          <el-option v-for="item in tags.data" :key="item.id" :label="item.name" :value="item.id"></el-option>
        </el-select>
      </el-form-item>
      <el-form-item label>
        <no-ssr>
          <mavon-editor :toolbars="markdownOption" @change="getData" v-model="entity.content"/>
        </no-ssr>
      </el-form-item>
      <el-form-item size="large">
        <el-button type="primary" @click="onSubmit">发布</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>
<script>
export default {
  layout: "nav",
  data() {
    return {
      markdownOption: {
        bold: true, // 粗体
        italic: true, // 斜体
        header: true, // 标题
        underline: true, // 下划线
        strikethrough: true, // 中划线
        mark: true, // 标记
        superscript: true, // 上角标
        subscript: true, // 下角标
        quote: true, // 引用
        ol: true, // 有序列表
        ul: true, // 无序列表
        link: true, // 链接
        imagelink: true, // 图片链接
        code: true, // code
        table: true, // 表格
        readmodel: true, // 沉浸式阅读
        htmlcode: true // 展示html源码
      },
      tagIds: [],
      content: "",
      entity: {
        content: "",
        category: { id: "" }
      }
    };
  },
  mounted() {
    let data = sessionStorage.getItem("article");
    if (data) {
      sessionStorage.removeItem("article");
      this.entity = JSON.parse(data);
    }
  },
  async asyncData({ $axios }) {
    const categorys = await $axios.$get("/category/list");
    const tags = await $axios.$get("/tag/list");
    return { categorys, tags };
  },
  methods: {
    async onSubmit() {
      if (this.tagIds) {
        let tags = [];
        this.tagIds.forEach(v => {
          tags.push({ id: v });
        });
        this.entity["tags"] = tags;
      }
      if (this.content) {
        this.entity["content_html"] = this.content;
      }
      const res = await this.$axios.$post("/article/save", this.entity);
      if (res) {
        console.info(res);
      }
    },
    getData(value, render) {
      this.content = render;
    }
  }
};
</script>


