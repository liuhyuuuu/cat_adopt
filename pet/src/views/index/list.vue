<template>
  <div v-loading="loading">
    <!--    宠物知识-->
    <el-row :gutter="20">
      <el-col :span="16">
        <el-row :gutter="20" class="plist">
          <div class="title" style="margin-left: 10px">
            <span>宠物待领养列表</span>
          </div>
          <el-col :span="12" v-for="(item, index) in pets" :key="index">
            <div class="pet-card" style="display: flex">
              <el-image
                style="
                  width: 110px;
                  height: 110px;
                  border-radius: 10px;
                  margin-right: 10px;
                "
                :src="item.img"
                :preview-src-list="srcList"
                fit="cover"
                @click="preview(item.img)"
              ></el-image>
              <div style="width: calc(100% - 120px)">
                <div class="pt">
                  {{ item.name }}
                  <a href="javascript: void(0)" class="ly" @click="ly(item)">领养</a>
                  <a
                    href="javascript: void(0)"
                    class="ly"
                    @click="detail(item)"
                    style="margin-right: 10px">详情</a>
                </div>
                <div class="ptt">
                  <span>性别：{{ item.sex }}</span>
                  <span style="float: right">年龄:{{ item.age }}</span>
                </div>
                <div class="ptt">
                  <span>健康状况:{{ item.height }}</span>
                  <span style="float: right">品种：{{ item.category }}</span>
                </div>
                <div class="ptt">所在地:{{ item.addr }}</div>
              </div>
            </div>
          </el-col>
        </el-row>
      </el-col>
      <el-col :span="8">
        <div class="card card1">
          <div class="title">宠物知识</div>
          <div
            v-for="item in tips"
            :key="item.id"
            style="margin: 10px 0"
            @click="view(item)"
          >
            <el-tag
              effect="dark"
              :type="
                item.type == '喂养'
                  ? 'primary'
                  : item.type == '健康'
                  ? 'success'
                  : item.type == '训练'
                  ? 'warning'
                  : 'danger'
              "
              size="mini"
            >
              {{ item.type }}
            </el-tag>
            <span>{{ item.title }}</span>
          </div>
        </div>
        <div class="card">
          <div>
            <el-button type="warning" @click="goto('/ask/list')">领养留言
            </el-button>
          </div>
        </div>
      </el-col>
    </el-row>
