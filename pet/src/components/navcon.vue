<!--头部菜单-->
<template>
  <el-menu class="el-menu-demo" mode="horizontal" active-text-color="#8f8400" background-color="#fff68f" text-color="#0a0a0a">
    <span class="title">猫语</span>
    <el-submenu index="2" class="submenu">
      <template slot="title">欢迎您： {{user?user.name:'未登录'}}</template>
      <el-menu-item @click="content()" index="2-2" v-if="role==='1'||role==='2'">个人中心</el-menu-item>
      <el-menu-item @click="exit()" index="2-3" v-if="role==='1'||role==='2'">退出</el-menu-item>
      <el-menu-item @click="goto()" index="2-4" v-else>去登录</el-menu-item>
    </el-submenu>
    <el-menu-item index="11" @click="to('/pet/list')" class="submenu" v-if="role==='1'">登记猫咪信息</el-menu-item>
    <el-menu-item index="9" @click="to('/forum/list')" class="submenu" v-if="role==='1'">论坛</el-menu-item>
    <el-menu-item index="8" @click="to('/pet/tlist')" class="submenu" v-if="role==='1'">领养中心</el-menu-item>

    <el-menu-item index="5" @click="to('/ask/list')" class="submenu" v-if="role==='2'">留言管理</el-menu-item>
    <el-menu-item index="6" @click="to('/forum/list')" class="submenu" v-if="role==='2'">论坛管理</el-menu-item>
    <el-menu-item index="4" @click="to('/tips/list')" class="submenu" v-if="role==='2'">猫咪知识管理</el-menu-item>
    <el-menu-item index="3" @click="to('/order/list')" class="submenu" v-if="role==='2'">领养管理</el-menu-item>
    <el-menu-item index="2" @click="to('/pet/list')" class="submenu" v-if="role==='2'">猫咪管理</el-menu-item>
    <el-menu-item index="10" @click="to('/my/list')" class="submenu" v-if="role==='2'">用户管理</el-menu-item>
    <el-menu-item index="1" @click="to('/index/list')" class="submenu">首页</el-menu-item>
  </el-menu>
</template>
<script>
export default {
  name: 'navcon',
  data() {
    return {
      collapsed: true,
      user: {},
      role: 0,
      data:[]
    }
  },
  mounted(){
    this.user = localStorage.getItem('user')?JSON.parse(localStorage.getItem('user')):{}
    this.role = localStorage.getItem('role')
  },
  methods: {
    // 退出登录
    exit() {
      this.$confirm('退出登录, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      })
        .then(() => {
          localStorage.setItem("user", undefined);
          this.user.name = '未登录'
          this.role = "0"
          localStorage.setItem("role", 0);
            this.$message({
              type: 'success',
              message: '已退出登录!'
          })
        })
        .catch(() => {
          this.$message({
            type: 'info',
            message: '已取消'
          })
        })
    },
    content(){
      this.$router.push({path: '/my/my'})
    },
    // 切换显示
    toggle(showtype) {
      this.collapsed = !showtype
      this.$root.Bus.$emit('toggle', this.collapsed)
    },
    to(url){
      this.$router.push({path: url})
    },
    goto(){
      this.$router.push({ path: '/login' })
    }
  }
}
</script>
<style scoped>
.title{
  color: #0a0a0a;
  line-height: 60px;
  margin-left: 20px;
  font-size: 30px;
  letter-spacing: 5px;
  font-family:KaiTi
}
.el-menu-vertical-demo:not(.el-menu--collapse) {
  border: none;
}
.submenu {
  float: right;
}
.buttonimg {
  height: 60px;
  background-color: transparent;
  border: none;
}
.showimg {
  width: 26px;
  height: 26px;
  position: absolute;
  top: 17px;
  left: 17px;
}
.showimg:active {
  border: none;
}
</style>
