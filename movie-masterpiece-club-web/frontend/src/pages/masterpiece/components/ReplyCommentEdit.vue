<template>
    <div id="replyCommentEdit">

        <el-input
                class="commentInputClass"
                placeholder="답글을 입력..."
                v-model="content" />

        <el-button @click="writeReplyProcess">OK</el-button>
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
        name: "ReplyCommentEdit",
        props: {
            commentId: {
                type: String,
                required: true,
                default: () => ''
            }
        },
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

            ...commentMapActions(['createReplyOnComment']),

            writeReplyProcess() {

                if(this.isCreate) {
                    return;
                }

                if (this.content === '') {
                    return;
                }

                this.isCreate = true;

                const params = {};
                params.articleId = this.articleOneState.id;
                params.commentId = this.commentId;
                params.content = this.content;


                this.createReplyOnComment(params).then(() => {
                    this.isCreate = false;
                    this.content = '';
                })
            }
        }
    }
</script>

<style scoped src="@/style/comment.css">

</style>