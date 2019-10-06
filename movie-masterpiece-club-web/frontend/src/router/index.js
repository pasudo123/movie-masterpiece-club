import Vue from 'vue'
import VueRouter from 'vue-router'

Vue.use(VueRouter);

export default new VueRouter({

    mode: 'history',

    base: process.env.NODE_ENV === 'production'
        ? process.env.VUE_APP_ROUTER_PATH
        : '/',

    routes: [
        {
            path: '/login',
            name: 'login',
            component: () => import('@/pages/login/Login')
        },
        {
            path: '/masterpiece',
            component: () => import('@/pages/masterpiece/Home'),
            children: [
                {
                    path: '',
                    name: 'articleList',
                    component: () => import('@/pages/masterpiece/ArticleList'),
                },
                {
                    path: 'article-edit',
                    name: 'articleEdit',
                    component: () => import('@/pages/masterpiece/ArticleEdit')
                }
            ]
        },
        {
            path: '/laboratory'
            
        }
    ]
});