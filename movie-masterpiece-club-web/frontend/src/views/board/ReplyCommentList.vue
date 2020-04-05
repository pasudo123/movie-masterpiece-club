<!--<template>-->
<!--  <div id="replyCommentList">-->

<!--    <div-->
<!--            class="replyElement"-->
<!--            v-for="(reply, index) in this.reply" :key="reply.id">-->

<!--      <div class="replyInfoWrapper">-->
<!--        <div class="createdInfo">-->
<!--          <el-avatar :size="30">-->
<!--            <img :src="reply.createdProfile"/>-->
<!--          </el-avatar>-->
<!--          <span class="nameInfo">{{reply.createdName}}</span>-->

<!--          <div v-if="isMine(reply.createdUserId)" class="dropDownWrapper">-->
<!--            <el-dropdown trigger="click">-->
<!--                <span class="el-dropdown-link">-->
<!--                    설정<i class="el-icon-arrow-down el-icon&#45;&#45;right"></i>-->
<!--                </span>-->
<!--              <el-dropdown-menu slot="dropdown">-->
<!--                <el-dropdown-item @click.native="toggleModifyEdit(index)">수정</el-dropdown-item>-->
<!--                <el-dropdown-item @click.native="deleteReplyProcess(reply.id)">삭제</el-dropdown-item>-->
<!--              </el-dropdown-menu>-->
<!--            </el-dropdown>-->
<!--          </div>-->
<!--        </div>-->

<!--        &lt;!&ndash; 답글 수정 태그 &ndash;&gt;-->
<!--        <div v-if="!modifyEdit[index]" class="replyInfo">{{reply.comment}}</div>-->
<!--        <div v-else class="commentEditInfo">-->
<!--          <el-input-->
<!--                  class="titleClass"-->
<!--                  v-model="modifyReply[index]"/>-->
<!--          <el-button @click="modifyReplyProcess(index, reply.id, modifyReply[index])">수정</el-button>-->
<!--          <el-button @click="toggleModifyEdit(index)">취소</el-button>-->
<!--        </div>-->
<!--        <div class="registerDateInfo">{{reply.registerDate}}</div>-->
<!--      </div>-->

<!--      <div class="replyEtcWrapper">-->
<!--        <div class="likeButton">좋아요</div>-->
<!--        <div class="replyButton" @click="toggleDoubleReplyComment(index)">답글</div>-->
<!--      </div>-->

<!--      <reply-comment-edit-->
<!--              v-on:toggleDoubleReplyComment="toggleDoubleReplyComment"-->
<!--              v-bind:commentId="reply.parentId"-->
<!--              v-bind:commentIndex="commentIndex"-->
<!--              v-bind:replyIndex="index"-->
<!--              v-if="isDoubleReplyEdit[index]"/>-->

<!--    </div>-->
<!--  </div>-->
<!--</template>-->

<!--<script>-->

<!--  import ReplyCommentEdit from '@/pages/masterpiece/components/ReplyCommentEdit';-->

<!--  import {createNamespacedHelpers} from 'vuex';-->

<!--  const {-->
<!--    mapActions: commentMapActions-->
<!--  } = createNamespacedHelpers('commentModule');-->
<!--  const {-->
<!--    mapGetters: authMapGetters-->
<!--  } = createNamespacedHelpers('auth');-->

<!--  export default {-->
<!--    name: "ReplyCommentList",-->
<!--    components: {ReplyCommentEdit},-->
<!--    props: {-->
<!--      reply: {-->
<!--        type: Array,-->
<!--        required: false,-->
<!--        default: () => []-->
<!--      },-->
<!--      commentIndex: {-->
<!--        type: Number,-->
<!--        required: true,-->
<!--        default: () => 1-->
<!--      }-->
<!--    },-->
<!--    data() {-->
<!--      return {-->
<!--        isDoubleReplyEdit: [],-->
<!--        modifyEdit: [],-->
<!--        modifyReply: [],-->
<!--        isDelete: false-->
<!--      }-->
<!--    },-->
<!--    computed: {-->
<!--      ...authMapGetters(['currentAuthState'])-->
<!--    },-->
<!--    methods: {-->

<!--      ...commentMapActions(['updateReply']),-->
<!--      ...commentMapActions(['deleteReply']),-->

<!--      toggleDoubleReplyComment(index) {-->
<!--        this.$set(this.isDoubleReplyEdit, index, !this.isDoubleReplyEdit[index]);-->
<!--      },-->

<!--      toggleModifyEdit(index) {-->
<!--        this.$set(this.modifyEdit, index, !this.modifyEdit[index]);-->

<!--        if (this.modifyEdit[index]) {-->
<!--          return;-->
<!--        }-->

<!--        this.modifyReply[index] = this.reply[index].comment;-->
<!--      },-->

<!--      modifyReplyProcess(replyIndex, replyId, updateComment) {-->

<!--        const params = {};-->
<!--        params.commentIndex = this.commentIndex;-->
<!--        params.replyId = replyId;-->
<!--        params.replyIndex = replyIndex;-->
<!--        params.content = updateComment;-->

<!--        this.updateReply(params).then(() => {-->
<!--          this.$set(this.modifyEdit, replyIndex, false);-->
<!--        }).catch(() => {-->
<!--          this.$set(this.modifyEdit, replyIndex, false);-->
<!--        });-->
<!--      },-->

<!--      deleteReplyProcess(replyId) {-->

<!--        if (this.isDelete) {-->
<!--          return;-->
<!--        }-->

<!--        this.isDelete = true;-->

<!--        const params = {};-->
<!--        params.replyId = replyId;-->
<!--        params.commentIndex = this.commentIndex;-->

<!--        this.deleteReply(params).then(() => {-->
<!--          console.debug('asdsad');-->
<!--          this.isDelete = false;-->
<!--        }).catch(() => {-->
<!--          this.isDelete = false;-->
<!--        })-->
<!--      },-->

<!--      isMine(createdUserId) {-->
<!--        return (this.currentAuthState.id === createdUserId);-->
<!--      }-->
<!--    },-->
<!--    created() {-->
<!--      for (let i = 0; i < this.reply.length; i++) {-->
<!--        this.modifyEdit[i] = false;-->
<!--        this.modifyReply[i] = this.reply[i].comment;-->
<!--      }-->
<!--    }-->
<!--  }-->
<!--</script>-->

<!--<style scoped src="@/style/comment.css">-->

<!--</style>-->
