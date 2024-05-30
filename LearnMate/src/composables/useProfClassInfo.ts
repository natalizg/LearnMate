import { ref } from "vue";
import { ProfessorFilterType } from "../types/ProfessorFilterType";
import { API } from "../services/API";

export default function useProfClassInfo() {
    
    const { getFilteredProfessor } = API();
    const filteredProfs = ref<ProfessorFilterType[]>([]);

    async function fetchProfs(precioMin:number, precioMax:number, materia:number, tramoHorario:string) {
        filteredProfs.value = [];
        const profFetch = await getFilteredProfessor(precioMin, precioMax, materia, tramoHorario);
        if(profFetch) {
            //por cada profesor:
            console.table(profFetch);
            profFetch.content.forEach((professor:any) => {
                //creamos la tarjeta prof
                const profCard: ProfessorFilterType = {
                    id:professor.profesor.idProfesor,
                    nombre: professor.nombre,
                    apellidos: professor.apellidos,
                    idMateria: professor.profesor.materia.idMateria,
                    materia: professor.profesor.materia.nombre,
                    descripcion: professor.profesor.descripcion,
                    precio: professor.profesor.precio,
                    idTramos: professor.profesor.tramosHorarios.map((tramo: any) => tramo.idTramoHorario),
                };
                // La metemos en la lista de cargados.
                filteredProfs.value.push(profCard);
            });
        }
    }



    return {
        filteredProfs,
        fetchProfs
    };
}