<template>
    <div id="laboratory">
        <h3>Laboratory</h3>

        <p @click="payloadMethod">payload 로 전송</p>

        <p @click="formDataMethod">form data 로 전송</p>

    </div>
</template>

<script>

    import {createNamespacedHelpers} from 'vuex';

    const {mapActions} = createNamespacedHelpers('laboratoryModule');

    export default {

        name: "Laboratory",
        methods: {

            ...mapActions(['payloadActions']),
            ...mapActions(['formDataActions']),

            payloadMethod() {
                this.payloadActions();
            },

            formDataMethod() {
                this.formDataActions();
            }
        }
    }
</script>

<style scoped>
    #laboratory{
        text-align: center;
        margin: 0 auto;
    }
</style>