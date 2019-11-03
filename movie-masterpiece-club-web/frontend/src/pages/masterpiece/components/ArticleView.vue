<template>
    <div id="articleView">

        <div
                class="loadingWrapper"
                v-loading="loading"
                v-if="loading"></div>

        <div v-else>
            <div class="titleWrapper">
                <span class="titleText">
                    {{articleOneState.title}}
                </span>
                <span class="articleRegDateText">
                    {{articleOneState.registerDate}}
                </span>
            </div>
            <div class="createdWrapper">
                <span class="articleCreatedByText">
                    작성자 : {{articleOneState.createdName}}
                </span>

                <!-- 해당 사용자에게 보여주기. -->
                <span class="modifyArticleText">
                    <span class="modifyText" @click="modifyArticleProcess(articleOneState)"> 수정하기 </span>
                    |
                    <span class="modifyText" @click="deleteArticleProcess(articleOneState.id)"> 삭제하기 </span>
                </span>
            </div>


            <div v-html="articleOneState.content"
                 class="ql-editor contentWrapper">
            </div>

            <div class="commentWrapper">

                <comment-edit/>

                <hr>

                <comment-list/>
            </div>
        </div>
    </div>
</template>

<script>

    import CommentEdit from '@/pages/masterpiece/components/CommentEdit';
    import CommentList from '@/pages/masterpiece/components/CommentList';

    import {createNamespacedHelpers} from 'vuex';

    const {
        mapGetters: articleMapGetters,
        mapActions: articleMapActions
    } = createNamespacedHelpers('articleModule');

    const {
        mapMutations: commentMapMutations
    } = createNamespacedHelpers('commentModule');

    export default {
        name: "ArticleView",
        components: {CommentEdit, CommentList},
        data() {
            return {
                article: {
                    title: '',
                    content: ''
                },
                loading: true
            }
        },
        computed: {
            ...articleMapGetters(['articleOneState'])
        },
        methods: {
            ...articleMapActions(['fetchOneArticle']),
            ...articleMapActions(['deleteOneArticle']),
            ...commentMapMutations(['initListCommentState']),

            modifyArticleProcess() {
                this.$router.push({name: 'articleOneEdit', params: {articleId: this.articleOneState.id}}).then(() => {
                });
            },

            deleteArticleProcess() {

                const params = {};
                params.articleId = this.articleOneState.id;

                this.deleteOneArticle(params).then(() => {
                    this.$router.push({name: 'articleList'}).then(() => {
                    });
                });
            },

            closeLoading() {
                this.loading = false;
            }
        },
        created() {

            const params = {};
            params.articleId = this.$route.params.articleId;

            this.initListCommentState();

            this.fetchOneArticle(params).then(() => {
                this.closeLoading();
            }).catch((error) => {
                /** 어떤 아티클 ID 를 입력하고 그에 따른 값이 없는 경우 404 임 **/
                console.error(error.response);
                this.closeLoading();
            })
        }
    }
</script>

<style scoped src="@/style/article-view.css">

</style>