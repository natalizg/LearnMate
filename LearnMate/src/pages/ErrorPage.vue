<script setup lang="ts">
</script>

<template>
    <div class="full-screen">
      <div class="content">
        <div class="row">
            <div class="left">
                <h1>404</h1>
            </div>
            <div class="right">
                <h2>¡Lo siento!</h2>
                <h3>No puedes acceder a esta página ahora mismo.</h3>
            </div>
        </div>
        <a href="/" class="go-back">Volver al inicio</a>
        <p>Copyright © 2024 All rights reserved.</p>
      </div>
    </div>
  </template>

<style lang="scss" scoped>
.full-screen {
  position: fixed;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background-color: #fbde68;
  display: flex;
  justify-content: center;
  align-items: center;
  z-index: 9999; /* Asegúrate de que este componente esté por encima de los demás */
}

.content {
  text-align: center;
  .row{
    display:flex;
    justify-content: center;
    align-items: center;
    gap: 100px;
    margin-bottom:50px;
    .right{
        h2{
            font-size:40px;
            font-weight: 800;
        }
        h3{
            font-weight: 500;
        }
    }
  }
  a{
    background-color:transparent;
    color: black;
  }
}

h1 {
  font-size: 200px;
  margin: 0;
}
</style>