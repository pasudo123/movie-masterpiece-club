
import request from '@/request';

const state = {};

const getters = {};

const actions = {

    currentUser({commit}) {

        const uri = 'user';

        return new Promise((resolve, reject) => {

            request.get(uri).then((response) => {
                commit('setCurrentUser', response.data);
            }).catch((error) => {
                reject(error);
            })
        });
    },

    // httpPostMethod() {
    //
    //     return new Promise((resolve, reject) => {
    //
    //         request.post(uri).then((response) => {
    //             resolve(response);
    //         }).catch((error) => {
    //             reject(error);
    //         })
    //
    //     });
    // }

};

const mutations = {

    setCurrentUser(state, params) {
        console.debug(params);
    }
};

export default {
    namespaced: true,
    state,
    getters,
    actions,
    mutations
}