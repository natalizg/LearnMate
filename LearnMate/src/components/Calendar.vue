<template>
    <div class="calendar-container">
      <FullCalendar :options="calendarOptions" class="calendar" />
    </div>
  </template>
  
  <script lang="ts">
  import { defineComponent, ref } from 'vue';
  import FullCalendar from '@fullcalendar/vue3';
  import dayGridPlugin from '@fullcalendar/daygrid';
  
  export default defineComponent({
    name: 'Calendar',
    components: { FullCalendar },
    setup() {
      const calendarOptions = ref({
        plugins: [dayGridPlugin],
        initialView: 'dayGridMonth',
        initialDate: '2024-05-01',
        selectable: true, // Permitir la selección de fechas
        selectMirror: true, // Muestra un espejo de la selección
        select: (arg: any) => { // Función para manejar la selección
          console.log('Fecha seleccionada:', arg.startStr, 'hasta', arg.endStr);
        },
        headerToolbar: { // Barra de herramientas del encabezado
          left: 'prev,next today',
          center: 'title',
          right: 'dayGridMonth,timeGridWeek,timeGridDay',
        },
        events: [ // Eventos
          { title: 'Alemán', start: '2024-05-01T10:00:00' },
          { title: 'Francés', start: '2024-05-15T14:00:00' },
          { title: 'Inglés', start: '2024-05-28T16:00:00' }
        ],
        locale: 'es', // Establecer el idioma en español
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
  