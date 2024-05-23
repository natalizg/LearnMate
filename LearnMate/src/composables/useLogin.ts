import { ref } from "vue";
import router from "../router";

// Obtener los valores almacenados en localStorage al cargar la página
const isLog = ref(localStorage.getItem("isLog") === "true" || false);
const isProfessor = ref(localStorage.getItem("isProfessor") === "true" || false);
const isStudent = ref(localStorage.getItem("isStudent") === "true" || false);

// Verificar si los valores en localStorage existen
if (localStorage.getItem("isLog") === null) {
    localStorage.setItem("isLog", "false");
}

if (localStorage.getItem("isProfessor") === null) {
    localStorage.setItem("isProfessor", "false");
}

if (localStorage.getItem("isStudent") === null) {
    localStorage.setItem("isStudent", "false");
}

export default function useLogin(){

    function login(email:String, password:String){
        // Lógica de inicio de sesión...
        //por ahora vamos a hacer que siempre iniciamos de estudiante
        isStudent.value = true;
        isLog.value = true;
        // Guardar los valores actualizados en localStorage
        localStorage.setItem("isLog", isLog.value.toString());
        localStorage.setItem("isProfessor", isProfessor.value.toString());
        localStorage.setItem("isStudent", isStudent.value.toString());
        router.push("/dashboard");
    }

    function logout(){
        // Limpiar las variables y el localStorage al cerrar sesión
        isLog.value = false;
        isProfessor.value = false;
        isStudent.value = false;
        localStorage.removeItem("isLog");
        localStorage.removeItem("isProfessor");
        localStorage.removeItem("isStudent");
        // Redireccionar al componente homepage o refrescar la página
    }

    function signProfessor(email:String, password:String){
        // Lógica para registrar un profesor...
        //isLog.value = true;
        //isProfessor.value = true;
        // Guardar los valores actualizados en localStorage
        //localStorage.setItem("isLog", isLog.value.toString());
        //localStorage.setItem("isProfessor", isProfessor.value.toString());

        router.push("/professor-register");
    }
    
    function signStudent(email:String, password:String){
        // Lógica para registrar un estudiante...
        isLog.value = true;
        isStudent.value = true;
        // Guardar los valores actualizados en localStorage
        localStorage.setItem("isLog", isLog.value.toString());
        localStorage.setItem("isStudent", isStudent.value.toString());
    }

    function routeSecurity(){
        if(!isLog.value){
            router.push("/error");
        }
    }

    return {
        isLog,
        isProfessor,
        isStudent,
        login,
        logout,
        signProfessor,
        signStudent,
        routeSecurity
    };
}
