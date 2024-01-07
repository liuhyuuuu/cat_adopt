<template>
  <!--  领养申请管理-->
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
          >
          </el-option>
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

    <!--列表-->
    <el-table size="small" :data="listData" border style="width: 100%">
      <el-table-column prop="nname" label="宠物主"></el-table-column>
      <el-table-column prop="pname" label="宠物"></el-table-column>
      <el-table-column prop="uname" label="申请人"></el-table-column>
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
      <el-table-column prop="tel" label="申请人电话"></el-table-column>
      <el-table-column align="center" label="操作" width="240">
        <template slot-scope="scope" v-if="scope.row.status === '申请中'">
          <el-button size="mini" type="success" @click="del(scope.row, '同意')"
          >同意
          </el-button>
          <el-button size="mini" type="danger" @click="del(scope.row, '拒绝')"
          >拒绝
          </el-button>
        </template>
      </el-table-column>
    </el-table>
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
      formInline: {},
      listData: [],
      user: {},
      options: ["申请中", "同意", "拒绝"],
      loading: false,
    };
  },
  /**
   * 创建完毕
   */
  created() {
    this.getList();
    this.user = JSON.parse(localStorage.getItem("user"));
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
    del(row, status) {
      this.$confirm("确定要" + status + "该申请?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      })
        .then(() => {
          this.loading = true;
          row.status = status
          axios
            .post(
              this.httpUrl + "order/update",
              row
            )
            .then((res) => {
              this.$message({
                type: "success",
                message: "成功",
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


