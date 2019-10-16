<template>
    <div id="commentList">

        <div
                class="commentElement"
                v-for="(comment, index) in this.ListCommentState" :key="comment.id">

            <div class="commentInfoWrapper">
                <div class="createdInfo">
                    <el-avatar :size="35">
                        <img :src="comment.createdProfile"/>
                    </el-avatar>
                    <span class="nameInfo">{{comment.createdName}}</span>
                </div>

                <div class="commentInfo">{{comment.comment}}</div>
                <div class="registerDateInfo">{{comment.registerDate}}</div>
            </div>

            <div class="commentEtcWrapper">
                <div class="likeButton">좋아요</div>
                <div class="replyButton" @click="toggleReplyComment(index)">답글</div>
            </div>

            <reply-comment-edit
                    v-bind:commentId="comment.id"
                    v-if="isReplyEdit[index]" />

            <div class="replyCommentWrapper">


                <div
                        v-if="comment.reply.list.length !== 0"
                        class="replyCommentShowButton"
                        @click="toggleReplyShow(index)">
                    답글보기 ( {{comment.reply.list.length}} )
                </div>

                <reply-comment-list
                        v-bind:reply="comment.reply.list"
                        v-if="isReplyShow[index]" />
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
                isReplyShow: [],
                isReplyLoading: [],
            }
        },
        computed: {
            ...articleMapGetters(['articleOneState']),
            ...commentMapGetters(['ListCommentState']),
            ...commentMapGetters(['ListReplyState']),
        },
        methods: {

            ...commentMapActions(['fetchCommentList']),
            ...commentMapActions(['fetchReplyList']),

            fetchCommentListProcess() {

                const params = {};
                params.articleId = this.$route.params.articleId;

                console.debug(params);

                this.fetchCommentList(params).then(() => {
                    for (let i = 0; i < this.ListCommentState.length; i++) {
                        // this.$set(this.isReplyEdit, i, false);
                        this.isReplyEdit[i] = false;
                        this.isReplyShow[i] = false;
                        this.isReplyLoading[i] = false;
                    }
                })
            },

            toggleReplyComment(index) {
                this.$set(this.isReplyEdit, index, !this.isReplyEdit[index]);
            },

            toggleReplyShow(index) {

                this.$set(this.isReplyShow, index, !this.isReplyShow[index]);

                if (!this.isReplyShow[index]) {
                    return;
                }



                // this.isReplyLoading[index] = true;
                //
                // const params = {};
                // params.commentId = this.ListCommentState[index].id;
                //
                // this.fetchReplyList(params).then(() => {
                //     this.isReplyLoading[index] = false;
                // });

            }

        },
        created() {
            this.fetchCommentListProcess();
        }
    }
</script>

<style scoped src="@/style/comment.css">

</style>