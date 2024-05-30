export interface ProfessorData {
    idUsuario: number;
    email: string;
    nombre: string;
    apellidos: string;
    telefono: number;
    profesor: {
        idProfesor: number;
        descripcion: string;
        precio: number;
        materia: {
            idMateria: number;
            nombre: string;
            color: string;
        };
        tramosHorarios: {
            idTramoHorario: number;
            descripcion: string;
        }[];
    };
    estudiante: null;
}