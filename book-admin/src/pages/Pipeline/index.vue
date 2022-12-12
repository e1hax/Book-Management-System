
<template>
  <div>
    <div class="list">
      <el-table align="center" v-loading="loading" :data="tableData" style="width: 100%">
        <el-table-column  align="center" prop="sernum" label="流水号"></el-table-column>
        <el-table-column  align="center" prop="bookId" label="图书号"></el-table-column>
        <el-table-column  align="center" prop="readerId" label="读者卡号"> </el-table-column>
        <el-table-column  align="center" prop="lendDate" label="借出日期"> </el-table-column>
        <el-table-column  align="center" prop="backDate" label="归还日期"></el-table-column>
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
import userApi from "@/api/general";
import { mapState } from 'vuex';
export default {
  name: "",
  data() {
    return {
      pageParams: {
        pageNum: 1,
        pageSize: 11,
        readerId:""
      },
      loading: false,
      tableData: [],
      total: 0,
    };
  },
  computed: {
    ...mapState({
      usertype: (state) => state.usertype,
    }),
  },
  mounted() {
    this.page();
  },

  methods: {
    // 获取分页数据
    page() {
      this.loading = true;
      userApi
        .recordPage({...this.pageParams,readerId:this.usertype.username})
        .then(r => {
          this.tableData = r.data.map(item => {
            return { ...item, lendDate: item.lendDate?item.lendDate.split("T")[0]:item.lendDate, backDate: item.backDate?item.backDate.split("T")[0]:item.backDate };
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
