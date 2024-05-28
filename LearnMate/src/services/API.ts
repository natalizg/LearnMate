import axios from "axios";
import { PostUser} from "../types/PostUserType/PostUserType";
import { PostStudent } from "../types/PostUserType/PostStudentType";

const API_URL = 'https://localhost:8443/learnmateback';

export function API(){
    const getUserLoginAPI = async (email:string, password:string) => {
        const response = await axios.get(`${API_URL}/usuarios/loginUsuario?email=${email}&password=${password}`)
        return response.data;
    }

    const createUser = async( user:PostUser, password:string) => {
        const response = await axios.post(`${API_URL}/usuarios/createUsuario?password=${password}`, user)
        return response.data
    }

    const createStudent = async( user:PostStudent, password:string) => {
        const response = await axios.post(`${API_URL}/usuarios/createUsuario?password=${password}`, user)
        return response.data
    }

    return{
        getUserLoginAPI,
        createUser,
        createStudent
    }
}