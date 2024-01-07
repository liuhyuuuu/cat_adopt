<template>
  <!--  用户管理-->
  <div>
    <!--    顶部搜索模块-->
    <el-form :inline="true" :model="formInline" class="user-search">
      <el-form-item label="用户名：">
        <el-input
          size="small"
          v-model="formInline.name"
          placeholder="输入用户名"
        ></el-input>
      </el-form-item>
      <el-form-item>
        <el-button
          size="small"
          type="primary"
          icon="el-icon-search"
          @click="getdata"
        >搜索
        </el-button>
      </el-form-item>
    </el-form>

    <!--    用户列表-->
    <el-table stripe size="small" :data="listData" border style="width: 100%">
      <el-table-column prop="name" label="姓名"></el-table-column>
      <el-table-column prop="sex" label="性别"></el-table-column>
      <el-table-column prop="tel" label="电话"></el-table-column>
      <el-table-column prop="email" label="邮箱"></el-table-column>
      <el-table-column prop="addr" label="地址"></el-table-column>
      <el-table-column prop="status" label="状态"></el-table-column>
      <el-table-column align="center" label="操作" width="240">
        <template slot-scope="scope">
          <el-button size="mini" type="primary" @click="edit(scope.row)">编辑
          </el-button>
          <el-button size="mini" type="danger" @click="del(scope.row)" v-if="scope.row.status === '通过'">拉黑
          </el-button>
          <el-button size="mini" type="danger" @click="canceldel(scope.row)" v-else>取消拉黑
          </el-button>
        </template>
      </el-table-column>
    </el-table>

    <!--编辑：修改用户信息表单-->
    <el-dialog
      :title="title"
      :visible.sync="editModelVisible"
      width="50%"
      @click="closeDialog"
    >
      <el-form
        label-width="100px"
        :model="editForm"
        :rules="rules"
        ref="editForm"
      >
        <el-form-item label="姓名" prop="name">
          <el-input
            size="small"
            v-model="editForm.name"
            auto-complete="off"
            placeholder="请输入姓名"
          ></el-input>
        </el-form-item>
        <el-form-item label="性别" prop="sex">
          <el-radio-group v-model="editForm.sex">
            <el-radio :label="'男'">男</el-radio>
            <el-radio :label="'女'">女</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="电话" prop="tel">
          <el-input
            size="small"
            v-model="editForm.tel"
            auto-complete="off"
            placeholder="请输入电话"
          ></el-input>
        </el-form-item>
        <el-form-item label="邮箱">
          <el-input
            size="small"
            v-model="editForm.email"
            auto-complete="off"
            placeholder="请输入邮箱"
          ></el-input>
        </el-form-item>
        <el-form-item label="地址">
          <el-input
            size="small"
            v-model="editForm.addr"
            auto-complete="off"
            placeholder="请输入地址"
          ></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button size="small" @click="closeDialog">返回</el-button>
        <el-button
          size="small"
          type="primary"
          class="title"
          @click="submitForm('editForm')"
        >保存
        </el-button>
      </div>
    </el-dialog>

  </div>
</template>

<script>
import axios from "axios";

export default {
  data() {
    return {
      editModelVisible: false,//控制编辑页面显示与隐藏
      title: "用户详情",
      editForm: {}, //编辑表单数据
      listData: [], //用户数据
      formInline: {}, //搜索框数据
      user: {},
      rules: {
        name: [{required: true, message: "请输入姓名", trigger: "blur"}],
        sex: [{required: true, message: "请选择性别", trigger: "change"}],
        tel: [{required: true, message: "请输入电话", trigger: "blur"}],
      },
      role: localStorage.getItem('role'),
    };
  },

  created() {
    this.user = JSON.parse(localStorage.getItem("user"));
    this.getdata();
  },
  // 方法
  methods: {
    // 获取数据
    getdata() {
      let that = this;
      axios.post(this.httpUrl + "user/list", this.formInline).then((res) => {
        that.listData = res.data.list;
      });
    },
    // 编辑：修改用户表单
    edit(row) {
      this.editModelVisible = true;
      this.editForm = JSON.parse(JSON.stringify(row));
    },
    // 编辑修改成功：提交表单
    submitForm(editData) {
      this.$refs[editData].validate((valid) => {
        if (valid) {
          axios
            .post(this.httpUrl + "user/update", this.editForm)
            .then((res) => {
              if (res.data.code === 200) {
                this.getdata();
                this.$message({
                  type: "success",
                  message: "修改成功！",
                });
                this.editModelVisible = false;
              } else {
                this.$message({
                  type: "error",
                  message: res.data.msg,
                });
              }
            });
        } else {
          return false;
        }
      });
    },
    // 关闭编辑弹出框
    closeDialog() {
      this.editModelVisible = false;
    },
    // 拉黑
    del(row) {
      this.$confirm("确定要拉黑给会员?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      })
        .then(() => {
          this.loading = true;
          this.status=
          axios
            .post(
              this.httpUrl + "user/black",
              {id: row.id, status: "拉黑"},
              {headers: {"Content-Type": "application/json"}}
            )
            .then((res) => {
              this.$message({
                type: "success",
                message: "拉黑成功",
              });
              this.getdata();
            })
          ;
        })
        .catch((err) => {
          this.$message({
            type: "info",
            message: "已取消",
          });
          this.loading = false;
        });
    },
    // 取消拉黑
    canceldel(row) {
      this.$confirm("确定要取消拉黑给会员?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      })
        .then(() => {
          this.loading = true;
          this.status=
            axios
              .post(
                this.httpUrl + "user/black",
                {id: row.id, status: "通过"},
                {headers: {"Content-Type": "application/json"}}
              )
              .then((res) => {
                this.$message({
                  type: "success",
                  message: "取消拉黑成功",
                });
                this.getdata();
              })
          ;
        })
        .catch((err) => {
          this.$message({
            type: "info",
            message: "已取消",
          });
          this.loading = false;
        });
    },
  },
};
</script>

<style scoped>
.user-search {
  margin-top: 20px;
}

.userRole {
  width: 100%;
}

.el-divider--horizontal {
  margin: 0;
}

.el-form-item {
  margin-bottom: 10px;
}
</style>


