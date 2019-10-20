import request from '@/request';
import {convertDate2Hangul} from '@/utils/moment-util';

const OneCommentState = {};
const ListCommentState = [];

const state = {
    OneCommentState,
    ListCommentState,
};

const getters = {
    OneCommentState: (state) => state.OneCommentState,
    ListCommentState: (state) => state.ListCommentState,
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
                replyValue.index = params.commentIndex;

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
        });
    },

    updateComment({commit}, params) {

        const uri = `comment/${params.commentId}`;

        const payload = {};
        payload.content = params.content;

        return new Promise((resolve, reject) => {

            request.put(uri, payload).then((response) => {

                let updateInfo = {};
                updateInfo.index = params.index;
                updateInfo.data = response.data;

                commit('updateComment', updateInfo);
                resolve();
            }).catch((error) => {
                console.debug(error.response);
                reject(error);
            })
        });
    },

    updateReply({commit}, params) {

        const uri = `comment/reply/${params.replyId}`;

        const payload = {};
        payload.content = params.content;

        return new Promise((resolve, reject) => {

            request.put(uri, payload).then((response) => {

                let updateInfo = {};
                updateInfo.commentIndex = params.commentIndex;
                updateInfo.replyIndex = params.replyIndex;
                updateInfo.data = response.data;

                commit('updateReply', updateInfo);
                resolve();
            }).catch((error) => {
                console.error(error.response);
                reject(error);
            })
        });
    },

    deleteComment({commit}, params) {

        const uri = `comment/${params.commentId}/status`;

        return new Promise((resolve, reject) => {

            request.put(uri).then((response) => {
                commit('deleteComment', response);
                resolve();
            }).catch((error) => {
                console.error(error.response);
                reject(error);
            })

        });
    },

    deleteReply({commit}, params) {

        const uri = `comment/reply/${params.replyId}/status`;

        return new Promise((resolve, reject) => {

            request.put(uri).then((response) => {

                let deleteInfo = {};
                deleteInfo.commentIndex = params.commentIndex;
                deleteInfo.data = response.data;

                commit('deleteReply', deleteInfo);
                resolve();
            }).catch((error) => {
                console.error(error.response);
                reject(error);
            })
        });
    }
};

const mutations = {

    addComment(state, response) {

        let comment = response.data;
        comment.registerDate = convertDate2Hangul(comment.registerDate);

        state.ListCommentState.push(comment)
    },

    addReply(state, replyValue) {

        let reply = replyValue.data;
        let index = replyValue.index;

        reply.registerDate = convertDate2Hangul(reply.registerDate);

        state.ListCommentState[index].reply.list.push(reply);
    },

    setListCommentState(state, response) {

        state.ListCommentState = [];

        response.data.commentList.forEach((comment) => {

            comment.registerDate = convertDate2Hangul(comment.registerDate);
            state.ListCommentState.push(comment);
        });

        console.debug(state.ListCommentState);
    },

    updateComment(state, updateInfo) {
        state.ListCommentState[updateInfo.index].comment = updateInfo.data.comment;
    },

    updateReply(state, updateInfo) {

        console.debug

        state.ListCommentState[updateInfo.commentIndex]
            .reply.list[updateInfo.replyIndex].comment = updateInfo.data.comment;
    },

    deleteComment(state, response) {

        let newListCommentState = [];
        newListCommentState = state.ListCommentState.filter(element => element.id !== response.data.id);

        state.ListCommentState = [];
        state.ListCommentState = newListCommentState;
    },

    deleteReply(state, deleteInfo) {

        let replyList = state.ListCommentState[deleteInfo.commentIndex].reply.list;
        let newListReplyState = replyList.filter(element => element.id !== deleteInfo.data.id);

        state.ListCommentState[deleteInfo.commentIndex].reply.list = [];
        state.ListCommentState[deleteInfo.commentIndex].reply.list = newListReplyState;
    }
};

export default {
    namespaced: true,
    state,
    getters,
    actions,
    mutations
}