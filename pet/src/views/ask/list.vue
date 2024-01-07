<template>
  <div>
    <el-tabs v-model="activeName" type="card" @tab-click="handleClick">
      <el-tab-pane label="留言信息" name="1">
        <!--搜索框-->
        <el-form :inline="true" :model="formInline" class="user-search">
          <el-form-item label="内容：">
            <el-input
              size="small"
              v-model="formInline.message"
              placeholder="输入内容"
            ></el-input>
          </el-form-item>
          <el-form-item>
            <el-button
              size="small"
              type="primary"
              icon="el-icon-search"
              @click="getMdata"
            >搜索
            </el-button>
            <el-button
              size="small"
              type="danger"
              @click="add"
              v-if="role==='1'"
            >新增
            </el-button>
          </el-form-item>
        </el-form>
        <!--留言列表-->
        <el-table size="small" :data="listData" border style="width: 100%">
          <el-table-column prop="name" label="会员"></el-table-column>
          <el-table-column prop="message" label="留言信息"></el-table-column>
          <el-table-column prop="reply" label="回复信息"></el-table-column>
          <el-table-column align="center" label="操作" v-if="role==='2'">
            <template slot-scope="scope">
              <el-button size="mini" type="primary" @click="edit(scope.row)"
              >回复
              </el-button>
              <el-button size="mini" type="danger" @click="del(scope.row)"
              >删除
              </el-button>
            </template>
          </el-table-column>
        </el-table>
      </el-tab-pane>
    </el-tabs>
    <!--新增留言-->
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
        <el-form-item label="内容" prop="message">
          <el-input
            size="small"
            type="textarea"
            :rows="5"
            v-model="editForm.message"
            auto-complete="off"
            placeholder="请输入内容"
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
    <!--回复留言-->
    <el-dialog
      :title="title"
      :visible.sync="editPwdVisible"
      width="50%"
      @click="closeDialog"
    >
      <el-form
        label-width="100px"
        :model="editForm"
        :rules="rules1"
        ref="editFormPwd"
      >
        <el-form-item label="回复" prop="reply">
          <el-input
            size="small"
            type="textarea"
            :rows="5"
            v-model="editForm.reply"
            auto-complete="off"
            placeholder="请输入回复"
            show-password
          ></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button size="small" @click="closeDialog">返回</el-button>
        <el-button
          size="small"
          type="primary"
          class="title"
          @click="submitPwd('editFormPwd')"
        >保存
        </el-button
        >
      </div>
    </el-dialog>
  </div>
</template>

<script>
import axios from "axios";

export default {
  data() {
    return {
      editModelVisible: false, //新增留言对话框的显示与隐藏
      editPwdVisible: false, //回复留言对话框的显示与隐藏
      title: "留言",
      editForm: {},
      listData: [], //用户数据
      formInline: {}, //搜索框数据
      user: {},
      rules: {
        message: [{required: true, message: "请输入内容", trigger: "blur"}]
      },
      rules1: {
        reply: [{required: true, message: "请输入回复", trigger: "blur"}],
      },
      activeName: "1",
      role: localStorage.getItem('role'),
    };
  },

  created() {
    this.getMdata()
    this.user = JSON.parse(localStorage.getItem("user"));
  },

  methods: {
    handleClick(tab, event) {
      this.getMdata();
    },
    // 获取留言列表
    getMdata() {
      let that = this;
      axios.post(this.httpUrl + "message/list", this.formInline).then((res) => {
        that.listData = res.data.list;
      });
    },
    // 新增留言
    add() {
      this.editModelVisible = true;
      this.editForm = {
        uid: this.user.id
      };
    },
    // 回复留言
    edit(row) {
      this.editPwdVisible = true;
      this.editForm = {...row};
    },
    // 删除留言
    del(row) {
      this.$confirm("确定要删除该留言吗?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      })
        .then(() => {
          axios
            .post(
              this.httpUrl + "message/del",
              {id: row.id},
              {headers: {"Content-Type": "application/json"}}
            )
            .then((res) => {
              this.$message({
                type: "success",
                message: "删除成功",
              });
              this.getMdata();
            });
        })
        .catch((err) => {
          this.$message({
            type: "info",
            message: "已取消",
          });
        });
    },
    // 新增留言表单提交操作
    submitForm(editData) {
      this.$refs[editData].validate((valid) => {
        if (valid) {
          axios
            .post(this.httpUrl + "message/add", this.editForm)
            .then((res) => {
              if (res.data.code === 200) {
                this.getMdata();
                this.$message({
                  type: "success",
                  message: "新增成功！",
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
    // 回复表单提交操作
    submitPwd(row) {
      this.$refs[row].validate((valid) => {
        if (valid) {
          axios.post(
            this.httpUrl + "message/edit",
            this.editForm,
            {headers: {"Content-Type": "application/json"}}
          )
            .then((res) => {
              this.$message({
                type: "success",
                message: "回复成功",
              });
              this.editPwdVisible = false;
              this.getMdata();
            });
        } else {
          return false;
        }
      });
    },
    // 关闭弹出框
    closeDialog() {
      this.editModelVisible = false;
      this.editPwdVisible = false;
    }
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


