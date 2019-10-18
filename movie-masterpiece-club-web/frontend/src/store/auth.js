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

    setCurrentAuth(state, currentAuth) {
        state.currentAuthState.id = currentAuth.id;
        state.currentAuthState.email = currentAuth.email;
        state.currentAuthState.username = currentAuth.username;
        state.currentAuthState.profile = currentAuth.profile;
        state.currentAuthState.role = currentAuth.role;
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