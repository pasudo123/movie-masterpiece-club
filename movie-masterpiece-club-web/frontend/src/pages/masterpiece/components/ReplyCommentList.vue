<template>
    <div id="replyCommentList">
        답글 목록.
        {{this.commentId}}
    </div>
</template>

<script>
    export default {
        name: "ReplyCommentList",
        props:{
            commentId: {
                type: String,
                required: true,
                default: () => ''
            },
            isReplyShow: {
                type: Boolean,
                required: true,
                default: () => false
            }
        },
        computed: {

        },
        watch: {
            isReplyShow(newValue, oldValue) {
                console.debug(this.commentId);
                console.debug(newValue);
                console.debug(oldValue);
            }
        },
        created() {

        }
    }
</script>

<style scoped>

</style>