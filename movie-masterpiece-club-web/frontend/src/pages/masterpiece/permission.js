import router from '@/router';

router.beforeEach(async(to, from, next) => {

  next();

  // store.dispatch('auth/currentAuth').then((response) => {
  //   next();
  // }).catch(() => {
  //   /** 에러 발생 **/
  // })
});
