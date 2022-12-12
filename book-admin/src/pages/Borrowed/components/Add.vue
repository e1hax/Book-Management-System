<template>
  <div>
    <el-form
      :rules="rules"
      :inline="true"
      ref="form"
      :model="form"
      label-width="100px"
    >
      <el-form-item style="width: 45%" prop="bookId" label="书ID">
        <el-input
          style="width: 310px"
          size="small"
          v-model="form.bookId"
        ></el-input>
      </el-form-item>
      <el-form-item style="width: 45%" prop="readerId" label="读者ID">
        <el-input
          style="width: 310px"
          size="small"
          v-model="form.readerId"
        ></el-input>
      </el-form-item>
      <el-form-item style="width: 45%" prop="lendDate" label="借出日期">
        <el-date-picker
          style="width: 310px"
          size="small"
          v-model="form.lendDate"
          type="date"
          placeholder="选择日期"
          format="yyyy 年 MM 月 dd 日"
          value-format="yyyy-MM-dd"
        >
        </el-date-picker>
      </el-form-item>
      <el-form-item style="width: 45%" prop="backDate" label="归还日期">
        <el-date-picker
          style="width: 310px"
          size="small"
          v-model="form.backDate"
          type="date"
          placeholder="选择日期"
          format="yyyy 年 MM 月 dd 日"
          value-format="yyyy-MM-dd"
        >
        </el-date-picker>
      </el-form-item>
    </el-form>
  </div>
</template>
  <script>
  import bookApi from "@/api/book"
export default {
  name: "",
  data() {
    return {
      form: {},
      rules: {
        bookId: [{ required: true, message: "请输入书ID", trigger: "blur" }],
        readerId: [
          { required: true, message: "请输入读者ID", trigger: "blur" },
        ],
        lendDate: [
          { required: true, message: "请选择借出日期", trigger: "blur" },
        ],
        backDate: [
          { required: true, message: "请选择归还日期", trigger: "blur" },
        ],
      },
    };
  },
  mounted() {
    this.initForm()
  },
  methods: {
    initForm() {
      this.form = {
        bookId: "",
        readerId: "",
        lendDate: "",
        backDate: "",
      };
    },
    add(){
      bookApi.borrowBook(this.form).then(r=>{
        // console.log(r);
      })
    }
  },
};
</script>
  
  <style lang="less" scoped></style>
  