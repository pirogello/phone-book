<template>
    <div class="notification">
        Ассинхронное обновление всех данных в таблице выполнено
    </div>
</template>

<script>
export default {
}
</script>

<style scoped>
.notification{
    max-width: 300px;
    background-color: #FF896B;
    position: absolute;
    z-index: 9999;
    right: 20px;
    top: 20px;
    padding: 10px 15px;
    border-radius: 15px;
    box-shadow: inset 0px 0px 12px 0px rgba(0, 0, 0, .4);
    color: white;
    font-size: 14px;
    opacity: 0;
    visibility: hidden;
    transition: all 0.8s ease;
}
</style>
