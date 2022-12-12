<template>
  <div>
    <el-row>
      <el-col :offset="1" :span="3"><div class="title">书名</div></el-col>
      <el-col :span="19"
        ><div class="text">{{ bookInfo.name }}</div></el-col
      >
    </el-row>
    <el-row>
      <el-col :offset="1" :span="3"><div class="title">作者</div></el-col>
      <el-col :span="19"
        ><div class="text">{{ bookInfo.author }}</div></el-col
      >
    </el-row>
    <el-row>
      <el-col :offset="1" :span="3"><div class="title">出版社</div></el-col>
      <el-col :span="19"
        ><div class="text">{{ bookInfo.publish }}</div></el-col
      >
    </el-row>
    <el-row>
      <el-col :offset="1" :span="3"><div class="title">ISBN</div></el-col>
      <el-col :span="19"
        ><div class="text">{{ bookInfo.isbn }}</div></el-col
      >
    </el-row>
    <el-row>
      <el-col :offset="1" :span="3"><div class="title">简介</div></el-col>
      <el-col :span="19"
        ><div class="text introduction">
          {{ bookInfo.introduction }}
        </div></el-col
      >
    </el-row>
    <el-row>
      <el-col :offset="1" :span="3"><div class="title">语言</div></el-col>
      <el-col :span="19"
        ><div class="text">{{ bookInfo.language }}</div></el-col
      >
    </el-row>
    <el-row>
      <el-col :offset="1" :span="3"><div class="title">价格</div></el-col>
      <el-col :span="19"
        ><div class="text">{{ bookInfo.price }}</div></el-col
      >
    </el-row>
    <el-row>
      <el-col :offset="1" :span="3"><div class="title">出版日期</div></el-col>
      <el-col :span="19"
        ><div class="text">{{ bookInfo.pubdate }}</div></el-col
      >
    </el-row>
    <el-row>
      <el-col :offset="1" :span="3"><div class="title">分类号</div></el-col>
      <el-col :span="19"
        ><div class="text">{{ bookInfo.classId }}</div></el-col
      >
    </el-row>
    <el-row>
      <el-col :offset="1" :span="3"><div class="title">书架号</div></el-col>
      <el-col :span="19"
        ><div class="text">{{ bookInfo.pressmark }}</div></el-col
      >
    </el-row>
    <el-row>
      <el-col :offset="1" :span="3"><div class="title">状态</div></el-col>
      <el-col :span="19"
        ><div class="text">{{ bookInfo.state }}</div></el-col
      >
    </el-row>
  </div>
</template>
<script>
export default {
  name: "",
  props: {
    bookInfo: {
      default: {},
      type: Object,
    },
  },
  data() {
    return {};
  },
};
</script>

<style lang="less" scoped>
.title {
  font-size: 18px;
}
.text {
  font-size: 16px;
}
.introduction {
  line-height: 30px;
  overflow: hidden; //溢出隐藏
  display: -webkit-box;
  -webkit-line-clamp: 6;
  -webkit-box-orient: vertical;
}
/deep/.el-row {
  margin-bottom: 30px;
}
</style>
