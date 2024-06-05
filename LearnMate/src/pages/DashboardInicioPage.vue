<template>
  <div class="centro">
    <div class="component-name">
      <span><i class="fa fa-home"></i></span>
      <h3>Dashboard</h3>
    </div>
    <!-- Verifica si userClasses y userPersons están vacíos -->
    <template v-if="userClasses.length === 0 && userPersons.length === 0">
      <div class="row-empty">
            <div class="left">
                <img src="../assets/empty.png" alt="">
            </div>
            <div class="right">
                <h2>¡Qué pena!</h2>
                <h3>Esperamos que esto se llene pronto de personitas.</h3>
            </div>
      </div>
    </template>
    <template v-else>
      <div class="row-prof-alum">
        <h3>{{ titleText }}</h3>
        <div class="prof-alum">
          <StudentProfessorCard 
            v-for="person in userPersons" 
            :key="person.idUsuario"
            :nombreCompleto="`${person.nombre} ${person.apellidos}`"
            :materiaNombre="person.profesor?.materia?.nombre || user?.profesor.materia.nombre"
            :materiaColor="person.profesor?.materia?.color || user?.profesor.materia.color"
            :profilePic="person.foto"
          />
        </div>
      </div>
      <div class="row-clases">
        <TableClassComponent :text="tableText" :userClasses="userClasses" @classDeleted="removeClassById" />
      </div>
    </template>
  </div>
</template>

<script setup lang="ts">
import { ref, onMounted } from 'vue';
import useLogin from '../composables/useLogin';
import TableClassComponent from '../components/TableClassComponent.vue';
import StudentProfessorCard from '../components/StudentProfessorCard.vue';
import { API } from '../services/API';

const { user, isStudent, isProfessor } = useLogin();
const { getAllClassByIdEst, getAllClassByIdProf, getAllEstByIdProf, getAllProfByIdEst } = API();

const titleText = ref('');
const tableText = ref('');
const idRol = ref<number>(-1);
const userClasses = ref<any[]>([]);
const userPersons = ref<any[]>([]);

const fetchData = async () => {
  if (user.value) {
    if (isStudent.value) {
      idRol.value = user.value.estudiante.idEstudiante;
      titleText.value = 'Tus Profesores';
      tableText.value = 'Profesor';
      userClasses.value = await getAllClassByIdEst(idRol.value);
      userPersons.value = await getAllProfByIdEst(idRol.value);
    } else if (isProfessor.value) {
      idRol.value = user.value.profesor.idProfesor;
      titleText.value = 'Tus Estudiantes';
      tableText.value = 'Estudiante';
      userClasses.value = await getAllClassByIdProf(idRol.value);
      userPersons.value = await getAllEstByIdProf(idRol.value);
    }
  }
};

onMounted(() => {
  fetchData();
});

fetchData();



const removeClassById = () => {
  fetchData();
};

</script>

<style scoped>
@import url('https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css');

.centro {
  flex-grow: 1;
  padding: 20px;
  background-color: #f2edf3;
  padding-bottom: 150px;
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
    margin-top: 60px;
  }
}

.row-empty{
    display:flex;
    justify-content: center;
    align-items: center;
    gap: 100px;
    margin-bottom:50px;
    .right{
      color: #B0BAC3;
        h2{
            font-size:40px;
            font-weight: 800;
        }
        h3{
            font-weight: 500;
        }
    }
  }
</style>
