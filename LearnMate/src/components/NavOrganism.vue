<script setup lang="ts">

const navProps = defineProps({
    bg: Boolean,
})

</script>

<template>
    <div :class="{ 'top-nav': true, 'top-nav-white': bg }">
        <nav>
            <div class="left">
                <router-link to="/"><img src="../assets/black-logo.png" alt=""></router-link>
                <div class="nav-bar">
                    <router-link to="/buscar-profesores"><a href=""> Clases Particulares</a></router-link>
                    <router-link to="/serprofesor"><a href=""> Ser Profesor</a></router-link>
                    <router-link to="/contactanos"><a href="">Contáctanos</a></router-link>
                </div>
            </div>
            <div class="right">
                <router-link to="/login"><a href="">Iniciar Sesión</a></router-link>
            </div>
        </nav>
    </div>
    
</template>

<style lang="scss" scoped>

.top-nav{
    background-color: #fbde68;
    padding-bottom:20px;
   nav{
    display:flex;
    justify-content: space-between;
    .left{
        display:flex;
        margin-left:20px;
        margin-top:20px;
        img{
            height:35px;
        }
        .nav-bar{
            margin-top: 10px;
            margin-left:20px;
            a{
                color:black;
                text-decoration: none;
                font-weight:600;
                margin-left:10px;
                margin-right: 10px;
            }
        }
    }
    .right{
        margin-left:20px;
        margin-right:50px;
        margin-top:30px;
        a{
            color:black;
            text-decoration: none;
            font-weight:600;
            margin-left:10px;
            margin-right: 10px;
        }
        
    }
   }
}

.top-nav-white {
  background-color: white;
}
</style>