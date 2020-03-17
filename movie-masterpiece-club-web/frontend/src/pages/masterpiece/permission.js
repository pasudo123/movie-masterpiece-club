import router from '@/router';
import store from '@/store';

const LOGIN = 'login';

router.beforeEach(async(to, from, next) => {
  const name = to.name;
  if(name === LOGIN) {
    next();
    return;
  }

  store.dispatch('auth/currentAuth').then((response) => {
    next();
  }).catch(() => {
    /** 에러 발생 **/
  })
});
