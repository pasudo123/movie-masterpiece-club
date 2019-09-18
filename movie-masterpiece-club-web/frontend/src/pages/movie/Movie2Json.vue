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

            <div>
                <span class="form-label">개봉일</span>
                <el-date-picker
                        style="width: 82%;"
                        v-model="movie.date"
                        value-format="yyyy-MM-ddTHH:mm:ss"
                        type="date"
                        placeholder="Pick a day">
                </el-date-picker>
            </div>

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
                        @click="generate()">
                    JSON Generate
                </el-button>
            </div>
        </div>
    </div>
</template>

<script>

    import {createNamespacedHelpers} from 'vuex'

    const {mapActions} = createNamespacedHelpers('jsonModule')

    export default {
        name: "Movie2Json",
        data() {
            return {
                movie: {title: '', director: '', date: '', summary: ''},
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
    .inputWrapper {
        margin: 0 auto;
        padding: 30px 0 30px 0;
        width: 800px;
    }

    .slotLabel {
        width: 100px;
        display: inline-block;
    }

    .form-label{
        display: inline-block;
        border-top-left-radius: 3px;
        border-bottom-left-radius: 3px;
        border: 1px solid #dbc3a9;
        background-color: bisque;
        padding: 8px 50px 8px 50px;
    }
</style>