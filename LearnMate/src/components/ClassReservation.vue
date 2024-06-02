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
                            <div class="select-box" :class="{ open: isOpenDate }">
                                <div class="select-box__current" @click="toggleDateMenu">
                                    <span>Fecha: {{ formattedDate }}</span>
                                    <img class="select-box__icon" src="http://cdn.onlinewebfonts.com/svg/img_295694.svg" alt="Arrow Icon">
                                </div>
                                <div class="select-box__list" id="select-datepicker" v-if="isOpenDate">
                                    <div class="date-picker">
                                        <VDatePicker v-model="date" @update:modelValue="updateFormattedDate" />
                                    </div>
                                </div>
                            </div>
                            
                            <div class="form-group">
                                <div class="select-box" @click="toggleMenu" :class="{ open: isOpenSelect }">
                                    <div class="select-box__current">
                                        <span>Tramo Horario: {{ getOptionLabel(selectedOption) }}</span>
                                        <img class="select-box__icon" src="http://cdn.onlinewebfonts.com/svg/img_295694.svg" alt="Arrow Icon">
                                    </div>
                                    <ul class="select-box__list" v-if="isOpenSelect">
                                        <li v-for="(option, index) in filteredOptions" :key="index" @click.stop="selectOption(option.id)">
                                            {{ option.name }}
                                        </li>
                                    </ul>
                                </div>
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
import useProfClassInfo	 from '../composables/useProfClassInfo';
import { onMounted, ref, computed } from 'vue';
import { DatePicker as VDatePicker } from 'v-calendar';
import 'v-calendar/style.css';

const { createNewClass } = useProfClassInfo();
const props = defineProps({
    idProf: Number,
    idMateria: Number,
    idTramos: Array<Number>,
    closeModal: Function
});

const emit = defineEmits(['close-modal'])
interface Option {
    id: number;
    name: string;
}

const date = ref<Date | null>(null);
const formattedDate = ref<string>('');
const selectedOption = ref<number>(1);
const isOpenSelect = ref<boolean>(false);
const isOpenDate = ref<boolean>(false);

const options: Option[] = [
    { id: 1, name: '9-12' },
    { id: 2, name: '12-15' },
    { id: 3, name: '15-18' },
    { id: 4, name: '18-21' },
    { id: 5, name: '21-24' },
];

// Filtrar opciones basadas en idTramos
const filteredOptions = computed(() => {
    return options.filter(option => props.idTramos?.includes(option.id));
});

const toggleMenu = () => {
    isOpenSelect.value = !isOpenSelect.value;
    closeOtherMenus('select');
};

const toggleDateMenu = () => {
    isOpenDate.value = !isOpenDate.value;
    closeOtherMenus('date');
};

const closeOtherMenus = (current: string) => {
    if (current !== 'select') isOpenSelect.value = false;
    if (current !== 'date') isOpenDate.value = false;
};

const selectOption = (optionId: number) => {
    selectedOption.value = optionId;
    isOpenSelect.value = false;
};

const getOptionLabel = (id: number) => {
    return options.find(option => option.id === id)?.name || '';
};

const updateFormattedDate = (newDate: Date) => {
    if (newDate) {
        formattedDate.value = newDate.toISOString().split('T')[0];
    } else {
        formattedDate.value = '';
    }
};

const submitForm = () => {
    createNewClass(formattedDate.value, props.idProf, selectedOption.value, props.idMateria);
    emit('close-modal');
};


onMounted(() => {
    if (date.value) {
        updateFormattedDate(date.value);
    }
});
</script>

<style lang="scss" scoped>
.modal-overlay {
    position: fixed;
    top: 0;
    left: 0;
    width: 100%;
    height: 100%;
    background-color: rgba(110, 114, 121, 0.6);
    display: flex;
    justify-content: center;
    align-items: center;
}

.modal {
    background-color: white;
    border-radius: 6px;
    border: none;
    padding: 1rem;
    width: 30%;
    max-width: 80rem;
    animation: fade-slide-up 0.3s ease-out forwards;
}

.modal::backdrop {
    background-color: rgba(0, 0, 0, 0.55);
}

@keyframes fade-slide-up {
    from {
        opacity: 0;
        transform: translateY(4rem);
    }
    to {
        opacity: 1;
        transform: translateY(0);
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

button {
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

// ESTILOS FAKE INPUT
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
    margin-bottom: 30px;
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

#select-datepicker {
    display: flex;
    align-items: center;
    flex-direction: column;
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
