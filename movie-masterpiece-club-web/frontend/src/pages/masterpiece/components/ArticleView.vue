<template>
    <div id="articleView">

        <div class="titleWrapper">
            {{articleOneState.title}}
        </div>

        <div v-html="articleOneState.content"
             class="ql-editor contentWrapper">
        </div>

        <div class="commentWrapper">

            <comment-edit />

            <div>댓글 경계선</div>

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
            ...articleMapActions(['fetchOneArticle'])
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

<style scoped>

</style>