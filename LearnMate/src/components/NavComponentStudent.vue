<script setup lang="ts">
</script>

<template>
    <div class="top-nav">
        <nav>
            <div class="left">
                <router-link to="/"><img src="../assets/black-logo.png" alt=""></router-link>
            </div>
            <div class="right">
                <router-link to="/buscar-profesores"><a href="">Buscar Profesores</a></router-link>
                <button><img src="../assets/logout.png" alt=""></button>
            </div>
        </nav>
        <hr/>
    </div>
    
</template>

<style lang="scss" scoped>

.top-nav{
    hr{
        border:1px solid #f5f5f8;
        margin:0;
    }
   nav{
    padding-bottom:5px;
    display:flex;
    justify-content: space-between;
    .left{
        display:flex;
        margin-left:20px;
        margin-top:20px;
        img{
            height:35px;
        }
        
    }
    .right{
        margin-left:20px;
        margin-right:50px;
        margin-top:30px;
        button{
            background-color: transparent;
            border:none;
            img{
                width:30px;
            }
        }
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