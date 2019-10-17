
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
                commit('addComment', response);
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

        return new Promise((resolve, reject) => {

            request.post(uri, payload).then((response) => {

                let replyValue = {};
                replyValue.data = response.data;
                replyValue.index = params.replyIndex;

                commit('addReply', replyValue);
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

    // fetchReplyList({commit}, params) {
    //
    //     const uri = `comment/${params.commentId}/reply`;
    //
    //     return new Promise((resolve, reject) => {
    //
    //         request.get(uri).then((response) => {
    //
    //             const mutationsParams = {};
    //             mutationsParams.data = response.data;
    //             mutationsParams.index = params.index;
    //
    //             commit('setListReplyState', mutationsParams);
    //             resolve();
    //         }).catch((error) => {
    //             console.error(error.response);
    //             reject(error);
    //         })
    //     });
    // }
};

const mutations = {

    addComment(state, response) {

        let comment = response.data;
        comment.registerDate = convertDate2Hangul(comment.registerDate);

        state.ListCommentState.push(comment)
    },

    setListCommentState(state, response) {

        state.ListCommentState = [];

        response.data.commentList.forEach((comment) => {

            comment.registerDate = convertDate2Hangul(comment.registerDate);
            state.ListCommentState.push(comment);
        });
    },

    addReply(state, replyValue) {

        let reply = replyValue.data;
        let index = replyValue.index;

        reply.registerDate = convertDate2Hangul(reply.registerDate);

        state.ListCommentState[index].reply.list.push(reply);
    }
};

export default {
    namespaced: true,
    state,
    getters,
    actions,
    mutations
}