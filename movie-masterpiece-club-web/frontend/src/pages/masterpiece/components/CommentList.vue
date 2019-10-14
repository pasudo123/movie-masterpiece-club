<template>
    <div id="commentList">

        <div
                class="commentElement"
                v-for="(comment, index) in this.ListCommentState" :key="comment.id">

            <div class="commentInfoWrapper">
                <div>
                    {{comment.createdProfile}}
                    <span>{{comment.createdName}}</span>
                </div>
                <div>{{comment.comment}}</div>
                <div>{{comment.registerDate}}</div>
            </div>
            <div class="commentEtcWrapper">
                <div class="likeButton">좋아요</div>
                <div class="replyButton" @click="toggleReplyComment(index)">답글</div>
            </div>

            <h1>{{index}}</h1>

            <reply-comment-edit v-if="isReplyEdit[index]" />
        </div>
    </div>
</template>

<script>

    import ReplyCommentEdit from '@/pages/masterpiece/components/ReplyCommentEdit'

    import {createNamespacedHelpers} from 'vuex';

    const {
        mapActions: commentMapActions,
        mapGetters: commentMapGetters
    } = createNamespacedHelpers('commentModule');
    const {
        mapGetters: articleMapGetters
    } = createNamespacedHelpers('articleModule');

    export default {
        name: "CommentList",
        components: {ReplyCommentEdit},
        data() {
            return {
                comment: '',
                isReplyEdit: [],
            }
        },
        computed: {
            ...articleMapGetters(['articleOneState']),
            ...commentMapGetters(['ListCommentState'])
        },
        methods: {
            ...commentMapActions(['fetchCommentList']),

            fetchCommentListProcess() {

                const params = {};
                params.articleId = this.$route.params.articleId;

                console.debug(params);

                this.fetchCommentList(params).then(() => {
                    for (let i = 0; i < this.ListCommentState.length; i++) {
                        // this.$set(this.isReplyEdit, i, false);
                        this.isReplyEdit[i] = false;
                    }
                })
            },

            toggleReplyComment(index) {
                this.$set(this.isReplyEdit, index, !this.isReplyEdit[index]);
            }
        },
        created() {
            this.fetchCommentListProcess();
        }
    }
</script>

<style scoped src="@/style/comment.css">

</style>