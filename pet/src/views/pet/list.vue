<template>
  <!--  宠物管理、登记猫咪信息-->
  <!--  v-loading在接口未请求到数据之前，显示加载中，直到请求到数据后消失。-->
  <div v-loading="loading">
    <!-- 搜索新增 -->
    <el-form :inline="true" :model="formInline" class="user-search">
      <el-form-item label="宠物：">
        <el-input
          size="small"
          v-model="formInline.name"
          placeholder="请输入宠物名字"
        ></el-input>
      </el-form-item>
      <el-form-item>
        <el-button
          size="small"
          type="primary"
          icon="el-icon-search"
          @click="search"
        >搜索
        </el-button>
        <el-button size="small" type="danger" icon="el-icon-plus" @click="add">新增</el-button>
      </el-form-item>
    </el-form>
    <!--列表-->
    <el-table size="small" :data="listData" border style="width: 100%">
      <el-table-column label="图片">
        <template slot-scope="scope">
          <el-image
            style="width: 100px; height: 50px"
            :src="scope.row.img"
          ></el-image>
        </template>
      </el-table-column>
      <el-table-column prop="name" label="名字"></el-table-column>
      <el-table-column prop="category" label="品种"></el-table-column>
      <el-table-column prop="sex" label="性别"></el-table-column>
      <el-table-column prop="age" label="年龄(岁)"></el-table-column>
      <el-table-column prop="weight" label="体重(kg)"></el-table-column>
      <el-table-column prop="height" label="健康"></el-table-column>
      <el-table-column prop="character" label="性格习惯"></el-table-column>
      <el-table-column prop="demand" label="领养模式"></el-table-column>
      <el-table-column prop="addr" label="所在地"></el-table-column>
      <el-table-column prop="status" label="领养状态"></el-table-column>
      <el-table-column label="发布日期">
        <template slot-scope="scope">
          <div>
            {{
              scope.row.date.substring(0, 10) +
              " " +
              scope.row.date.substring(11, 19)
            }}
          </div>
        </template>
      </el-table-column>
      <el-table-column align="center" label="操作" width="240">
        <template slot-scope="scope">
          <el-button size="mini" type="success" @click="edit(scope.row)">编辑</el-button>
          <el-button size="mini" type="danger" @click="deleteUser(scope.row)">删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <!-- 编辑界面 -->
    <el-dialog
      :title="title"
      :visible.sync="editFormVisible"
      width="50%"
      @click="closeDialog"
    >
      <el-form
        label-width="100px"
        :rules="rules"
        :model="editForm"
        ref="editForm"
      >
        <el-form-item label="宠物名：" prop="name">
          <el-input
            size="small"
            v-model="editForm.name"
            auto-complete="off"
            placeholder="请输入宠物名"
          ></el-input>
        </el-form-item>
        <el-form-item label="品种：" prop="category">
          <el-input
            size="small"
            v-model="editForm.category"
            auto-complete="off"
            placeholder="请输入品种"
          ></el-input>
        </el-form-item>
        <el-form-item label="性别：" prop="sex">
          <el-select
            v-model="editForm.sex"
            size="small"
            placeholder="请选择性别"
          >
            <el-option
              v-for="item in options"
              :key="item"
              :label="item"
              :value="item"
            >
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="年龄：" prop="age">
          <el-input
            size="small"
            v-model="editForm.age"
            auto-complete="off"
            placeholder="请输入年龄"
          ></el-input>
        </el-form-item>
        <el-form-item label="体重：">
          <el-input
            size="small"
            v-model="editForm.weight"
            auto-complete="off"
            placeholder="请输入体重"
          ></el-input>
        </el-form-item>
        <el-form-item label="健康情况：">
          <el-input
            size="small"
            v-model="editForm.height"
            auto-complete="off"
            placeholder="请输入健康情况"
          ></el-input>
        </el-form-item>
        <el-form-item label="性格习惯：" prop="character">
          <el-input
            size="small"
            v-model="editForm.character"
            auto-complete="off"
            placeholder="请输入性格习惯"
          ></el-input>
        </el-form-item>
        <el-form-item label="领养模式：" prop="demand">
          <el-select
            v-model="editForm.demand"
            size="small"
            placeholder="请选择领养模式："
          >
            <el-option
              v-for="item in statusList1"
              :key="item"
              :label="item"
              :value="item"
            >
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="所在地址：" prop="addr">
          <el-input
            size="small"
            v-model="editForm.addr"
            auto-complete="off"
            placeholder="请输入所在地址"
          ></el-input>
        </el-form-item>
        <el-form-item label="领养状态：" prop="status">
          <el-select
            v-model="editForm.status"
            size="small"
            placeholder="请选择领养状态"
          >
            <el-option
              v-for="item in statusList"
              :key="item"
              :label="item"
              :value="item"
            >
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="图片：">
          <el-upload
            style="display: inline"
            :show-file-list="false"
            :on-success="onSuccess"
            :action="httpUrl + 'file/upload'"
          >
            <el-button type="warning" size="mini" style="margin-left: 10px">上传</el-button>
            <span style="color: #67c23a">{{editForm.img }}</span>
          </el-upload>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button size="small" @click="closeDialog">返回</el-button>
        <el-button size="small" type="primary" @click="save('editForm')">保存</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import axios from "axios";

