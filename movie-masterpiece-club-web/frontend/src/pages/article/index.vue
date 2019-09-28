<template>
    <div id="articleIndex">
        <div class="header">
            <div class="meeting-icon">

            </div>
            <div class="meeting-title">메뉴 공간</div>
            <div class="user-info-wrapper">사용자 계정 공간</div>
        </div>
        <router-view></router-view>
    </div>
</template>

<script>

    export default {
        name: "index",
    }
</script>

<style scoped src="@/style/article.css">

</style>