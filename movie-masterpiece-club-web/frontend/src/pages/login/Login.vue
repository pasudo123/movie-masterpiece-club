<template>

    <div id="login">

        <!-- Default form login -->
        <form class="text-center border border-light p-5" action="#!">

            <h1>Gulagbu</h1>

            <div class="whiteSpaceLine"></div>

            <!-- Email -->
            <input type="email" id="defaultLoginFormEmail" class="form-control mb-4" placeholder="E-mail">

            <!-- Password -->
            <input type="password" id="defaultLoginFormPassword" class="form-control mb-4" placeholder="Password">

            <div class="d-flex justify-content-around">
                <div>
                    <!-- Remember me -->
                    <div class="custom-control custom-checkbox">
                        <input type="checkbox" class="custom-control-input" id="defaultLoginFormRemember">
                        <label class="custom-control-label" for="defaultLoginFormRemember">Remember me</label>
                    </div>
                </div>
                <div>
                    <!-- Forgot password -->
                    <a href="">Forgot password?</a>
                </div>
            </div>

            <!-- Sign in button -->
            <button class="btn btn-info btn-block my-4" type="submit">Sign in</button>

            <!-- Register -->
            <p>Not a member?
                <a href="">Register</a>
            </p>

            <!-- Social login -->
            <p>or sign in with:</p>

            <a @click="googleLoginProcess">
                <img style="width: 50px" :src="googleLogoImage"/>
            </a>
        </form>
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