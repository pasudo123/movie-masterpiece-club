<template>
    <div id="movie2Json">

        <div class="inputWrapper">
            <el-input
                    placeholder="Please input"
                    v-model="movie.title">
                <span class="slotLabel" slot="prepend">영화제목</span>
            </el-input>

            <el-input
                    placeholder="Please input"
                    v-model="movie.director">
                <span class="slotLabel" slot="prepend">감독</span>
            </el-input>

            <el-input
                    placeholder="YYYY-MM-DD"
                    v-model="movie.date">
                <span class="slotLabel" slot="prepend">개봉일</span>
            </el-input>

            <div style="text-align: left;">
                <h4 style="margin-left: 15px;">Summary</h4>
            </div>

            <el-input
                    type="textarea"
                    :autosize="{ minRows: 4, maxRows: 10 }"
                    placeholder="Please input"
                    v-model="movie.summary">
            </el-input>

            <div style="text-align: right; margin: 10px 0 0 0">
                <el-button
                        @click="generate()"
                >JSON Generate</el-button>
            </div>
        </div>
    </div>
</template>

<script>

    import { createNamespacedHelpers } from 'vuex'
    const { mapActions } = createNamespacedHelpers('jsonModule')

    export default {
        name: "Movie2Json",
        data() {
            return {
                movie: {title:'', director: '', date: '', summary: ''},
            }
        },
        methods: {

            ...mapActions(['createMovie2Json']),

            generate() {
                this.createMovie2Json(this.movie);
            }
        }
    }
</script>

<style scoped>
    .inputWrapper{
        margin: 0 auto;
        padding: 30px 0 30px 0;
        width: 800px;
    }

    .slotLabel{
        width: 100px;
        display:inline-block;
    }
</style>