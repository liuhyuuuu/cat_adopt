<template>
  <!--  个人中心-->
  <div>
    <el-tabs v-model="activeName" type="card" @tab-click="handleClick">
      <el-tab-pane label="个人中心" name="1">
        <el-table size="small" :data="listData" border style="width: 100%">
          <el-table-column prop="name" label="姓名"></el-table-column>
          <el-table-column prop="sex" label="性别"></el-table-column>
          <el-table-column prop="tel" label="电话"></el-table-column>
          <el-table-column prop="email" label="邮箱"></el-table-column>
          <el-table-column prop="addr" label="地址"></el-table-column>
          <el-table-column prop="status" label="注册状态"></el-table-column>
          <el-table-column align="center" label="操作" width="240">
            <template slot-scope="scope">
              <el-button size="mini" type="primary" @click="edit(scope.row)">编辑</el-button>
              <el-button size="mini" type="danger" @click="editPwd(scope.row)">密码重置</el-button>
            </template>
          </el-table-column>
        </el-table>
      </el-tab-pane>
      <el-tab-pane label="领养进度" name="2" v-if="role=='1'">
        <MyOrder></MyOrder>
      </el-tab-pane>
    </el-tabs>

    <!--    编辑页面对话框-->
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
        <el-form-item label="身份证号" prop="idcard">
          <el-input
            size="small"
            v-model="editForm.idcard"
            auto-complete="off"
            placeholder="请输入身份证号"
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
        <el-form-item label="出生日期">
          <el-input
            size="small"
            v-model="editForm.birth"
            auto-complete="off"
            placeholder="请输入出生日期"
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
          @click="submitForm('editForm')">
          保存
        </el-button>
      </div>
    </el-dialog>

    <!--    重置密码对话框-->
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
        <el-form-item label="旧密码" prop="pwd">
          <el-input
            size="small"
            v-model="editForm.pwd"
            placeholder="请输入旧密码"
          ></el-input>
        </el-form-item>
        <el-form-item label="新密码" prop="newPwd">
          <el-input
            size="small"
            v-model="editForm.newPwd"
            auto-complete="off"
            placeholder="请输入新密码"
            show-password
          ></el-input>
        </el-form-item>
        <el-form-item label="再次输入" prop="newPwd1">
          <el-input
            size="small"
            v-model="editForm.newPwd1"
            auto-complete="off"
            placeholder="请再次输入新密码"
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
import MyOrder from "../order/my.vue"

export default {
  components: {MyOrder},
  data() {
    return {
      editModelVisible: false, //控制编辑页面显示与隐藏
      editPwdVisible: false, //控制重置密码页面显示与隐藏
      title: "用户详情",
      editForm: {}, //修改用户数据对话框
      listData: [],  //用户数据
      user: {},
      obj: {},
      rules: {
        name: [{required: true, message: "请输入姓名", trigger: "blur"}],
        sex: [{required: true, message: "请选择性别", trigger: "change"}],
        idcard: [{required: true, message: "请输入身份证号", trigger: "blur"},],
        tel: [{required: true, message: "请输入电话", trigger: "blur"}],
      },
      rules1: {
        pwd: [{required: true, message: "请输入旧密码", trigger: "blur"}],
        newPwd: [{required: true, message: "请输入新密码", trigger: "change"},],
        newPwd1: [{required: true, message: "请再次输入新密码", trigger: "blur"},],
      },
      activeName: "1", //默认打开个人中心
      role: localStorage.getItem('role'),
    };
  },

  created() {
    this.user = JSON.parse(localStorage.getItem("user"));
    this.listData = [this.user];
  },


  methods: {
    handleClick(tab, event) {
      if (tab.index === "0") {
        this.activeName = "1"; //打开个人中心
        this.listData = [this.user];
      } else {
        this.activeName = "2"; //打开领养进度
        // this.getdata();
      }
    },
    // 弹出修改表单
    edit(row) {
      this.editModelVisible = true;
      this.editForm = row;
    },
    // 修改用户其他信息
    submitForm(editData) {
      this.$refs[editData].validate((valid) => {
        if (valid) {
          axios
            .post(this.httpUrl + "user/update", this.editForm)
            .then((res) => {
              if (res.data.code === 200) {
                // this.getdata();
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
    // 弹出重置密码对话框
    editPwd(row) {
      this.editPwdVisible = true;
      this.obj = row;
      this.editForm = {};
    },
    // 修改密码
    submitPwd(row) {
      this.$refs[row].validate((valid) => {
        if (valid) {
          if (this.editForm.pwd !== this.obj.pwd) {
            this.$message({
              type: "error",
              message: "旧密码错误",
            });
            return;
          } else if (this.editForm.newPwd1 !== this.editForm.newPwd) {
            this.$message({
              type: "error",
              message: "新密码两次输入不同",
            });
            return;
          } else if (this.editForm.newPwd1 === this.editForm.pwd) {
            this.$message({
              type: "error",
              message: "新密码与旧密码相同",
            });
            return;
          }
          axios
            .post(
              this.httpUrl + "user/update",
              {id: this.obj.id, pwd: this.editForm.newPwd1},
              {headers: {"Content-Type": "application/json"}}
            )
            .then((res) => {
              this.$message({
                type: "success",
                message: "重置密码成功",
              });
              this.editPwdVisible = false;
              this.user.pwd = this.editForm.newPwd1;
              localStorage.setItem("user", JSON.stringify(this.user));
            });
        } else {
          return false;
        }
      });
    },
    // 关闭编辑、增加弹出框
    closeDialog() {
      this.editModelVisible = false;
      this.editPwdVisible = false;
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


