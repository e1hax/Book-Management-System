<template>
  <div>
    <div class="list">
      <el-table v-loading="loading" :data="tableData" style="width: 100%">
        <el-table-column
          align="center"
          prop="readerId"
          label="卡号"
        ></el-table-column>
        <el-table-column
          align="center"
          prop="name"
          label="姓名"
        ></el-table-column>
        <el-table-column align="center" prop="sex" label="性别">
        </el-table-column>
        <el-table-column align="center" prop="birth" label="生日">
        </el-table-column>
        <el-table-column
          align="center"
          prop="address"
          label="地址"
        ></el-table-column>
        <el-table-column
          align="center"
          prop="telcode"
          label="电话"
        ></el-table-column>
        <el-table-column align="center" prop="" label="操作" width="300">
          <template align="center" slot-scope="{ row }">
            <el-button
              @click="updateGs(row)"
              size="small"
              :type="row.gs == 1 ? '' : 'danger'"
              >{{ row.gs == 1 ? "取消" : "" }}挂失</el-button
            >
            <el-button
              @click="updateStatus(row)"
              size="small"
              :type="row.status == 1 ? '' : 'primary'"
              >{{ row.status == 1 ? "取消" : "" }}停借</el-button
            >
          </template>
        </el-table-column>
      </el-table>
    </div>
    <el-pagination
      @current-change="currentChange"
      background
      layout="prev, pager, next ,total"
      :total="total"
      :current-page="pageParams.pageNum"
      :page-size="pageParams.pageSize"
    >
    </el-pagination>
  </div>
</template>
<script>
import readerApi from "@/api/reader";
export default {
  name: "",
  data() {
    return {
      pageParams: {
        pageNum: 1,
        pageSize: 11,
      },
      loading: false,
      tableData: [],
      total: 0,
    };
  },
  mounted() {
    this.page();
  },
  methods: {
    // 修改挂失状态
    updateGs(row) {
      let ops = { readerId: row.readerId, status: row.gs == 1 ? 0 : 1 };
      readerApi.updateGs(ops).then(r => {
        this.page();
      });
    },
    // 修改停借状态
    updateStatus(row) {
      let ops = { readerId: row.readerId, status: row.status == 1 ? 0 : 1 };
      readerApi.updateStatus(ops).then(r => {
        this.page();
      });
    },
    // 获取分页数据
    page() {
      this.loading = true;
      readerApi
        .page(this.pageParams)
        .then(r => {
          this.tableData = r.data.map(item => {
            return {
              ...item,
              birth: item.birth ? item.birth.split("T")[0] : item.birth,
            };
          });
          this.total = r.map.total;
          this.loading = false;
        })
        .catch(() => {
          this.loading = false;
        });
    },
    // 翻页
    currentChange(index) {
      this.pageParams.pageNum = index;
      this.page();
    },
  },
};
</script>

<style lang="less" scoped>
.list {
  padding: 0 200px;
  // height: 800px;
  margin-top: 20px;
  /deep/.el-table {
    .el-table__header-wrapper {
      border-top: 1px solid #ebeef5;
    }
  }
}
/deep/.el-pagination {
  margin-top: 20px;
  text-align: center;
}
</style>