<!--领养申请-->
    <el-dialog
      :title="title"
      :visible.sync="editFormVisible"
      width="50%"
      @click="closeDialog"
    >
      <div v-if="flag">
        <div style="color: red">
          提示：该宠物的领养难度为：{{ editForm.type }},你需要达到{{
            editForm.type == "简单" ? 60 : editForm.type == "普通" ? 80 : 100
          }}分，每题10分
        </div>
        <div
          v-for="(item, index) in question"
          :key="index"
          style="line-height: 30px; margin-top: 10px"
        >
          <div>{{ index + 1 }}、{{ item.title }}</div>
          <el-radio-group v-model="item.check">
            <el-radio label="a">{{ item.qa }}</el-radio>
            <el-radio label="b">{{ item.qb }}</el-radio>
            <el-radio label="c">{{ item.qc }}</el-radio>
            <el-radio label="d">{{ item.qd }}</el-radio>
          </el-radio-group>
        </div>
      </div>

      <el-form
        v-else
        label-width="100px"
        :rules="rules"
        :model="editForm"
        ref="editForm"
      >
        <el-form-item label="宠物：" prop="name">
          <el-input
            size="small"
            v-model="editForm.name"
            auto-complete="off"
            placeholder="请输入宠物"
          ></el-input>
        </el-form-item>
        <el-form-item label="提货方式：" prop="way">
          <el-select
            v-model="editForm.way"
            size="small"
            placeholder="请选择提货方式"
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
        <el-form-item label="收货人：" prop="name" v-if="editForm.way == '物流'">
          <el-input
            size="small"
            v-model="editForm.uname"
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
        <el-button size="small" type="primary" @click="sumbit()" v-if="flag"
        >提交
        </el-button>
        <el-button size="small" type="primary" @click="save('editForm')" v-else
        >保存
        </el-button
        >
      </div>
    </el-dialog>

    <el-dialog
      :title="title"
      :visible.sync="editFormVisible1"
      width="50%"
      @click="closeDialog"
    >
      <div style="text-align: center; font-size: 20px; font-weight: bold">
        {{ editForm.title }}
      </div>
      <div
        style="text-align: center; color: #999; margin: 15px 0"
        v-if="editForm.date"
      >
        发布时间：{{
          editForm.date.substring(0, 10) + " " + editForm.date.substring(11, 19)
        }}
      </div>
      <div style="display: flex">
        <el-image style="width: 300px" :src="editForm.img"></el-image>
        <div
          style="flex: 1; margin-left: 10px; font-size: 15px; line-height: 25px"
        >
          {{ editForm.content }}
        </div>
      </div>
      <div slot="footer" class="dialog-footer">
        <el-button size="small" @click="closeDialog">返回</el-button>
      </div>
    </el-dialog>

    <el-dialog
      :title="title"
      :visible.sync="editFormVisible2"
      width="50%"
      @click="closeDialog"
    >
      <el-row :gutter="20">
        <el-col :span="16" style="line-height: 30px">
          <el-col :span="12">名字：{{ editForm.name }}</el-col>
          <el-col :span="12">品种：{{ editForm.category }}</el-col>
          <el-col :span="12">体重：{{ editForm.weight }}</el-col>
          <el-col :span="12">性别：{{ editForm.sex }}</el-col>
          <el-col :span="12">年龄：{{ editForm.age }}</el-col>
          <el-col :span="12">健康状况：{{ editForm.height }}</el-col>
        </el-col>
        <el-col :span="8">
          <el-image style="height: 100px" :src="editForm.img"></el-image>
        </el-col>
        <el-row :gutter="20">
          <el-col :span="8">生活习惯：{{ editForm.character }}</el-col>
          <el-col :span="8">领养模式：{{ editForm.demand }}</el-col>
          <el-col :span="8">所在地：{{ editForm.addr }}</el-col>
        </el-row>
      </el-row>
      <div slot="footer" class="dialog-footer">
        <el-button size="small" @click="closeDialog">返回</el-button>
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
      editFormVisible1: false,
      editFormVisible2: false,
      title: "修改",
      editForm: {},
      options: ["自提", "物流"],
      video: "",
      pets: [],
      tips: [],
      user: {},
      role: 0,
      notices: [],
      loading: false,
      rules: {
        way: [{required: true, message: "请选择提货方式", trigger: "change"}],
        name: [{required: true, message: "请输入", trigger: "blur"}],
        tel: [{required: true, message: "请输入", trigger: "blur"}],
        addr: [{required: true, message: "请输入", trigger: "blur"}],
      },
      srcList: [],
      kinds: [],
      colors: [],
      formInline: {
        status: "未领养",
      },
      options1: ["喂养", "健康", "训练", "行为解析"],
      question: [],
      flag: true,
      score: 0,
    };
  },


  created() {
    this.getPet();
    this.getTips();
    this.user = JSON.parse(localStorage.getItem("user"));
    this.role = localStorage.getItem("role");
  },

  methods: {
    preview(url) {
      this.srcList = [url];
    },
    getPet() {
      let that = this;
      axios.post(this.httpUrl + "pet/list", that.formInline).then((res) => {
        that.pets = res.data.list;
      });
    },
    getTips() {
      let that = this;
      axios.post(this.httpUrl + "tips/list", {}).then((res) => {
        that.tips = res.data.list;
      });
    },
    ly(item) {
      if (this.role !== "1" && this.role !== "2") {
        this.$message({
          type: "error",
          message: "领养请先注册会员！",
        });
      } else {
        this.title = "领养答题与相关信息填写";
        this.editFormVisible = true;
        this.getQuestion();
        this.editForm = {
          pid: item.id,
          uid: this.user.id,
          status: "申请中",
          name: item.name,
          uname: this.user.name,
          nid: item.uid,
          type: item.demand,
        };
      }
    },
    getQuestion() {
      let that = this;
      axios.post(this.httpUrl + "question/list").then((res) => {
        that.question = res.data.list;
      });
    },
    view(item) {
      this.title = "宠物知识之-" + item.title;
      this.editFormVisible1 = true;
      this.editForm = {...item};
    },
    detail(item) {
      this.title = item.name + "-详细信息";
      this.editFormVisible2 = true;
      this.editForm = {...item};
    },
    save(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          this.loading = true;
          this.editForm.date = new Date();
          axios.post(this.httpUrl + `order/add`, this.editForm).then((res) => {
            this.loading = false;
            if (res.data.code === -1) {
              this.$message({
                type: "error",
                message: res.data.msg,
              });
              return;
            }
            this.$message({
              type: "success",
              message: `领养申请成功，等待审核`,
            });
            this.getPet();
            this.closeDialog();
          });
        } else {
          this.loading = false;
          return false;
        }
      });
    },
    // 关闭编辑、增加弹出框
    closeDialog() {
      this.editFormVisible = false;
      this.editFormVisible1 = false;
      this.editFormVisible2 = false;
    },
    goto(url) {
      this.$router.push({path: url});
    },
    reset() {
      this.formInline = {status: "未领养"};
      this.getPet();
    },
    sumbit() {
      this.score = 0;
      this.question.forEach((item) => {
        if (item.answer == item.check) {
          this.score += 10;
        }
      });
      let target =
        this.editForm.type == "简单"
          ? 60
          : this.editForm.type == "普通"
            ? 80
            : 100;
      if (this.score < target) {
        this.$message({
          type: "error",
          message: `您的分数为${this.score}分，非常抱歉，考试为不通过，不能领养`,
        });
        this.closeDialog()
      } else {
        this.$message({
          type: "success",
          message: `恭喜你，通过考核，请填写领养信息`,
        });
        this.flag = false
      }
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
  padding: 10px;
  margin-bottom: 20px;
}

.card1 {
  height: calc(100vh - 225px);
  overflow: auto;
}

.title {
  color: #000;
  font-weight: bold;
  margin-bottom: 5px;
}

.pet-card {
  border: solid 1px #ccc;
  border-radius: 10px;
  padding: 10px;
  background: #f2f2f2;
  line-height: 2;
  margin-bottom: 20px;
}

.pt {
  color: #000;
  font-weight: bold;
  font-size: 15px;
}

.ptt {
  color: #666;
  font-size: 13px;
}

.ly {
  color: #409eff;
  font-size: 14px;
  font-weight: 400;
  float: right;
}

.notice {
  color: #409eff;
  font-size: 14px;
  font-weight: 400;
  line-height: 2;
}

.notices {
  height: 25vh;
  overflow-y: auto;
}

.plist {
  height: 85vh;
  overflow-y: auto;
}

.el-form-item[data-v-69489bd5] {
  margin-bottom: 0;
}

.user-search[data-v-69489bd5] {
  margin-top: 0;
}
</style>


