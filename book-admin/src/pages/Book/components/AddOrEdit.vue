<template>
  <div>
    <el-form
      :rules="rules"
      :inline="true"
      ref="form"
      :model="form"
      label-width="100px"
    >
      <el-form-item style="width: 45%" prop="name" label="书名">
        <el-input
          style="width: 310px"
          size="small"
          v-model="form.name"
        ></el-input>
      </el-form-item>
      <el-form-item style="width: 45%" prop="author" label="作者">
        <el-input
          style="width: 310px"
          size="small"
          v-model="form.author"
        ></el-input>
      </el-form-item>
      <el-form-item style="width: 45%" prop="publish" label="出版社">
        <el-input
          style="width: 310px"
          size="small"
          v-model="form.publish"
        ></el-input>
      </el-form-item>
      <el-form-item style="width: 45%" prop="isbn" label="ISBN">
        <el-input
          style="width: 310px"
          size="small"
          v-model="form.isbn"
        ></el-input>
      </el-form-item>
      <el-form-item style="width: 45%" prop="language" label="语言">
        <el-input
          style="width: 310px"
          size="small"
          v-model="form.language"
        ></el-input>
      </el-form-item>
      <el-form-item style="width: 45%" prop="price" label="价格（元）">
        <el-input
          style="width: 310px"
          size="small"
          v-model="form.price"
        ></el-input>
      </el-form-item>
      <el-form-item style="width: 45%" prop="pubdate" label="出版日期">
        <el-date-picker
          style="width: 310px"
          size="small"
          v-model="form.pubdate"
          type="date"
          placeholder="选择日期"
          format="yyyy 年 MM 月 dd 日"
          value-format="yyyy-MM-dd"
        >
        </el-date-picker>
      </el-form-item>
      <el-form-item style="width: 45%" prop="classId" label="分类号">
        <el-input
          style="width: 310px"
          size="small"
          v-model="form.classId"
        ></el-input>
      </el-form-item>
      <el-form-item style="width: 45%" prop="pressmark" label="书架号">
        <el-input
          style="width: 310px"
          size="small"
          v-model="form.pressmark"
        ></el-input>
      </el-form-item>
      <el-form-item style="width: 45%" prop="state" label="状态">
        <el-input
          style="width: 310px"
          size="small"
          v-model="form.state"
        ></el-input>
      </el-form-item>
      <el-form-item style="width: 100%" prop="introduction" label="简介">
        <el-input
          style="width: 750px"
          size="small"
          type="textarea"
          :rows="6"
          v-model="form.introduction"
        ></el-input>
      </el-form-item>
    </el-form>
  </div>
</template>
<script>
import bookApi from "@/api/book";
export default {
  name: "",
  data() {
    return {
      form: {},
      rules: {
        name: [{ required: true, message: "请输入书名", trigger: "blur" }],
        author: [{ required: true, message: "请输入作者", trigger: "blur" }],
        publish: [{ required: true, message: "请输入出版社", trigger: "blur" }],
        isbn: [{ required: true, message: "请输入ISBN", trigger: "blur" }],
        language: [{ required: true, message: "请输入语言", trigger: "blur" }],
        price: [{ required: true, message: "请输入价格", trigger: "blur" }],
        pubdate: [
          { required: true, message: "请选择出版日期", trigger: "blur" },
        ],
        classId: [{ required: true, message: "请选择分类号", trigger: "blur" }],
        pressmark: [
          { required: true, message: "请选择书架号", trigger: "blur" },
        ],
        state: [{ required: true, message: "请选择状态", trigger: "blur" }],
        introduction: [
          { required: true, message: "请输入简介", trigger: "blur" },
        ],
      },
    };
  },
  mounted() {},
  methods: {
    initForm() {
      this.form = {
        name: "",
        author: "",
        publish: "",
        isbn: "",
        introduction: "",
        language: "",
        price: "",
        pubdate: "",
        classId: "",
        pressmark: "",
        state: "",
      };
      this.$refs.form.clearValidate();
    },
    add() {
      if (this.form.bookId) {
        this.form.bookId = String(this.form.bookId);
        bookApi.updateBook(this.form).then(r => {
          this.$emit("setShowDialog", false);
          this.$emit("page");
          this.$message({
            message: "修改成功!",
            type: "success",
          });
        });
      } else {
        this.$refs.form.validate(valid => {
          if (valid) {
            // const formData = new FormData();
            // Object.keys(this.form).forEach(item => {
            //   formData.append(item, this.form[item]);
            // });
            bookApi.add(this.form).then(r => {
              // this.dialogVisible = false;
              this.$emit("setShowDialog", false);
              this.$emit("setPageIndex", 1);
              this.$emit("page");
              this.initForm();
              this.$message({
                message: "添加图书成功!",
                type: "success",
              });
            });
          } else {
            return false;
          }
        });
      }
    },
    setForm(row) {
      this.form = row;
    },
  },
};
</script>

<style lang="less" scoped></style>
