<script setup lang="ts">
import useLogin from '../composables/useLogin';
const { routeSecurity } = useLogin();
routeSecurity();
</script>

<template>
    <div class="panel">
        <div class="sidebar">
            <div class="logo">
                <h2>Mi Aplicación</h2>
            </div>
            <ul>
                <li><i class="fas fa-home"></i> Inicio</li>
                <li><i class="fas fa-calendar-alt"></i> Calendario</li>
                <li><i class="fas fa-bell"></i> Notificaciones</li>
            </ul>
        </div>
        <div class="centro">
            <h1>Bienvenido estudiante X</h1>
        </div>
    </div>
</template>

<style lang="scss" scoped>
@import url('https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css');

.panel {
    display: flex;
    .sidebar {
        position: relative;
        top: 0;
        left: 0;
        width: 250px;
        height: 100vh;
        background-color: #fbde68;
        box-shadow: 2px 0 5px rgba(0,0,0,0.1);
        z-index: 1000;
        padding: 20px;
        display: flex;
        flex-direction: column;
        align-items: center;

        .logo {
            margin-bottom: 20px;
            h2 {
                margin: 0;
                font-size: 1.5em;
                color: #333;
            }
        }

        ul {
            list-style-type: none;
            padding: 0;
            width: 100%;

            li {
                width: 100%;
                margin: 10px 0;
                padding: 10px;
                display: flex;
                align-items: center;
                font-size: 1.2em;
                color: #333;
                cursor: pointer;
                border-radius: 5px;
                transition: background 0.3s ease;

                i {
                    margin-right: 10px;
                }

                &:hover {
                    background-color: #f0c040;
                }
            }
        }
    }
    .centro {
        flex-grow: 1;
        padding: 20px;
    }
}
</style>
