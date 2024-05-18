import { RouteRecordRaw, createRouter, createWebHistory } from "vue-router"
import HomePage from "../pages/HomePage.vue"
import TeachersPage from "../pages/TeachersPage.vue"
import LoginPage from "../pages/LoginPage.vue"
import SignUpAlumnoPage from "../pages/SignUpAlumnoPage.vue"
import SignUpProfesorPage from "../pages/SignUpProfesorPage.vue"
import DashBoardPage from "../pages/DashBoardPage.vue"
import ErrorPage from "../pages/ErrorPage.vue"
const routes: RouteRecordRaw[] = [
    {
        path: "/",
        name: "Home",
        component: HomePage,
    },
    {
        path: "/buscar-profesores",
        name: "Buscar Profesores",
        component: TeachersPage
    },
    {
        path: "/login",
        name: "Inicio sesión",
        component: LoginPage
    },
    {
        path: "/signup-student",
        name: "Registro alumno",
        component: SignUpAlumnoPage
    },
    {
        path: "/signup-professor",
        name: "Registro profesor",
        component: SignUpProfesorPage
    },
    {
        path: "/dashboard",
        name: "Panel de control",
        component: DashBoardPage
    },
    {
        path: "/error",
        name: "Seguridad pag",
        component: ErrorPage
    },


]

const router = createRouter({
    history: createWebHistory(),
    routes,
})

export default router
