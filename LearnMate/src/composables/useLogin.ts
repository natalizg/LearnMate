import { ref } from "vue";
import router from "../router";
const isLog = ref(true);
const isProfessor = ref(false);
const isStudent = ref(true);
export default function useLogin(){

    function login(email:String, password:String){
        //lógica que conecta con la base de datos para ver si está conectado.
        //si entra por ejemplo un profe, guardo el correo para traerme luego todos los datos ya que es la pk
        //por ahora vamos a poner que es student y que todo sale bien:
        isStudent.value = true;
        isLog.value = true;
        router.push("/dashboard");
    }

    function logout(){
        isLog.value = false;
        isProfessor.value = false;
        isStudent.value = false;
        router.push("/");
        //llevar al componente homepage o puedes cambiar la logica y simplemente refrescar la pag
    }

    function signProfessor(email:String, password:String){
        //logica que mete en la base de datos el profesor
        isLog.value = true;
        isProfessor.value = true;
    }
    function signStudent(email:String, password:String){
        //logica que mete en la base de datos el estudiante
        isLog.value = true;
        isStudent.value = true;
    }

    function routeSecurity(){
        if(!isLog.value){
            router.push("/error");
        }
    }
    return{
        isLog,
        isProfessor,
        isStudent,
        login,
        logout,
        signProfessor,
        signStudent,
        routeSecurity
    }
}