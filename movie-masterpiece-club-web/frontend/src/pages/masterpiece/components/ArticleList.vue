<template>
    <div id="articleList">

        <div class=loadingWrapper v-if="isLoading" v-loading="isLoading"></div>
        <div v-else>
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

            <el-pagination
                    class="pagination"
                    layout="prev, pager, next"
                    @current-change="pageMove"
                    :page-size="pageSize"
                    :current-page="currentPage"
                    :total="articleAllCountState">
            </el-pagination>
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
                isLoading: false,
                currentPage: 1,
                pageSize: 5,
            }
        },
        computed: {
            ...articleMapGetters(['articleAllCountState']),
            ...articleMapGetters(['articleListState'])
        },
        watch: {
            $route(to, from) {

                if (Object.keys(to.query).length === 0) {
                    this.renderArticleListPage(1);
                    return;
                }

                this.renderArticleListPage(to.query.page);
            }
        },
        methods: {

            ...articleMapActions(['fetchAllArticleCount']),
            ...articleMapActions(['fetchAllArticle']),
            ...articleMapActions(['fetchPartialArticle']),

            goArticleViewPage(articleId) {
                this.$router.push({name: 'articleView', params: {articleId: articleId}}).then(() => {
                });
            },

            renderArticleListPage(page) {

                this.isLoading = true;

                Promise.all([
                    this.fetchAllArticleCount().catch(error => {
                        return error
                    }),
                    this.fetchPartialArticle({page}).catch(error => {
                        return error
                    })
                ]).then((returnValue) => {
                    /**
                     * 정상적 메시지이면 정상
                     * 오류면 오류로 값이 리턴.
                     *  **/
                    this.isLoading = false;
                    this.currentPage = page;
                });
            },

            pageMove(currentPage) {
                this.$router.push({name: 'articleList', query: {page: currentPage}});
            }
        },
        created() {
            this.renderArticleListPage(1);
        }
    }
</script>

<style scoped src="@/style/article-list.css">

</style>