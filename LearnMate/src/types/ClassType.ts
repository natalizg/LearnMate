export type ClassType = {
    fecha: string,
    estudiante: {
        idEstudiante: number | undefined ;
    },
    profesor: {
        idProfesor: number | undefined;
    },
    tramoHorario: {
        idTramoHorario: number | undefined;
    },
    materia: {
        idMateria: number | undefined;
    }
}