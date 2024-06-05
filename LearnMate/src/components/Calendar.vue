<template>
  <div class="calendar-container">
    <FullCalendar :options="calendarOptions" class="calendar" />
  </div>
</template>

<script lang="ts">
import { defineComponent, ref, onMounted, watch } from 'vue';
import FullCalendar from '@fullcalendar/vue3';
import dayGridPlugin from '@fullcalendar/daygrid';
import useLogin from '../composables/useLogin';
import { API } from '../services/API';
import { UserType } from '../types/UserType';

export default defineComponent({
  name: 'Calendar',
  components: { FullCalendar },
  setup() {
    const { isStudent, isProfessor } = useLogin();
    const { getAllClassByIdEst, getAllClassByIdProf } = API();
    const user = ref<UserType | null>(null);
    const idRol = ref<number>(-1);
    const userClasses = ref<any>([]);
    const storedUser = localStorage.getItem('user');

    const fetchData = async () => {
      if (storedUser) {
        user.value = JSON.parse(storedUser) as UserType;
        if (isStudent.value) {
          idRol.value = user.value.estudiante.idEstudiante;
          userClasses.value = await getAllClassByIdEst(idRol.value);
        } else if (isProfessor.value) {
          idRol.value = user.value.profesor.idProfesor;
          userClasses.value = await getAllClassByIdProf(idRol.value);
        }
      }
    };

    onMounted(() => {
      fetchData();
    });

    const calendarOptions = ref({
      plugins: [dayGridPlugin],
      initialView: 'dayGridMonth',
      initialDate: '2024-06-01',
      selectable: true,
      selectMirror: true,
      select: (arg: any) => {
        console.log('Fecha seleccionada:', arg.startStr, 'hasta', arg.endStr);
      },
      headerToolbar: {
        left: 'prev,next today',
        center: 'title',
        right: 'dayGridMonth,timeGridWeek,timeGridDay',
      },
      events: [],
      locale: 'es',
    });

    watch(userClasses, (newClasses) => {
      calendarOptions.value.events = newClasses.map((clase: any) => ({
        title: clase.materia.nombre,
        start: clase.fecha,
      }));
    });

    return { calendarOptions };
  },
});
</script>

<style scoped>
.calendar-container {
  width: 85%;
  margin: auto;
  margin-top: 20px;
  margin-bottom: 100px;
}

.calendar {
  width: 100%;
}
</style>
