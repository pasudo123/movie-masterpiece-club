<template>
    <div id="articleView">

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

            <comment-edit />

            <hr>

            <comment-list />
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

    export default {
        name: "ArticleView",
        components: {CommentEdit, CommentList},
        data() {
            return {
                article: {
                    title: '',
                    content: ''
                }
            }
        },
        computed: {
            ...articleMapGetters(['articleOneState'])
        },
        methods:{
            ...articleMapActions(['fetchOneArticle']),
            ...articleMapActions(['deleteOneArticle']),

            modifyArticleProcess() {

            },

            deleteArticleProcess() {

                const params = {};
                params.articleId = this.$route.params.articleId;

                this.deleteOneArticle(params).then(() => {
                    this.$router.push({name: 'articleList'}).then(() => {});
                });
            }
        },
        created() {

            const params = {};
            params.articleId = this.$route.params.articleId;

            this.fetchOneArticle(params).then(() => {
            }).catch((error) => {
                /** 어떤 아티클 ID 를 입력하고 그에 따른 값이 없는 경우 404 임 **/
                console.error(error.response);
            })
        }
    }
</script>

<style scoped src="@/style/article-view.css">

</style>