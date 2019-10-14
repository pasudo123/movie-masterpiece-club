
import request from '@/request';
import {convertDate2Hangul} from '@/utils/moment-util';

const OneCommentState = {};
const ListCommentState = [];

const state = {
    OneCommentState,
    ListCommentState
};

const getters = {
    OneCommentState: (state) => state.OneCommentState,
    ListCommentState: (state) => state.ListCommentState
};

const actions = {

    createCommentOnArticle({commit}, params) {

        const uri = `article/${params.articleId}/comment`;

        const payload = {};
        payload.content = params.content;

        return new Promise((resolve, reject) => {

            request.post(uri, payload).then((response) => {
                commit('addCommentOnListCommentState', response);
                resolve();
            }).catch((error) => {
                console.error(error.response);
                reject(error);
            })
        })
    },

    fetchCommentList({commit}, params) {

        const uri = `article/${params.articleId}/comment`;

        return new Promise((resolve, reject) => {

            request.get(uri).then((response) => {
                commit('setListCommentState', response);
                resolve();
            }).catch((error) => {
                console.error(error.response);
                reject(error);
            })
        })
    },




};

const mutations = {

    addCommentOnListCommentState(state, response) {

        let comment = response.data;
        comment.registerDate = convertDate2Hangul(comment.registerDate);

        state.ListCommentState.push(comment)
    },

    setListCommentState(state, response) {

        state.ListCommentState = [];

        response.data.commentList.forEach((element) => {

            element.registerDate = convertDate2Hangul(element.registerDate);
            state.ListCommentState.push(element);
        });
    }
};

export default {
    namespaced: true,
    state,
    getters,
    actions,
    mutations
}