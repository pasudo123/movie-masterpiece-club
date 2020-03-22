<template>
  <div id="articleEdit">

    <div class="titleWrapper">
      <el-input
              class="titleClass"
              placeholder="제목을 입력하세요."
              v-model="title"/>
    </div>

    <div class="contentWrapper">
      <quill-editor
              :options="editorOption"
              v-model="content"/>
    </div>

    <div class="buttonWrapper">
      <el-button v-if="!this.isEdit" @click="writeArticleProcess">등록</el-button>
      <el-button v-else @click="modifyArticleProcess">수정</el-button>
      <el-button @click="moveHomePage">취소</el-button>
    </div>
  </div>
</template>

<script>

  import {createNamespacedHelpers} from 'vuex';

  const {
    mapActions: articleMapActions,
    mapGetters: articleMapGetters
  } = createNamespacedHelpers('articleModule');

  import 'quill/dist/quill.core.css'
  import 'quill/dist/quill.snow.css'
  import Quill from 'quill'
  import {ImageDrop} from 'quill-image-drop-module'
  import ImageResize from 'quill-image-resize-module'

  Quill.register('modules/imageDrop', ImageDrop);
  Quill.register('modules/imageResize', ImageResize);

  import {quillEditor} from 'vue-quill-editor'

  var toolbarOptions = [
    [{'header': [1, 2, 3, 4, 5, false]}],
    ['bold', 'italic', 'underline', 'strike'],
    [{'align': []}],
    [{'list': 'ordered'}, {'list': 'bullet'}],
    ['link', 'image'],
    [{'color': []}, {'background': []}],
  ];

  export default {
    name: "ArticleEdit",
    components: {quillEditor},
    data() {
      return {
        isWriteProcess: false,
        isModifyProcess: false,
        title: '',
        content: '',
        type: 'GENERAL',
        editorOption: {
          modules: {
            toolbar: toolbarOptions,
            imageDrop: true,
            imageResize: true,
            scrollingContainer: '',
          },
          theme: 'snow'
        },
        isEdit: false
      }
    },
    computed: {
      ...articleMapGetters(['articleOneState'])
    },
    watch: {
      '$route'(to, from) {

        /** routing 이 되는 상태 **/
        /** to 에 params 가 없는 경우 **/
        if (Object.keys(to.params).length === 0) {
          this.noParamsProcess();
          return;
        }
      }
    },
    methods: {

      ...articleMapActions(['writeArticle']),
      ...articleMapActions(['modifyArticle']),

      noParamsProcess() {
        this.isEdit = false;
        this.title = '';
        this.content = '';
      },

      moveHomePage() {
        if (this.isEdit) {
          this.$router.go(-1);
          return;
        }
        this.$router.push({name: 'Home'});
      },

      writeArticleProcess() {

        if (this.isWriteProcess) {
          return;
        }

        this.isWriteProcess = true;

        const params = {};
        params.title = this.title;
        params.content = this.content;
        params.type = this.type;

        this.writeArticle(params).then(() => {
          this.isWriteProcess = false;
          this.$router.push({name: 'articleList'}).then(() => {
          });
        }).catch(() => {
          this.isWriteProcess = false;
        })
      },

      modifyArticleProcess() {

        if (this.isModifyProcess) {
          return;
        }

        this.isModifyProcess = true;

        const params = {};
        params.articleId = this.$route.params.articleId;
        params.title = this.title;
        params.content = this.content;
        params.type = this.type;

        this.modifyArticle(params).then(() => {
          this.isModifyProcess = false;
          this.$router.push({name: 'articleList'}).then(() => {
          });
        }).catch(() => {
          this.isModifyProcess = false;
        })
      }
    },
    created() {

      let articleId = this.$route.params.articleId;

      /** no params **/
      if (articleId === undefined) {
        this.noParamsProcess();
        return;
      }

      /** params => state 미존재 => 작성 **/
      if (this.articleOneState.id === undefined) {
        this.isEdit = false;
        return;
      }

      /** params => state 존재 => 일치 **/
      if (articleId === this.articleOneState.id) {
        this.isEdit = true;
        this.title = this.articleOneState.title;
        this.content = this.articleOneState.content;
      }
    }
  }
</script>

<style lang="scss" scoped src="@/style/article/article-edit.scss">

</style>

<style lang="scss">
  .titleClass {
    padding: 5px 0 5px 0;
    border-bottom: 1px solid #dadada;
    .el-input__inner {
      padding: 10px 10px 10px 10px;
      font-size: 30px;
      border: none;
    }
  }
  .contentWrapper {
    .ql-toolbar.ql-snow {
      border: none;
    }
    .ql-container.ql-snow {
      height: 350px;
      max-height: 350px;
      position:relative;
      overflow-y: auto;
      top: 10px;
      border: none;
    }
  }
</style>
