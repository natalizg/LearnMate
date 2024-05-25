<!-- DashboardInicioPage.vue -->
<template>
  <div class="centro">
    <div class="component-name">
      <span><i class="fa fa-home"></i></span>
      <h3>Dashboard</h3>
    </div>
    <div class="row-prof-alum">
      <h3>{{ titleText }}</h3>
      <div class="prof-alum">
        <StudentProfessorCard/>
      </div>
    </div>
    <div class="row-clases">
      <TableClassComponent :text=tableText />
    </div>
  </div>
</template>

<script setup lang="ts">
import { ref } from 'vue';
import { UserType } from '../types/UserType';
import useLogin from '../composables/useLogin';
import TableClassComponent from '../components/TableClassComponent.vue';
import StudentProfessorCard from '../components/StudentProfessorCard.vue';


const { isStudent, isProfessor } = useLogin();
const user = ref<UserType | null>(null);

const storedUser = localStorage.getItem('user');

if (storedUser) {
  user.value = JSON.parse(storedUser) as UserType;
}

//textos:
const titleText = ref('');
const tableText = ref('');
if (isStudent.value) {
  titleText.value = 'Tus Profesores';
  tableText.value = 'Profesor'
} else if (isProfessor.value) {
  titleText.value = 'Tus Estudiantes';
  tableText.value = 'Estudiante';
}

</script>

<style scoped>
@import url('https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css');

.centro {
  flex-grow: 1;
  padding: 20px;
  background-color: #f2edf3;

  .component-name {
    display: flex;
    align-items: center;
    margin-top: 10px;
    h3 {
      font-size: 20px;
      margin-left: 15px;
      font-weight: 600;
    }
    span {
      display: flex;
      justify-content: center;
      align-items: center;
      width: 36px;
      height: 36px;
      border-radius: 4px;
      text-align: center;
      box-shadow: 0px 3px 8.3px 0.7px #b7d4e2;
      background: linear-gradient(to right, #c6edff, #54c4f6) !important;
      i {
        color: white;
      }
    }
  }
  .row-prof-alum {
    margin-top: 20px;
    .prof-alum {
      display: flex;
      flex-wrap: wrap;
      gap: 20px;
    }
  }
  .row-clases {
    margin-top: 50px;
  }
}
</style>
