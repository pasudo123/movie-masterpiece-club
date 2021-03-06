import Vue from 'vue'
import App from './App.vue';
import router from '@/router';
import store from '@/store';
import ElementUI from 'element-ui';
import locale from 'element-ui/lib/locale/lang/ko';
import 'element-ui/lib/theme-chalk/index.css';
import '@/style/global.scss';

/** 라우터 권한 설정 **/
import './permission';

Vue.config.productionTip = false;
Vue.use(ElementUI, {locale});

new Vue({
  store,
  router,
  render: h => h(App)
}).$mount('#app');
