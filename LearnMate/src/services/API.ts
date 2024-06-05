import axios from "axios";
import { PostUser} from "../types/PostUserType/PostUserType";
import { PostStudent } from "../types/PostUserType/PostStudentType";
import { ClassType } from "../types/ClassType";

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
        try{
            const response = await axios.post(`${API_URL}/usuarios/createUsuario?password=${password}`, user)
            return response.data
        }catch(error){
            console.log("pilla el error");
            throw error;
        }
    }

    const getFilteredProfessor = async (precioMin:number, precioMax:number, materia:number, disponibilidad:string) => {
        const response = await axios.get(`${API_URL}/usuarios/getAllProfesores?precioMin=${precioMin}&precioMax=${precioMax}&idMateria=${materia}&idsTramosHorarios=${disponibilidad}`);
        return response.data;
    }

    const createClass = async (newClass:ClassType) => {
        const response = await axios.post(`${API_URL}/usuarios/createClase`, newClass);
        return response.data
    }

    const getAllClassByIdEst = async (id:number) => {
        const response = await axios.get(`${API_URL}/usuarios/getAllClasesByIdProfesorOrIdEstudiante?idEstudiante=${id}`)
        return response.data;
    }
    const getAllClassByIdProf = async (id:number) => {
        const response = await axios.get(`${API_URL}/usuarios/getAllClasesByIdProfesorOrIdEstudiante?idProfesor=${id}`)
        return response.data;
    }

    const getAllEstByIdProf = async (id:number) => {
        const response = await axios.get(`${API_URL}/usuarios/getAllEstudiantesByidProfesor?idProfesor=${id}`)
        return response.data;
    }

    const getAllProfByIdEst = async (id:number) => {
        const response = await axios.get(`${API_URL}/usuarios/getAllProfesoresByIdEstudiante?idEstudiante=${id}`)
        return response.data;
    }

    const deleteClassById = async (id:number) => {
        const response = await axios.delete(`${API_URL}/usuarios/deleteClase?idClase=${id}`)
        return response.data;
    }

    return{
        getUserLoginAPI,
        createUser,
        createStudent,
        getFilteredProfessor,
        createClass,
        getAllClassByIdEst,
        getAllClassByIdProf,
        getAllEstByIdProf,
        getAllProfByIdEst,
        deleteClassById
    }
}