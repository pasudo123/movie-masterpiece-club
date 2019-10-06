
import request from '@/request';

const state = {};

const getters = {};

const actions = {

    httpPostMethod() {

        return new Promise((resolve, reject) => {

            request.post(uri).then((response) => {
                resolve(response);
            }).catch((error) => {
                reject(error);
            })

        });
    }

};

const mutations = {};

export default {
    namespaced: true,
    state,
    getters,
    actions,
    mutations
}