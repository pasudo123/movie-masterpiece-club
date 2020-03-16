<template>
  <div id="commentList">

    <div
            class="commentElement"
            v-for="(comment, index) in this.listCommentState" :key="comment.id">

      <div class="commentInfoWrapper">
        <div class="createdInfo">
          <el-avatar :size="35">
            <img :src="comment.createdProfile"/>
          </el-avatar>
          <span class="nameInfo">{{comment.createdName}}</span>

          <div v-if="isMine(comment.createdUserId)" class="dropDownWrapper">
            <el-dropdown trigger="click">
                            <span class="el-dropdown-link">
                                설정<i class="el-icon-arrow-down el-icon--right"></i>
                            </span>
              <el-dropdown-menu slot="dropdown">
                <el-dropdown-item @click.native="toggleModifyEdit(index)">수정</el-dropdown-item>
                <el-dropdown-item @click.native="deleteCommentProcess(comment.id)">삭제</el-dropdown-item>
              </el-dropdown-menu>
            </el-dropdown>
          </div>
        </div>

        <!-- 댓글 수정 태그 -->
        <div v-if="!modifyEdit[index]" class="commentInfo">
          {{comment.comment}}
        </div>
        <div v-else class="commentEditInfo">
          <el-input
                  class="titleClass"
                  v-model="modifyComment[index]"/>
          <el-button @click="modifyCommentProcess(index, comment.id, modifyComment[index])">수정</el-button>
          <el-button @click="toggleModifyEdit(index)">취소</el-button>
        </div>

        <div class="registerDateInfo">{{comment.registerDate}}</div>
      </div>

      <div class="commentEtcWrapper">
        <div class="likeButton">좋아요</div>
        <div class="replyButton" @click="toggleReplyComment(index)">답글</div>
      </div>

      <reply-comment-edit
              v-on:toggleReplyComment="toggleReplyComment"
              v-bind:commentId="comment.id"
              v-bind:commentIndex="index"
              v-if="isReplyEdit[index]"/>

      <div class="replyCommentWrapper">


        <div
                v-if="comment.reply.list.length !== 0"
                class="replyCommentShowButton"
                @click="toggleReplyShow(index)">
          답글보기 ( {{comment.reply.list.length}} )
        </div>

        <reply-comment-list
                v-bind:reply="comment.reply.list"
                v-bind:commentIndex="index"
                v-if="isReplyShow[index]"/>
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
  const {
    mapGetters: authMapGetters
  } = createNamespacedHelpers('auth');

  export default {
    name: "CommentList",
    components: {ReplyCommentList, ReplyCommentEdit},
    data() {
      return {
        comment: '',
        isReplyEdit: [],
        isReplyShow: [],
        isReplyLoading: [],
        modifyEdit: [],
        modifyComment: [],
        isDelete: false,
      }
    },
    computed: {
      ...articleMapGetters(['articleOneState']),
      ...commentMapGetters(['listCommentState']),
      ...commentMapGetters(['ListReplyState']),
      ...authMapGetters(['currentAuthState']),
    },
    methods: {

      ...commentMapActions(['fetchCommentList']),
      ...commentMapActions(['updateComment']),
      ...commentMapActions(['deleteComment']),

      fetchCommentListProcess() {

        const params = {};
        params.articleId = this.$route.params.articleId;

        this.fetchCommentList(params).then(() => {
          for (let i = 0; i < this.listCommentState.length; i++) {
            this.isReplyEdit[i] = false;
            this.isReplyShow[i] = false;
            this.isReplyLoading[i] = false;
            this.modifyEdit[i] = false;
            this.modifyComment[i] = this.listCommentState[i].comment;
          }
        })
      },

      toggleModifyEdit(index) {
        this.$set(this.modifyEdit, index, !this.modifyEdit[index]);
        this.modifyComment[index] = this.listCommentState[index].comment;
      },

      modifyCommentProcess(index, commentId, updateComment) {

        const params = {};
        params.commentId = commentId;
        params.content = updateComment;
        params.index = index;

        this.updateComment(params).then(() => {
          this.$set(this.modifyEdit, index, false);
        }).catch(() => {
          this.$set(this.modifyEdit, index, false);
        });
      },

      deleteCommentProcess(commentId) {

        if (this.isDelete) {
          return;
        }

        this.isDelete = true;

        const params = {};
        params.commentId = commentId;

        this.deleteComment(params).then(() => {
          this.isDelete = false;
        }).catch(() => {
          this.isDelete = false;
        })
      },

      toggleReplyComment(index) {
        this.$set(this.isReplyEdit, index, !this.isReplyEdit[index]);
      },

      toggleReplyShow(index) {
        this.$set(this.isReplyShow, index, !this.isReplyShow[index]);
      },

      isMine(createdUserId) {
        return (this.currentAuthState.id === createdUserId);
      }

    },
    created() {
      this.fetchCommentListProcess();
    }
  }
</script>

<style scoped src="@/style/comment.css">

</style>