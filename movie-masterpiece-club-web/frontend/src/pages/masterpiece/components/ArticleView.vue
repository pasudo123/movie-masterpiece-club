<template>
    <div id="articleView">

        <div class="titleWrapper">
            {{article.title}}
        </div>

        <div class="contentWrapper">
            {{article.content}}
        </div>


    </div>
</template>

<script>

    import {createNamespacedHelpers} from 'vuex';

    const {mapActions: articleMapActions} = createNamespacedHelpers('articleModule');

    export default {
        name: "ArticleView",
        data() {
            return {
                article: {
                    title: '',
                    content: ''
                }
            }
        },
        methods:{
            ...articleMapActions(['fetchOneArticle'])
        },
        created() {
            console.debug(this.$route.params);

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