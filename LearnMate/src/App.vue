<script setup lang="ts">
import NavOrganism from "./components/NavOrganism.vue"
import NavComponentProfessor from "./components/NavComponentProfessor.vue";
import NavComponentStudent from "./components/NavComponentStudent.vue";
import FooterComponent from "./components/FooterComponent.vue";
import { ref, watchEffect } from "vue";
import router from "./router";
import useLogin from "./composables/useLogin";

const {isLog, isProfessor, isStudent} = useLogin();

const notHomePage = ref(false)
watchEffect(() => {
  notHomePage.value = router.currentRoute.value.path !== "/"
})


</script>

<template>
  <NavOrganism v-if="!isLog" :bg="notHomePage"/>
  <NavComponentProfessor v-if="isProfessor" />
  <NavComponentStudent v-if="isStudent" :bg="!notHomePage"/>
  <router-view></router-view>
  <FooterComponent/>
</template>

<style lang="scss" scoped>
</style>
