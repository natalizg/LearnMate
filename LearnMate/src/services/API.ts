import axios from "axios";

const API_URL = 'https://localhost:8443/learnmateback';

export function API(){
    const getUserLoginAPI = async (email:string, password:string) => {
        const response = await axios.get(`${API_URL}/usuarios/loginUser?email=${email}&password=${password}`)
        return response.data;
    }

    return{
        getUserLoginAPI
    }
}