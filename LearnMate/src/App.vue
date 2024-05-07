<script setup lang="ts">
import NavOrganism from "./components/NavOrganism.vue"
import { ref, watchEffect } from "vue";
import router from "./router";

const notHomePage = ref(false)
watchEffect(() => {
  notHomePage.value = router.currentRoute.value.path !== "/"
})


</script>

<template>
  <NavOrganism :bg="notHomePage"/>
  <router-view></router-view>
</template>

<style lang="scss" scoped>
</style>
