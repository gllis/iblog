<template>
  <div>
    <el-row>
      <el-col :span="16">
        <el-table :data="list">
          <el-table-column type="index" width="50"></el-table-column>
          <el-table-column label="标签" property="name" width="150">
            <template slot-scope="scope">
              <el-tag effect="dark" :color="scope.row.color">{{scope.row.name}}</el-tag>
            </template>
          </el-table-column>
          <el-table-column label="操作">
             <template slot-scope="scope">
              <el-button
                size="mini"
                @click="handleEdit(scope.row)">编辑</el-button>
              <el-button
                size="mini"
                type="danger"
                @click="remove(scope.row.id)">删除</el-button>
            </template>
          </el-table-column>
        </el-table>
      </el-col>
      <el-col :span="6">
        <el-form :model="entity" :rules="rules" ref="submitForm">
          <el-form-item prop="name">
            <el-input placeholder="标签名" v-model="entity.name"></el-input>
          </el-form-item>
          <el-form-item prop="color">
            <el-row>
              <el-col :span="12">
                <el-input readonly  placeholder="颜色" v-model="entity.color"></el-input>
              </el-col>
              <el-col :span="12">
                &nbsp;<el-color-picker v-model="entity.color"></el-color-picker>
              </el-col>
            </el-row>
          </el-form-item>
          <el-form-item>
            <el-button type="primary" @click="submitForm()">创建/更新</el-button>
          </el-form-item>
        </el-form>
      </el-col>
    </el-row>
  </div>
</template>
<script>
export default {
  layout: "nav",
  data() {
    return {
      list: [],
      entity: {
        name: "",
        color: ""
      },
      rules: {
        name: [{ required: true, message: "请输入标签", trigger: "blur" }],
        color: [{ required: true, message: "请选择颜色", trigger: "blur" }],
      }
    };
  },
  mounted() {
    this.fetchData();
  },
  methods: {
    async fetchData() {
      const res = await this.$axios.$get("/admin/tag/list");
      this.list = res.data;
    },
    async submitForm() {
      let isValid = false;
      this.$refs['submitForm'].validate((valid) => {
          isValid = valid;
      });
      if (!isValid) {
        return;
      }
      const res = await this.$axios.$post("/tag/save", this.entity);
      if (res.errcode == 0) {
        this.fetchData();
      }
    },
    async remove(id) {
      const res = await this.$axios.$get('/tag/remove', {params: {id: id}});
      if (res.errcode == 0) {
        this.fetchData();
      }
    },
    handleEdit(row) {
      this.entity = row;
    }
  }
};
</script>

<style scoped>
.el-form {
  margin-left: 22px;
}
.el-tag {
  color: #ffffff;
}
</style>



