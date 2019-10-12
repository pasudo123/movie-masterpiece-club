
import request from '@/request';


const articleListState = [];
const articleOneState = {};

const state = {
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
    },

    fetchOneArticle({commit}, params) {

        const uri = `article/${params.articleId}`

        return new Promise((resolve, reject) => {

            request.get(uri).then((response) => {
                commit('setArticleOneState', response);
                resolve();
            })
        })
    }
};

const mutations = {

    setArticleListState(state, response){

        let articleList = response.data.articleList;

        state.articleListState = [];

        articleList.forEach(element => {

            let date = element.registerDate;
            let array = date.split('-');
            element.registerDate = `${array[0]}년 ${array[1]}월 ${array[2]}일`;

            state.articleListState.push(element);
        })
    },

    setArticleOneState(state, response) {

        let articleOne = response.data;

        console.debug(articleOne);
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