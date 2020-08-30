<template>
  <div>
    <el-form :model="entity" :rules="rules" ref="publishForm">
      <el-form-item label prop="title">
        <el-input v-model="entity.title" placeholder="标题"></el-input>
      </el-form-item>
      <el-form-item label prop="summary">
        <el-input type="textarea" v-model="entity.summary" placeholder="简介"></el-input>
      </el-form-item>
      <el-form-item label prop="tag">
        <el-select v-model="entity.tag.id" placeholder="请选择标签">
          <el-option v-for="item in tags.data" :key="item.id" :label="item.name" :value="item.id"></el-option>
        </el-select>
      </el-form-item>
      <el-form-item label>
        <no-ssr>
          <mavon-editor
            ref="md"
            :ishljs="true"
            :toolbars="markdownOption"
            @change="getData"
            @imgAdd="imgAdd"
            v-model="entity.content" />
        </no-ssr>
      </el-form-item>
      <el-form-item>
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
        fullscreen: true,
        readmodel: true, // 沉浸式阅读
        htmlcode: true // 展示html源码
      },
      tagIds: [],
      content: "",
      entity: {
        content: "",
        tag: { id: "" }
      },
      rules: {
        title: [{ required: true, message: "请输入标题", trigger: "blur" }],
        summary: [{ required: true, message: "请输入简介", trigger: "blur" }],
      }
    };
  },
  mounted() {
    let data = sessionStorage.getItem("article");
    if (data) {
      sessionStorage.removeItem("article");
      this.entity = JSON.parse(data);
      if (!this.entity.tag) {
        this.entity.tag = { id: "" };
      }
    }
  },
  async asyncData({ $axios }) {
    const tags = await $axios.$get("/admin/tag/list");
    return { tags };
  },
  methods: {
    async onSubmit() {
      let isValid = false;
      this.$refs['publishForm'].validate((valid) => {
          isValid = valid;
      });
      if (!isValid) {
        return;
      }
      if (this.content) {
        this.entity["content_html"] = this.content;
      }
      const res = await this.$axios.$post("/article/save", this.entity);
      if (res && res.errcode == 0) {
        this.entity = {
          content: "",
          tag: { id: "" }
        }
        this.$notify({
            title: '发布结果',
            message: '发布文章成功',
            type: 'success'
        });
      }
    },
    getData(value, render) {
      this.content = render;
    },
    imgAdd(pos, $file) {
      var formdata = new FormData();
      formdata.append("file", $file);
      this.$axios({
        url: "/file/upload",
        method: "post",
        data: formdata,
        headers: { "Content-Type": "multipart/form-data" }
      }).then(res => {
        let url = res.data.data;
        this.$refs.md.$img2Url(pos, url);
      });
    }
  }
};
</script>


