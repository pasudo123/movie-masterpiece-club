import Vue from 'vue'
import App from '@/pages/login/App.vue';
import router from '@/router';
import store from '@/store';
import 'bootstrap-css-only/css/bootstrap.min.css'
import 'mdbvue/lib/css/mdb.min.css'
import '@/style/global.css'

Vue.config.productionTip = false;

new Vue({
    store,
    router,
    render: h => h(App)
}).$mount('#app');