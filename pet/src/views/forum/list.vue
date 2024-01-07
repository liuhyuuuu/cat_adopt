<template>
  <!--  论坛-->
  <div>
    <!--    搜索新增-->
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
          @click="getdata"
        >搜索
        </el-button
        >
        <el-button size="small" type="danger" @click="add">新增</el-button>
      </el-form-item>
    </el-form>

    <!--    论坛表单-->
    <div v-for="item in listData" :key="item.id" class="card">
      <div>
        <span class="title">{{ item.name }}</span>
        <span class="time"
        >发布时间：{{
            item.date.substring(0, 10) + " " + item.date.substring(11, 19)
          }}
        </span>
      </div>
      <div class="content" @click="comment(item)">{{ item.message }}</div>
      <el-image
        style="width: 300px; height: 200px"
        :src="item.img"
        v-if="item.img"
      ></el-image>
      <!--      回复-->
      <div class="reply" v-if="item.reply">
        <div v-for="(item1, index) in item.replys" :key="index">
          <div>
            <span class="title">{{ item1.name }}</span>
            <span class="time"
            >发布时间：{{
                item1.date.substring(0, 10) + " " + item1.date.substring(11, 19)
              }}
            </span>
          </div>
          <div class="content">{{ item1.message }}</div>
        </div>
      </div>
    </div>

    <!--    发布帖子-->
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
        <el-form-item label="图片：">
          <el-upload
            style="display: inline"
            :show-file-list="false"
            :limit="1"
            :on-success="onSuccess"
            :action="httpUrl + 'file/upload'"
          >
            <el-button type="warning" size="mini" style="margin-left: 10px">上传
            </el-button>
            <span style="color: #67c23a">{{ editForm.img }}</span>
          </el-upload>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button size="small" @click="closeDialog">返回</el-button>
        <el-button
          size="small"
          type="primary"
          class="title"
          @click="submitForm('editForm')">保存
        </el-button>
      </div>
    </el-dialog>

    <!--    回复帖子-->
    <el-dialog
      :title="title"
      :visible.sync="editPwdVisible"
      width="50%"
      @click="closeDialog"
    >
      <el-form
        label-width="80px"
        :model="editForm"
        :rules="rules1"
        ref="editFormPwd"
      >
        <el-form-item label="回复" prop="hf">
          <el-input
            size="small"
            type="textarea"
            :rows="5"
            v-model="editForm.hf"
            auto-complete="off"
            placeholder="请输入回复"
            show-password
          ></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button size="small" @click="closeDialog">返回</el-button>
        <el-button size="small" type="primary" @click="submitPwd('editFormPwd')">保存
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
      editModelVisible: false,
      editPwdVisible: false,
      title: "",
      editForm: {},
      listData: [], //用户数据
      formInline: {},
      user: {},
      obj: {},
      rules: {
        message: [{required: true, message: "请输入内容", trigger: "blur"}],
      },
      rules1: {
        hf: [{required: true, message: "请输入回复", trigger: "blur"}],
      },
      role: localStorage.getItem("role"),
    };
  },
  created() {
    this.getdata();
    this.user = JSON.parse(localStorage.getItem("user"));
  },

  /**
   * 里面的方法只有被调用才会执行
   */
  methods: {
    getdata() {
      let that = this;
      axios.post(this.httpUrl + "forum/list", this.formInline)
        .then((res) => {
          that.listData = res.data.list;
          that.listData.forEach((item) => {
            item.replys = JSON.parse(item.reply || "[]");
          });
        });
    },
    add() {
      this.editModelVisible = true;
      this.editForm = {
        uid: this.user.id,
      };
      this.title = "发布帖子";
    },
    comment(row) {
      this.title = "评价帖子";
      this.editPwdVisible = true;
      this.editForm = {...row};
    },
    submitForm(editData) {
      this.$refs[editData].validate((valid) => {
        if (valid) {
          this.editForm.date = new Date()
          axios.post(this.httpUrl + "forum/add", this.editForm).then((res) => {
            if (res.data.code === 200) {
              this.getdata();
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
    submitPwd(row) {
      this.$refs[row].validate((valid) => {
        if (valid) {
          this.editForm.replys.push({
            name: this.user.name,
            message: this.editForm.hf,
            date: new Date(),
          });
          this.editForm.reply = JSON.stringify(this.editForm.replys);
          axios.post(this.httpUrl + "forum/edit", this.editForm).then((res) => {
            this.$message({
              type: "success",
              message: "回复成功",
            });
            this.editPwdVisible = false;
            this.getdata();
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

.card {
  border: solid 1px #ccc;
  border-radius: 10px;
  padding: 10px 20px;
  margin-bottom: 15px;
  line-height: 30px;
}

.title {
  font-size: 14px;
  font-weight: bold;
  color: #348ef5;
}

.time {
  font-size: 14px;
  color: #666;
  margin-left: 20px;
}

.content {
  font-size: 15px;
  color: #333;
}

.reply {
  background: #d3d2d2;
  padding: 10px;
  margin-top: 10px;
  border-radius: 10px;
}
</style>
