<template>
    <div class="teacher-card">
        <div class="card">
            <div class="photo-container">
                <img src="../assets/user-standar.jpg" alt="">
            </div>
            <div class="info-container">
                <div class="title">
                    <h3>{{ nombre }} {{ apellidos }}</h3>
                    <p>{{ materia }}</p>
                </div>
                <div class="description">
                    <p>{{ descripcion }}</p>
                </div>
            </div>
            <div class="btn-container">
                <div class="precio">
                    <h3>{{ precio }} € la hora</h3>
                    <button @click="openModal()">Reservar clase</button>
                </div>
            </div>
        </div>
    </div>
    <ClassReservation 
    v-if="isOpenClassReserv" 
    @close-modal="closeModal"
    :idProf="id"
    :idMateria="idMateria"
    :idTramos="idTramos"
    :closeModal="closeClassReserv" />
</template>

<script setup lang="ts">
import useUserProgress from '../composables/useUserProgress';
import ClassReservation from './ClassReservation.vue';
import { defineProps } from 'vue';
const {isOpenClassReserv, openClassReserv, closeClassReserv} = useUserProgress();

const props = defineProps({
    id: Number,
    nombre: String,
    apellidos: String,
    materia: String,
    idMateria: Number,
    descripcion: String,
    precio: Number,
    idTramos: Array<Number>
});

function openModal(){
    openClassReserv();
}

function closeModal(){
    closeClassReserv();
}
</script>



<style lang="scss" scoped>
.teacher-card{
    display: flex;
    justify-content: center;
    margin:20px;
    border: 2px solid #dcdce4;
    border-radius:15px;
    min-height:250px;
    width:800px;
    &:hover{
        border: 2px solid black;
    }
    .card{
        padding-top:30px;
        width:100%;
        height: 100%;
        display:flex;
        justify-content:center;
        .photo-container{
            width:20%;
            padding-right:20px;
            img{
                width:150px;
                height:150px;
                object-fit: cover;

            }
        }
        .info-container{
            width: 50%;
            padding-right:20px;
            .title{
                h3{
                    margin:0;
                }
                p{
                    color: #ccc;
                }
            }
        }
        .btn-container{
            width:20%;
            display: flex;
            justify-content: center;
            text-align: center;
            h3{
                margin:0;
                margin-bottom:55px;
            }
            button{
                border: 2px solid black;
                padding: 10px;
                border-radius: 10px;
                background-color: #fbde68;
                color: black;
                cursor: pointer;
                font-size: 18px;
                font-weight: 600;
                &:hover {
                    background-color: #ffba24;
                }
            }
        }
    }
}

</style>
