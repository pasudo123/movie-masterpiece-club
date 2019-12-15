<template>
    <div id="articleEdit">

        <div class="titleWrapper">
            <el-input
                    class="titleClass"
                    placeholder="Enter a title..."
                    v-model="title"/>
        </div>

        <div class="contentWrapper">
            <quill-editor
                    :options="editorOption"
                    v-model="content"/>
        </div>

        <div class="buttonWrapper">
            <el-button @click="goHomePage">Cancel</el-button>
            <el-button v-if="!this.isEdit" @click="writeArticleProcess">OK</el-button>
            <el-button v-else @click="modifyArticleProcess">Modify</el-button>
        </div>
    </div>
</template>

<script>



    import {createNamespacedHelpers} from 'vuex';
    const {
        mapActions: articleMapActions,
        mapGetters: articleMapGetters
    } = createNamespacedHelpers('articleModule');

    import Quill from 'quill'
    import { ImageDrop } from 'quill-image-drop-module'
    import ImageResize from 'quill-image-resize-module'
    Quill.register('modules/imageDrop', ImageDrop);
    Quill.register('modules/imageResize', ImageResize);

    import {quillEditor} from 'vue-quill-editor'

    var toolbarOptions = [
        [{ 'header': [2, 3, 4, 5, false] }],
        ['bold', 'italic', 'underline', 'strike'],
        [{'align': [] }],
        [{'list': 'ordered'}, {'list': 'bullet' }],
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
                this.content= '';
            },

            goHomePage() {

                if (this.isEdit) {
                    this.$router.go(-1);
                    return;
                }

                this.$router.push({name: 'articleList'}).then(() => {});
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
                    this.$router.push({name: 'articleList'}).then(() => {});
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
                    this.$router.push({name: 'articleList'}).then(() => {});
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

<style scoped src="@/style/article-edit.css">

</style>