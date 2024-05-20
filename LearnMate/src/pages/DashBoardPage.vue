<template>
    <div class="panel">
      <!--SEPARAR A OTRO COMPONENTE-->
      <div class="sidebar">
        <ul>
          <li>
            <div class="opcion">
              <p>Inicio</p>
              <i class="fas fa-home"></i>
            </div>
          </li>
          <li>
            <div class="opcion">
              <p>Calendario</p>
              <i class="fas fa-calendar-alt"></i>
            </div>
          </li>
          <li>
            <div class="opcion">
              <p>Notificaciones</p>
              <i class="fas fa-bell"></i>
            </div>
          </li>
          <li>
            <div class="opcion">
              <p>Ajustes</p>
              <i class="fas fa-cog"></i>
            </div>
          </li>
        </ul>
      </div>
      <!--SEPARAR A OTRO COMPONENTE-->
      <div class="centro">
        <div class="component-name">
            <span><i class="fa fa-home"></i></span>
            <h3>Dashboard</h3>
        </div>
        <div class="row-prof-alum">
          <h4>Tus profesores/Alumnos*</h4>

        </div>
        <div class="row-clases">
          <TableClassComponent/>
        </div>
      </div>
    </div>
  </template>
  
  <script setup lang="ts">
  import useLogin from '../composables/useLogin';
  import TableClassComponent from '../components/TableClassComponent.vue';
  const { routeSecurity } = useLogin();
  routeSecurity();
  </script>
  
  <style lang="scss" scoped>
  @import url('https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css');
  .panel {
    display: flex;
  
    .sidebar {
      top: 0;
      left: 0;
      width: 250px;
      height: 100vh;
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
            margin:auto;
            p {
              margin: 0;
              width: 70%;
            }
            i {
              width: 30%;
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
      }
    }
  
    .centro {
      flex-grow: 1;
      padding: 20px;
      background-color: #f2edf3;
      .component-name{
        display:flex;
        align-items: center;
        margin-top: 10px;
        h3{
            font-size: 20px;
            margin-left:15px;
            font-weight: 600;
        }
        span{
            display:flex;
            justify-content: center;
            align-items: center;
            width: 36px;
            height: 36px;
            border-radius: 4px;
            text-align: center;
            box-shadow: 0px 3px 8.3px 0.7px #b7d4e2;
            background: linear-gradient(to right, #c6edff, #54c4f6) !important;
            i{
                color:white
            }
        }
      }
      .row-prof-alum{
        margin-top: 50px;
      }
      .row-clases{
        margin-top: 50px;
      }
    }
  }
  </style>
  