export default {
  data() {
    return {
      editFormVisible: false, //控制编辑页面显示与隐藏
      title: "修改",
      editForm: {},
      formInline: {
        name: "",
      },
      listData: [], //用户数据

      options: ["男", "女"],
      statusList: ["未领养", "已领养", "删除"],
      statusList1: ["简单", "普通", "困难"],
      rules: {
        name: [{required: true, message: "请输入宠物名", trigger: "blur"}],
        category: [{required: true, message: "请输入品种", trigger: "blur"}],
        age: [{required: true, message: "请输入年龄", trigger: "blur"}],
        weight: [{required: true, message: "请输入体重", trigger: "blur"}],
        sex: [{required: true, message: "请选择性别", trigger: "change"}],
        status: [
          {required: true, message: "请选择领养状态", trigger: "change"},
        ],
        // status: [{required: true, message: "请选择", trigger: "blur"}],
        height: [
          {required: true, message: "请输入健康状况", trigger: "blur"},
        ],
        addr: [{required: true, message: "请输入健康状况", trigger: "blur"}],
        character: [
          {required: true, message: "请输入性格习惯", trigger: "blur"},
        ],
        demand: [
          {required: true, message: "请输入领养模式", trigger: "blur"},
        ],
      },
      type: 1, // 1-新增 2-修改
      loading: false,
      role: localStorage.getItem("role"),
      user: JSON.parse(localStorage.getItem("user")),
    };
  },


  created() {
    if (this.role == 1) {
      this.formInline.uid = this.user.id;
    }
    this.getList();
  },
  /**
   * 里面的方法只有被调用才会执行
   */
  methods: {
    getList() {
      let that = this;
      axios
        .post(this.httpUrl + "pet/list", that.formInline)
        .then((res) => {
          that.listData = res.data.list;
          this.loading = false;
        });
    },
    // 搜索事件
    search() {
      this.getList();
    },
    add() {
      this.type = 1;
      this.title = "新增";
      this.editFormVisible = true;
      this.editForm = {time: new Date()};
    },
    edit(row) {
      this.type = 2;
      this.title = "修改";
      this.editFormVisible = true;
      this.editForm = JSON.parse(JSON.stringify(row));
    },
    save(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          this.loading = true;
          if (this.type === 1) {
            this.editForm.date = new Date();
            if (this.role == 1) {
              this.editForm.uid = this.user.id;
            }
          }
          axios
            .post(
              this.httpUrl + `pet/${this.type === 1 ? "add" : "update"}`,
              this.editForm
            )
            .then((res) => {
              this.$message({
                type: "success",
                message: `${this.type === 1 ? "新增" : "修改"}成功`,
              });
              this.closeDialog();
              this.loading = false;
              this.getList();
            });
        } else {
          this.logining = false;
          return false;
        }
      });
    },
    // 删除
    deleteUser(row) {
      this.$confirm("确定要删除该宠物?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      })
        .then(() => {
          this.loading = true;
          axios
            .post(
              this.httpUrl + "pet/del",
              {id: row.id},
              {headers: {"Content-Type": "application/json"}}
            )
            .then((res) => {
              this.$message({
                type: "success",
                message: "删除成功",
              });
              // this.formInline.page = 1;
              this.getList(this.formInline);
            });
        })
        .catch((err) => {
          this.$message({
            type: "info",
            message: "已取消删除",
          });
          this.loading = false;
        });
    },
    // 关闭编辑、增加弹出框
    closeDialog() {
      this.editFormVisible = false;
    },
    onSuccess(response, file, fileList) {
      this.editForm.img = this.httpUrl + response.url;
      this.$message.success("上传成功！");
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
