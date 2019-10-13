
import Vue from 'vue'
import Vuex from 'vuex'

import auth from '@/store/auth'
import userModule from '@/store/modules/user-module';
import articleModule from '@/store/modules/article-module';
import commentModule from '@/store/modules/comment-module';
import laboratoryModule from '@/store/modules/laboratory-module';
import jsonModule from '@/store/modules/json.module';

Vue.use(Vuex);

export default new Vuex.Store({
    state: {

    },

    actions: {

    },

    mutations: {

    },

    getters: {

    },

    modules: {
        auth,
        userModule,
        articleModule,
        commentModule,
        laboratoryModule,
        jsonModule
    }
})