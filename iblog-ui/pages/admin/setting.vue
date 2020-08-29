<template>
  <div>
    <el-form :model="entity" ref="submitForm">
        <el-form-item>
            <el-input v-model="entity.name" placeholder="网站名称"></el-input>
        </el-form-item>
        <el-form-item>
            <el-input v-model="entity.description" type="textarea" placeholder="网站描述"></el-input>
        </el-form-item>
        <el-form-item>
            <el-input v-model="entity.keywords" type="textarea" placeholder="搜索关键词"></el-input>
        </el-form-item>
        <el-form-item>
            <el-input v-model="entity.author" placeholder="作者"></el-input>
        </el-form-item>
         <el-form-item>
            <el-input v-model="entity.job" placeholder="职业"></el-input>
        </el-form-item>
        <el-form-item>
            <el-input v-model="entity.mail" placeholder="邮箱"></el-input>
        </el-form-item>
        <el-form-item>
            <el-button @click="submitForm" type="primary">保存</el-button>
        </el-form-item>
    </el-form>
  </div>
</template>
<script>
export default {
  layout: "nav",
  data() {
    return {
      entity: {
          name: "",
          description: "",
          keywords: ""
      }
    };
  },
  mounted() {
    this.fetchData();
  },
  methods: {
    async fetchData() {
      const res = await this.$axios.$get("/admin/sys/get?key=blog-setting");
      if (res.data != undefined) {
          this.entity = res.data;
      }
    },
    async submitForm() {
      let isValid = false;
      this.$refs["submitForm"].validate((valid) => {
          isValid = valid;
      });
      if (!isValid) {
        return;
      }
      let params = {key: "blog-setting", value: this.entity}
      const res = await this.$axios.$post("/admin/sys/save", params);
      if (res.errcode == 0) {
        this.fetchData();
      }
    }
    
  }
};
</script>

<style scoped>
.el-form {
  margin-left: 22px;
}

</style>



