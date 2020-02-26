<template>
    <div id="articleIndex">

        <div class="header">


            <el-menu
                    class="el-menu-demo"
                    mode="horizontal">

                <h3 class="titleText"
                    @click="goServiceMainPage">Gulagbu</h3>

                <el-menu-item
                        index="1"
                        @click="goServiceMainPage"
                        class="homeLinkText">
                    Home
                </el-menu-item>


                <el-menu-item
                        index="2"
                        @click="goArticleEditPage"
                        class="writeArticleText">
                    Write a article
                </el-menu-item>

                <el-menu-item
                        index="3"
                        class="scheduleText">
                    Schedule
                </el-menu-item>

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
                    <el-menu-item index="5-1">Profile</el-menu-item>
                    <el-menu-item
                            @click="logoutProcess"
                            index="5-2">
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
            }
        },
        computed: {
            ...authMapGetters(['currentAuthState'])
        },
        methods: {
            ...articleMapActions(['writeArticle']),

            goServiceMainPage() {


                if (this.$router.currentRoute.name === 'articleList') {
                    return;
                }

                this.$router.push({name: 'articleList'}).then(() => {
                });
            },

            goArticleEditPage() {

                if (this.$router.currentRoute.name === 'articleEdit') {
                    return;
                }

                this.$router.push({name: 'articleEdit'}).then(() => {
                });
            },

            logoutProcess() {
                const ROOT_URI = process.env.VUE_APP_BASE_PATH;
                window.location.href = `${ROOT_URI}/logout`;
            },
        },
        created() {
            // this.activePath = this.$router.currentRoute.path;
        }
    }
</script>

<style>
    .el-menu-item:not(.is-disabled):hover {
        /*color: #212422 !important;*/
    }

    .el-menu--horizontal > .el-menu-item.is-active {
        border-bottom: 1px solid transparent !important;
        color: #303133;
    }

    .el-menu--horizontal > .el-submenu.is-active .el-submenu__title {
        border-bottom: 1px solid transparent !important;
        color: #303133;
    }
</style>

<style scoped src="@/style/article.css">

</style>