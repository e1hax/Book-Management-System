<template>
  <div class="book">
    <div class="book-header">
      <div></div>
      <el-input
        size="small"
        style="width: 300px"
        placeholder="请输入内容"
        v-model="pageParams.keyword"
      >
        <template slot="append">
          <el-button size="small" @click="search"  icon="el-icon-search" type="success"
            >搜索</el-button
          >
        </template>
      </el-input>
    </div>
    <div class="book-content">
      <div class="book-content-list">
        <el-table
          align="center"
          v-loading="loading"
          :data="tableData"
          style="width: 100%"
        >
          <el-table-column
            align="center"
            prop="name"
            label="书名"
          ></el-table-column>
          <el-table-column
            align="center"
            prop="author"
            label="作者"
          ></el-table-column>
          <el-table-column align="center" prop="publish" label="出版社">
          </el-table-column>
          <el-table-column align="center" prop="isbn" label="ISBN">
          </el-table-column>
          <el-table-column
            align="center"
            prop="price"
            label="价格(元)"
            width="100px"
          ></el-table-column>
          <el-table-column align="center" prop="" label="操作" width="300">
            <template slot-scope="{ row }">
              <el-button
                size="small"
                icon="el-icon-info"
                @click="info(row)"
                type="info"
                >详情</el-button
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
    <!-- 抽屉 -->
    <el-drawer
      title="图书详情"
      size="50%"
      :visible.sync="drawer"
      direction="rtl"
    >
    <BookInfo :bookInfo="bookInfo" />
    </el-drawer>
  </div>
</template>
<script>
import bookApi from "@/api/book";
import BookInfo from "@/pages/Book/components/BookInfo";
export default {
  name: "",
  components: {
    BookInfo
  },
  data() {
    return {
      tableData: [],
      pageParams: {
        pageNum: 1,
        pageSize: 11,
        keyword: "",
      },
      total: 0,
      dialogVisible: false,
      loading: false,
      keyWord: "",
      drawer: false,
      bookInfo: {},
    };
  },
  mounted() {
    this.page();
  },
  methods: {
    // 请求所有图书
    page() {
      this.loading = true;
      bookApi
        .page(this.pageParams)
        .then(r => {
          this.tableData = r.data.map(item => {
            return {
              ...item,
              pubdate: item.pubdate ? item.pubdate.split("T")[0] : item.pubdate,
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
      // console.log(index);
      this.pageParams.pageNum = index;
      this.page();
    },
    //详情
    info(row) {
      this.bookInfo = row;
      this.drawer = true;
    },
    // 搜索
    search() {
      this.page();
    },
  }
};
</script>

<style lang="less" scoped>
.book {
  padding: 0 200px;
  .book-header {
    display: flex;
    justify-content: space-between;
    margin: 20px 0;
    /deep/.el-input {
      .el-input__inner {
        border: 1px solid #ccc;
        border-radius: 5px;
      }
      .el-input-group__append {
        border: none;
        .el-button {
          background-color: #67c23a;
          color: #fff;
          border: 1px solid #67c23a;
        }
      }
    }
  }
  .book-content {
    .book-content-list {
      height: 700px;
    }
    /deep/.el-table {
      .el-table__header-wrapper {
        border-top: 1px solid #ebeef5;
      }
    }
    /deep/.el-pagination {
      margin-top: 10px;
      text-align: center;
    }
  }
  /deep/.el-drawer__wrapper {
    .el-drawer__header {
      span {
        font-size: 20px;
      }
    }
  }
}
</style>
