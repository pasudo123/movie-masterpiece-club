
import request from '@/request';
import {convertDate2Hangul} from '@/utils/moment-util';

const OneCommentState = {};
const ListCommentState = [];
const ListReplyState = [];

const state = {
    OneCommentState,
    ListCommentState,
    ListReplyState
};

const getters = {
    OneCommentState: (state) => state.OneCommentState,
    ListCommentState: (state) => state.ListCommentState,
    ListReplyState: (state) => state.ListReplyState
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

    createReplyOnComment({commit}, params) {

        const uri = `article/${params.articleId}/comment/${params.commentId}/reply`;

        const payload = {};
        payload.content = params.content;

        console.debug(payload);

        return new Promise((resolve, reject) => {

            request.post(uri, payload).then((response) => {
                commit('addReply', response);
                resolve();
            }).catch((error) => {
                console.error(error.response);
                reject(error);
            })
        });
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

    fetchReplyList({commit}, params) {

        const uri = `comment/${params.commentId}/reply`;

        return new Promise((resolve, reject) => {

            request.get(uri).then((response) => {

                const mutationsParams = {};
                mutationsParams.data = response.data;
                mutationsParams.index = params.index;

                commit('setListReplyState', mutationsParams);
                resolve();
            }).catch((error) => {
                console.error(error.response);
                reject(error);
            })

        });
    }
};

const mutations = {

    addCommentOnListCommentState(state, response) {

        let comment = response.data;
        comment.registerDate = convertDate2Hangul(comment.registerDate);

        state.ListCommentState.push(comment)
    },

    setListCommentState(state, response) {

        state.ListCommentState = [];

        console.debug(response);

        response.data.commentList.forEach((element) => {

            element.registerDate = convertDate2Hangul(element.registerDate);
            state.ListCommentState.push(element);
        });
    },

    setListReplyState(state, params) {

        console.debug(params);
    },

    addReply(state, response) {
        console.debug(response.data);
    }
};

export default {
    namespaced: true,
    state,
    getters,
    actions,
    mutations
}