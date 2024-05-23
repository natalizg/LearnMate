<!-- DashboardInicioPage.vue -->
<template>
    <div class="centro">
      <div class="component-name">
        <span><i class="fa fa-home"></i></span>
        <h3>Dashboard</h3>
      </div>
      <div class="row-prof-alum">
        <h3>Tus profesores/Alumnos*</h3>
        <div class="prof-alum">
          <StudentProfessorCard />
          <StudentProfessorCard />
          <StudentProfessorCard />
          <StudentProfessorCard />
        </div>
      </div>
      <div class="row-clases">
        <TableClassComponent />
      </div>
    </div>
  </template>
  
  <script setup lang="ts">
  import TableClassComponent from '../components/TableClassComponent.vue';
  import StudentProfessorCard from '../components/StudentProfessorCard.vue';
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
  