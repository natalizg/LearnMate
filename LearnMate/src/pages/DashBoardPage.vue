<!-- Dashboard.vue -->
<template>
  <div class="panel">
    <Sidebar />
    <RouterView></RouterView>
  </div>
</template>

<script setup lang="ts">
import useLogin from '../composables/useLogin';
import Sidebar from '../components/Sidebar.vue';
const { routeSecurity } = useLogin();
routeSecurity();
</script>

<style lang="scss" scoped>
@import url('https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css');

.panel {
  display: flex;
}
</style>
