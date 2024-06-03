<script setup lang="ts">
import SliderComponent from '../components/SliderComponent.vue';
</script>

<template>
  <div class="ad">
    <div class="ad-interior">
        <div class="left">
            <h1>Aprende con los mejores profesores de idiomas.</h1>
            <h2>Aprende <span>Hoy</span>, lidera <span>Mañana</span></h2>
            <div class="go-to-login"><a href="/login">Empezar →</a></div>
        </div>
        <div class="right">
            <img class="first" src="../assets/ad-final.png" height="450px" alt="girl">
        </div>
    </div>
    </div>
    <div class="page-body">
        <div class="remarcable-data">
            <div class="column">
                <h2>Más de 3 200</h2>
                <p>profesores experimentados</p>
            </div>
            <div class="column">
                <h2>Más de 30 000</h2>
                <p>opiniones de profesores de 5</p>
                <p>estrellas</p>
            </div>
            <div class="column">
                <h2>Más de 10</h2>
                <p>materias impartidas</p>
            </div>
            <div class="column">
                <h2>Más de 180</h2>
                <p>nacionalidades de los</p>
                <p>profesores</p>
            </div>
        </div>
        <div class="how-it-works">
            <h2>¿Cómo funciona LearMate?</h2>
            <div class="row">
                <div class="box">
                    <div class="box-interior">
                        <img src="../assets/asian-woman.jpg" alt="">
                        <h3>Encuentra a tu profesor</h3>
                        <p>Elige el profesor ideal para ti</p>
                    </div>
                </div>
                <div class="box">
                    <div class="box-interior">
                        <img src="../assets/laptop-woman.jpg" alt="">
                        <h3>Toma clases personalizadas</h3>
                        <p>Aprende con un profesor que se enfoque en tus metas</p>
                    </div>
                </div>
                <div class="box">
                    <div class="box-interior">
                        <img src="../assets/laptop-man-woman.jpg" alt="">
                        <h3>Habla con confianza</h3>
                        <p>Comienza a hablar desde el primer día y observa tu rápido progreso</p>
                    </div>
                </div>
            </div>
        </div>
        <SliderComponent/>
    </div>
</template>


<style lang="scss" scoped>
    .ad{
        background-color: #fbde68;
        .ad-interior{
            display:flex;
            margin:auto;
            justify-content: center;
            .left{
                width:560px;
                margin-top:60px;
                margin-right:3%;
                h1{
                    font-size: 64px;
                    font-weight: 650;
                    letter-spacing: -0.32px;
                    line-height: 68px;
                    font-family: 'Platform', 'Platform-fallback', 'Platform-fallback-android', 'Noto Sans',
                    'NotoSans-fallback', 'NotoSans-fallback-android', sans-serif;
                    margin: 0;
                    padding: 0;
                    letter-spacing: -2px;
                }
                h2{
                    font-size: 25px;
                    span{
                        background-color: white;
                        padding: 3px 7px;
                        border: 1px solid black;
                        border-radius:10px;
                    }
                }
                .go-to-login{
                    margin-top:50px;
                    a{
                        background-color: black;
                        color:white;
                        border:none;
                        border-radius: 10px;
                        padding: 20px 130px;
                        font-size: 18px;
                        font-weight: 600;
                        cursor:pointer;
                        margin-bottom:40px;
                        color:white;
                        text-decoration: none;
                        width:100%;
                    }
                }
            }
            .right{
                display: flex;
                margin-top:30px;
                margin-left:5%;
            }
        }
    }
    .remarcable-data{
        display:flex;
        justify-content: center;
        margin-top: 50px;
        h2{
            font-size:35px;
            margin-bottom:0;
        }
        .column{
            padding: 0 30px;
            p{
                margin:0;
                color: grey;
                font-weight: 600;
                text-align: center
            }
        }
    }
    .how-it-works{
        margin-top:100px;
        h2{
            text-align:center;
            font-size:50px;
        }
        .row{
            display:flex;
            justify-content:center;
            .box{
                width:396px;
                height: 378px;
                border:2px solid #dcdce4;
                border-radius: 10px;
                margin: 0 30px;
                .box-interior{
                    margin:auto;
                    margin-top:25px;
                    padding:auto;
                    height:330px;
                    width:348px;
                    h3{
                        font-size:x-large;
                        margin-bottom:0;
                    }
                    img{
                        height:218px;
                        width: 348px;
                        object-fit: cover;
                    }
                }
            }
        }
    }
    .slider{
        height:400px;
    }
</style>
