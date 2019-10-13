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
            <el-button @click="goHomePage">Cancel</el-button>
            <el-button @click="writeArticleProcess">OK</el-button>
        </div>
    </div>
</template>

<script>

    import {createNamespacedHelpers} from 'vuex';
    const {mapActions: articleMapActions} = createNamespacedHelpers('articleModule');

    // require styles
    import 'quill/dist/quill.core.css'
    import 'quill/dist/quill.snow.css'
    import 'quill/dist/quill.bubble.css'

    import {quillEditor} from 'vue-quill-editor'

    export default {
        name: "ArticleEdit",
        components: {quillEditor},
        data() {
            return {
                isWrite: false,
                title: '',
                content: '',
                type: 'GENERAL',
                editorOption: {
                    theme: 'snow'
                }
            }
        },
        methods: {

            ...articleMapActions(['writeArticle']),

            goHomePage() {
                this.$router.push({name: 'articleList'}).then(() => {});
            },

            writeArticleProcess() {

                if (this.isWrite) {
                    return;
                }

                this.isWrite = true;

                const params = {};
                params.title = this.title;
                params.content = this.content;
                params.type = this.type;

                this.writeArticle(params).then(() => {
                    this.isWrite = false;
                    /** router 이동 **/
                    this.$router.push({name: 'articleList'}).then(() => {});
                }).then(() => {
                    this.isWrite = false;
                })
            }
        },
        beforeUpdate() {
            console.debug('=====================');
            console.debug(this.content);
        }
    }
</script>

<style scoped src="@/style/article-edit.css">

</style>