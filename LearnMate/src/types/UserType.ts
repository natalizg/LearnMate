import { ProfessorType } from "./ProfessorType"
import { StudentType } from "./StudentType"

export type UserType = {
    idUsuario: number,
    email: string,
    nombre: string,
    apellidos: string,
    telefono: number,
    foto: string,
    profesor: ProfessorType,
    estudiante: StudentType		
}