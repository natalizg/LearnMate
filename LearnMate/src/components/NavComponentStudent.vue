<script setup lang="ts">
import useLogin from '../composables/useLogin';
const { logout } = useLogin();
import { ref } from 'vue';
import { FontAwesomeIcon } from '@fortawesome/vue-fontawesome';
import { faSignOutAlt, faChevronDown, faUser } from '@fortawesome/free-solid-svg-icons';

const isMenuOpen = ref(false);

const toggleMenu = () => {
  isMenuOpen.value = !isMenuOpen.value;
}

const menuItems = [
  { text: 'Perfil', icon: 'user', action: () => console.log('Go to Profile') },
  { text: 'Cerrar sesión', icon: 'sign-out-alt', action: logout }
];
</script>

<template>
    <div class="top-nav">
        <nav>
            <div class="left">
                <router-link to="/dashboard"><img src="../assets/black-logo.png" alt=""></router-link>
            </div>
            <div class="right">
                <router-link to="/buscar-profesores"><a href="">Buscar Profesores</a></router-link>
                <button><img src="../assets/logout.png" alt=""></button>
            </div>
        </nav>
    </div>
    
</template>

<style lang="scss" scoped>

.top-nav{
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
            cursor:pointer;
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