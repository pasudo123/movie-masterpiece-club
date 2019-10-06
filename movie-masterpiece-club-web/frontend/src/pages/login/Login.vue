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
        components: {},
        data() {
            return {}
        },
        methods: {
            googleLogin() {

            }
        },
        created() {

        }
    }
</script>

<style scoped src="@/style/login.css">

</style>