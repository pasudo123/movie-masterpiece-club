import request from '@/request';

let articleAllCountState = 0;
const articleListState = [];
const articleOneState = {};

const state = {
    articleAllCountState,
    articleListState,
    articleOneState
};

const actions = {

    writeArticle({commit}, params) {

        const uri = 'article';

        const payload = {};
        payload.title = params.title;
        payload.content = params.content;
        payload.type = params.type;

        return new Promise((resolve, reject) => {

            request.post(uri, payload).then((response) => {
                resolve();
            }).catch((error) => {
                reject(error);
            })
        });
    },

    fetchAllArticleCount({commit}) {

        const uri = 'article/count';

        return new Promise((resolve, reject) => {

            request.get(uri).then((response) => {
                commit('setArticleAllCountState', response);
                resolve();
            }).catch((error) => {
                console.debug('asddsa');
                reject(error);
            })
        });
    },

    fetchAllArticle({commit}, params) {

        const uri = 'article';

        return new Promise((resolve, reject) => {

            request.get(uri).then((response) => {
                commit('setArticleListStateByFindAll', response);
                resolve();
            }).catch((error) => {
                reject(error);
            })
        });
    },

    fetchPartialArticle({commit}, params) {

        const uri = 'article';

        const queryParam = {};
        queryParam.page = params.page;
        queryParam.size = 5;
        queryParam.direction = 'DESC';

        return new Promise((resolve, reject) => {

            request.get(uri, {params: queryParam}).then((response) => {
                commit('setArticleListStateByFindPartial', response);
                resolve();
            }).catch((error) => {
                reject(error);
            })
        });
    },

    fetchOneArticle({commit}, params) {

        const uri = `article/${params.articleId}`;

        return new Promise((resolve, reject) => {

            request.get(uri).then((response) => {
                commit('setArticleOneState', response);
                resolve();
            })
        })
    },

    modifyArticle({commit}, params) {

        const uri = `article/${params.articleId}`;

        const payload = {};
        payload.title = params.title;
        payload.content = params.content;

        return new Promise((resolve, reject) => {

            request.put(uri, payload).then((response) => {
                commit('updateArticleListState', response);
                resolve();
            }).catch((error) => {
                console.error(error.response);
                reject();
            })
        })
    },

    deleteOneArticle({commit}, params) {

        const uri = `article/${params.articleId}`;

        return new Promise((resolve, reject) => {

            request.delete(uri).then((response) => {
                console.debug(response.data);
                // commit('setArticleListState', response);
                resolve();
            }).catch((error) => {
                reject(error);
            })
        });
    }
};

const mutations = {

    setArticleAllCountState(state, response) {
        state.articleAllCountState = response.data;
    },

    setArticleListStateByFindAll(state, response) {

        let articleList = response.data.articleList;

        state.articleListState = [];

        articleList.forEach(element => {

            let date = element.registerDate;
            let array = date.split('-');
            element.registerDate = `${array[0]}년 ${array[1]}월 ${array[2]}일`;

            state.articleListState.push(element);
        })
    },

    setArticleListStateByFindPartial(state, response) {

        let articleList = response.data.content;

        state.articleListState = [];

        articleList.forEach(element => {

            let date = element.registerDate;
            let array = date.split('-');
            element.registerDate = `${array[0]}년 ${array[1]}월 ${array[2]}일`;

            state.articleListState.push(element);
        })
    },

    setArticleOneState(state, response) {

        state.articleOneState = {};

        let oneArticle = response.data;
        let date = oneArticle.registerDate;
        let array = date.split('-');
        oneArticle.registerDate = `${array[0]}년 ${array[1]}월 ${array[2]}일`;

        state.articleOneState = oneArticle;
    },

    updateArticleListState(state, response) {

        articleListState.forEach(element => {
            if (element.id === response.data.id) {
                element.title = response.data.title;
                element.content = response.data.content;
            }
        })
    }
};

const getters = {
    articleAllCountState: (state) => state.articleAllCountState,
    articleListState: (state) => state.articleListState,
    articleOneState: (state) => state.articleOneState

};

export default {
    namespaced: true,
    state,
    actions,
    mutations,
    getters
}