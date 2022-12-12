<template>
  <div class="borrowed">
    <div class="book-header">
      <el-button size="small" icon="el-icon-plus" type="primary" @click="toAdd"
        >借书</el-button
      >
    </div>
    <div class="list">
      <el-table
        align="center"
        v-loading="loading"
        :data="tableData"
        style="width: 100%"
      >
        <el-table-column
          align="center"
          prop="sernum"
          label="流水号"
        ></el-table-column>
        <el-table-column
          align="center"
          prop="bookId"
          label="图书号"
        ></el-table-column>
        <el-table-column align="center" prop="readerId" label="读者卡号">
        </el-table-column>
        <el-table-column align="center" prop="lendDate" label="借出日期">
        </el-table-column>
        <el-table-column
          align="center"
          prop="backDate"
          label="归还日期"
        ></el-table-column>
        <el-table-column align="center" prop="" label="操作">
          <template slot-scope="{ row }">
            <el-button
              size="small"
              icon="el-icon-delete"
              type="danger"
              @click="returnBook(row)"
              >归还</el-button
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

    <el-dialog
      title="添加图书"
      :visible.sync="dialogVisible"
      :before-close="beforeClose"
      width="50%"
    >
      <Add ref="infoOrEditRef" />
      <span slot="footer" class="dialog-footer">
        <el-button @click="cancel">取 消</el-button>
        <el-button type="primary" @click="add">确 定</el-button>
      </span>
    </el-dialog>
  </div>
</template>
<script>
import recordApi from "@/api/record";
import bookApi from "@/api/book";
import Add from "@/pages/Borrowed/components/Add";
export default {
  name: "",
  components: {
    Add,
  },
  data() {
    return {
      dialogVisible: false,
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
    // 获取分页数据
    page() {
      this.loading = true;
      recordApi
        .page(this.pageParams)
        .then((r) => {
          this.tableData = r.data.map((item) => {
            return {
              ...item,
              lendDate: item.lendDate
                ? item.lendDate.split("T")[0]
                : item.lendDate,
              backDate: item.backDate
                ? item.backDate.split("T")[0]
                : item.backDate,
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
    //归还
    returnBook(row) {
      this.$confirm("此操作将归还该图书, 是否继续?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      })
        .then(() => {
          bookApi.returnBook(row).then((r) => {
            this.page();
            this.$message({
            type: "success",
            message: "归还成功",
          });
          });
        })
        .catch(() => {
          this.$message({
            type: "info",
            message: "已取消归还",
          });
        });
    },
    // 打开弹窗
    toAdd() {
      this.dialogVisible = true;
    },
    beforeClose(done) {
      done();
    },
    cancel() {
      this.dialogVisible = false;
    },
    add() {
      this.$refs.infoOrEditRef.add();
      this.dialogVisible = false;
      this.page()
    },
  },
};
</script>

<style lang="less" scoped>
.borrowed {
  padding: 0 200px;
}
.list {
  // height: 800px;
  margin-top: 20px;
  /deep/.el-table {
    .el-table__header-wrapper {
      border-top: 1px solid #ebeef5;
    }
  }
}
.book-header {
  display: flex;
  justify-content: space-between;
  margin: 20px 0;
}
/deep/.el-pagination {
  margin-top: 20px;
  text-align: center;
}
</style>
