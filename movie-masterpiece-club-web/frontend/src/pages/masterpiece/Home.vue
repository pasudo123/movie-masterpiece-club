<template>
    <div id="articleIndex">

        <div class="header">
            <div class="centerHeader">
                <div class="meeting-icon-wrapper">
        <span
                @click="goServiceMainPage"
                class="serviceTitle">Gulagbu
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
                backgroundColor: '#F5DEB3'
            }
        },
        computed: {
            ...authMapGetters(['currentAuthState'])
        },
        methods: {
            ...articleMapActions(['writeArticle']),

            goServiceMainPage() {

                // console.debug(this.$route.path);

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
            }
        },
        created() {
        }
    }
</script>

<style>
    .navbar {
        padding: 8px 240px 8px 240px;
    }
</style>

<style scoped src="@/style/article.css">

</style>