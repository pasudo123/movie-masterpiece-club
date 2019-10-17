<template>
    <div id="replyCommentList">

        <div
                class="replyElement"
                v-for="(reply, index) in this.reply" :key="reply.id">

            <div class="replyInfoWrapper">
                <div class="createdInfo">
                    <el-avatar :size="30">
                        <img :src="reply.createdProfile"/>
                    </el-avatar>
                    <span class="nameInfo">{{reply.createdName}}</span>
                </div>

                <div class="replyInfo">{{reply.comment}}</div>
                <div class="registerDateInfo">{{reply.registerDate}}</div>
            </div>

            <div class="replyEtcWrapper">
                <div class="likeButton">좋아요</div>
                <div class="replyButton" @click="toggleDoubleReplyComment(index)">답글</div>
            </div>

            <reply-comment-edit
                    v-on:toggleDoubleReplyComment="toggleDoubleReplyComment"
                    v-bind:doubleReply="true"
                    v-bind:commentId="reply.parentId"
                    v-bind:replyIndex="replyIndex"
                    v-bind:doubleReplyIndex="index"
                    v-if="isDoubleReplyEdit[index]" />

        </div>
    </div>
</template>

<script>

    import ReplyCommentEdit from '@/pages/masterpiece/components/ReplyCommentEdit';

    export default {
        name: "ReplyCommentList",
        components: {ReplyCommentEdit},
        props: {
            reply: {
                type: Array,
                required: true,
                default: () => []
            },
            replyIndex: {
                type: Number,
                required: true,
                default: () => '1'
            }
        },
        data() {
            return {
                isDoubleReplyEdit: []
            }
        },
        watch: {
            reply(newValue, oldValue) {
                /** 값의 변경. **/
                for (let i = 0; i < reply.length; i++) {
                    this.isDoubleReplyEdit[i] = false;
                }
            }
        },
        methods: {
            toggleDoubleReplyComment(index) {
                this.$set(this.isDoubleReplyEdit, index, !this.isDoubleReplyEdit[index]);
            }
        },
        created() {

        }
    }
</script>

<style scoped src="@/style/comment.css">

</style>