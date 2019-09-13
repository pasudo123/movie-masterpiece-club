
import Vue from 'vue'
import VueRouter from 'vue-router'

Vue.use(VueRouter);

export default new VueRouter({

    mode: 'history',

    routes: [
        {
            path: '/movie-to-json',
            name: 'movie',
            component: () => import("@/pages/movie/Movie2Json")
        },
    ]

});