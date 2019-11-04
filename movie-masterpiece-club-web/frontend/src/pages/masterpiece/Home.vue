<template>
    <div id="articleIndex">

        <div class="header">
            <div class="centerHeader">
                <div class="meeting-icon-wrapper">
                    <movie-icon class="c-movie-icon"/>

                    <span
                            @click="goServiceMainPage"
                            class="serviceTitle">
                        Masterpiece Club
                    </span>
                </div>

                <div class="meeting-title">
                    <el-button
                            @click="goArticleEditPage"
                            class="writeButton"
                            type="text">
                        Write a article
                    </el-button>
                    <el-button class="scheduleButton" type="text">Schedule</el-button>
                </div>

                <div class="user-info-wrapper">
                    <span class="userEmailText">{{this.currentAuthState.email}}</span>
                    <el-avatar
                            class="userAvatar"
                            :fit="'cover'"
                            :size="userProfileSize"
                            :src="currentAuthState.profile"></el-avatar>
                </div>
            </div>

            <el-button
                    @click="logoutProcess"
                    class="LogoutButton"
                    type="text">
                Logout
            </el-button>
        </div>


        <div class="body">
            <router-view></router-view>
        </div>
    </div>
</template>

<script>

    import {createNamespacedHelpers} from 'vuex';

    const {mapActions: articleMapActions} = createNamespacedHelpers('articleModule');
    const {mapGetters: authMapGetters} = createNamespacedHelpers('auth');

    import {foo} from '@/utils/moment-util';
    import MovieIcon from 'icons/MovieOpen'
    import ArticleList from '@/pages/masterpiece/components/ArticleList'

    export default {
        name: "index",
        components: {MovieIcon, ArticleList},
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
                this.$router.push({name: 'articleList'}).then(() => {});
            },

            goArticleEditPage() {
                this.$router.push({name: 'articleEdit'}).then(() => {});
            },

            logoutProcess() {
                const ROOT_URI = process.env.VUE_APP_BASE_PATH;
                window.location.href = `${ROOT_URI}/logout`;
            }
        },
        created() {
            console.debug('created');
        }
    }
</script>

<style scoped src="@/style/article.css">

</style>