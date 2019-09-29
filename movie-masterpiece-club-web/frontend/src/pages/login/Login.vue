<template>
    <div id="login">

        <div>
            <el-button
                    class="googleLoginButton"
                    @click="googleLogin"
                    type="text">
                Google login
            </el-button>
        </div>
    </div>
</template>

<script>

    export default {
        name: "Login",
        components: {},
        data() {
            return {}
        },
        methods: {
            googleLogin() {

            }
        }
    }
</script>

<style scoped src="@/style/login.css">

</style>