import Vue from 'vue'
import App from '@/pages/masterpiece/App.vue';
import router from '@/router';
import store from '@/store';
import ElementUI from 'element-ui';
import locale from 'element-ui/lib/locale/lang/ko';
import 'element-ui/lib/theme-chalk/index.css';
import 'vue-material-design-icons/styles.css';
import 'bootstrap-css-only/css/bootstrap.min.css'
import 'mdbvue/lib/css/mdb.min.css'
import '@/style/global.css'

// require styles
import 'quill/dist/quill.core.css'
import 'quill/dist/quill.snow.css'
import 'quill/dist/quill.bubble.css'

Vue.config.productionTip = false;
Vue.use(ElementUI, {locale});

// router.push('/masterpiece/articleList').catch(error => {});
// router.push('/masterpiece/articleEdit').catch(error => {});

router.beforeEach((to, from, next) => {
    store.dispatch('auth/currentAuth').then((response) => {
        next();
    }).catch(() => {
        /** 에러 발생 **/
    })
});

new Vue({
    store,
    router,
    render: h => h(App)
}).$mount('#app');