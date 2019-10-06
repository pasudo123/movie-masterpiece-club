<template>
    <div id="app">
        <router-view></router-view>
    </div>
</template>

<script>

    import {createNamespacedHelpers} from 'vuex';

    const {mapActions} = createNamespacedHelpers('articleModule');

    import {foo} from '@/utils/moment-util';
    import MovieIcon from 'icons/MovieOpen'
    import ArticleList from '@/pages/masterpiece/ArticleList'

    export default {
        name: "App",
        components: {MovieIcon, ArticleList},
        data() {
            return {
                userEmail: 'oraedoa@gmail.com',
                userProfile: 'https://avatars0.githubusercontent.com/u/17797352?s=460&v=4',
                userProfileSize: 35,
            }
        },
        methods: {
            goServiceMainPage() {
                this.$router.push({name: 'articleList'}).then(() => {});
            },

            goArticleEditPage() {
                this.$router.push({name: 'articleEdit'}).then(() => {});
            },
        }
    }
</script>

<style>
    body {
        margin: 0;
        padding: 0;
    }
</style>