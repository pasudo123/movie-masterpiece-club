
import request from '@/request';


const articleListState = {};

const state = {
    articleListState
};

const actions = {

    writeArticle({commit}, params) {

        const uri = 'article';

        const payload = {};
        payload.content = params.content;
        payload.type = params.type;

        return new Promise((resolve, reject) => {

            request.post(uri, payload).then((response) => {
                console.debug(response);
                resolve();
            }).catch((error) => {
                reject(error);
            })
        });
    },

    fetchAllArticle({commit}, params) {

        const uri = 'article';

        return new Promise((resolve, reject) => {

            request.get(uri).then((response) => {
                commit('setArticleListState', response);
                resolve();
            }).catch((error) => {
                reject(error);
            })
        });
    }

};

const mutations = {
    setArticleListState(state, response){
        console.debug(response);

        let articleList = response.data.articleList;

        state.articleListState = [];
        articleList.forEach(element => {
            state.articleListState.push(element);
        })
    }
};

const getters = {
    articleListState: (state) => state.articleListState
};

export default {
    namespaced: true,
    state,
    actions,
    mutations,
    getters
}