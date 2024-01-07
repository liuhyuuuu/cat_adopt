<template>
  <div v-loading="loading">
    <!-- 搜索筛选 -->
    <el-form :inline="true" :model="formInline" class="user-search">
      <el-form-item label="标题：">
        <el-input
          size="small"
          v-model="formInline.title"
          placeholder="输入标题"
        ></el-input>
      </el-form-item>
      <el-form-item>
        <el-button
          size="small"
          type="primary"
          icon="el-icon-search"
          @click="search"
          >搜索</el-button
        >
        <el-button size="small" type="danger" icon="el-icon-plus" @click="add"
          >新增</el-button
        >
      </el-form-item>
    </el-form>
    <!--列表-->
    <el-table size="small" :data="listData" border style="width: 100%">
      <el-table-column prop="type" label="所属类型"> </el-table-column>
      <el-table-column prop="title" label="标题"> </el-table-column>
      <el-table-column prop="content" label="内容" show-overflow-tooltip>
      </el-table-column>
      <el-table-column label="图片">
        <template slot-scope="scope">
          <el-image
            style="width: 100px; height: 50px"
            :src="scope.row.img"
          >
          </el-image>
        </template>
      </el-table-column>
      <el-table-column label="发送日期">
        <template slot-scope="scope">
          <div>{{ scope.row.date | time }}</div>
        </template>
      </el-table-column>
      <el-table-column align="center" label="操作" width="240">
        <template slot-scope="scope">
          <el-button size="mini" type="primary" @click="edit(scope.row)"
            >修改</el-button
          >
          <el-button size="mini" type="danger" @click="deleteUser(scope.row)"
            >删除</el-button
          >
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
        <el-form-item label="所属类别：" prop="type">
          <el-select v-model="editForm.type" size="small" placeholder="请选择">
            <el-option
              v-for="item in options"
              :key="item"
              :label="item"
              :value="item"
            >
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="标题：" prop="title">
          <el-input
            size="small"
            v-model="editForm.title"
            auto-complete="off"
            placeholder="请输入标题"
          ></el-input>
        </el-form-item>
        <el-form-item label="内容：" prop="content">
          <el-input
            size="small"
            v-model="editForm.content"
            auto-complete="off"
            type="textarea"
            :rows="2"
            placeholder="请输入内容"
          ></el-input>
        </el-form-item>
        <el-form-item label="图片：">
          <el-upload
            style="display: inline"
            :show-file-list="false"
            :limit="1"
            :on-success="onSuccess"
            :action="httpUrl + 'file/upload'"
          >
            <el-button type="warning" size="mini" style="margin-left: 10px"
              >上传</el-button
            >
            <span style="color: #67c23a">{{ editForm.img }}</span>
          </el-upload>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button size="small" @click="closeDialog">返回</el-button>
        <el-button size="small" type="primary" @click="save('editForm')"
          >保存</el-button
        >
      </div>
    </el-dialog>
  </div>
</template>

<script>
import axios from "axios";
// import Pagination from "../../components/Pagination";
export default {
  data() {
    return {
      editFormVisible: false, //控制编辑页面显示与隐藏
      title: "修改",
      editForm: {},
      formInline: {
        // page: 1,
        limit: 10,
        name: "",
      },
      listData: [], //用户数据
      // 分页参数
      // formInline: {
      //   uid: -1,
      // },
      user: {},
      rules: {
        type: [
          { required: true, message: "请选择所属类别", trigger: "change" },
        ],
        title: [{ required: true, message: "请输入标题", trigger: "blur" }],
        content: [{ required: true, message: "请输入内容", trigger: "blur" }],
      },
      type: 1, // 1-新增
      loading: false,
      options: ["喂养", "健康", "训练", "行为解析"],
    };
  },
  // 注册组件
  // components: {
  //   Pagination,
  // },
  /**
   * 创建完毕
   */
  created() {
    this.getList();
  },
  filters: {
    time(item) {
      let date = new Date(item);
      let Y = date.getFullYear() + "-";
      let M =
        (date.getMonth() + 1 < 10
          ? "0" + (date.getMonth() + 1)
          : date.getMonth() + 1) + "-";
      let D =
        (date.getDate() < 10 ? "0" + date.getDate() : date.getDate()) + " ";
      return Y + M + D;
    },
  },
  /**
   * 里面的方法只有被调用才会执行
   */
  methods: {
    getList() {
      let that = this;
      axios.post(this.httpUrl + "tips/list", that.formInline).then((res) => {
        that.listData = res.data.list;
        this.loading = false;
      });
    },
    // 搜索事件
    search() {
      this.getList();
    },
    edit(row) {
      this.type = 2;
      this.title = "修改";
      this.editFormVisible = true;
      this.editForm = JSON.parse(JSON.stringify(row));
    },
    add() {
      this.type = 1;
      this.title = "新增";
      this.editFormVisible = true;
      this.editForm = { date: new Date(), uid: -1 };
    },
    // edit(row) {
    //   this.type = 2;
    //   this.title = "修改";
    //   this.editFormVisible = true;
    //   this.editForm = { ...row };
    // },
    save(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          this.loading = true;
          if (this.type === 1) {
            this.editForm.date = new Date();
          }
          axios
            .post(
              this.httpUrl + `tips/${this.type === 1 ? "add" : "edit"}`,
              this.editForm
            )
            .then((res) => {
              this.$message({
                type: "success",
                message: `${this.type === 1 ? "新增" : "修改"}成功`,
              });
              this.closeDialog();
              this.logining = false;
              this.getList();
            });
        } else {
          this.logining = false;
          return false;
        }
      });
    },
    deleteUser(row) {
      this.$confirm("确定要删除?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      })
        .then(() => {
          this.loading = true;
          axios.post(this.httpUrl + "tips/del", { id: row.id }).then((res) => {
            this.$message({
              type: "success",
              message: "删除成功",
            });
            this.formInline.page = 1;
            this.getList(this.formInline);
          });
        })
        .catch((err) => {
          this.loading = false;
        });
    },
    // 关闭编辑、增加弹出框
    closeDialog() {
      this.editFormVisible = false;
    },
    onSuccess(response, file, fileList) {
      this.editForm.img =this.httpUrl + response.url;
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
