import request from '@/request';

const currentAuthState = {};

const state = {
    currentAuthState
};

const actions = {

    currentAuth({commit}) {

        const uri = 'auth';

        return new Promise((resolve, reject) => {

            request.get(uri).then((response) => {
                commit('setCurrentAuth', response.data);
                resolve();
            }).catch((error) => {
                console.error(error.response);
                reject();
            })
        })
    }
};

const mutations = {

    setCurrentAuth(state, response) {
        state.currentAuthState.email = response.email;
        state.currentAuthState.username = response.username;
        state.currentAuthState.profile = response.profile;
        state.currentAuthState.role = response.role;
    }
};

const getters = {
    currentAuthState: (state) => state.currentAuthState
};

export default {
    namespaced: true,
    state,
    actions,
    mutations,
    getters
}