import { MateriaType } from "./MateriaType"
import { TramosHorariosType } from "./TramosHorariosType"
export type ProfessorType = {
    idProfesor: number,
    descripcion: string, 
    precio: number,
    materia: MateriaType,
    tramosHorarios: TramosHorariosType						
}