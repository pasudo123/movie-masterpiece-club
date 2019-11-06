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

        <el-pagination
                layout="prev, pager, next"
                @current-change="pageMove"
                :page-size="pageSize"
                :current-page="currentPage"
                :total="articleAllCountState">
        </el-pagination>

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
        methods: {

            ...articleMapActions(['fetchAllArticleCount']),
            ...articleMapActions(['fetchAllArticle']),
            ...articleMapActions(['fetchPartialArticle']),

            goArticleViewPage(articleId) {
                this.$router.push({name: 'articleView', params:{articleId: articleId}}).then(() => {});
            },

            pageMove(currentPage) {
                console.debug("currentPage : ", currentPage);
            }
        },
        created() {

            this.isLoading = true;

            const params = {};
            params.page = 1;

            Promise.all([
                this.fetchAllArticleCount().catch(error => { return error }),
                this.fetchPartialArticle(params).catch(error => { return error })
            ]).then((returnValue) => {
                /**
                 * 정상적 메시지이면 정상
                 * 오류면 오류로 값이 리턴.
                 *  **/
                this.isLoading = false;
            });
        }
    }
</script>

<style scoped src="@/style/article-list.css">

</style>