<template>
    <div id="articleEdit">

        <div>
            제목 삽입 구간.
        </div>

        <quill-editor
                v-model="content"
        >
        </quill-editor>
    </div>
</template>

<script>

    // require styles
    import 'quill/dist/quill.core.css'
    import 'quill/dist/quill.snow.css'
    import 'quill/dist/quill.bubble.css'

    import { quillEditor } from 'vue-quill-editor'

    export default {
        name: "ArticleEdit",
        components: {quillEditor},
        data() {
            return {
                content: '',
            }
        }
    }
</script>

<style scoped src="@/style/article-edit.css">

</style>