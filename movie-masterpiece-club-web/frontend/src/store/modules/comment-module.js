
import request from '@/request';

const OneCommentState = {};
const ListCommentState = {};

const state = {
    OneCommentState,
    ListCommentState
};

const getters = {

};

const actions = {

    createCommentOnArticle({commit}, params) {

        const uri = `article/${params.articleId}/comment`;

        const payload = {};
        payload.content = params.content;

        return new Promise((resolve, reject) => {

            request.post(uri, payload).then((response) => {
                commit('setOneCommentState', response);
            }).catch((error) => {
                console.error(error.response);
            })
        })

    }
};

const mutations = {

    setOneCommentState(state, response) {
        console.debug(response);
    }
};

export default {
    namespaced: true,
    state,
    getters,
    actions,
    mutations
}