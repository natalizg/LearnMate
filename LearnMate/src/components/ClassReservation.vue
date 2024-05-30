<template>
    <div class="modal-overlay">
        <div class="modal" id="modal">
            <div class="modal-content">
                <div class="class-reservation">
                    <div class="top-actions">
                        <!-- Icono de una cruz para cerrar -->
                        <button @click="$emit('close-modal');" class="close-button">
                            <i class="fas fa-times"></i>
                        </button>
                    </div>
                    <div class="title">
                        <h2>Reserva tu clase</h2>
                    </div>
                    <div class="form-container">
                        <form @submit.prevent="submitForm">
                            <div class="form-group">
                                <div class="select-box" @click="toggleMenu" :class="{ open: isOpen }">
                                    <div class="select-box__current">
                                        <span>Tramo Horario: {{ getOptionLabel(selectedOption) }}</span>
                                        <img class="select-box__icon" src="http://cdn.onlinewebfonts.com/svg/img_295694.svg" alt="Arrow Icon">
                                    </div>
                                    <ul class="select-box__list" v-if="isOpen">
                                        <li v-for="(option, index) in options" :key="index" @click.stop="selectOption(option.id)">
                                            {{ option.name }}
                                        </li>
                                    </ul>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="datepicker">Fecha de la clase:</label>
                                <input type="date" id="datepicker" v-model="selectedDate" :disabled="isDateDisabled(selectedDate)">
                            </div>
                            <button type="submit">Enviar</button>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>

<script setup lang="ts">
import { ref } from 'vue';

interface Option {
    id: number;
    name: string;
}

const selectedOption = ref<number>(1);
const isOpen = ref<boolean>(false);
const selectedDate = ref<string>('');

const options: Option[] = [
    { id: 1, name: '9-12' },
    { id: 2, name: '12-15' },
    { id: 3, name: '15-18' },
    { id: 4, name: '18-21' },
    { id: 5, name: '21-24' },
];

const toggleMenu = () => {
    isOpen.value = !isOpen.value;
};

const selectOption = (optionId: number) => {
    selectedOption.value = optionId;
    isOpen.value = false;
};

const getOptionLabel = (id: number) => {
    return options.find(option => option.id === id)?.name || '';
};

const isDateDisabled = (date: string) => {
    // Aquí puedes implementar la lógica para deshabilitar fechas específicas
    const disabledDates = ['2024-06-01', '2024-06-15', '2024-06-30'];
    return disabledDates.includes(date);
};

const submitForm = () => {
    // Aquí puedes manejar el envío del formulario
    console.log('Tramo horario seleccionado:', selectedOption.value);
    console.log('Fecha seleccionada:', selectedDate.value);
};
</script>

<style lang="scss" scoped>
.modal-overlay{
    position:fixed;
    top:0;
    left:0;
    width:100%;
    height: 100%;
    background-color:rgba(110, 114, 121, 0.6);
    display:flex;
    justify-content: center;
    align-items: center;
}

.modal{
    background-color: white;
    border-radius: 6px;
    border:none;
    padding:1rem;
    width:30%;
    max-width: 80rem;
    animation: fade-slide-up 0.3s ease-out forwards;
}

.modal::backdrop {
    background-color: rgba(0,0,0,0.55);
}

@keyframes fade-slide-up {
    from {
        opacity:0;
        transform:translateY(4rem);
    }
    to {
        opacity:1;
        transform:translateY(0);
    }
}

.form-container {
  max-width: 400px;
  margin: 0 auto;
  padding: 20px;
}

.form-group {
  margin-bottom: 20px;
}

label {
  display: block;
  margin-bottom: 5px;
}

input[type="date"],
select {
  width: 300px;
  padding: 8px;
  font-size: 16px;
}

button{
    border: 2px solid black;
    padding: 10px;
    border-radius: 10px;
    background-color: #fbde68;
    color: black;
    cursor: pointer;
    font-size: 18px;
    font-weight: 600;
}

.close-button {
    position: absolute;
    top: 10px;
    right: 10px;
    background: none;
    border: none;
    cursor: pointer;
}

//ESTILOS FAKE INPUT
.select-box {
  position: relative;
  display: block;
  width: 100%;
  max-height: 52px;
  margin: 0 auto;
  cursor: pointer;
  border: 2px solid #dcdce4;
  border-radius: 5px;
  font-size: 18px;
  color: #60666d;
  background-color: #fff;
  transition: border-color 0.2s;
}

.select-box:hover {
  border: 2px solid black;
}

.select-box__current {
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 15px;
}

.select-box__icon {
  width: 20px;
  transition: transform 0.2s;
}

.select-box__list {
  position: absolute;
  top: 100%;
  left: 0;
  width: 100%;
  border: 1px solid #dcdce4;
  border-top: none;
  border-radius: 0 0 5px 5px;
  background-color: #fff;
  box-shadow: 0 15px 30px -10px rgba(0, 0, 0, 0.1);
  list-style: none;
  padding: 0;
  margin: 0;
  z-index: 10;
}

.select-box__list li {
  padding: 15px;
  cursor: pointer;
}

.select-box__list li:hover {
  background-color: #f0f0f0;
}

.select-box.open .select-box__icon {
  transform: rotate(180deg);
}

.overlay {
  position: fixed;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background: rgba(0, 0, 0, 0.5);
  display: none;
  z-index: 5;
}

.open .overlay {
  display: block;
}
</style>
