<script setup lang="ts">
import NavOrganism from "./components/NavOrganism.vue"
import NavComponentProfessor from "./components/NavComponentProfessor.vue";
import NavComponentStudent from "./components/NavComponentStudent.vue";
import FooterComponent from "./components/FooterComponent.vue";
import { ref, watchEffect, onMounted } from "vue";
import router from "./router";
import useLogin from "./composables/useLogin";
import { UserType } from "./types/UserType";
import 'v-calendar/style.css';

const { isLog, isProfessor, isStudent } = useLogin();

const notHomePage = ref(false);

watchEffect(() => {
  notHomePage.value = router.currentRoute.value.path !== "/";
});

const storedUser = localStorage.getItem('user');

const user = ref<UserType | null>(null);

const profilePic = ref('');
const fetchData = async () => {
  if (storedUser) {
    user.value = JSON.parse(storedUser) as UserType;
    profilePic.value = user.value.foto;
  }
};

onMounted(() => {
  fetchData();
});

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
