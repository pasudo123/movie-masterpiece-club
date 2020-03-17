import Vue from 'vue';
import VueRouter from 'vue-router';
import Layout from '@/layout';

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
      path: '/',
      component: Layout,
      redirect: '/home',
      children: [
        {
          path: 'home',
          name: 'Home',
          component: () => import('@/views/board/ArticleList')
        }
      ]
    },
    {
      path: '/article',
      component: Layout,
      redirect: '/home',
      children: [
        {
          path: ':id',
          name: "Article",
          component: () => import('@/views/board/ArticleView')
        },
      ]
    }
    // {
    //   path: '/index',
    //   name: 'index',
    //   component: () => import('@/pages/masterpiece/Home'),
    //   children: [
    //     {
    //       path: '',
    //       name: 'articleList',
    //       component: () => import('@/pages/masterpiece/components/ArticleList')
    //     },
    //     {
    //       path: 'article-edit',
    //       name: 'articleEdit',
    //       component: () => import('@/pages/masterpiece/components/ArticleEdit'),
    //       children: [
    //         {
    //           path: ':articleId',
    //           name: 'articleOneEdit',
    //           component: () => import('@/pages/masterpiece/components/ArticleEdit'),
    //         }
    //       ]
    //     },
    //     {
    //       path: 'article/:articleId',
    //       name: 'articleView',
    //       component: () => import('@/pages/masterpiece/components/ArticleView')
    //     }
    //   ]
    // },
    // {
    //   path: '/laboratory',
    //   name: 'laboratory',
    //   component: () => import('@/pages/laboratory/Laboratory')
    // }
  ]
});
