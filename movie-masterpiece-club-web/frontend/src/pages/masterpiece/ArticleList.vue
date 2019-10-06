<template>
    <div id="articleList">
        <div
                class="articleElement"
                v-for="item in articleListState"
                :key="item.id">

            <div class="element">
                <el-avatar
                        class="userProfile"
                        shape="square"
                        :size="45"
                        :fit="'cover'"
                        :src="articleWriterUserProfile"></el-avatar>

                <div class="contentArea">
                    <div>{{item.registerDate}}</div>
                    <div>{{item.content}}</div>
                </div>
            </div>

            <!--<el-divider><i class="el-icon-star-on"></i></el-divider>-->
        </div>

    </div>
</template>

<script>

    import {createNamespacedHelpers} from 'vuex';

    const {mapActions, mapGetters} = createNamespacedHelpers('articleModule');

    export default {
        name: "ArticleList",
        data() {
            return {
                articleWriterUserProfile: 'https://avatars0.githubusercontent.com/u/17797352?s=460&v=4'
            }
        },
        computed: {
            ...mapGetters(['articleListState'])
        },
        methods: {
            ...mapActions(['fetchAllArticle'])
        },
        created() {
            this.fetchAllArticle().then((response) => {

            }).catch((error) => {
                console.error(error);
            })
        }
    }
</script>

<style scoped src="@/style/article-list.css">

</style>