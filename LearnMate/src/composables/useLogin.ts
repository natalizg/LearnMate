import { ref } from "vue";
import router from "../router";
import { API } from "../services/API";
import { UserType } from "../types/UserType";
import { PostUser } from "../types/PostUserType/PostUserType";
import { PostStudent } from "../types/PostUserType/PostStudentType";

const { getUserLoginAPI, createUser, createStudent } = API();
const loginError = ref(false);

// Obtener el usuario almacenado en localStorage al cargar la página
const storedUser = JSON.parse(localStorage.getItem("user") || "null");
const user = ref<UserType | null>(storedUser);

const emailProf = ref('');
const passwordProf = ref('');

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

export default function useLogin() {

    async function login(email: string, password: string) {
        try{
            const fetchedUser = await getUserLoginAPI(email, password);
            if (fetchedUser) {
                user.value = fetchedUser;
                localStorage.setItem("user", JSON.stringify(user.value));

                isStudent.value = user.value?.estudiante !== null;
                isProfessor.value = user.value?.profesor !== null;
                isLog.value = true;

                localStorage.setItem("isLog", isLog.value.toString());
                localStorage.setItem("isProfessor", isProfessor.value.toString());
                localStorage.setItem("isStudent", isStudent.value.toString());
                console.log("es profesor: " + isProfessor.value + " es estudiante: " + isStudent.value);
                console.log("ESTE ES MI USUARIO: ");
                console.table(user.value);
                router.push("/dashboard");
            }
        }catch(error){
            loginError.value = true;
        }
        
    }

    function logout() {
        isLog.value = false;
        isProfessor.value = false;
        isStudent.value = false;
        user.value = null;
        localStorage.removeItem("isLog");
        localStorage.removeItem("isProfessor");
        localStorage.removeItem("isStudent");
        localStorage.removeItem("user");
    }

    function sendProfessor(email: string, password: string) {
        emailProf.value = email;
        passwordProf.value = password;
        router.push("/professor-register");
    }

    async function signProfessor(newUser: PostUser) {
        const createdUser = await createUser(newUser, passwordProf.value);

        if (createdUser) {
            user.value = createdUser;
            
            isLog.value = true;
            isProfessor.value = true;
            localStorage.setItem("isLog", isLog.value.toString());
            localStorage.setItem("isProfessor", isProfessor.value.toString());
            localStorage.setItem("user", JSON.stringify(user.value));
            console.log("es profesor: " + isProfessor.value + "es estudiante: " + isStudent.value);
            router.push("/dashboard");
        } else {
            console.error("User creation failed: Invalid user data");
        }
    }

    async function signStudent(newUser: PostStudent, password:string) {
        const createdUser = await createStudent(newUser, password);
        if(createdUser) {
            user.value = createdUser;
            localStorage.setItem("user", JSON.stringify(user.value));

            isLog.value = true;
            isStudent.value = true;
            localStorage.setItem("isLog", isLog.value.toString());
            localStorage.setItem("isStudent", isStudent.value.toString());
            console.log("es profesor: " + isProfessor.value + "es estudiante: " + isStudent.value);
            router.push("/dashboard");
        }else {
            console.error("User creation failed: Invalid user data");
        }
    }

    function routeSecurity() {
        if (!isLog.value) {
            router.push("/error");
        }
    }
    

    return {
        isLog,
        isProfessor,
        isStudent,
        emailProf,
        passwordProf,
        login,
        logout,
        sendProfessor,
        signProfessor,
        signStudent,
        routeSecurity,
        user,
        loginError
    };
}
