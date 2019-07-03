<template>
  <div>
    <el-table :data="list">
      <el-table-column type="index" width="50"></el-table-column>
      <el-table-column label="标签" property="tag.name" width="80"></el-table-column>
      <el-table-column label="标题" property="title"></el-table-column>
      <el-table-column label="简介" property="summary"></el-table-column>
      <el-table-column label="创建时间" property="created">
        <template slot-scope="scope">{{new Date(scope.row.created).format('yyyy/MM/dd hh:mm')}}</template>
      </el-table-column>
      <el-table-column label="操作">
        <template slot-scope="scope">
          <el-button size="mini" @click="handleEdit(scope.row)">编辑</el-button>
          <el-button size="mini" type="danger" @click="remove(scope.row.id)">删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <el-pagination
      background
      layout="prev, pager, next"
      :page-size="limit"
      :total="total"
      @current-change="fetchData"
      style="float:right;padding-top:20px;"
    ></el-pagination>
  </div>
</template>
<script>
export default {
  layout: "nav",
  data() {
    return {
      list: [],
      page: 0,
      limit: 10,
      total: 0,
      entity: {
        name: ""
      }
    };
  },
  mounted() {
    this.fetchData();
  },
  methods: {
    async fetchData(page) {
      page = (page - 1) | 0;
      const res = await this.$axios.$post("/admin/article/list", {
        page: page,
        size: this.limit
      });
      this.list = res.data;
      this.total = res.total;
    },
    async submitForm() {},
    async remove(id) {
      const res = await this.$axios.$get("/article/remove", {
        params: { id: id }
      });
      if (res.errcode == 0) {
        this.fetchData();
      }
    },
    handleEdit(row) {
      console.info(row);
      sessionStorage.setItem('article', JSON.stringify(row));
      this.$router.push('/admin/article/publish');
    }
  }
};
</script>

<style scoped>
.el-form {
  margin-left: 22px;
}
</style>



