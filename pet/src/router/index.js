// 导入组件
import Vue from 'vue';
import Router from 'vue-router';
// 登录
import login from '@/views/login';
// 首页头部菜单
import index from '@/views/index';

// 首页
import petindex from '@/views/index/list';

// 领养申请列表管理
import order from '@/views/order/list';
// 个人领养进度
import myorder from '@/views/order/my';

// 宠物管理
import petInfo from '@/views/pet/list';
// 领养中心
import tpetInfo from '@/views/pet/tlist';

// 宠物知识管理
import tips from '@/views/tips/list';

// 论坛
import forum from '@/views/forum/list';

// 用户管理
import user from '@/views/my/list';
// 个人中心
import myInfo from '@/views/my/my';

// 留言
import ask from '@/views/ask/list';

// 启用路由
Vue.use(Router);

// 路由
export default new Router({
    routes: [{
        path: '/',
        name: '首页',
        component: index,
        iconCls: 'el-icon-tickets',
        children: [{
            path: '/',
            name: '首页',
            component: petindex,
            meta: {
                requireAuth: true
            }
        }]
    },{
        path: '/login',
        name: '登录',
        component: login,
        hidden: true,
        meta: {
            requireAuth: false
        }
    },
    {
        path: '/index',
        name: '首页',
        component: index,
        iconCls: 'el-icon-tickets',
        children: [{
            path: '/index/list',
            name: '首页',
            component: petindex,
            meta: {
                requireAuth: true
            }
        }]
    },
    {
        path: '/index',
        name: '首页',
        component: index,
        iconCls: 'el-icon-tickets',
        children: [{
            path: '/order/list',
            name: '领养申请列表管理',
            component: order,
            meta: {
                requireAuth: true
            }
        }]
    },
    {
        path: '/index',
        name: '首页',
        component: index,
        iconCls: 'el-icon-tickets',
        children: [{
            path: '/order/my',
            name: '用户个人领养进度',
            component: myorder,
            meta: {
                requireAuth: true
            }
        }]
    },
    {
        path: '/index',
        name: '',
        component: index,
        iconCls: 'el-icon-tickets',
        children: [{
            path: '/pet/list',
            name: '宠物管理',
            component: petInfo,
            meta: {
                requireAuth: true
            }
        }]
    },
    {
        path: '/index',
        name: '',
        component: index,
        iconCls: 'el-icon-tickets',
        children: [{
            path: '/pet/tlist',
            name: '领养中心',
            component: tpetInfo,
            meta: {
                requireAuth: true
            }
        }]
    },
    {
        path: '/index',
        name: '',
        component: index,
        iconCls: 'el-icon-tickets',
        children: [{
            path: '/my/my',
            name: '个人信息',
            component: myInfo,
            meta: {
                requireAuth: true
            }
        }]
    },
    {
        path: '/index',
        name: '',
        component: index,
        iconCls: 'el-icon-tickets',
        children: [{
            path: '/my/list',
            name: '用户管理',
            component: user,
            meta: {
                requireAuth: true
            }
        }]
    },
    {
        path: '/index',
        name: '',
        component: index,
        iconCls: 'el-icon-tickets',
        children: [{
            path: '/forum/list',
            name: '论坛',
            component: forum,
            meta: {
                requireAuth: true
            }
        }]
    },
    {
        path: '/index',
        name: '',
        component: index,
        iconCls: 'el-icon-tickets',
        children: [{
            path: '/tips/list',
            name: '宠物知识管理',
            component: tips,
            meta: {
                requireAuth: true
            }
        }]
    },
    {
        path: '/index',
        name: '',
        component: index,
        iconCls: 'el-icon-tickets',
        children: [{
            path: '/ask/list',
            name: '留言',
            component: ask,
            meta: {
                requireAuth: true
            }
        }]
    }
]
})
