<template>
  <div id="headerIndex">
    <el-menu class="el-menu-demo" mode="horizontal">
      <el-menu-item
        index="0"
        @click="moveHomePage"
        class="siteTitleText">
        띵작1번지</el-menu-item>

      <el-menu-item
        index="1"
        @click="moveHomePage"
        class="homeLinkText">
        홈</el-menu-item>

      <el-menu-item
        index="2"
        @click="moveEditPage"
        class="writeArticleText">
        게시글 작성</el-menu-item>

      <el-menu-item
        index="3"
        class="scheduleText">
        영화 스케쥴</el-menu-item>

      <el-submenu
        index="4"
        class="userInfoText">
        <template slot="title">
          <el-avatar
            class="userAvatar"
            :fit="'cover'"
            :size="userProfileSize"
            :src="currentAuthState.profile"/>
          {{this.currentAuthState.email}}
        </template>
        <el-menu-item index="5-1">
          프로필
        </el-menu-item>
        <el-menu-item @click="logoutProcess" index="5-2">
          로그아웃
        </el-menu-item>
      </el-submenu>
    </el-menu>
  </div>
</template>

<script>

  import {createNamespacedHelpers} from 'vuex';
  const {mapGetters: authMapGetters} = createNamespacedHelpers('auth');

  export default {
    name: "Header",
    data() {
      return {
        userProfileSize: 35,
      }
    },
    computed: {
      ...authMapGetters(['currentAuthState'])
    },
    methods: {
      moveHomePage() {
        this.$router.push({name: 'Home'});
      },

      moveEditPage() {
        this.$router.push({name: 'ArticleEdit'});
      },

      logoutProcess() {
        const ROOT_URI = process.env.VUE_APP_BASE_PATH;
        window.location.href = `${ROOT_URI}/logout`;
      },
    }
  }
</script>

<style>
  .el-menu--horizontal > .el-menu-item.is-active {
    border-bottom: 1px solid transparent !important;
    color: #303133;
  }

  .el-menu--horizontal > .el-submenu.is-active .el-submenu__title {
    border-bottom: 1px solid transparent !important;
    color: #303133;
  }
</style>

<style lang="scss" scoped src="@/style/layout/layout.scss">

</style>
