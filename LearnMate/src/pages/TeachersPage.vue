<script setup lang="ts">
import { ref } from 'vue';
import FilterComponent from '../components/FilterComponent.vue';
import TeacherCardComponent from '../components/TeacherCardComponent.vue';
const p = document.getElementById('desplegar');
const pDesplegado = ref(false)

const showHideP = () => {
    pDesplegado.value= !pDesplegado.value
}
</script>

<template>
  <div class="ad">
        <div class="left">
            <h1>Clases particulares de inglés online: encuentra tu profesor ahora</h1>
            <div class="p-desplegable">
                <p>LearMate es la plataforma online líder de aprendizaje de idiomas donde podrás  
                    <a href="#" id="leerMas" v-show="!pDesplegado" @click="showHideP">... Leer Más</a>
                </p>
            </div>
            <p id="desplegar" v-show="pDesplegado"> 
                encontrar un profesor que hable tu idioma y que pueda enseñarte chino en clases
                particulares. En Preply hay una gran variedad profesores disponibles especializados
                en diferentes niveles y con diferentes precios y horarios, para que puedas elegir en función de tu nivel
                y de tu presupuesto. Programa una clase online a cualquier hora del día con profesores de todo el mundo. 
                ¿A qué estás esperando? ¡Entra en Preply y mejora tu chino! ¿Buscas algo más? Explora las cursos de chino
                o descubre otros recursos para aprender chino online.
            </p>
            <a href="#" id="ocultar" v-show="pDesplegado" @click="showHideP">Ocultar</a>
        </div>
        <div class="right">
            <img class="first" src="../assets/videocall2.png" height="450px" alt="VideoLlamada">
        </div>
    </div>
    <div class="filter-container">
    </div>
    <div class="teachers">
        <TeacherCardComponent/>
        <TeacherCardComponent/>
        <TeacherCardComponent/>
        <TeacherCardComponent/>
    </div>
</template>


<style lang="scss" scoped>
    .ad{
        height: 500px;
        display:flex;
        .left{
            margin-top:150px;
            margin-left:15%;
            margin-right:100px;
            h1{
                font-size: 64px;
                font-weight: 650;
                letter-spacing: -0.32px;
                line-height: 68px;
                font-family: 'Platform', 'Platform-fallback', 'Platform-fallback-android', 'Noto Sans',
                'NotoSans-fallback', 'NotoSans-fallback-android', sans-serif;
                margin: 0;
                padding: 0;
                letter-spacing: -4px;
            }
            h3{
                margin-right:80px;
            }
            .p-desplegable{
                display:flex;
                p{
                    margin-bottom:0px;
                }
            }
            #desplegar{
                margin-top:0px;
            }
            a{
                color:black;
            }
            a:hover{
                color:#ff7aac;
            }
        }
        .right{
            margin-top:100px;
            margin-left:100px;
            margin-right:350px;
        }
    }
    .filter-container{
        margin-top:300px;
        margin-bottom:100px;
    }
    .teachers{
        margin-top:100px;
        display:flex;
        justify-content: center;
    }
</style>
