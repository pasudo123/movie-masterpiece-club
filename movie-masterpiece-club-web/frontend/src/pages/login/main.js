import Vue from 'vue'
import App from '@/pages/login/App.vue';
import router from '@/router';
import store from '@/store';
import ElementUI from 'element-ui';
import locale from 'element-ui/lib/locale/lang/ko';
import 'element-ui/lib/theme-chalk/index.css';
import 'vue-material-design-icons/styles.css';

Vue.config.productionTip = false;
Vue.use(ElementUI, {locale});



new Vue({
    store,
    router,
    render: h => h(App)
}).$mount('#app');