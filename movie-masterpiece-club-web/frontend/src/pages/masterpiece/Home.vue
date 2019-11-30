<template>
    <div id="articleIndex">

        <div class="header">
            <el-menu :default-active="activeIndex"
                     class="el-menu-demo"
                     mode="horizontal">

                <el-menu-item class="titleWrapper">
                    <h3 class="titleText">Gulagbu</h3>
                </el-menu-item>

                <el-menu-item
                        @click="goServiceMainPage"
                        class="homeLinkText"
                        index="1">
                    Home
                </el-menu-item>

                <el-menu-item
                        @click="goArticleEditPage"
                        class="writeArticleText"
                        index="2">
                    Write a article
                </el-menu-item>

                <el-menu-item
                        class="scheduleText"
                        index="3">
                    Schedule
                </el-menu-item>

                <el-submenu class="userInfoText" index="4">
                    <template slot="title">
                        <el-avatar
                            class="userAvatar"
                            :fit="'cover'"
                            :size="userProfileSize"
                            :src="currentAuthState.profile"/>
                        {{this.currentAuthState.email}}
                    </template>
                    <el-menu-item index="4-1">Profile</el-menu-item>
                    <el-menu-item
                            @click="logoutProcess"
                            index="4-2">
                        Logout
                    </el-menu-item>
                </el-submenu>
            </el-menu>
        </div>

        <div class="body">
            <router-view></router-view>
        </div>
    </div>
</template>

<script>

    import {createNamespacedHelpers} from 'vuex';
    import ArticleList from '@/pages/masterpiece/components/ArticleList'

    const {mapActions: articleMapActions} = createNamespacedHelpers('articleModule');
    const {mapGetters: authMapGetters} = createNamespacedHelpers('auth');

    export default {
        name: "index",
        components: {
            ArticleList,
        },
        data() {
            return {
                userProfileSize: 35,
                activeIndex: '1'
            }
        },
        computed: {
            ...authMapGetters(['currentAuthState'])
        },
        methods: {
            ...articleMapActions(['writeArticle']),

            goServiceMainPage() {

                if (this.$route.path === '/masterpiece') {
                    return;
                }
                
                this.$router.push({name: 'articleList'}).then(() => {
                });
            },

            goArticleEditPage() {
                this.$router.push({name: 'articleEdit'}).then(() => {
                });
            },

            logoutProcess() {
                const ROOT_URI = process.env.VUE_APP_BASE_PATH;
                window.location.href = `${ROOT_URI}/logout`;
            },
        }
    }
</script>

<style>
    .el-menu-item:not(.is-disabled):hover {
        color: #212422 !important;
    }

    .el-menu--horizontal > .el-menu-item.is-active {
        border-bottom: 1px solid transparent !important;
        color: #303133;
    }

    .el-menu--horizontal>.el-submenu.is-active .el-submenu__title {
        border-bottom: 1px solid transparent !important;
        color: #303133;
    }
</style>

<style scoped src="@/style/article.css">

</style>