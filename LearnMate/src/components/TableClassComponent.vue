<template>
    <div class="row">
      <div class="col-12 grid-margin">
        <div class="card">
          <div class="card-body">
            <h3 class="card-title">Clases Particulares</h3>
            <div class="table-responsive">
              <table class="table">
                <thead>
                  <tr>
                    <th> {{text}} </th>
                    <th> Materia </th>
                    <th> Fecha </th>
                    <th> Hora </th>
                    <th> Acciones </th>
                  </tr>
                </thead>
                <tbody>
                  <tr v-for="clase in userClasses" :key="clase.idClase">
                    
                    <td class="name">
                      <img class="me-2" v-if="getPic(clase) === null" src="../assets/user-standar.jpg" alt="">
                      <img class="me-2" v-else v-bind:src="'data:image/jpeg;base64,'+getPic(clase)" />
                      <p>{{ getNombre(clase) }}</p>
                    </td>
                    <td class="asignatura"> <p  class="materia-color" :style="{ backgroundColor: '#' + clase.materia.color }">{{clase.materia.nombre}}</p></td>
                    <td> {{ new Date(clase.fecha).toLocaleDateString() }}</td>
                    <td>{{ clase.tramoHorario.descripcion }} </td>
                    <td class="acciones">
                      <button @click="handleDelete(clase.idClase)"class="delete-button">
                        <i class="fas fa-times"></i>
                      </button>
                    </td>
                  </tr>
                </tbody>
              </table>
            </div>
          </div>
        </div>
      </div>
    </div>
  </template>

  
<script lang="ts" setup>
import { DefineProps } from 'vue';
import { API } from '../services/API';
const { deleteClassById } = API();
const props = defineProps({
  text: String,
  userClasses: Object
})

const getNombre = (clase: any) => {
  if (props.text === 'Profesor') {
    return clase.usuarioProfesor.nombre;
  } else if (props.text === 'Estudiante') {
    return clase.usuarioEstudiante.nombre;
  }
  return null;
};

const getPic = (clase: any) => {
  if (props.text === 'Profesor') {
    return clase.usuarioProfesor.foto;
  } else if (props.text === 'Estudiante') {
    return clase.usuarioEstudiante.foto;
  }
  return '';
}

const emits = defineEmits(['classDeleted']);
const handleDelete = async (idClase: number) => {
  try {
    await deleteClassById(idClase);
    emits('classDeleted', idClase);
  } catch (error) {
    console.error('Error deleting class:', error);
  }
};
</script>
  
  
  <style lang="scss" scoped>
  .table {
    width: 100%;
    border-collapse: collapse;
    border-spacing: 0;
    
    th, td {
      padding: 8px;
      text-align: left;
      border-bottom: 1px solid #dee2e6;
    }
    
    th {
      font-weight: bold;
    }
  }
  
  .me-2 {
    width: 40px;
    height: 40px;
    object-fit: cover;
    border-radius: 50%;
  }
  
  .asignatura p {
    margin: 0;
  }
  
  .card {
    background-color: white;
    border-radius: 5px;
    box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
    
    .card-body {
      padding: 20px;
    }
    
    .card-title {
      margin-bottom: 15px;
    }
  }

  .name{
    display:flex;
    align-items: center;
    p{
      margin-left:15px;
    }
  }

  .materia-color{
    width:80px;
    text-align: center;
    padding:3px 0px;
    border-radius:15px;
    font-weight: 600;
  }
  .acciones {
    width:100px;
}

.delete-button {
  display: flex;
  align-items: center;
  justify-content: center;
  width: 20px;
  height: 20px;
  background-color: red;
  border: none;
  border-radius: 50%;
  cursor: pointer;
  color: white;
  margin-left:20px;
}

.delete-button i {
  font-size: 14px;
}
  </style>
