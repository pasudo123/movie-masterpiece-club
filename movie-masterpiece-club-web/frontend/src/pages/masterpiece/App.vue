<template>
    <div id="app">
        <router-view></router-view>
    </div>
</template>

<script>

    import {foo} from '@/utils/moment-util';

    export default {
        name: "App",
    }
</script>

<style>
    body {
        margin: 0;
        padding: 0;
    }
</style>