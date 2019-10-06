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

    import {createNamespacedHelpers} from 'vuex';

    const {mapActions: loginMapActions} = createNamespacedHelpers('userModules');

    export default {
        name: "Login",
        methods: {

            googleLogin() {
                const ROOT_URI = process.env.VUE_APP_BASE_PATH;
                window.location.href = `${ROOT_URI}/oauth2/authorization/google`;
            }
        },
        created() {

        }
    }
</script>

<style scoped src="@/style/login.css">

</style>