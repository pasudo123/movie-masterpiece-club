
import request from '@/request'

const state = {

}

const actions = {

    createMovie2Json({commit}, params){

        const payload = {};
        payload.title = params.title;
        payload.director = params.director;
        payload.date = params.date;
        payload.summary = params.summary;

        const uri = 'movie/parse';

        return new Promise((resolve, reject) => {
            request.post(uri, payload).then((response) => {
                resolve(response);
            }).catch((error) => {
                reject(error);
            })
        })
    }

}

const getters = {

}

const mutations = {

}

export default{
    namespaced: true,
    state,
    actions,
    getters,
    mutations
}