import { ref } from "vue";
import { ProfessorFilterType } from "../types/ProfessorFilterType";
import { API } from "../services/API";
import useLogin from "./useLogin";
import { ClassType } from "../types/ClassType";
export default function useProfClassInfo() {
    const {user} = useLogin();
    const { getFilteredProfessor, createClass} = API();
    const filteredProfs = ref<ProfessorFilterType[]>([]);
    
    async function fetchProfs(precioMin:number, precioMax:number, materia:number, tramoHorario:string) {
        filteredProfs.value = [];
        const profFetch = await getFilteredProfessor(precioMin, precioMax, materia, tramoHorario);
        if(profFetch) {
            //por cada profesor:
            profFetch.content.forEach((professor:any) => {
                //creamos la tarjeta prof
                const profCard: ProfessorFilterType = {
                    id:professor.profesor.idProfesor,
                    nombre: professor.nombre,
                    apellidos: professor.apellidos,
                    foto: professor.foto,
                    idMateria: professor.profesor.materia.idMateria,
                    materia: professor.profesor.materia.nombre,
                    materiaColor: professor.profesor.materia.color,
                    descripcion: professor.profesor.descripcion,
                    precio: professor.profesor.precio,
                    idTramos: professor.profesor.tramosHorarios.map((tramo: any) => tramo.idTramoHorario),
                };
                // La metemos en la lista de cargados.
                filteredProfs.value.push(profCard);
            });
        }
    }

    async function createNewClass(date:string, idProf:number | undefined, idTramo:number, idMat:number | undefined) {

        const newClass:ClassType = {
            fecha: date,
            estudiante: {
                idEstudiante: user.value?.estudiante.idEstudiante
            },
            profesor: {
                idProfesor: idProf
            },
            tramoHorario: {
                idTramoHorario: idTramo
            },
            materia: {
                idMateria: idMat
            }

        }
        await createClass(newClass);
    }


    return {
        filteredProfs,
        fetchProfs,
        createNewClass,
    };
}