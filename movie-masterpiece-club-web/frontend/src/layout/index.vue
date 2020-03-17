<template>
  <div id="layoutIndex">
    <div class="header">
      <app-header />
    </div>
    <div class="body">
      <router-view/>
    </div>
    <div class="bottom">
      <app-bottom />
    </div>
  </div>
</template>

<script>

  import AppHeader from '@/layout/components/Header.vue';
  import AppBottom from '@/layout/components/Bottom.vue';

  export default {
    name: "index",
    components: {AppHeader, AppBottom}
  }
</script>

<style lang="scss" scoped src="@/style/layout/layout.scss">
</style>
