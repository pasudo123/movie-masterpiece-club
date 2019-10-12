
import request from '@/request'
const qs = require('qs');

const state = {};

const getters = {};

const actions = {

    payloadActions() {

        const uri = '/test-api/req-payload';

        const payload = {};
        payload.name = 'payload-name';
        payload.desc = 'payload-desc';
        console.debug(payload);

        return new Promise((resolve, reject) => {

            request.post(uri, payload).then((response) => {
                console.debug(response);
            }).catch((error) => {
                console.error(error.response);
            })

        });
    },

    formDataActions() {

        const uri = '/test-api/req-form';

        const formData = qs.stringify({ 'name': 'payload-name-00', 'desc': 'payload-desc-00' });
        console.debug(formData);

        return new Promise((resolve, reject) => {

            request.post(uri, formData).then((response) => {
                console.debug(response);
            }).catch((error) => {
                console.error(error.response);
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