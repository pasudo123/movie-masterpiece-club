<template>
    <div id="login">
        <a @click="googleLoginProcess">
            <img style="width: 50px" :src="googleLogoImage"/>
        </a>
    </div>
</template>

<script>

    import {createNamespacedHelpers} from 'vuex';
    // const {mapActions: loginMapActions} = createNamespacedHelpers('userModules');

    export default {
        name: "Login",
        data() {
            return {
                googleLogoImage: require('@/assets/logo-google-icon.png')
            }
        },
        methods: {
            googleLoginProcess() {
                const ROOT_URI = process.env.VUE_APP_BASE_PATH;
                window.location.href = `${ROOT_URI}/oauth2/authorization/google`;
            }
        },
    }
</script>

<style scoped src="@/style/login.css">

</style>