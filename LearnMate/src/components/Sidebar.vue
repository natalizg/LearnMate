<!-- Sidebar.vue -->
<template>
  <div class="sidebar">
    <ul>
      <li class="perfil-container">
        <div class="perfil">
          <div class="img-profile">
            <img src="../assets/user-standar.jpg" alt="">
          </div>
          <div class="perfil-data">
            <h3>{{user?.nombre}}</h3>
            <p>{{rol}}</p>
          </div>
        </div>
      </li>
      <li>
        <router-link to="/dashboard" class="opcion">
          <p>Inicio</p>
          <i class="fas fa-home"></i>
        </router-link>
      </li>
      <li>
        <router-link to="/dashboard/calendario" class="opcion">
          <p>Calendario</p>
          <i class="fas fa-calendar-alt"></i>
        </router-link>
      </li>
      <li>
        <router-link to="/dashboard/notificaciones" class="opcion">
          <p>Notificaciones</p>
          <i class="fas fa-bell"></i>
        </router-link>
      </li>
      <li>
        <router-link to="/dashboard/ajustes" class="opcion">
          <p>Ajustes</p>
          <i class="fas fa-cog"></i>
        </router-link>
      </li>
    </ul>
  </div>
</template>

<script setup lang="ts">
import useLogin from '../composables/useLogin';
import { UserType } from '../types/UserType';
import { ref } from 'vue';
const { isStudent, isProfessor } = useLogin();
const rol = ref('');
if(isProfessor.value){
  rol.value = 'Profesor'
}
if(isStudent.value){
  rol.value = 'Estudiante'
}

const user = ref<UserType | null>(null);

const storedUser = localStorage.getItem('user');

if (storedUser) {
  user.value = JSON.parse(storedUser) as UserType;
}
</script>

<style scoped>
@import url('https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css');

.sidebar {
  top: 0;
  left: 0;
  width: 250px;
  height: auto;
  background-color: white;
  box-shadow: 0px 2px 5px rgba(0, 0, 0, 0.1);

  ul {
    list-style-type: none;
    padding: 0;
    width: 100%;
    li {
      padding: 15px 15px 15px 30px;
      display: flex;
      align-items: center;
      font-size: 1.2em;
      color: #333;
      cursor: pointer;
      transition: background 0.3s ease;
      font-size: 16px;

      .opcion {
        width: 100%;
        display: flex;
        justify-content: center;
        align-items: center;
        margin: auto;
        p {
          margin: 0;
          width: 150px;
        }
        i {
          width: 40px;
          color: #beabc2;
        }
      }
      &:hover {
        background-color: #c8eeff;
        color: #54c4f6;
        .opcion i {
          color: #54c4f6; /* Cambia el color del icono al azul */
        }
      }
    }
    .perfil-container {
      .perfil {
        display: flex;
        img {
          width: 80px;
          height: 80px;
          object-fit: cover;
          border-radius: 50%;
        }
        .perfil-data {
          margin-left: 10px;
          margin-top: 15px;
          h3 {
            margin: 0;
            font-weight: 600;
          }
          p {
            margin: 0;
            color: lightgrey;
            font-size: 14px;
          }
        }
      }
      &:hover {
        background-color: white;
        color: black;
      }
    }
  }
}
a { text-decoration: none; color:inherit; }
</style>
