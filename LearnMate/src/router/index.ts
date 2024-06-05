import { RouteRecordRaw, createRouter, createWebHistory } from "vue-router";
import HomePage from "../pages/HomePage.vue";
import TeachersPage from "../pages/TeachersPage.vue";
import LoginPage from "../pages/LoginPage.vue";
import SignUpAlumnoPage from "../pages/SignUpAlumnoPage.vue";
import SignUpProfesorPage from "../pages/SignUpProfesorPage.vue";
import DashBoardPage from "../pages/DashBoardPage.vue";
import ErrorPage from "../pages/ErrorPage.vue";
import ProfessorRegister from "../pages/ProfessorRegister.vue";
import DashboardInicioPage from "../pages/DashboardInicioPage.vue";
import DashboardCalendarPage from "../pages/DashboardCalendarPage.vue";
import DashboardNotificationsPage from "../pages/DashboardNotificationsPage.vue";
import DashBoardSettingsPage from "../pages/DashBoardSettingsPage.vue";
import ContactUsPage from "../pages/ContactUsPage.vue";
import BeATeacherPage from  "../pages/BeATeacherPage.vue";
import UploadPhotoProfessor from "../pages/UploadPhotoProfessor.vue";
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
    component: DashBoardPage,
    children: [
      {
        path: "",
        name: "Dashboard Inicio",
        component: DashboardInicioPage
      },
      {
        path: "calendario",
        name: "Dashboard Calendario",
        component: DashboardCalendarPage
      },
      {
        path: "notificaciones",
        name: "Dashboard Notificaciones",
        component: DashboardNotificationsPage
      },
      {
        path: "ajustes",
        name: "Dashboard Ajustes",
        component: DashBoardSettingsPage
      }
    ]
  },
  {
    path: "/professor-register",
    name: "Registro Completo Profesor",
    component: ProfessorRegister
  },
  {
    path: "/error",
    name: "Seguridad pag",
    component: ErrorPage
  },
  {
    path: "/contactanos",
    name: "Contáctanos",
    component: ContactUsPage
  },
  {
    path: "/serprofesor",
    name: "Hazte profesor",
    component: BeATeacherPage
  },
  {
    path:"/uploadfoto",
    name: "Sube una foto",
    component:UploadPhotoProfessor
  }
];

const router = createRouter({
  history: createWebHistory(),
  routes,
});

export default router;
