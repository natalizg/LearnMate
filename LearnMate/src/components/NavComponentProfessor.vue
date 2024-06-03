<template>
    <div :class="{ 'top-nav': true, 'top-nav-yellow': navProps.bg }">
        <nav>
            <div class="left">
                <router-link to="/dashboard"><img src="../assets/black-logo.png" alt="Logo"></router-link>
                <div class="nav-bar">
                    <a href="">Contáctanos</a>
                </div>
            </div>
            <div class="right">
                <div class="button-profile">
                    <button @click="openMenu">
                        <img v-if="!profilePic" src="../assets/user-standar.jpg" alt="">
                        <img v-else :src="'data:image/jpeg;base64,' + profilePic" />
                        
                        <i :class="[showDropdown ? 'fa-chevron-up' : 'fa-chevron-down', 'fas', 'fa-xs', 'ml-2']"></i>
                    </button>
                    <ul v-if="showDropdown" id="dropdown-menu" class="dropdown-menu">
                        <li><a href="/dashboard">Perfil</a></li>
                        <li><a @click="logout" href="/">Cerrar Sesión</a></li>
                    </ul>
                </div>
            </div>
        </nav>
    </div>
</template>

<script setup lang="ts">
import { ref, computed, onMounted, onBeforeUnmount } from 'vue';
import useLogin from '../composables/useLogin';

const { user, logout } = useLogin();

const navProps = defineProps({
    bg: Boolean
});

const showDropdown = ref(false);

const openMenu = (event: MouseEvent) => {
    event.stopPropagation();
    showDropdown.value = !showDropdown.value;
};

const handleClickOutside = (event: MouseEvent) => {
    const dropdown = document.getElementById('dropdown-menu');
    if (dropdown && !dropdown.contains(event.target as Node)) {
        showDropdown.value = false;
    }
};

onMounted(() => {
    window.addEventListener('click', handleClickOutside);
});

onBeforeUnmount(() => {
    window.removeEventListener('click', handleClickOutside);
});

const profilePic = computed(() => user.value?.foto || '');

</script>

<style lang="scss" scoped>
.top-nav {
    nav {
        padding-bottom: 5px;
        display: flex;
        justify-content: space-between;
        .left {
            display: flex;
            margin-left: 50px;
            margin-top: 20px;
            img {
                height: 35px;
            }
            .nav-bar {
                margin-top: 10px;
                margin-left: 60px;
                a {
                    color: black;
                    text-decoration: none;
                    font-weight: 600;
                    margin-left: 10px;
                    margin-right: 10px;
                }
            }
        }
        .right {
            display: flex;
            margin-left: 20px;
            margin-right: 50px;
            margin-top: 20px;
            position: relative;
            button {
                display: flex;
                align-items: center;
                background-color: transparent;
                border: none;
                cursor: pointer;
                img {
                    width: 40px;
                    height: 40px;
                    object-fit: cover;
                    border-radius: 50%;
                }
                .fas {
                    margin-left: 10px;
                    font-size: 0.75rem;
                }
            }
            a {
                margin-top: 10px;
                color: black;
                text-decoration: none;
                font-weight: 600;
                margin-left: 10px;
                margin-right: 10px;
            }
        }
        .dropdown-menu {
            display: block;
            position: absolute;
            right: 0;
            background-color: white;
            box-shadow: 0 8px 16px rgba(0, 0, 0, 0.2);
            list-style: none;
            padding: 0;
            margin: 10px 0 0;
            border-radius: 5px;
            z-index: 1;
            li {
                padding: 12px 30px;
                cursor: pointer;
                &:hover {
                    background-color: #ffeda7;
                    border-radius: 5px;
                }
            }
            li a {
                color: black;
                text-decoration: none;
                display: block;
            }
        }
    }
}

.top-nav-yellow {
    background-color: #fbde68;
}
</style>
