
import Vue from 'vue'
import VueRouter from 'vue-router'

Vue.use(VueRouter);

export default new VueRouter({

    mode: 'history',

    routes: [
        {
            path: '/login',
            name: 'login',
            component: () => import('@/pages/login/Login')
        },
        {
            path: '',
            component: () => import('@/pages/article/index'),
            children: [
                {
                    path: '/',
                    name: 'articleList',
                    component: () => import('@/pages/article/ArticleList'),
                },
                {
                    path: '/article-edit',
                    name: 'articleEdit',
                    component: () => import('@/pages/article/ArticleEdit')
                }
            ]
        },
        {
            path: '/movie-to-json',
            name: 'movie',
            component: () => import("@/pages/movie/Movie2Json")
        },
    ]

});