
import Vue from 'vue'
import Vuex from 'vuex'

import userModule from '@/store/modules/user-module';
import articleModule from '@/store/modules/article-module';
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
        userModule,
        articleModule,
        jsonModule
    }
})