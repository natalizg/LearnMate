<script setup lang="ts">
import { defineProps } from 'vue';

const props = defineProps({
  nombreCompleto: String,
  materiaNombre: String,
  materiaColor: String,
});

</script>

<template>
    <div class="teacher-card">
        <div class="card">
            <div class="img">
                <img src="../assets/user-standar.jpg" alt="">
            </div>
            <div class="card-content">
                <div class="name-description">
                    <h2>{{ props.nombreCompleto }}</h2>
                    <div  :style="{ backgroundColor: '#' + materiaColor }" class="profession" >
                        {{ props.materiaNombre }}
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>

<style lang="scss" scoped>
.teacher-card{
    displaY:flex;
    background-color: white;
    border-radius: 5px;
    box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
    height:150px;
    width:300px;
    .card{
        display: flex;
        .img{
            display:flex;
            width:70%;
            justify-content: center;
            align-items: center;
            img{
                max-width: 100%;
                height: 100px;
                width:100px;
                border-radius: 50%;
                object-fit: cover;
                margin:0;
            }
        }
        .card-content{
            margin:0;
            display:flex;
            align-items: center;
            .name-description{
                margin: 0px 15px;
                h2{
                    font-size: 20px;
                }
                p{
                    font-size:17px;
                }
                .profession{
                    background-color:lightcoral;
                    color:white;
                    font-weight: 600;
                    margin: 5px 0px;
                    margin-right:15px;
                    padding: 5px;
                    border-radius: 15px;
                    text-align: center;
                }
            }

        }
    }
}

</style>
