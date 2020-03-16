import Vue from 'vue'
import App from '@/pages/login/App.vue';
import router from '@/router';
import store from '@/store';
import '@/style/global.scss';

Vue.config.productionTip = false;

new Vue({
    store,
    router,
    render: h => h(App)
}).$mount('#app');