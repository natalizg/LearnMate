import { RouteRecordRaw, createRouter, createWebHistory } from "vue-router"
import HomePage from "../pages/HomePage.vue"
import TeachersPage from "../pages/TeachersPage.vue"
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
    }
]

const router = createRouter({
    history: createWebHistory(),
    routes,
})

export default router
