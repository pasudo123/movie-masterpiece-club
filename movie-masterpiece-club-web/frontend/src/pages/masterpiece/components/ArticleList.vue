<template>
    <div id="articleList">

        <div class="dataTableWrapper"
             v-loading="isLoading">
            <el-table
                    header-cell-class-name="headerCellName"
                    cell-class-name="dataCellName"
                    :data="articleListState"
                    @row-click="enterArticleProcess"
                    style="width: 100%">

                <el-table-column
                        label="Title">
                    <template slot-scope="scope">
                        {{ scope.row.title }}
                    </template>
                </el-table-column>

                <el-table-column
                        label="Writer"
                        width="200">
                    <template slot-scope="scope">
                        <span class="writerText">{{ scope.row.createdName }}</span>
                    </template>
                </el-table-column>

                <el-table-column
                        label="Register Date"
                        width="160">
                    <template slot-scope="scope">
                        {{ scope.row.registerDate }}
                    </template>
                </el-table-column>
            </el-table>

            <div class="pageWrapper">
                <el-pagination
                        class="pagination"
                        layout="prev, pager, next"
                        @current-change="pageMove"
                        :page-size="pageSize"
                        :current-page="currentPage"
                        :total="articleAllCountState">
                </el-pagination>
            </div>
        </div>

        <!--<div v-else>-->
        <!--<div-->
        <!--class="articleElement"-->
        <!--v-for="item in articleListState"-->
        <!--:key="item.id">-->

        <!--<div class="element">-->
        <!--<el-avatar-->
        <!--class="userProfile"-->
        <!--shape="circle"-->
        <!--:size="60"-->
        <!--:fit="'cover'"-->
        <!--:src="item.createdProfile"></el-avatar>-->

        <!--<div-->
        <!--@click="goArticleViewPage(item.id)"-->
        <!--class="contentArea">-->
        <!--<div class="listRegisterDateArea">{{item.registerDate}}</div>-->
        <!--<div class="listTitleArea">{{item.title}}</div>-->
        <!--</div>-->
        <!--</div>-->

        <!--<el-divider><i class="el-icon-star-on"></i></el-divider>-->
        <!--</div>-->

    </div>
</template>

<script>

    import {createNamespacedHelpers} from 'vuex';

    const {
        mapActions: articleMapActions,
        mapGetters: articleMapGetters
    } = createNamespacedHelpers('articleModule');

    export default {
        name: "ArticleList",
        data() {
            return {
                isLoading: false,
                currentPage: 1,
                pageSize: 5
            }
        },
        computed: {
            ...articleMapGetters(['articleAllCountState']),
            ...articleMapGetters(['articleListState'])
        },
        watch: {
            $route(to, from) {

                if (Object.keys(to.query).length === 0) {
                    this.renderArticleListPage(1);
                    return;
                }

                this.renderArticleListPage(to.query.page);
            }
        },
        methods: {

            ...articleMapActions(['fetchAllArticleCount']),
            ...articleMapActions(['fetchAllArticle']),
            ...articleMapActions(['fetchPartialArticle']),

            enterArticleProcess(row) {
                this.$router.push({name: 'articleView', params: {articleId: row.id}})
                    .then(() => {
                    });
            },

            renderArticleListPage(page) {

                this.isLoading = true;

                Promise.all([
                    this.fetchAllArticleCount().catch(error => {
                        return error
                    }),
                    this.fetchPartialArticle({page}).catch(error => {
                        return error
                    })
                ]).then((returnValue) => {
                    /**
                     * [ promise all ]
                     * 정상적 메시지이면 정상
                     * 오류면 오류로 값이 리턴.
                     *  **/
                    this.isLoading = false;
                    this.currentPage = page;
                });
            },

            pageMove(currentPage) {
                this.$router.push({name: 'articleList', query: {page: currentPage}});
            }
        },
        created() {
            this.renderArticleListPage(1);
        }
    }
</script>

<style>
    .el-table .headerCellName {
        text-align: center;
        font-weight: bold;
        font-size: 14px;
        letter-spacing: 1px;
        color: black;
    }

    .el-table .dataCellName {
        text-align: center;
    }

    .el-table .dataCellName:hover {
        cursor: pointer;
    }

</style>

<style scoped src="@/style/article-list.css">

</style>