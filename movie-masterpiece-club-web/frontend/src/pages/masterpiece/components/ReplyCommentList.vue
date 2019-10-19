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

                    <div v-if="isMine(reply.createdUserId)" class="dropDownWrapper">
                        <el-dropdown trigger="click">
                            <span class="el-dropdown-link">
                                설정<i class="el-icon-arrow-down el-icon--right"></i>
                            </span>
                            <el-dropdown-menu slot="dropdown">
                                <el-dropdown-item>수정</el-dropdown-item>
                                <el-dropdown-item @click="deleteReplyProcess(reply.id)">삭제</el-dropdown-item>
                            </el-dropdown-menu>
                        </el-dropdown>
                    </div>
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
                    v-bind:commentId="reply.parentId"
                    v-bind:commentIndex="commentIndex"
                    v-bind:replyIndex="index"
                    v-if="isDoubleReplyEdit[index]" />

        </div>
    </div>
</template>

<script>

    import ReplyCommentEdit from '@/pages/masterpiece/components/ReplyCommentEdit';

    import {createNamespacedHelpers} from 'vuex';

    const {
        mapGetters: authMapGetters
    } = createNamespacedHelpers('auth');

    export default {
        name: "ReplyCommentList",
        components: {ReplyCommentEdit},
        props: {
            reply: {
                type: Array,
                required: true,
                default: () => []
            },
            commentIndex: {
                type: Number,
                required: true,
                default: () => 1
            }
        },
        data() {
            return {
                isDoubleReplyEdit: []
            }
        },
        computed: {
            ...authMapGetters(['currentAuthState'])
        },
        methods: {
            toggleDoubleReplyComment(index) {
                this.$set(this.isDoubleReplyEdit, index, !this.isDoubleReplyEdit[index]);
            },

            deleteReplyProcess(replyId) {

            },

            isMine(createdUserId) {
                return (this.currentAuthState.id === createdUserId);
            }
        },
        created() {

        }
    }
</script>

<style scoped src="@/style/comment.css">

</style>