<template>
    <div id="commentEdit">

        <el-input
                class="commentInputClass"
                placeholder="댓글을 입력..."
                v-model="content" />

        <el-button @click="writeCommentProcess">OK</el-button>

    </div>
</template>

<script>

    import {createNamespacedHelpers} from 'vuex';
    const {
        mapActions: commentMapActions
    } = createNamespacedHelpers('commentModule');
    const {
        mapGetters: articleMapGetters
    } = createNamespacedHelpers('articleModule');

    export default {
        name: "CommentEdit",
        data() {
            return {
                isCreate: false,
                content: ''
            }
        },
        computed: {
            ...articleMapGetters(['articleOneState'])
        },
        methods: {

            ...commentMapActions(['createCommentOnArticle']),

            writeCommentProcess() {

                if (this.isCreate) {
                    return;
                }

                if (this.content === '') {
                    return;
                }

                this.isCreate = true;

                const params = {};
                params.articleId = this.articleOneState.id;
                params.content = this.content;

                this.createCommentOnArticle(params).then(() => {
                    this.isCreate = false;
                    this.content = '';
                });
            }
        }
    }
</script>

<style scoped src="@/style/comment.css">

</style>