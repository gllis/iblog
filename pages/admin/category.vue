<template>
  <div>
    <el-row>
      <el-col :span="16">
        <el-table :data="list">
          <el-table-column type="index" width="50"></el-table-column>
          <el-table-column label="分类" property="name"></el-table-column>
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
        <el-form>
          <el-form-item label="标签">
            <el-input v-model="entity.name"></el-input>
          </el-form-item>
          <el-form-item>
            <el-button type="primary" @click="submitForm()">立即创建</el-button>
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
        name: ""
      }
    };
  },
  mounted() {
    this.fetchData();
  },
  methods: {
    async fetchData() {
      const res = await this.$axios.$get("/category/list");
      this.list = res.data;
    },
    async submitForm() {
      const res = await this.$axios.$post("/category/save", this.entity);
      if (res.errcode == 0) {
        this.fetchData();
      }
    },
    async remove(id) {
      console.info(id);
      const res = await this.$axios.$get('/category/remove', {params: {id: id}});
      if (res.errcode == 0) {
        this.fetchData();
      }
    },
    handleEdit(row) {
      console.info(row);
      this.entity = row;
    }
  }
};
</script>

<style scoped>
.el-form {
  margin-left: 22px;
}
</style>



