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

            <reply-comment-edit v-if="isReplyEdit[index]" />

            <div class="replyCommentWrapper">
                <div class="replyCommentShowButton" @click="toggleReplyShow(index)">답글보기 (개수)</div>
                <reply-comment-list v-if="isReplyShow[index]" />
            </div>
        </div>
    </div>
</template>

<script>

    import ReplyCommentEdit from '@/pages/masterpiece/components/ReplyCommentEdit';
    import ReplyCommentList from '@/pages/masterpiece/components/ReplyCommentList';

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
        components: {ReplyCommentList, ReplyCommentEdit},
        data() {
            return {
                comment: '',
                isReplyEdit: [],
                isReplyShow: []
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
                        this.isReplyShow[i] = false;
                    }
                })
            },

            toggleReplyComment(index) {
                this.$set(this.isReplyEdit, index, !this.isReplyEdit[index]);
            },

            toggleReplyShow(index) {
                this.$set(this.isReplyShow, index, !this.isReplyShow[index]);
            }

        },
        created() {
            this.fetchCommentListProcess();
        }
    }
</script>

<style scoped src="@/style/comment.css">

</style>