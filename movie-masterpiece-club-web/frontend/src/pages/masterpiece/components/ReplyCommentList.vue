<template>
    <div id="replyCommentList">
        <div>
            {{this.reply}}
        </div>
    </div>
</template>

<script>
    export default {
        name: "ReplyCommentList",
        props:{
            reply: {
                type: Array,
                required: false,
                default: () => []
            }
        },
        computed: {

        },
        created() {

        }
    }
</script>

<style scoped>

</style>