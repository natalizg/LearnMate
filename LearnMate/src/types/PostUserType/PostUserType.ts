
type PostMateria = {
    idMateria: number;
}
  
type PostTramoHorario = {
    idTramoHorario: number;
}
  
type PostProfesor = {
    descripcion: string;
    precio: number;
    materia: PostMateria;
    tramosHorarios: PostTramoHorario[];
}
  
export type PostUser = {
    email: string;
    nombre: string;
    apellidos: string;
    telefono: number;
    profesor: PostProfesor;
    estudiante: null;
}