<template>
    <div id="articleList">
        <div
                class="articleElement"
                v-for="item in articleListState"
                :key="item.id">

            <div class="element">
                <el-avatar
                        class="userProfile"
                        shape="circle"
                        :size="60"
                        :fit="'cover'"
                        :src="item.createdProfile"></el-avatar>

                <div
                        @click="goArticleViewPage(item.id)"
                        class="contentArea">
                    <div class="listRegisterDateArea">{{item.registerDate}}</div>
                    <div class="listTitleArea">{{item.title}}</div>
                </div>
            </div>

            <el-divider><i class="el-icon-star-on"></i></el-divider>
        </div>

    </div>
</template>

<script>

    import {createNamespacedHelpers} from 'vuex';

    const {
        mapActions: articleMapActions,
        mapGetters: articleMapGetters
    } = createNamespacedHelpers('articleModule');

    export default {
        name: "ArticleList",
        data() {
            return {
                isLoading: false
            }
        },
        computed: {
            ...articleMapGetters(['articleListState'])
        },
        methods: {

            ...articleMapActions(['fetchAllArticle']),
            ...articleMapActions(['fetchPartialArticle']),

            goArticleViewPage(articleId) {
                this.$router.push({name: 'articleView', params:{articleId: articleId}}).then(() => {});
            }
        },
        created() {

            this.isLoading = true;

            const params = {};
            params.page = 1;

            this.fetchPartialArticle(params).then(() => {
                this.isLoading = false;
            }).catch((error) => {
                console.error(error.response);
                this.isLoading = false;
            });
        }
    }
</script>

<style scoped src="@/style/article-list.css">

</style>