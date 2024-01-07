<template>
  <!--  个人中心中的领养进度-->
  <div v-loading="loading" class="card">
    <!-- 搜索筛选 -->
    <el-form :inline="true" :model="formInline" class="user-search">
      <el-form-item label="状态：">
        <el-select v-model="formInline.status" size="small" clearable placeholder="请选择">
          <el-option
            v-for="item in options"
            :key="item"
            :label="item"
            :value="item"
          ></el-option>
        </el-select>
      </el-form-item>
      <el-form-item>
        <el-button
          size="small"
          type="primary"
          icon="el-icon-search"
          @click="search"
        >搜索
        </el-button>
      </el-form-item>
    </el-form>

    <!--申请列表-->
    <el-table size="small" :data="listData" border style="width: 100%">
      <el-table-column prop="uname" label="申请人"></el-table-column>
      <el-table-column prop="pname" label="宠物"></el-table-column>
      <el-table-column prop="way" label="接收宠物方式"></el-table-column>
      <el-table-column prop="status" label="申请状态"></el-table-column>
      <el-table-column prop="remark" label="备注" show-overflow-tooltip>
      </el-table-column>
      <el-table-column label="申请时间">
        <template slot-scope="scope">
          <div>{{ scope.row.date | time }}</div>
        </template>
      </el-table-column>
      <el-table-column prop="addr" label="接收宠物地址"></el-table-column>
      <el-table-column prop="tel" label="电话"></el-table-column>
      <el-table-column align="center" label="操作" width="240">
        <template slot-scope="scope" v-if="scope.row.status === '申请中'">
          <el-button size="mini" type="success" @click="edit(scope.row)">修改</el-button>
          <el-button size="mini" type="danger" @click="del(scope.row)">取消</el-button>
        </template>
      </el-table-column>
    </el-table>

    <!--修改申请-->
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
        <el-form-item label="提货方式：" prop="way">
          <el-select
            v-model="editForm.way"
            size="small"
            placeholder="请选择提货方式"
          >
            <el-option
              v-for="item in options1"
              :key="item"
              :label="item"
              :value="item"
            >
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="收货人：" prop="name" v-if="editForm.way == '物流'">
          <el-input
            size="small"
            v-model="editForm.name"
            auto-complete="off"
            placeholder="请输入"
          ></el-input>
        </el-form-item>
        <el-form-item label="收货人电话：" prop="tel" v-if="editForm.way == '物流'">
          <el-input
            size="small"
            v-model="editForm.tel"
            auto-complete="off"
            placeholder="请输入"
          ></el-input>
        </el-form-item>
        <el-form-item label="收货地址：" prop="addr" v-if="editForm.way == '物流'">
          <el-input
            size="small"
            v-model="editForm.addr"
            auto-complete="off"
            placeholder="请输入"
          ></el-input>
        </el-form-item>
        <el-form-item label="备注：">
          <el-input
            size="small"
            v-model="editForm.remark"
            auto-complete="off"
            placeholder="请输入备注"
          ></el-input>
        </el-form-item>
      </el-form>

      <div slot="footer" class="dialog-footer">
        <el-button size="small" @click="closeDialog">返回</el-button>
        <el-button size="small" type="primary" @click="save('editForm')">保存
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
      editFormVisible: false, //控制编辑页面显示与隐藏
      title: "修改",
      editForm: {},
      formInline: {
        uid: JSON.parse(localStorage.getItem('user')).id,
      },
      listData: [],
      user: {},
      options: ["申请中", "同意", "拒绝"],
      loading: false,
      rules: {
        way: [{required: true, message: "请选择接收宠物方式", trigger: "change"}],
        name: [{required: true, message: "请输入", trigger: "blur"}],
        tel: [{required: true, message: "请输入", trigger: "blur"}],
        addr: [{required: true, message: "请输入", trigger: "blur"}],
      },
      options1: ["自提", "物流"],
    };
  },


  created() {
    this.getList();
    this.user = JSON.parse(localStorage.getItem("user"));
  },

  // 过滤器修改时间格式
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

  methods: {
    getList() {
      let that = this;
      axios.post(this.httpUrl + "order/list", that.formInline).then((res) => {
        that.listData = res.data.list;
        this.loading = false;
      });
    },
    // 搜索事件
    search() {
      this.getList();
    },
    edit(item) {
      this.title = "修改信息"
      this.editFormVisible = true;
      this.editForm = {...item};
    },
    save(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          this.loading = true
          this.editForm.date = new Date()
          axios.post(this.httpUrl + `order/update`, this.editForm)
            .then((res) => {
              this.$message({
                type: "success",
                message: `修改成功，等待审核`,
              });
              this.getList();
              this.closeDialog();
            });
        } else {
          return false;
        }
      });
    },
    del(row) {
      this.$confirm("确定要取消该申请吗?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      })
        .then(() => {
          this.loading = true;
          axios
            .post(
              this.httpUrl + "order/del",
              {id: row.id},
              {headers: {"Content-Type": "application/json"}}
            )
            .then((res) => {
              this.$message({
                type: "success",
                message: "取消成功",
              });
              this.loading = false;
              this.getList(this.formInline);
            });
        })
        .catch((err) => {
          this.$message({
            type: "info",
            message: "已取消",
          });
          this.loading = false;
        });
    },
    closeDialog() {
      this.editFormVisible = false;
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
